function isDroid() {
  var gettingInfo = browser.runtime.getPlatformInfo();
  gettingInfo.then((got) => {
    if (got.os == "android") {
      return true;
    } else {
      return false;
    }
  });
}

function SetHostText() {
  var hostid = document.getElementById("hostText");
  hostid.textContent = chrome.i18n.getMessage("hostText");
}

function SetPortText() {
  var portid = document.getElementById("portText");
  portid.textContent = chrome.i18n.getMessage("portText");
}

function SetControlHostText() {
  var controlhostid = document.getElementById("controlHostText");
  controlhostid.textContent = chrome.i18n.getMessage("controlHostText");
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
  chrome.proxy.settings.set(
    {
      value: config,
      scope: "regular",
    },
    function () {}
  );
}

function SetControlPortText() {
  var controlportid = document.getElementById("controlPortText");
  controlportid.textContent = chrome.i18n.getMessage("controlPortText");
}

function SetControlHelpText() {
  var portid = document.getElementById("controlHelpText");
  portid.textContent = chrome.i18n.getMessage("controlHelpText");
}

function getScheme() {
  const proxy_scheme = document.querySelector("#proxy_scheme");
  console.log("Got i2p proxy scheme:", proxy_scheme.value);
  if (proxy_scheme == "HTTP") {
    return "http";
  }
  if (proxy_scheme == "SOCKS") {
    return "socks";
  }
  return proxy_scheme.value;
}

function getHost() {
  proxy_host = document.getElementById("host").value;
  console.log("Got i2p proxy host:", proxy_host);
  if (proxy_host == undefined) {
    return "127.0.0.1";
  }
  return proxy_host;
}

function getPort() {
  proxy_port = document.getElementById("port").value;
  console.log("Got i2p proxy port:", proxy_port);
  if (proxy_port == undefined) {
    return "4444";
  }
  return proxy_port;
}

function getControlHost() {
  control_host = document.getElementById("controlhost").value;
  console.log("Got i2p control host:", control_host);
  if (control_host == undefined) {
    return "127.0.0.1";
  }
  return control_host;
}

function getControlPort() {
  control_port = document.getElementById("controlport").value;
  console.log("Got i2p control port:", control_port);
  if (control_port == undefined) {
    return "4444";
  }
  return control_port;
}

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

function onError(e) {
  console.error(e);
}

function storeSettings() {
  let proxy_scheme = getScheme();
  let proxy_host = getHost();
  let proxy_port = getPort();
  let control_host = getControlHost();
  let control_port = getControlPort();
  chrome.storage.local.set({
    proxy_scheme,
    proxy_host,
    proxy_port,
    control_host,
    control_port,
  });
  console.log("storing proxy scheme:", proxy_scheme);
  console.log("storing proxy host:", proxy_host);
  console.log("storing proxy port:", proxy_port);
  console.log("storing control host:", control_host);
  console.log("storing control port:", control_port);
  setupProxy();
}

function updateUI(restoredSettings) {
  const selectList = document.querySelector("#proxy_scheme");
  selectList.value = restoredSettings.proxy_scheme;
  console.log("showing proxy scheme:", selectList.value);

  const hostitem = document.getElementById("host");
  hostitem.value = restoredSettings.proxy_host;
  console.log("showing proxy host:", hostitem.value);

  const portitem = document.getElementById("port");
  portitem.value = restoredSettings.proxy_port;
  console.log("showing proxy port:", portitem.value);

  const controlhostitem = document.getElementById("controlhost");
  controlhostitem.value = restoredSettings.control_host;
  console.log("showing control host:", controlhostitem.value);

  const controlportitem = document.getElementById("controlport");
  controlportitem.value = restoredSettings.control_port;
  console.log("showing control port:", controlportitem.value);

  SetHostText();
  SetPortText();
  SetControlHostText();
  SetControlPortText();
  SetControlHelpText();
  setupProxy();
}

function onError(e) {
  console.error(e);
}
chrome.storage.local.get(function (got) {
  checkStoredSettings(got);
  updateUI(got);
});

const saveButton = document.querySelector("#save-button");
saveButton.addEventListener("click", storeSettings);

//EXPERIMENTAL: Open in I2P Tab
