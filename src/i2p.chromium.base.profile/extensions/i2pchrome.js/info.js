document.addEventListener("click", (clickEvent) => {
  if (clickEvent.target.id === "window-create-help-panel") {
    let createData = {
      type: "panel",
      incognito: true,
    };
    let creating = chrome.tabs.create(createData);
    creating.then(() => {
      console.log("The help panel has been created");
    });
  } else if (clickEvent.target.id === "window-create-news-panel") {
    let createData = {
      type: "panel",
      incognito: true,
    };
    let creating = chrome.tabs.create(createData);
    creating.then(() => {
      console.log("The news panel has been created");
    });
  } else if (clickEvent.target.id === "generate-fresh-tunnel") {
    function refreshIdentity() {
      console.log("Generating new identity");
      const Http = new XMLHttpRequest();
      const url = "http://" + controlHost + ":" + controlPort;
      Http.open("GET", url);
      Http.send();
      Http.onreadystatechange = (event) => {
        console.log(Http.responseText);
      };
    }
    refreshIdentity();
  } else if (clickEvent.target.id === "window-preface-title") {
    console.log("attempting to create homepage tab");
    goHome();
  } else if (clickEvent.target.id === "window-visit-homepage") {
    console.log("attempting to create homepage tab");
    goHome();
  } else if (clickEvent.target.id === "window-visit-readme") {
    console.log("attempting to create readme tab");
    goIndex();
  } else if (clickEvent.target.id === "window-visit-i2ptunnel") {
    console.log("attempting to create i2ptunnel tab");
    goTunnel();
  } else if (clickEvent.target.id === "window-visit-susimail") {
    console.log("attempting to create susimail tab");
    goMail();
  } else if (clickEvent.target.id === "window-visit-snark") {
    console.log("attempting to create snark tab");
    goSnark();
  } else if (clickEvent.target.id === "clear-chrome-data") {
    forgetBrowsingData();
  } else if (clickEvent.target.id === "check-i2p-control") {
    //echo("I2P Router Detected", "panel-section-i2pcontrol-check");
  } else if (clickEvent.target.id === "enable-web-rtc") {
    if (clickEvent.target.checked) {
      chrome.runtime.sendMessage({ rtc: "enableWebRTC" });
    } else {
      chrome.runtime.sendMessage({ rtc: "disableWebRTC" });
    }
    checkPeerConnection();
    return;
  } else if (clickEvent.target.id === "disable-history") {
    if (clickEvent.target.checked) {
      chrome.runtime.sendMessage({ history: "disableHistory" });
    } else {
      chrome.runtime.sendMessage({ history: "enableHistory" });
    }
    return;
  }

  clickEvent.preventDefault();
});

function proxyReadiness() {
  console.log(this.responseText);
}

control_host = "localhost";
control_port = "7657";

function onTabCreated() {
  console.log("Tab Created");
}

function goIndex() {
  let createData = {
    url: "index.html",
  };
  console.log("visiting readme");
  let creating = chrome.tabs.create(createData);
}

function goHome() {
  let createData = {
    url: "home.html",
  };
  console.log("visiting homepage");
  let creating = chrome.tabs.create(createData);
}

function goTunnel() {
  let createData = {
    url: "http://" + control_host + ":" + control_port + "/i2ptunnel",
  };
  console.log("visiting i2ptunnel");
  let creating = chrome.tabs.create(createData);
}

function goMail() {
  let createData = {
    url: "http://" + control_host + ":" + control_port + "/susimail",
  };
  console.log("visiting mail");
  let creating = chrome.tabs.create(createData);
}

function goSnark() {
  let createData = {
    url: "http://" + control_host + ":" + control_port + "/i2psnark",
  };
  console.log("visiting snark");
  let creating = chrome.tabs.create(createData);
}
