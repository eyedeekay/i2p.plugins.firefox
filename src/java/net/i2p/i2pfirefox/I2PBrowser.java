package net.i2p.i2pfirefox;

import java.util.ArrayList;

/**
 * I2PBrowser.java
 * Copyright (C) 2022 idk <hankhill19580@gmail.com>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License. See LICENSE.md for details.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * @description I2PBrowser is a class that is used to open a browser window to
 * the I2P network. It automatically detects the operating system and available
 * browsers, and selects the best one to use, with Tor Browser at the top for
 * Firefox and Brave at the top for Chrome.
 *
 * @author idk
 * @since 0.0.16
 */
public class I2PBrowser extends I2PGenericUnsafeBrowser {
  private final I2PFirefox i2pFirefox = new I2PFirefox();
  private final I2PGenericUnsafeBrowser i2pGeneral =
      new I2PGenericUnsafeBrowser();
  public boolean firefox = false;
  public boolean chromium = false;
  public boolean generic = false;
  public boolean chromiumFirst = false;
  public boolean usability = false;
  public int privateBrowsing = 0;
  private boolean outputConfig = false;

  public void launchFirefox(int privateWindow, String[] url) {
    String priv = privateWindow == 1 ? "private-window" : "long-profile";
    logger.info("I2PFirefox" + priv);
    i2pFirefox.usability = usability;
    if (url == null)
      url = new String[] {"about:blank"};
    if (outputConfig)
      i2pFirefox.storeFirefoxDefaults();
    i2pFirefox.launch(privateWindow, url);
  }
  private void launchGeneric(int privateWindowInt, String[] url) {
    String priv = privateWindowInt == 1 ? "private-window" : "long-profile";
    boolean privateWindow = false;
    if (url == null)
      url = new String[] {"about:blank"};
    if (privateWindowInt == 1)
      privateWindow = true;
    if (outputConfig)
      i2pGeneral.storeGenericDefaults();
    logger.info("I2PGeneric" + priv);
    i2pGeneral.launch(privateWindow, url);
  }

  /**
   * Construct an I2PBrowser class which automatically determines which browser
   * to use.
   *
   * @since 0.0.16
   */
  public I2PBrowser() {}

  /**
   * Construct an I2PBrowser class which automatically determines which browser
   * to use.
   *
   * @since 0.0.18
   */
  public I2PBrowser(String browserPath) { this.BROWSER = browserPath; }

  public void setBrowser(String browserPath) { this.BROWSER = browserPath; }

  /**
   * Return true if there is a Firefox variant available
   *
   * @return true if Firefox variant is available, false otherwise
   * @since 0.0.16
   */
  public boolean hasFirefox() {
    String fox = i2pFirefox.topFirefox();
    if (fox == null) {
      return false;
    }
    if (fox.isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * Populates a profile directory with a proxy configuration.
   * Waits for an HTTP proxy on the port 4444 to be ready.
   * Launches either Firefox or Chromium with the profile directory.
   *
   * @param bool if true, the profile will be ephemeral(i.e. a --private-window
   *     profile).
   * @since 0.0.17
   */
  public void launch(int privateWindow, String[] url) {
    if (firefox) {
      this.launchFirefox(privateWindow, url);
    } else {
      this.launchGeneric(privateWindow, url);
    }
    return;
  }

  /**
   * Populates a profile directory with a proxy configuration.
   * Waits for an HTTP proxy on the port 4444 to be ready.
   * Launches either Firefox or Chromium with the profile directory.
   *
   * @param bool if true, the profile will be ephemeral(i.e. a --private-window
   *     profile).
   * @since 0.0.16
   */
  public void launch(boolean privateWindow) {
    int privateWindowInt = 0;
    if (privateWindow)
      privateWindowInt = 1;
    launch(privateWindowInt, null);
  }

  /**
   * Populates a profile directory with a proxy configuration.
   * Waits for an HTTP proxy on the port 4444 to be ready.
   * Launches either Firefox or Chromium with the profile directory.
   *
   * @since 0.0.16
   */
  public void launch() { launch(false); }

  /**
   * Stop all running processes managed by the browser manager.
   *
   * @return true if successful, false if not
   */
  public boolean stop() {
    boolean r = true;
    if (i2pFirefox != null)
      r = i2pFirefox.stop();
    if (i2pGeneral != null)
      r = i2pGeneral.stop();
    return r;
  }

  public boolean running() {
    if (i2pFirefox != null)
      return i2pFirefox.running();
    if (i2pGeneral != null)
      return i2pGeneral.running();
    return false;
  }

  private String ValidURL(String inUrl) {
    String[] schemes = {"http", "https"};
    for (String scheme : schemes) {
      if (inUrl.startsWith(scheme)) {
        return inUrl;
      }
    }
    return "";
  }

  public static void main(String[] args) {
    I2PBrowser i2pBrowser = new I2PBrowser();
    i2pBrowser.startup(args);
  }
  public ArrayList<String> parseArgs(String[] args) {
    logger.info("I2PBrowser");
    ArrayList<String> visitURL = new ArrayList<String>();
    if (args != null) {
      if (args.length > 0) {
        for (String arg : args) {
          if (arg.equals("-private")) {
            this.privateBrowsing = 1;
          }
          if (arg.equals("-chromium")) {
            this.chromium = true;
          }
          if (arg.equals("-firefox")) {
            this.firefox = true;
          }
          if (arg.equals("-usability")) {
            this.usability = true;
          }
          if (arg.equals("-strict")) {
            this.usability = false;
          }
          if (arg.equals("-generic")) {
            this.generic = true;
          }
          if (arg.equals("-app")) {
            this.usability = true;
            privateBrowsing = 2;
          }
          if (arg.equals("-outputconfig")) {
            outputConfig = true;
          }
          if (arg.equals("-noproxycheck")) {
            logger.info("zeroing out proxy check");
            this.setProxyTimeoutTime(0);
          }
          if (!arg.startsWith("-")) {
            visitURL.add(ValidURL(arg));
          }
        }
      }
      if (!this.chromium)
        this.firefox = true;
    }
    return visitURL;
  }
  public void startup(String[] args) {
    ArrayList<String> visitURL = parseArgs(args);
    try {
      Runtime.getRuntime().addShutdownHook(new Thread() {
        @Override
        public void run() {}
      });
    } catch (Exception e) {
      logger.warning(e.toString());
    }
    this.launch(this.privateBrowsing,
                visitURL.toArray(new String[visitURL.size()]));
  }
}