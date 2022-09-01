if (UA.isMozilla) {
  let y = async (url, originUrl = '') => await XSS.test({originUrl, url, method: "GET"});
  let n = async (...args) => !await y(...args);
  Promise.all([
      () => y("https://sandbox.hack.vet/issue/noscript/bypass/simple_xss.php?name=%22;alert?.(%22NoScript%2011.1.7%20Bypass%20XSS%20@reinforchu%22)//"),
      () => y("https://sandbox.hack.vet/issue/noscript/bypass/simple_xss.php?name=%22;location?.assign?.(%22https://reinforc.hu%22)//"),
      () => y("https://sandbox.hack.vet/issue/noscript/bypass/simple_xss.php?name=%22;document?.[%27write%27]?.(%22XSS%22)//"),
      () => y("https://sandbox.hack.vet/issue/noscript/bypass/simple_xss.php?name=%22;document?.[%27write%27]?.(%22%3Cinput%20%22%2b%22+on%22%2b%22focus=alert?.(document?.cookie)%22%2b%22+autofocus%3E%22)//"),
    ].map(t => Test.run(t))
    ).then(() => Test.report());
}
