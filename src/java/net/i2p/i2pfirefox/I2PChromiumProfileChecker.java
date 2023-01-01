package net.i2p.i2pfirefox;

import java.io.File;

/**
 * I2PChromiumProfileChecker.java
 * Copyright (C) 2022 idk <hankhill19580@gmail.com>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License. See LICENSE.md for details.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * I2PChromiumProfileChecker is a class that checks if the Chromium profile
 * directory exists and is valid.
 *
 * @author idk
 * @since 0.0.1
 */
public class I2PChromiumProfileChecker extends I2PCommonBrowser {
  /**
   * Output feedback if the profile directory is valid or invalid
   *
   * @description Output feedback if the profile directory is valid or invalid
   * @args unused
   * @since 0.0.1
   */
  public static void main(String[] args) {
    I2PChromiumProfileChecker pc = new I2PChromiumProfileChecker();
    String profileDirectory = pc.profileDirectory("base");
    if (profileDirectory == null) {
      pc.logger.info("No profile directory found");
      return;
    }
    pc.logger.info("Profile directory: " + profileDirectory);
    boolean ok = pc.validateProfileDirectory(profileDirectory);
    if (ok) {
      pc.logger.info("Profile directory is valid");
    } else {
      pc.logger.info("Profile directory is invalid");
    }
  }

  /**
   * get the profile directory, creating it if necessary
   *
   * @return the profile directory, or null if it could not be created
   */
  public String profileDirectory(String base) {
    return profileDirectory("I2P_CHROMIUM_PROFILE", "chromium", base, false);
  }

  /**
   * Return true if the profile directory is valid.
   *
   * @param profileDirectory the profile directory to check
   * @return true if the profile directory is valid, false otherwise
   * @since 0.0.1
   */
  public boolean validateProfileDirectory(String profileDirectory) {
    File profileDir = new File(profileDirectory);
    if (!profileDir.exists()) {
      logger.info("Profile directory does not exist");
      return false;
    }
    if (!profileDir.isDirectory()) {
      logger.info("Profile directory is not a directory");
      return false;
    }
    if (!profileDir.canRead()) {
      logger.info("Profile directory is not readable");
      return false;
    }
    if (!profileDir.canWrite()) {
      logger.info("Profile directory is not writable");
      return false;
    }
    if (!validateExtensionDirectory(profileDir + "/extensions")) {
      logger.info("extensions directory is invalid");
      return false;
    }
    return true;
  }
  /**
   * Return true if the file is valid.
   *
   * @param file the file to check
   * @return true if the file is valid, false otherwise
   * @since 0.0.1
   */
  public boolean validateFile(String file) {
    File f = new File(file);
    if (!f.exists()) {
      logger.info("User JavaScript file does not exist");
      return false;
    }
    if (!f.isFile()) {
      logger.info("User JavaScript file is not a file");
      return false;
    }
    if (!f.canRead()) {
      logger.info("User JavaScript file is not readable");
      return false;
    }
    if (!f.canWrite()) {
      logger.info("User JavaScript file is not writable");
      return false;
    }
    return true;
  }
  /**
   * Return true if the extension directory is valid.
   *
   * @param extensionDirectory the extension directory to check
   * @return true if the extension directory is valid, false otherwise
   * @since 0.0.1
   */
  public boolean validateExtensionDirectory(String extensionDirectory) {
    File extensionDir = new File(extensionDirectory);
    if (!extensionDir.exists()) {
      logger.info("Extension directory does not exist");
      return false;
    }
    if (!extensionDir.isDirectory()) {
      logger.info("Extension directory is not a directory");
      return false;
    }
    if (!extensionDir.canRead()) {
      logger.info("Extension directory is not readable");
      return false;
    }
    if (!extensionDir.canWrite()) {
      logger.info("Extension directory is not writable");
      return false;
    }
    return true;
  }
}
