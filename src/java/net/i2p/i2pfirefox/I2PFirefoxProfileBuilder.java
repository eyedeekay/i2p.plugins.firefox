package net.i2p.i2pfirefox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * I2PFirefoxProfileBuilder.java
 * Copyright (C) 2022 idk <hankhill19580@gmail.com>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License. See LICENSE.md for details.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * I2PFirefoxProfileBuilder is a class that builds a profile directory which
 * contains the I2P browser profile for the Firefox browser family. It manages
 * the base profile directory and copies it's contents to the active profile
 * directory, which is actually used by Firefox.
 *
 * @author idk
 * @since 0.0.1
 */
public class I2PFirefoxProfileBuilder extends I2PFirefoxProfileChecker {
  private boolean strict;

  private String userChromeCSS() {
    String ret = "@namespace url(\"http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul\")\n";

    /* only needed once */

    ret += "@namespace html url(\" http : // www.w3.org/1999/xhtml\");\n";
    ret += "#PersonalToolbar,\n";
    ret += "#PanelUI-Button,\n";
    ret += "#PanelUI-menu-button,\n";
    ret += "#star-button,\n";
    ret += "#forward-button,\n";
    ret += "#home-button,\n";
    ret += "#bookmarks-toolbar-button,\n";
    ret += "#library-button,\n";
    ret += "#sidebar-button,\n";
    ret += "#pocket-button,\n";
    ret += "#fxa-toolbar-menu-button,\n";
    ret += "#reader-mode-button,\n";
    ret += "#identity-icon {\n";
    ret += "    visibility: collapse;\n";
    ret += "}\n";
    ret += "\n";
    ret += "#urlbar-background {\n";
    ret += "    background-color: black !important;\n";
    ret += "}\n";
    ret += "\n";
    ret += "\n";
    ret += "/* Remove back button circle */\n";
    ret += "\n";
    ret += "#back-button:not(:hover),\n";
    ret += "#back-button:not(:hover)>.toolbarbutton-icon {\n";
    ret += "    background: transparent !important;\n";
    ret += "    border: none !important;\n";
    ret += "    box-shadow: none !important;\n";
    ret += "}\n";
    ret += "\n";
    ret += "#back-button:hover,\n";
    ret += "#back-button:hover>.toolbarbutton-icon {\n";
    ret += "    border: none !important;\n";
    ret += "    border-radius: 2px !important;\n";
    ret += "}\n";
    ret += "\n";
    ret += "#urlbar-container {\n";
    ret += "    visibility: collapse !important;\n";
    ret += "}\n";
    ret += "\n";
    ret += "#TabsToolbar-customization-target {\n";
    ret += "    min-width: 50vw;\n";
    ret += "    max-width: 50vw;\n";
    ret += "    width: 50vw;\n";
    ret += "}\n";
    ret += "\n";
    ret += "#TabsToolbar {";
    ret += "    display: inherit;";
    ret += "}";
    ret += "\n";
    ret += "toolbar {";
    ret += "    max-width: 50%;";
    ret += "}";
    ret += "\n";
    ret += "#navigator-toolbox {";
    ret += "    display: inline-flex;";
    ret += "}";
    ret += "\n";
    return ret;
  }

  /**
   * get the profile directory, creating it if necessary
   *
   * @return the profile directory, or null if it could not be created
   */
  // public String profileDirectory() {
  // return profileDirectory("I2P_FIREFOX_PROFILE", "firefox", false);
  // }

  private String baseProfileDir(String file, String base,
      boolean isTorBrowser) {
    File profileDir = new File(file, "i2p.firefox." + base + ".profile");
    // make sure the directory exists
    if (profileDir.exists()) {
      return profileDir.getAbsolutePath();
    } else {
      // create the directory
      if (!isTorBrowser) {
        if (!this.unpackProfile(profileDir.getAbsolutePath(), "firefox",
            base)) {
          return null;
        }
      } else {
        makeTBBDirectory(profileDir, profileDir);
      }
      return profileDir.getAbsolutePath();
    }
  }

  /**
   * get the base profile directory, creating it if necessary
   *
   * @return the base profile directory, or null if it could not be created
   */
  public String baseProfileDirectory(String base, boolean isTorBrowser) {
    String pd = System.getenv("I2P_FIREFOX_BASE_PROFILE");
    if (pd != null && !pd.isEmpty()) {
      File pdf = new File(pd);
      if (pdf.exists() && pdf.isDirectory()) {
        return pd;
      } else {
        if (!this.unpackProfile(pdf.getAbsolutePath(), "firefox", base)) {
          return null;
        }
      }
    }
    String rtd = runtimeDirectory();
    return baseProfileDir(rtd, base, isTorBrowser);
  }

  /**
   * get the runtime directory, creating it if create=true
   *
   * @param create if true, create the runtime directory if it does not exist
   * @return the runtime directory, or null if it could not be created
   * @since 0.0.1
   */
  public File runtimeDirectory(boolean create) {
    String rtd = runtimeDirectory();
    return runtimeDirectory(create, rtd);
  }

  /**
   * get the correct runtime directory
   *
   * @return the runtime directory, or null if it could not be created or found
   * @since 0.0.1
   */
  public String runtimeDirectory() {
    // get the I2P_FIREFOX_DIR environment variable
    String rtd = System.getenv("I2P_FIREFOX_DIR");
    // if it is not null and not empty
    if (rtd != null && !rtd.isEmpty()) {
      // check if the file exists
      File rtdFile = new File(rtd);
      if (rtdFile.exists()) {
        // if it does, return it
        return runtimeDirectory(rtd);
      }
    }
    return runtimeDirectory("");
  }

  /**
   * Copy the inert base profile directory to the runtime profile directory
   *
   * @since 0.0.1
   */
  public boolean copyBaseProfiletoProfile(String base, boolean app,
      boolean isTorBrowser) {
    String baseProfile = baseProfileDirectory(base, isTorBrowser);
    String profile = profileDirectory(app, base);
    if (baseProfile.isEmpty() || profile.isEmpty()) {
      return false;
    }
    File baseProfileDir = new File(baseProfile);
    File profileDir = new File(profile);

    if (!profileDir.exists()) {
      try {
        if (!isTorBrowser) {
          logger.info("Copying base profile to profile directory: " +
              baseProfile + " -> " + profile);
          copyDirectory(baseProfileDir, profileDir, "firefox", base);
        } else {
          logger.info("Creating base directory for use with Tor Browser");
          makeTBBDirectory(baseProfileDir, profileDir);
        }
        logger.info("Copied base profile to profile directory");
        return true;
      } catch (Exception e) {
        logger.info("Error copying base profile to profile" + e);
        return false;
      }
    }
    // if user.js does not exist yet, make an empty one.
    // if (!touch(profileDir.toString(), "user.js")) {
    // return false;
    // }
    // if extensions does not exist yet, make an empty one.
    // if (!mkExtensionsDir(profileDir.toString())){
    // return false;
    // }

    return copyStrictOptions(base, app, isTorBrowser);
  }

  protected boolean writeAppChrome(String profile) {
    File dir = new File(profile, "chrome");
    if (!dir.exists())
      dir.mkdirs();
    File f = new File(dir, "userChrome.css");
    try {
      Files.write(f.toPath(), userChromeCSS().getBytes());
    } catch (IOException e) {
      logger.warning(e.toString());
      return false;
    }
    return true;
  }

  protected boolean deleteAppChrome(String profile) {
    File dir = new File(profile, "chrome");
    if (!dir.exists())
      return true;
    File f = new File(dir, "userChrome.css");
    if (f.exists())
      f.delete();
    return true;
  }

  /**
   * Copy the strict options from the base profile to the profile
   *
   * @return true if successful, false otherwise
   * @since 0.0.1
   */
  public boolean copyStrictOptions(String base, boolean app,
      boolean isTorBrowser) {
    logger.info("Checking strict options");
    String baseProfile = baseProfileDirectory(base, isTorBrowser);
    String profile = profileDirectory(app, base);
    if (baseProfile.isEmpty() || profile.isEmpty()) {
      logger.info("Empty paths");
      return false;
    }
    File baseProfileDir = new File(baseProfile);
    File profileDir = new File(profile);
    setupUserChrome(profileDir, app);
    if (!baseProfileDir.exists()) {
      logger.info("Empty base directories: " + baseProfileDir);
      return false;
    }
    File baseOverrides = new File(baseProfile, "strict-overrides.js");
    File userOverrides = new File(baseProfile, "user-overrides.js");
    try {
      if (baseOverrides.exists()) {
        if (strict) {
          logger.info("Enabling strict options");
          Files.copy(baseOverrides.toPath(), userOverrides.toPath(),
              StandardCopyOption.REPLACE_EXISTING);
          return true;
        }
      }
    } catch (Exception e) {
      logger.info("Error copying base profile to profile" + e);
      return false;
    }
    logger.info("Done setting up fancy Firefox options");

    return true;
  }

  public void setupUserChrome(File profileDir, boolean app) {
    File workingUserOverrides = new File(profileDir, "user-overrides.js");
    logger.info(workingUserOverrides.getAbsolutePath());
    if (workingUserOverrides.exists()) {
      logger.info("Checking app mode settings");
      if (app) {
        logger.info("Setting profile to app mode");
        this.undoValue(
            "toolkit.legacyUserProfileCustomizations.stylesheets\", false",
            "toolkit.legacyUserProfileCustomizations.stylesheets\", true",
            workingUserOverrides);
        writeAppChrome(profileDir.toString());
      } else {
        logger.info("Taking profile out of app mode");
        this.undoValue(
            "toolkit.legacyUserProfileCustomizations.stylesheets\", true",
            "toolkit.legacyUserProfileCustomizations.stylesheets\", false",
            workingUserOverrides);
        deleteAppChrome(profileDir.toString());
      }
    }
    logger.info("Done setting up fancy Firefox options");
    File workingPrefOverrides = new File(profileDir, "prefs.js");
    logger.info(workingPrefOverrides.getAbsolutePath());
    if (workingPrefOverrides.exists()) {
      logger.info("Checking app mode settings");
      if (app) {
        logger.info("Setting profile to app mode");
        this.undoValue(
            "toolkit.legacyUserProfileCustomizations.stylesheets\", false",
            "toolkit.legacyUserProfileCustomizations.stylesheets\", true",
            workingPrefOverrides);
        writeAppChrome(profileDir.toString());
      } else {
        logger.info("Taking profile out of app mode");
        this.undoValue(
            "toolkit.legacyUserProfileCustomizations.stylesheets\", true",
            "toolkit.legacyUserProfileCustomizations.stylesheets\", false",
            workingPrefOverrides);
        deleteAppChrome(profileDir.toString());
      }
    }
  }

  private String TBB_BASE = "user_pref(\"extensions.torbutton.use_nontor_proxy\", true);\n"
+ "user_pref(\"extensions.torlauncher.start_tor\", false);\n"
+ "user_pref(\"extensions.torlauncher.prompt_at_startup\", false);\n"
+ "user_pref(\"network.proxy.no_proxies_on\", \"127.0.0.1:7657,localhost:7657,127.0.0.1:7662,localhost:7662,127.0.0.1:7669,localhost:7669\");\n"
+ "user_pref(\"network.proxy.type\", 1);\n"
+ "user_pref(\"network.proxy.http\", \"127.0.0.1\");\n"
+ "user_pref(\"network.proxy.http_port\", 4444);\n"
+ "user_pref(\"network.proxy.ssl\", \"127.0.0.1\");\n"
+ "user_pref(\"network.proxy.ssl_port\", 4444);\n"
+ "user_pref(\"network.proxy.ftp\", \"127.0.0.1\");"
+ "user_pref(\"network.proxy.ftp_port\", 4444);\n"
+ "user_pref(\"network.proxy.socks\", \"127.0.0.1\");\n"
+ "user_pref(\"network.proxy.socks_port\", 4444);\n"
+ "user_pref(\"network.proxy.share_proxy_settings\", true);\n"
+ "user_pref(\"browser.startup.homepage\", \"about:blank\");\n"
+ "user_pref(\"keyword.enabled\", false);\n"
+ "user_pref(\"extensions.allowPrivateBrowsingByDefault\", true);\n"
+ "user_pref(\"extensions.PrivateBrowsing.notification\", false);\n"
+ "user_pref(\"extensions.pocket.enabled\", false);\n"
+ "user_pref(\"browser.newtabpage.activity-stream.showSponsoredTopSites\", false);\n"
+ "user_pref(\"browser.newtabpage.activity-stream.showSponsored\", false);\n"
+ "user_pref(\"services.sync.prefs.sync.browser.newtabpage.activity-stream.showSponsored\", false);\n"
+ "user_pref(\"browser.newtabpage.activity-stream.feeds.section.highlights\", false);\n"
+ "user_pref(\"browser.newtabpage.activity-stream.feeds.section.topstories\", false);\n"
+ "user_pref(\"browser.newtabpage.activity-stream.default.sites\", \"http://planet.i2p/,http://legwork.i2p/,http://i2pwiki.i2p/,http://i2pforums.i2p/,http://zzz.i2p/\");\n"
+ "user_pref(\"browser.newtabpage.activity-stream.feeds.topsites\", true);\n"
+ "user_pref(\"browser.fixup.domainsuffixwhitelist.i2p\", true);\n";

  protected void makeTBBDirectory(File sourceDir, File destDir) {
    logger.info("creating TBB directory");
    if (!destDir.exists())
      destDir.mkdir();
    File workUserJs = new File(destDir, "user.js");
    // Write the value of TBB_BASE to the file workUserJs
    try (FileWriter writer = new FileWriter(workUserJs)) {
      logger.info("writing TBB user.js");
      writer.write(TBB_BASE);
    } catch (IOException e) {
       logger.info("Error writing to file: " + e.getMessage());
    }
  }

  /**
   * Construct a new Profile Builder
   *
   * @since 0.0.1
   */
  public I2PFirefoxProfileBuilder() {
    this.strict = false;
  }

  /**
   * Construct a new Profile Builder
   *
   * @param strict if true, the strict overrides will be copied to the profile
   *
   * @since 0.0.1
   */
  public I2PFirefoxProfileBuilder(boolean strict) {
    this.strict = strict;
  }
}
