/**
 * Internal Helper Module
 * Belongs to LocalCDN (since 2020-02-26)
 * (Origin: Decentraleyes)
 *
 * @author      Thomas Rientjes
 * @since       2017-10-26
 *
 * @author      nobody
 * @since       2020-02-26
 *
 * @license     MPL 2.0
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */

'use strict';


/**
 * Helpers
 */

var helpers = {};


/**
 * Public Methods
 */

helpers.insertI18nContentIntoDocument = function (document) {
    let scriptDirection, i18nElements, translationComplete;

    translationComplete = true;
    scriptDirection = helpers.determineScriptDirection(navigator.language);
    i18nElements = document.querySelectorAll('[data-i18n-content]');

    i18nElements.forEach(function (i18nElement) {
        let i18nMessageName = i18nElement.getAttribute('data-i18n-content');

        if (chrome.i18n.getMessage(i18nMessageName) !== '') {
            if (i18nElement.type === 'button') {
                i18nElement.value = chrome.i18n.getMessage(i18nMessageName);
            } else {
                i18nElement.textContent = chrome.i18n.getMessage(i18nMessageName);
            }
            i18nElement.setAttribute('dir', scriptDirection);
        } else {
            translationComplete = false;
        }
    });

    return translationComplete;
};

helpers.insertI18nTitlesIntoDocument = function (document) {
    let scriptDirection, i18nElements;

    scriptDirection = helpers.determineScriptDirection(navigator.language);
    i18nElements = document.querySelectorAll('[data-i18n-title]');

    i18nElements.forEach(function (i18nElement) {
        let i18nMessageName = i18nElement.getAttribute('data-i18n-title');

        i18nElement.setAttribute('title', chrome.i18n.getMessage(i18nMessageName));
        i18nElement.setAttribute('dir', scriptDirection);
    });
};

helpers.languageIsFullySupported = function (language) {
    let languageSupported, supportedLanguages;

    languageSupported = false;

    supportedLanguages = [
        'ar',
        'bg',
        'ca',
        'cs',
        'da',
        'de',
        'el',
        'en',
        'en_CA',
        'en_US',
        'eo',
        'es',
        'et',
        'fa',
        'fi',
        'fr',
        'he',
        'hr',
        'hu',
        'id',
        'is',
        'it',
        'ja',
        'kn',
        'ko',
        'lb',
        'lt',
        'ml',
        'nb_NO',
        'nl',
        'pl',
        'pt',
        'pt_BR',
        'pt_PT',
        'ro',
        'ru',
        'sco',
        'si',
        'sk',
        'sl',
        'sr',
        'sv',
        'te',
        'tl',
        'tr',
        'uk',
        'vi',
        'zh_Hans',
        'zh_Hant'
    ];

    for (let supportedLanguage of supportedLanguages) {
        if (language.search(supportedLanguage) !== -1) {
            languageSupported = true;
        }
    }

    return languageSupported;
};

helpers.normalizeDomain = function (domain) {
    return domain.toLowerCase().trim();
};

helpers.extractDomainFromUrl = function (url, normalize) {
    if ((/^(?!(http[s]?|file):\/\/).*/).test(url)) {
        return null;
    }

    let extractedDomain;

    try {
        extractedDomain = new URL(url).host;
    } catch (exception) {
        extractedDomain = null;
    }

    if (url.startsWith(Address.CHROME)) {
        extractedDomain = null;
    }

    if (extractedDomain === '') {
        extractedDomain = null;
    }

    if (extractedDomain !== null && normalize === true) {
        extractedDomain = helpers.normalizeDomain(extractedDomain);
    }

    return extractedDomain;
};

helpers.getWildcard = function (initiatorDomain) {
    let domain = initiatorDomain.split('.');

    if (domain.length > 2) {
        domain[0] = '*';
        domain = domain.join().replace(/,/g, '.');
        return domain;
    }

};

helpers.checkAllowlisted = function (domain, list) {
    let domainWithoutPrefix, wildcard;

    if (domain === null) {
        return false;
    }

    if (domain.startsWith(Address.WWW_PREFIX)) {
        domainWithoutPrefix = domain.slice(Address.WWW_PREFIX.length);
    }
    wildcard = helpers.getWildcard(domain);

    return Boolean(list[domain] || list[domainWithoutPrefix] || list[wildcard] || list[domainWithoutPrefix]);
};

helpers.extractFilenameFromPath = function (path) {
    let pathSegments, filename;

    pathSegments = path.split('/');
    filename = pathSegments[pathSegments.length - 1];

    return filename === '' ? pathSegments[1] : filename;
};

helpers.generateRandomHexString = function (length) {
    let randomValues, randomHexString;

    randomValues = crypto.getRandomValues(new Uint8Array(length));
    randomHexString = '';

    for (let value of randomValues) {
        // eslint-disable-next-line no-bitwise
        let hexValue = (0 ^ (value & (15 >> (0 / 4)))).toString(16);
        randomHexString += hexValue;
    }

    return randomHexString;
};

helpers.determineCdnName = function (domainName) {
    return CDNs[domainName] || 'Unknown';
};

helpers.determineScriptDirection = function (language) {
    let rightToLeftLanguages, scriptDirection;

    rightToLeftLanguages = ['ar', 'he'];

    if (rightToLeftLanguages.indexOf(language) === -1) {
        scriptDirection = 'ltr';
    } else {
        scriptDirection = 'rtl';
    }

    return scriptDirection;
};

helpers.determineActiveTab = function () {
    return new Promise((resolve) => {
        let opt = {
            'active': true,
            'currentWindow': true
        };

        chrome.tabs.query(opt, function (tabs) {
            if (tabs[0]) {
                resolve(tabs[0]);
            } else {
                opt = {'active': true};

                chrome.tabs.query(opt, function () {
                    resolve(tabs[0]);
                });
            }
        });
    });
};


helpers.formatNumber = function (number) {
    if (typeof number === 'number') {
        return number.toLocaleString();
    }
};

helpers.formatFilename = function (targetPath) {
    if (targetPath.startsWith('resources/element-ui/')) {
        targetPath = targetPath.toLowerCase();
        if (!targetPath.endsWith('.min.jsm')) {
            targetPath = targetPath.replace('.jsm', '.min.jsm');
        }
    }
    return targetPath;
};

helpers.compareVersion = function (v1, v2) {
    /**
     *  compareVersion( '1.5.7' , '1.5.8' ) is FALSE
     *  compareVersion( '1.5.8' , '1.5.7' ) is TRUE
     *  compareVersion( '1.5.7' , '1.5.7' ) is TRUE
     */
    v1 = v1.split('.');
    v2 = v2.split('.');
    const k = Math.min(v1.length, v2.length);
    for (let i = 0; i < k; ++i) {
        v1[i] = parseInt(v1[i], 10);
        v2[i] = parseInt(v2[i], 10);
        if (v1[i] > v2[i]) {
            return true;
        }
        if (v1[i] < v2[i]) {
            return false;
        }
    }
    return v1.length === v2.length ? true : v1.length < v2.length ? false : true;
};
