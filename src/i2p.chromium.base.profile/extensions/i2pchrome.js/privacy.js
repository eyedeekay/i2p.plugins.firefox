var titlepref = chrome.i18n.getMessage("titlePreface");

/* This disables protected content, which is a form of digital restrictions
   management dependent on identifying information */
function disableDigitalRestrictionsManagement(platformInfo) {
  if (platformInfo.PlatformOs == "android") {
    chrome.privacy.websites.protectedContentEnabled.set({ value: false });
  } else if (platformInfo.PlatformOs == "windows") {
    chrome.privacy.websites.protectedContentEnabled.set({ value: false });
  }
}

function getBrowser() {
  if (typeof chrome !== "undefined") {
    if (typeof browser !== "undefined") {
      return "Firefox";
    } else {
      return "Chrome";
    }
  } else {
    return "Chrome";
  } /* else {
    return "Edge";
  }*/
}

function setAllPrivacy() {
  chrome.privacy.network.networkPredictionEnabled.set({ value: false });
  if (getBrowser() == "Chrome") {
    chrome.privacy.services.alternateErrorPagesEnabled.set({ value: false });
    chrome.privacy.services.autofillEnabled.set({ value: false });
    chrome.privacy.services.passwordSavingEnabled.set({ value: false });
    chrome.privacy.services.safeBrowsingEnabled.set({ value: false });
    chrome.privacy.services.safeBrowsingExtendedReportingEnabled.set({
      value: false,
    });
    chrome.privacy.services.searchSuggestEnabled.set({ value: false });
    chrome.privacy.services.spellingServiceEnabled.set({ value: false });
    chrome.privacy.services.translationServiceEnabled.set({ value: false });
    chrome.privacy.websites.thirdPartyCookiesAllowed.set({ value: false });
    chrome.privacy.websites.doNotTrackEnabled.set({ value: true });
    chrome.privacy.websites.hyperlinkAuditingEnabled.set({ value: false });
    chrome.privacy.websites.referrersEnabled.set({ value: false });
    //chrome.privacy.services.hotwordSearchEnabled.set({ value: false });
  } else {
    browser.privacy.websites.hyperlinkAuditingEnabled.set({ value: false });
    browser.privacy.websites.firstPartyIsolate.set({ value: true });
    browser.privacy.websites.resistFingerprinting.set({ value: true });
    //    browser.privacy.websites.thirdPartyCookiesAllowed.set({ value: false });
    browser.privacy.websites.trackingProtectionMode.set({ value: true });
    browser.privacy.websites.cookieConfig.set({
      value: {
        behavior: "reject_third_party",
        nonPersistentCookies: true,
      },
    });
    browser.privacy.network.networkPredictionEnabled.set({ value: false });
  }
  return chrome.runtime.getPlatformInfo(disableDigitalRestrictionsManagement);
}

setAllPrivacy();

function ResetPeerConnection() {
  AssurePeerConnection();
}

function EnablePeerConnection() {
  AssurePeerConnection();
  console.log("Enabled WebRTC");
}

function AssurePeerConnection() {
  chrome.privacy.network.webRTCIPHandlingPolicy.set({
    value: "disable_non_proxied_udp",
  });
}

chrome.tabs.onCreated.addListener(AssurePeerConnection);

var defaultSettings = {
  since: "forever",
  dataTypes: ["downloads", "passwords", "formData", "localStorage", "history"],
};

function onError(therror) {
  console.error(therror);
}

function forgetBrowsingData(storedSettings) {
  function getSince(selectedSince) {
    if (selectedSince === "forever") {
      return 0;
    }

    const times = {
      hour: () => 1000 * 60 * 60,
      day: () => 1000 * 60 * 60 * 24,
      week: () => 1000 * 60 * 60 * 24 * 7,
    };

    const sinceMilliseconds = times[selectedSince].call();
    return Date.now() - sinceMilliseconds;
  }

  function getTypes(selectedTypes) {
    let dataTypes = {};
    for (let item of selectedTypes) {
      dataTypes[item] = true;
    }
    return dataTypes;
  }

  const since = getSince(defaultSettings.since);
  const dataTypes = getTypes(defaultSettings.dataTypes);

  function notify() {
    let dataTypesString = Object.keys(dataTypes).join(", ");
    let sinceString = new Date(since).toLocaleString();
    chrome.notifications.create({
      type: "basic",
      title: "Removed browsing data",
      message: `Removed ${dataTypesString}\n for I2P Browsing`,
    });
  }

  function deepCleanHistory(historyItems) {
    console.log("Deep cleaning history");
    for (let item of historyItems) {
      if (i2pHost(item.url)) {
        chrome.history.deleteUrl({
          url: item.url,
        });
        chrome.browsingData.removeCache({});
        console.log("cleared Cache");
        chrome.browsingData
          .removePasswords({
            hostnames: [i2pHostName(item.url)],
            since,
          })
          .then(onContextGotLog);
        console.log("cleared Passwords");
        chrome.browsingData
          .removeDownloads({
            hostnames: [i2pHostName(item.url)],
            since,
          })
          .then(onContextGotLog);
        console.log("cleared Downloads");
        chrome.browsingData
          .removeFormData({
            hostnames: [i2pHostName(item.url)],
            since,
          })
          .then(onContextGotLog);
        console.log("cleared Form Data");
        chrome.browsingData
          .removeLocalStorage({
            hostnames: [i2pHostName(item.url)],
            since,
          })
          .then(onContextGotLog);
        console.log("cleared Local Storage");

        let contexts = chrome.contextualIdentities.query({
          name: titlepref,
        });

        function deepCleanCookies(cookies) {
          for (let cookie of cookies) {
            var removing = chrome.cookies.remove({
              firstPartyDomain: cookie.firstPartyDomain,
              name: cookie.name,
              url: item.url,
            });
            removing.then(onContextGotLog, onError);
          }
          console.log("Cleared cookies");
        }

        function deepCleanContext(cookieStoreIds) {
          for (let cookieStoreId of cookieStoreIds) {
            var removing = chrome.cookies.getAll({
              firstPartyDomain: null,
              storeId: cookieStoreId.cookieStoreId,
            });
            removing.then(deepCleanCookies, onError);
          }
        }

        contexts.then(deepCleanContext, onError);
      }
    }
    notify();
  }

  var searching = chrome.history.search({
    text: "i2p",
    startTime: 0,
  });

  searching.then(deepCleanHistory);

  setAllPrivacy();
  ResetPeerConnection();
}

function i2pHostName(url) {
  let hostname = "";
  if (url.indexOf("://") > -1) {
    hostname = url.split("/")[2];
  } else {
    hostname = url.split("/")[0];
  }
  return hostname;
}

function i2pHost(url) {
  let hostname = i2pHostName(url);
  return hostname.endsWith(".i2p");
}

function onContextGotLog(contexts) {
  if (contexts !== null) {
    for (let context of contexts) {
      console.log(context);
    }
  }
}

chrome.runtime.onMessage.addListener(message);

function enableHistory() {
  function checkStoredSettings(storedSettings) {
    storedSettings["disable_history"] = false;
    console.log(storedSettings);
    function enablehistory(settings) {
      console.log("Store History:", settings);
    }
    let setting = chrome.storage.local.set(storedSettings);
    setting.then(enablehistory);
  }
  const gettingStoredSettings = chrome.storage.local.get();
  gettingStoredSettings.then(checkStoredSettings, onError);
}

function disableHistory() {
  function checkStoredSettings(storedSettings) {
    storedSettings["disable_history"] = true;
    console.log(storedSettings);
    function enablehistory(settings) {
      console.log("Store History:", settings);
    }
    var setting = chrome.storage.local.set(storedSettings);
    setting.then(enablehistory);
  }
  const gettingStoredSettings = chrome.storage.local.get();
  gettingStoredSettings.then(checkStoredSettings, onError);
}

function message(recieved) {
  console.log(recieved);
  if (recieved.rtc === "enableWebRTC") {
    console.log("enableWebRTC");
    EnablePeerConnection();
  } else if (recieved.rtc === "disableWebRTC") {
    console.log("disableWebRTC");
    ResetPeerConnection();
  }
  if (recieved.history === "enableHistory") {
    console.log("enableHistory");
    enableHistory();
  } else if (recieved.history === "disableHistory") {
    console.log("disableHistory");
    disableHistory();
  }
}
