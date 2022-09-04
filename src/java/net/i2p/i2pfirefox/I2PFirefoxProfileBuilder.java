package net.i2p.i2pfirefox;

import java.io.File;
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

  /**
   * get the profile directory, creating it if necessary
   *
   * @return the profile directory, or null if it could not be created
   */
  public static String profileDirectory() {
    return profileDirectory("I2P_FIREFOX_PROFILE", "firefox");
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
  public static boolean copyBaseProfiletoProfile(String base) {
    String baseProfile = baseProfileDirectory(base);
    String profile = profileDirectory();
    System.out.println("Copying base profile to profile directory: " +
                       baseProfile + " -> " + profile);
    if (baseProfile.isEmpty() || profile.isEmpty()) {
      return false;
    }
    File baseProfileDir = new File(baseProfile);
    File profileDir = new File(profile);

    if (!profileDir.exists()) {
      try {
        System.out.println("Copying base profile to profile directory");
        copyDirectory(baseProfileDir, profileDir, "firefox", base);
      } catch (Exception e) {
        System.out.println("Error copying base profile to profile" + e);
        return false;
      }
      System.out.println("Copied base profile to profile directory");
    }
    // if user.js does not exist yet, make an empty one.
    // if (!touch(profileDir.toString(), "user.js")) {
    // return false;
    //}
    // if extensions does not exist yet, make an empty one.
    // if (!mkExtensionsDir(profileDir.toString())){
    // return false;
    //}

    return copyStrictOptions(base);
  }

  /**
   * Copy the strict options from the base profile to the profile
   *
   * @return true if successful, false otherwise
   * @since 0.0.1
   */
  public static boolean copyStrictOptions(String base) {
    if (!strict) {
      return true;
    }
    String baseProfile = baseProfileDirectory(base);
    String profile = profileDirectory();
    if (baseProfile.isEmpty() || profile.isEmpty()) {
      return false;
    }
    File baseProfileDir = new File(baseProfile);
    File profileDir = new File(profile);
    if (!baseProfileDir.exists() || !profileDir.exists()) {
      return false;
    }
    File baseOverrides = new File(baseProfile, "strict-overrides.js");
    File userOverrides = new File(baseProfile, "user-overrides.js");
    if (!baseOverrides.exists()) {
      return false;
    }
    try {
      Files.copy(baseOverrides.toPath(), userOverrides.toPath(),
                 StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception e) {
      System.out.println("Error copying base profile to profile" + e);
      return false;
    }
    // if user-overrides.js does not exist yet, make an empty one.
    // if (!touch(profileDir.toString(), "user-overrides.js")) {
    // return false;
    //}
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
