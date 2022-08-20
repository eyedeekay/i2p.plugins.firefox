function platformCallback(platformInfo) {
    if (platformInfo.PlatformOs == "android") {
        console.log("android detected");
        return true;
    } else {
        console.log("desktop detected");
        return false;
    }
}

function isDroid() {
    return chrome.runtime.getPlatformInfo(platformCallback);
}

//chrome.privacy.network.peerConnectionEnabled.set({value: false});

chrome.privacy.network.networkPredictionEnabled.set({ value: false });
chrome.privacy.network.webRTCIPHandlingPolicy.set({
    value: "disable_non_proxied_udp",
});

console.log("Preliminarily disabled WebRTC.");

function shouldProxyRequest(requestInfo) {
    return requestInfo.parentFrameId != -1;
}

function handleProxyRequest(requestInfo) {
    console.log(`Proxying: ${requestInfo.url}`);
    console.log("   ", getScheme(), getHost(), ":", getPort());
    return { type: getScheme(), host: getHost(), port: getPort() };
}

var proxy_scheme = "HTTP";

function getScheme() {
    if (proxy_scheme == undefined) {
        proxy_scheme = "http";
    }
    if (proxy_scheme == "HTTP") {
        proxy_scheme = "http";
    }
    if (proxy_scheme == "SOCKS") {
        proxy_scheme = "socks";
    }
    console.log("Got i2p proxy scheme:", proxy_scheme);
    return proxy_scheme;
}

var proxy_host = "127.0.0.1";

function getHost() {
    if (proxy_host == undefined) {
        proxy_host = "127.0.0.1";
    }
    console.log("Got i2p proxy host:", proxy_host);
    return proxy_host;
}

var proxy_port = "4444";

function getPort() {
    if (proxy_port == undefined) {
        proxy_port = "4444";
    }
    console.log("Got i2p proxy port:", proxy_port);
    return proxy_port;
}

var control_port = "7657";

function getControlPort() {
    if (control_port == undefined) {
        return "7657";
    }
    console.log("Got i2p control port:", control_port);
    return control_port;
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
    }
    /* else {
       return "Edge";
     }*/
}

function setupProxy() {
    var Host = getHost();
    var Port = getPort();
    var Scheme = getScheme();
    var config = {
        mode: "fixed_servers",
        rules: {
            singleProxy: {
                scheme: Scheme,
                host: Host,
                port: parseInt(Port),
            },
        },
    };
    chrome.proxy.settings.set({
            value: config,
            scope: "regular",
        },
        function() {}
    );
}

setupProxy();

function checkStoredSettings(storedSettings) {
    let defaultSettings = {};
    if (!storedSettings.proxy_scheme) {
        defaultSettings["proxy_scheme"] = "http";
    }
    if (!storedSettings.proxy_host) {
        defaultSettings["proxy_host"] = "127.0.0.1";
    }
    if (!storedSettings.proxy_port) {
        defaultSettings["proxy_port"] = 4444;
    }
    if (!storedSettings.control_host) {
        defaultSettings["control_host"] = "127.0.0.1";
    }
    if (!storedSettings.control_port) {
        defaultSettings["control_port"] = 4444;
    }
    chrome.storage.local.set(defaultSettings);
}

function update(restoredSettings) {
    proxy_scheme = restoredSettings.proxy_scheme;
    console.log("restoring proxy scheme:", proxy_scheme);
    proxy_host = restoredSettings.proxy_host;
    console.log("restoring proxy host:", proxy_host);
    proxy_port = restoredSettings.proxy_port;
    console.log("restoring proxy port:", proxy_port);
    control_host = restoredSettings.control_host;
    console.log("restoring control host:", control_host);
    control_port = restoredSettings.control_port;
    console.log("restoring control port:", control_port);
}

chrome.storage.local.get(function(got) {
    checkStoredSettings(got);
    update(got);
    setupProxy();
});

chrome.windows.onCreated.addListener(() => {
    const gettingStoredSettings = chrome.storage.local.get();
    gettingStoredSettings.then(setupProxy, onError);
});