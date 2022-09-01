/**
 * Storage Manager
 * Belongs to LocalCDN
 *
 * @author      nobody
 * @since       2020-08-28
 *
 * @license     MPL 2.0
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */

'use strict';


/**
 * Constants
 */

const InvalidFile = 'Invalid file!';


/**
 * Storage Manager
 */

var storageManager = {};


/**
 * Public Methods
 */

storageManager.checkStorageType = function () {
    chrome.storage.local.get([Setting.STORAGE_TYPE], function (items) {
        if (items.storageType === 'sync') {
            storageManager.type = chrome.storage.sync;
        } else {
            storageManager.type = chrome.storage.local;
        }
    });
};

storageManager.migrateData = function (target) {
    let storageSource, storageDestination;

    if (target === 'local') {
        storageSource = chrome.storage.sync;
        storageDestination = chrome.storage.local;
    } else if (target === 'sync') {
        storageSource = chrome.storage.local;
        storageDestination = chrome.storage.sync;
    } else {
        return;
    }

    storageSource.get(null, function (data) {
        chrome.storage.local.set({
            [Setting.AMOUNT_INJECTED]: data.amountInjected,
            [Setting.INTERNAL_STATISTICS]: data.internalStatistics,
            [Setting.INTERNAL_STATISTICS_DATA]: data.internalStatisticsData,
            [Setting.STORAGE_TYPE]: target
        });
        storageDestination.set({
            [Setting.ALLOWED_DOMAINS_GOOGLE_FONTS]: data.allowedDomainsGoogleFonts,
            [Setting.BLOCK_GOOGLE_FONTS]: data.blockGoogleFonts,
            [Setting.BLOCK_MISSING]: data.blockMissing,
            [Setting.DISABLE_PREFETCH]: data.disablePrefetch,
            [Setting.DOMAINS_MANIPULATE_DOM]: data.domainsManipulateDOM,
            [Setting.LOGGING]: data.logging,
            [Setting.ENFORCE_STAGING]: data.enforceStaging,
            [Setting.UPDATE_NOTIFICATION]: data.updateNotification,
            [Setting.LAST_MAPPING_UPDATE]: data.lastMappingUpdate,
            [Setting.NEGATE_HTML_FILTER_LIST]: data.negateHtmlFilterList,
            [Setting.SELECTED_ICON]: data.selectedIcon,
            [Setting.SHOW_ICON_BADGE]: data.showIconBadge,
            [Setting.STRIP_METADATA]: data.stripMetadata,
            [Setting.ALLOWLISTED_DOMAINS]: data.allowlistedDomains,
            [Setting.XHR_TEST_DOMAIN]: data.xhrTestDomain,
            [Setting.BADGE_DEFAULT_BACKGROUND_COLOR]: data.badgeDefaultBackgroundColor,
            [Setting.BADGE_DEFAULT_TEXT_COLOR]: data.badgeDefaultTextColor,
            [Setting.BADGE_HTML_FILTER_BACKGROUND_COLOR]: data.badgeHTMLFilterBackgroundColor,
            [Setting.BADGE_HTML_FILTER_TEXT_COLOR]: data.badgeHTMLfilterTextColor
        });
    });
};

storageManager.export = function () {
    let filename = new Date().toISOString();
    filename = `${filename.substring(0, 10)}_localcdn_backup.txt`;

    storageManager.type.get(null, function (items) {
        delete items['whitelistedDomains'];
        let element = document.createElement('a');
        element.setAttribute('href', `data:text/plain;charset=utf-8,${encodeURIComponent(JSON.stringify(items, null, '  '))}`);
        element.setAttribute('download', filename);
        element.style.display = 'none';
        document.body.appendChild(element);
        element.click();
        document.body.removeChild(element);
    });
};

storageManager.startImportFilePicker = function () {
    const input = document.getElementById('import-file-picker');
    input.value = '';
    input.click();
};

storageManager.handleImportFilePicker = function () {
    return new Promise((resolve) => {
        try {
            let file = document.getElementById('import-file-picker').files[0];
            storageManager._readFile(file)
                .then(JSON.parse)
                .then(storageManager._validation);
            resolve();

        } catch (err) {
            console.error(`[ LocalCDN ] ${err}`);
            alert(err);
        }
    });
};


/**
 * Private Methods
 */

storageManager._handleStorageChanged = function (type) {
    if (Setting.STORAGE_TYPE in type) {
        if (type.storageType.newValue === 'sync') {
            storageManager.type = chrome.storage.sync;
        } else {
            storageManager.type = chrome.storage.local;
        }
    }
};

storageManager._readFile = function (file) {
    return new Promise((resolve, reject) => {
        let reader = new FileReader();
        reader.onload = () => {
            resolve(reader.result);
        };
        reader.onerror = reject;
        reader.readAsText(file);
    });
};

storageManager._validation = function (content) {
    let imported = {};

    // Delete old keys
    if (content.whitelistedDomains !== undefined) {
        content.allowlistedDomains = content.whitelistedDomains;
        delete content['whitelistedDomains'];
    }

    // Convert value of notifications
    if (content.hideReleaseNotes !== undefined) {
        content.updateNotification = content.hideReleaseNotes ? 0 : 2;
        delete content['hideReleaseNotes'];
    }

    // Convert type of updateNotification
    if (typeof content.updateNotification === 'string') {
        content.updateNotification = parseInt(content.updateNotification);
    }

    // Migrate badge colors
    if (content.badgeDefaultBackgroundColor === undefined) {
        content.badgeDefaultBackgroundColor = content.badgeColor;
    }
    if (content.badgeDefaultTextColor === undefined) {
        content.badgeDefaultTextColor = content.badgeTextColor;
    }

    for (const [key, value] of Object.entries(SettingDefaults)) {
        // If type the same as default settings
        if (typeof value === typeof content[key]) {
            if (typeof value === 'object') {
                imported[key] = storageManager._validateDomainsAndStatistics(key, content[key]);
            } else if (typeof value === 'string') {
                imported[key] = storageManager._validateStrings(content[key]);
            } else if (typeof value === 'number') {
                imported[key] = storageManager._validateNumbers(content[key]);
            } else if (typeof value === 'boolean') {
                imported[key] = content[key];
            }
        } else if (content[key] === undefined) {
            // Set default if not existing in file
            imported[key] = value;
        } else {
            alert(`${chrome.i18n.getMessage('dialogImportFailed')}\n\n${key}: ${content[key]}`);
            throw InvalidFile;
        }
    }

    storageManager.type.set(imported);

    alert(chrome.i18n.getMessage('dialogImportSuccessful'));
    chrome.runtime.reload();
};

storageManager._validateDomainsAndStatistics = function (type, obj) {
    let valid = {};

    if (type === 'allowedDomainsGoogleFonts' || type === 'domainsManipulateDOM' || type === 'allowlistedDomains') {
        for (const [key, value] of Object.entries(obj)) {
            // eslint-disable-next-line no-useless-escape
            if (((/[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,24}/).test(key) || key === '') && value === true) {
                valid[key] = value;
            } else {
                alert(`${chrome.i18n.getMessage('dialogImportFailed')}: ${key}`);
                throw InvalidFile;
            }
        }
    } else if (type === 'internalStatisticsData') {
        for (const [date, values] of Object.entries(obj)) {
            if ((/((2\d)\d{2})-(0[1-9]|1[012])-(0[1-9]|[12]\d|3[01])/).test(date)) {
                for (const [types, category] of Object.entries(values)) {
                    if (types === 'frameworks') {
                        for (const [name, counter] of Object.entries(category)) {
                            // eslint-disable-next-line max-len
                            if (!(/resources\/[0-9a-z.-]+\/((?:\d{1,2}\.){1,3}\d{1,2})?.*\.(css|jsm)/).test(name) && !storageManager._validateNumbers(counter)) {
                                alert(`${chrome.i18n.getMessage('dialogImportFailed')}: ${name}`);
                                throw InvalidFile;
                            }
                        }
                    } else if (types === 'cdns') {
                        for (const [name, counter] of Object.entries(category)) {
                            // eslint-disable-next-line no-useless-escape, max-len
                            if (!(/[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,24}/).test(name) && !storageManager._validateNumbers(counter)) {
                                alert(`${chrome.i18n.getMessage('dialogImportFailed')}: ${name}`);
                                throw InvalidFile;
                            }
                        }
                    } else {
                        alert(`${chrome.i18n.getMessage('dialogImportFailed')}: ${type}`);
                        throw InvalidFile;
                    }
                }
            } else {
                alert(`${chrome.i18n.getMessage('dialogImportFailed')}: ${date}`);
                throw InvalidFile;
            }
        }
        valid = obj;
    } else {
        alert(`${chrome.i18n.getMessage('dialogImportFailed')}: ${type}`);
        throw InvalidFile;
    }
    return valid;
};

storageManager._validateStrings = function (value) {
    if ((/((2\d)\d{2})-(0[1-9]|1[012])-(0[1-9]|[12]\d|3[01])/).test(value)) {
        return value;
    } else if ((/#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})/).test(value)) {
        return value;
    } else if (value === 'Default' || value === 'Light' || value === 'Grey') {
        return value;
    } else if (value === 'local' || value === 'sync') {
        return value;
    } else if (value === 'decentraleyes.org' || value === 'localcdn.org') {
        return 'localcdn.org';
    } else {
        return '';
    }
};

storageManager._validateNumbers = function (value) {
    return isNaN(value) ? 0 : value;
};


/**
 * Initializations
 */

storageManager.data = {};
storageManager.type = chrome.storage.local;
storageManager.amountInjected = 0;
storageManager.statistics = {};

chrome.storage.onChanged.addListener(storageManager._handleStorageChanged);
