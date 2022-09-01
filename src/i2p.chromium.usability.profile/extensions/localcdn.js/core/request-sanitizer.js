/**
 * Request Sanitizer
 * Belongs to Decentraleyes.
 *
 * @author      Thomas Rientjes
 * @since       2018-01-10
 * @license     MPL 2.0
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */

'use strict';


/**
 * Request Sanitizer
 */

var requestSanitizer = {};


/**
 * Preparation
 * https://codeberg.org/nobody/LocalCDN/issues/442
 */

/* eslint-disable indent */
const ExtraInfoSpec = BrowserType.FIREFOX
                      ? [WebRequest.BLOCKING, WebRequest.HEADERS]
                      : [WebRequest.BLOCKING, WebRequest.HEADERS, WebRequest.EXTRA_HEADERS];
/* eslint-enable indent */


/**
 * Public Methods
 */

requestSanitizer.enable = function () {
    let onBeforeSendHeaders = chrome.webRequest.onBeforeSendHeaders;

    onBeforeSendHeaders.addListener(requestSanitizer._stripMetadata, {
        'urls': stateManager.validHosts
    }, ExtraInfoSpec);
};

requestSanitizer.disable = function () {
    let onBeforeSendHeaders = chrome.webRequest.onBeforeSendHeaders;

    onBeforeSendHeaders.removeListener(requestSanitizer._stripMetadata, {
        'urls': stateManager.validHosts
    }, ExtraInfoSpec);
};


/**
 * Private Methods
 */

requestSanitizer._stripMetadata = function (requestDetails) {
    let sensitiveHeaders, initiatorDomain, allowlistedDomains;

    sensitiveHeaders = [Header.COOKIE, Header.ORIGIN, Header.REFERER];
    initiatorDomain = helpers.extractDomainFromUrl(requestDetails.initiator, true);
    allowlistedDomains = helpers.checkAllowlisted(initiatorDomain, requestAnalyzer.allowlistedDomains);

    if (allowlistedDomains) {
        return {
            [WebRequest.HEADERS]: requestDetails.requestHeaders,
        };
    }

    for (let i = 0; i < requestDetails.requestHeaders.length; ++i) {
        if (sensitiveHeaders.indexOf(requestDetails.requestHeaders[i].name) > -1) {
            requestDetails.requestHeaders.splice(i--, 1);
        }
    }

    return {
        [WebRequest.HEADERS]: requestDetails.requestHeaders
    };
};


/**
 * Initializations
 */

storageManager.type.get({[Setting.STRIP_METADATA]: true}, function (items) {
    if (items === null || items.stripMetadata !== false) {
        requestSanitizer.enable();
    }
});
