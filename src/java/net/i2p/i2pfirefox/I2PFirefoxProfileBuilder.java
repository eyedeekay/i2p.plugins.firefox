package net.i2p.i2pfirefox;

import java.io.File;
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
public class I2PFirefoxProfileBuilder extends I2PCommonBrowser {
  private static boolean strict;
  private static String userChromeCSS() {
    String ret =
        "@namespace url(\"http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul\")\n";

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
  // public static String profileDirectory() {
  //   return profileDirectory("I2P_FIREFOX_PROFILE", "firefox", false);
  // }

  /**
   * get the profile directory, creating it if necessary
   *
   * @return the profile directory, or null if it could not be created
   */
  public static String profileDirectory(boolean app) {
    return profileDirectory("I2P_FIREFOX_PROFILE", "firefox", app);
  }

  private static String baseProfileDir(String file, String base) {
    File profileDir = new File(file, "i2p.firefox." + base + ".profile");
    // make sure the directory exists
    if (profileDir.exists()) {
      return profileDir.getAbsolutePath();
    } else {
      // create the directory
      I2PFirefoxProfileUnpacker unpacker = new I2PFirefoxProfileUnpacker();
      if (!unpacker.unpackProfile(profileDir.getAbsolutePath(), base)) {
        return null;
      }
      return profileDir.getAbsolutePath();
    }
  }

  /**
   * get the base profile directory, creating it if necessary
   *
   * @return the base profile directory, or null if it could not be created
   */
  public static String baseProfileDirectory(String base) {
    String pd = System.getenv("I2P_FIREFOX_BASE_PROFILE");
    if (pd != null && !pd.isEmpty()) {
      File pdf = new File(pd);
      if (pdf.exists() && pdf.isDirectory()) {
        return pd;
      } else {
        I2PFirefoxProfileUnpacker unpacker = new I2PFirefoxProfileUnpacker();
        if (!unpacker.unpackProfile(pdf.getAbsolutePath(), base)) {
          return null;
        }
      }
    }
    String rtd = runtimeDirectory();
    return baseProfileDir(rtd, base);
  }

  /**
   * get the runtime directory, creating it if create=true
   *
   * @param create if true, create the runtime directory if it does not exist
   * @return the runtime directory, or null if it could not be created
   * @since 0.0.1
   */
  public static File runtimeDirectory(boolean create) {
    String rtd = runtimeDirectory();
    return runtimeDirectory(create, rtd);
  }

  /**
   * get the correct runtime directory
   *
   * @return the runtime directory, or null if it could not be created or found
   * @since 0.0.1
   */
  public static String runtimeDirectory() {
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
  public static boolean copyBaseProfiletoProfile(String base, boolean app) {
    String baseProfile = baseProfileDirectory(base);
    String profile = profileDirectory(app);
    logger.info("Copying base profile to profile directory: " + baseProfile +
                " -> " + profile);
    if (baseProfile.isEmpty() || profile.isEmpty()) {
      return false;
    }
    File baseProfileDir = new File(baseProfile);
    File profileDir = new File(profile);

    if (!profileDir.exists()) {
      try {
        logger.info("Copying base profile to profile directory");
        copyDirectory(baseProfileDir, profileDir, "firefox", base);
      } catch (Exception e) {
        logger.info("Error copying base profile to profile" + e);
        return false;
      }
      logger.info("Copied base profile to profile directory");
    }
    // if user.js does not exist yet, make an empty one.
    // if (!touch(profileDir.toString(), "user.js")) {
    // return false;
    //}
    // if extensions does not exist yet, make an empty one.
    // if (!mkExtensionsDir(profileDir.toString())){
    // return false;
    //}

    return copyStrictOptions(base, app);
  }

  protected static boolean writeAppChrome(String profile) {
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
  protected static boolean deleteAppChrome(String profile) {
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
  public static boolean copyStrictOptions(String base, boolean app) {
    logger.info("Checking strict options");
    String baseProfile = baseProfileDirectory(base);
    String profile = profileDirectory(app);
    if (baseProfile.isEmpty() || profile.isEmpty()) {
      logger.info("Empty paths");
      return false;
    }
    File baseProfileDir = new File(baseProfile);
    File profileDir = new File(profile);
    if (!baseProfileDir.exists() || !profileDir.exists()) {
      logger.info("Empty directories");
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
    File workingUserOverrides = new File(profileDir, "user-overrides.js");
    logger.info(workingUserOverrides.getAbsolutePath());
    if (workingUserOverrides.exists()) {
      logger.info("Checking app mode settings");
      if (app) {
        logger.info("Setting profile to app mode");
        I2PFirefoxProfileChecker.undoValue(
            "toolkit.legacyUserProfileCustomizations.stylesheets\", false",
            "toolkit.legacyUserProfileCustomizations.stylesheets\", true",
            workingUserOverrides);
        writeAppChrome(profileDir.toString());
      } else {
        logger.info("Taking profile out of app mode");
        I2PFirefoxProfileChecker.undoValue(
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
        I2PFirefoxProfileChecker.undoValue(
            "toolkit.legacyUserProfileCustomizations.stylesheets\", false",
            "toolkit.legacyUserProfileCustomizations.stylesheets\", true",
            workingPrefOverrides);
        writeAppChrome(profileDir.toString());
      } else {
        logger.info("Taking profile out of app mode");
        I2PFirefoxProfileChecker.undoValue(
            "toolkit.legacyUserProfileCustomizations.stylesheets\", true",
            "toolkit.legacyUserProfileCustomizations.stylesheets\", false",
            workingPrefOverrides);
        deleteAppChrome(profileDir.toString());
      }
    }
    logger.info("Done setting up fancy Firefox options");

    return true;
  }

  /**
   * Construct a new Profile Builder
   *
   * @since 0.0.1
   */
  public I2PFirefoxProfileBuilder() { I2PFirefoxProfileBuilder.strict = false; }

  /**
   * Construct a new Profile Builder
   * @param strict if true, the strict overrides will be copied to the profile
   *
   * @since 0.0.1
   */
  public I2PFirefoxProfileBuilder(boolean strict) {
    I2PFirefoxProfileBuilder.strict = strict;
  }
}
