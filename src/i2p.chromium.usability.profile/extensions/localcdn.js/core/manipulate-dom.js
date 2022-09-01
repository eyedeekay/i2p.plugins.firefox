/**
 * Remove integrity checks from embedded CSS and JavaScript files
 * Belongs to LocalCDN.
 *
 * @author      nobody
 * @since       2020-02-27
 *
 * @license     MPL 2.0
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */

'use strict';


/**
 * Manipulate DOM
 */

var manipulateDOM = {};


/**
 * Private Methods
 */

manipulateDOM._removeCrossOriginAndIntegrityAttr = function (details) {

    if (!BrowserType.FIREFOX) {
        // Chromium (and other) browsers do not support webRequest.filterResponseData
        // https://bugs.chromium.org/p/chromium/issues/detail?id=487422
        console.warn('[ LocalCDN ] browser.webRequest.filterResponseData not supported by your browser.');
        log.append(details.url, '-', 'browser.webRequest.filterResponseData not supported by your browser', true);
        return;
    }
    if (details.statusCode === 200) {
        let initiatorDomain, header;

        initiatorDomain = helpers.extractDomainFromUrl(details.url, true) || Address.EXAMPLE;

        // by Jaap (https://gitlab.com/Jaaap)
        header = details.responseHeaders.find((h) => h.name.toLowerCase() === 'content-type');

        if (header && manipulateDOM.checkHtmlFilterEnabled(initiatorDomain)) {

            let mimeType, isAllowlisted;

            mimeType = header.value.replace(/;.*/, '').toLowerCase();
            isAllowlisted = stateManager._domainIsListed(initiatorDomain);

            if (!isAllowlisted && mimeType === 'text/html') {

                let asciiDecoder, decoder, encoder, charset, isFirstData, filter, data;

                charset = (/charset\s*=/).test(header.value) && header.value.replace(/^.*?charset\s*=\s*/, '').replace(/["']?/g, '');

                // Check if charset is supported by TextDecoder()
                if ((/charset\s*=/).test(header.value) && !EncodingTypes[charset.toString().toLowerCase()]) {
                    console.error(`[ LocalCDN ] Unsupported charset: ${charset}`);
                    log.append(details.url, '-', `Unsupported charset: ${charset}`, true);
                    return;
                }

                asciiDecoder = new TextDecoder('ASCII');
                encoder = new TextEncoder();
                isFirstData = true;
                filter = browser.webRequest.filterResponseData(details.requestId);
                data = [];

                header.value = 'text/html; charset=UTF-8';

                // NOTE: should work if 'script' string is divided into two chunks
                filter.ondata = (evt) => {
                    if (isFirstData) {
                        if (!charset) {
                            // content-type has no charset declared
                            let htmlHead, charsetMatch;

                            htmlHead = asciiDecoder.decode(evt.data, {'stream': false});
                            // eslint-disable-next-line no-useless-escape
                            charsetMatch = htmlHead.match(/<meta\s+charset=["']?([^>"'\/]+)["'>\/]/i);
                            if (charsetMatch === null) {
                                // eslint-disable-next-line no-useless-escape
                                charsetMatch = htmlHead.match(/<meta.*charset=["']?([^>"'\/]+)["'].*[>\/]/i) || 'utf8';
                            }

                            if (EncodingTypes[charsetMatch[1].toLowerCase()] !== undefined) {
                                charset = charsetMatch[1];
                            } else {
                                // If charset is unclear, then use ASCII by default.
                                // Other charsets are mostly tagged in the header or HTML source code.
                                // https://codeberg.org/nobody/LocalCDN/issues/567
                                charset = 'ASCII';
                            }
                        }
                        decoder = new TextDecoder(charset);
                    }
                    isFirstData = false;

                    data.push(evt.data);
                };

                filter.onstop = () => {
                    let str = '';
                    for (let buffer of data) {
                        str += decoder.decode(buffer, {'stream': true});
                    }
                    str += decoder.decode(); // end-of-stream

                    // set UTF-8 in document
                    str = manipulateDOM._searchCharset(str, charset);

                    // remove crossorigin and integrity attributes
                    str = str.replace(/<(link|script)[^>]+>/ig, (m) => {
                        // eslint-disable-next-line no-use-before-define
                        if (cdnDomainsRE.test(m)) {
                            return m.replace(/\s+(integrity|crossorigin)(="[^"]*"|='[^']*'|=[^"'`=>\s]+=?|)/ig, '');
                        }
                        return m;
                    });
                    filter.write(encoder.encode(str));
                    filter.close();
                };
            }
            return {'responseHeaders': details.responseHeaders};

        }
    }
};

manipulateDOM._searchCharset = function (str, charset) {
    if (str.indexOf(`charset="${charset}"`) > 0) {
        return str.replace(`charset="${charset}"`, 'charset="utf8"');
    }
    if (str.indexOf(`charset='${charset}'`) > 0) {
        return str.replace(`charset='${charset}'`, 'charset=\'utf8\'');
    }
    if (str.indexOf(`charset=${charset}`) > 0) {
        return str.replace(`charset=${charset}`, 'charset=utf8');
    }
    return str;
};


/**
 * Public Methods
 */

manipulateDOM.checkHtmlFilterEnabled = function (url) {
    let listedToManipulateDOM, negateHtmlFilter;
    listedToManipulateDOM = stateManager._domainIsListed(url, 'manipulate-dom');
    negateHtmlFilter = stateManager.getInvertOption;

    if ((negateHtmlFilter || listedToManipulateDOM) && !(negateHtmlFilter && listedToManipulateDOM)) {
        return true;
    }
    return false;
};

/**
 * Initializations
 */

/* eslint-disable one-var */
let cdnDomainsRE = new RegExp(`//(${Object.keys(mappings.cdn).map((m) => m.replace(/\W/g, '\\$&')).join('|')})/`);
/* eslint-enable one-var */


/**
 * Event Handlers
 */

chrome.webRequest.onHeadersReceived.addListener(
    manipulateDOM._removeCrossOriginAndIntegrityAttr,
    {'types': [WebRequestType.MAIN_FRAME, WebRequestType.SUB_FRAME], 'urls': [Address.ANY]},
    [WebRequest.BLOCKING, WebRequest.RESPONSE_HEADERS]
);
