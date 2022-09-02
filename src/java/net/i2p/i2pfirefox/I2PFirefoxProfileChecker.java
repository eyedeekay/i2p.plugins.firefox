package net.i2p.i2pfirefox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * I2PFirefoxProfileChecker.java
 * Copyright (C) 2022 idk <hankhill19580@gmail.com>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License. See LICENSE.md for details.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * I2PFirefoxProfileChecker is a class that checks if the Firefox profile
 * directory exists and is valid.
 *
 * @author idk
 * @since 0.0.1
 */
public class I2PFirefoxProfileChecker {

  /**
   * @param args unused
   */
  public static void main(String[] args) {
    String profileDirectory = I2PFirefoxProfileBuilder.profileDirectory();
    if (profileDirectory == null) {
      System.out.println("No profile directory found");
      return;
    }
    System.out.println("Profile directory: " + profileDirectory);
    boolean ok = validateProfileDirectory(profileDirectory);
    if (ok) {
      System.out.println("Profile directory is valid");
    } else {
      System.out.println("Profile directory is invalid");
    }
  }
  /**
   * Return true if the profile directory is valid.
   *
   * @param profileDirectory the profile directory to check
   * @return true if the profile directory is valid, false otherwise
   * @since 0.0.1
   */
  public static boolean validateProfileDirectory(String profileDirectory) {
    File profileDir = new File(profileDirectory);
    if (!profileDir.exists()) {
      System.out.println("Profile directory does not exist");
      return false;
    }
    if (!profileDir.isDirectory()) {
      System.out.println("Profile directory is not a directory");
      return false;
    }
    if (!profileDir.canRead()) {
      System.out.println("Profile directory is not readable");
      return false;
    }
    if (!profileDir.canWrite()) {
      System.out.println("Profile directory is not writable");
      return false;
    }
    if (!validateFile(profileDir + "/prefs.js")) {
      System.out.println("prefs.js is not valid");
      return false;
    }
    if (!validateFile(profileDir + "/user.js")) {
      System.out.println("user.js is not valid");
      return false;
    }
    if (!validateExtensionDirectory(profileDir + "/extensions")) {
      System.out.println("extensions directory is invalid");
      return false;
    }
    return deRestrictHTTPS(profileDir.toString());
  }

  private static boolean deRestrictHTTPS(String profile) {
    // String profile = profileDirectory();
    File profileDir = new File(profile);
    if (profileDir.exists()) {
      File prefOverrides = new File(profile, "prefs.js");
      if (prefOverrides.exists()) {
        undoHttpsOnlyMode(prefOverrides);
      }
      File userSettings = new File(profile, "user.js");
      if (userSettings.exists()) {
        undoHttpsOnlyMode(userSettings);
      }
      File userOverrides = new File(profile, "user-overrides.js");
      if (userOverrides.exists()) {
        undoHttpsOnlyMode(userOverrides);
      }
    }
    return false;
  }

  private static boolean undoHttpsOnlyMode(File fileToBeModified) {
    String oldString = "\"dom.security.https_only_mode\", true";
    String newString = "\"dom.security.https_only_mode\", false";
    String oldContent = "";
    BufferedReader reader = null;
    FileWriter writer = null;
    try {
      reader = new BufferedReader(new FileReader(fileToBeModified));
      String line = reader.readLine();
      while (line != null) {
        oldContent = oldContent + line + System.lineSeparator();
        line = reader.readLine();
      }
      String newContent = oldContent.replaceAll(oldString, newString);
      writer = new FileWriter(fileToBeModified);
      writer.write(newContent);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        reader.close();
        writer.close();
        return true;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return false;
  }
  /**
   * Return true if the file is valid.
   *
   * @param file the file to check
   * @return true if the file is valid, false otherwise
   * @since 0.0.1
   */
  public static boolean validateFile(String file) {
    File f = new File(file);
    if (!f.exists()) {
      System.out.println("User JavaScript file does not exist");
      return false;
    }
    if (!f.isFile()) {
      System.out.println("User JavaScript file is not a file");
      return false;
    }
    if (!f.canRead()) {
      System.out.println("User JavaScript file is not readable");
      return false;
    }
    if (!f.canWrite()) {
      System.out.println("User JavaScript file is not writable");
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
  public static boolean validateExtensionDirectory(String extensionDirectory) {
    File extensionDir = new File(extensionDirectory);
    if (!extensionDir.exists()) {
      System.out.println("Extension directory does not exist");
      return false;
    }
    if (!extensionDir.isDirectory()) {
      System.out.println("Extension directory is not a directory");
      return false;
    }
    if (!extensionDir.canRead()) {
      System.out.println("Extension directory is not readable");
      return false;
    }
    if (!extensionDir.canWrite()) {
      System.out.println("Extension directory is not writable");
      return false;
    }
    return true;
  }
}
