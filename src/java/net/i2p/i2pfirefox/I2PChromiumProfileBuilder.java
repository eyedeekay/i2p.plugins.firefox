package net.i2p.i2pfirefox;

import java.io.File;

/**
 * I2PChromiumProfileBuilder.java
 * Copyright (C) 2022 idk <hankhill19580@gmail.com>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License. See LICENSE.md for details.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * I2PChromiumProfileBuilder is a class that builds a profile directory which
 * contains the I2P browser profile for the Chromium browser family. It manages
 * the base profile directory and copies it's contents to the active profile
 * directory, which is actually used by Chromium.
 *
 * @author idk
 * @since 0.0.1
 */
public class I2PChromiumProfileBuilder extends I2PCommonBrowser {
  // private static boolean strict;

  /**
   * get the profile directory, creating it if necessary
   *
   * @return the profile directory, or null if it could not be created
   */
  public static String profileDirectory() {
    return profileDirectory("I2P_CHROMIUM_PROFILE", "chromium");
  }

  private static String baseProfileDir(String file) {
    File profileDir = new File(file, "i2p.chromium.base.profile");
    // make sure the directory exists
    if (profileDir.exists()) {
      return profileDir.getAbsolutePath();
    } else {
      // create the directory
      I2PChromiumProfileUnpacker unpacker = new I2PChromiumProfileUnpacker();
      if (!unpacker.unpackProfile(profileDir.getAbsolutePath())) {
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
  public static String baseProfileDirectory() {
    String pd = System.getenv("I2P_CHROMIUM_BASE_PROFILE");
    if (pd != null && !pd.isEmpty()) {
      File pdf = new File(pd);
      if (pdf.exists() && pdf.isDirectory()) {
        return pd;
      } else {
        I2PChromiumProfileUnpacker unpacker = new I2PChromiumProfileUnpacker();
        if (!unpacker.unpackProfile(pdf.getAbsolutePath())) {
          return null;
        }
      }
    }
    String rtd = runtimeDirectory();
    return baseProfileDir(rtd);
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
    // get the I2P_CHROMIUM_DIR environment variable
    String rtd = System.getenv("I2P_CHROMIUM_DIR");
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
  public static boolean copyBaseProfiletoProfile() {
    String baseProfile = baseProfileDirectory();
    String profile = profileDirectory();
    System.out.println("Copying base profile to profile directory: " +
                       baseProfile + " -> " + profile);
    if (baseProfile.isEmpty() || profile.isEmpty()) {
      return false;
    }
    File baseProfileDir = new File(baseProfile);
    File profileDir = new File(profile);

    try {
      System.out.println("Copying base profile to profile directory");
      copyDirectory(baseProfileDir, profileDir, "chromium");
    } catch (Exception e) {
      System.out.println("Error copying base profile to profile" + e);
      return false;
    }
    System.out.println("Copied base profile to profile directory");
    return true;
  }

  /**
   * Construct a new Profile Builder
   *
   * @since 0.0.1
   */
  public I2PChromiumProfileBuilder() {
    // I2PChromiumProfileBuilder.strict = false;
  }

  /**
   * Construct a new Profile Builder
   * @param strict if true, the strict overrides will be copied to the profile
   *
   * @since 0.0.1
   */
  public I2PChromiumProfileBuilder(boolean strict) {
    // I2PChromiumProfileBuilder.strict = strict;
  }
}
