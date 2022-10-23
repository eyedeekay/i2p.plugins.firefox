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
public class I2PBrowser extends I2PCommonBrowser {
  private final I2PFirefox i2pFirefox = new I2PFirefox();
  private final I2PChromium i2pChromium = new I2PChromium();
  private final I2PGenericUnsafeBrowser i2pGeneral =
      new I2PGenericUnsafeBrowser();
  public boolean firefox = false;
  public boolean chromium = false;
  public boolean generic = false;
  public boolean chromiumFirst = false;
  public boolean usability = false;
  static private boolean outputConfig = true;

  private void launchFirefox(int privateWindow, String[] url) {
    logger.info("I2PFirefox" + privateWindow);
    I2PFirefox.usability = usability;
    if (outputConfig)
      i2pFirefox.storeFirefoxDefaults();
    i2pFirefox.launch(privateWindow, url);
  }
  private void launchChromium(int privateWindow, String[] url) {
    logger.info("I2PChromium" + privateWindow);
    I2PChromiumProfileBuilder.usability = usability;
    if (outputConfig)
      i2pChromium.storeChromiumDefaults();
    i2pChromium.launch(privateWindow, url);
  }
  private void launchGeneric(int privateWindowInt, String[] url) {
    boolean privateWindow = false;
    if (privateWindowInt == 1)
      privateWindow = true;
    logger.info("I2PGeneric" + privateWindowInt);
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
  public I2PBrowser(String browserPath) {
    I2PGenericUnsafeBrowser.BROWSER = browserPath;
  }

  public void setBrowser(String browserPath) {
    I2PGenericUnsafeBrowser.BROWSER = browserPath;
  }

  /**
   * Return true if there is a Chromium available
   *
   * @return true if Chromium is available, false otherwise
   * @since 0.0.16
   */
  public boolean hasChromium() {
    String chrome = i2pChromium.topChromium();
    if (chrome == null) {
      return false;
    }
    if (chrome.isEmpty()) {
      return false;
    }
    return true;
  }

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
    validateUserDir();
    if (generic)
      this.launchGeneric(privateWindow, url);
    if ((chromium && firefox) || (!chromium && !firefox)) {
      if (this.hasFirefox()) {
        this.launchFirefox(privateWindow, url);
      } else if (this.hasChromium()) {
        this.launchChromium(privateWindow, url);
      } else {
        this.launchGeneric(privateWindow, url);
      }
      return;
    }
    if (chromiumFirst) {
      if (chromium) {
        this.launchChromium(privateWindow, url);
      } else if (firefox) {
        this.launchFirefox(privateWindow, url);
      } else {
        this.launchGeneric(privateWindow, url);
      }
      return;
    }
    if (firefox) {
      this.launchFirefox(privateWindow, url);
    } else if (chromium) {
      this.launchChromium(privateWindow, url);
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

  private static String ValidURL(String inUrl) {
    String[] schemes = {"http", "https"};
    for (String scheme : schemes) {
      if (inUrl.startsWith(scheme)) {
        return inUrl;
      }
    }
    return "";
  }

  public static void main(String[] args) {
    validateUserDir();
    int privateBrowsing = 0;
    logger.info("I2PBrowser");
    I2PBrowser i2pBrowser = new I2PBrowser();
    ArrayList<String> visitURL = new ArrayList<String>();
    if (args != null) {
      if (args.length > 0) {
        for (String arg : args) {
          if (arg.equals("-private")) {
            privateBrowsing = 1;
          }
          if (arg.equals("-chromium")) {
            i2pBrowser.chromium = true;
          }
          if (arg.equals("-firefox")) {
            i2pBrowser.firefox = true;
          }
          if (arg.equals("-usability")) {
            i2pBrowser.usability = true;
          }
          if (arg.equals("-app")) {
            i2pBrowser.usability = true;
            privateBrowsing = 2;
          }
          if (arg.equals("-outputconfig")) {
            outputConfig = true;
          }
          if (arg.equals("-noproxycheck")) {
            logger.info("zeroing out proxy check");
            i2pBrowser.setProxyTimeoutTime(0);
          }
          if (!arg.startsWith("-")) {
            visitURL.add(ValidURL(arg));
          }
        }
      }
    }
    i2pBrowser.launch(privateBrowsing,
                      visitURL.toArray(new String[visitURL.size()]));
  }
}