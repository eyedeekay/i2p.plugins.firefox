package net.i2p.i2pfirefox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
public class I2PFirefoxProfileChecker extends I2PCommonBrowser {

  /**
   * @param args unused
   */
  public static void main(String[] args) {
    String profileDirectory = I2PFirefoxProfileBuilder.profileDirectory();
    if (profileDirectory == null) {
      println("No profile directory found");
      return;
    }
    println("Profile directory: " + profileDirectory);
    boolean ok = validateProfileDirectory(profileDirectory);
    if (ok) {
      println("Profile directory is valid");
    } else {
      println("Profile directory is invalid");
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
      println("Profile directory does not exist");
      return false;
    }
    if (!profileDir.isDirectory()) {
      println("Profile directory is not a directory");
      return false;
    }
    if (!profileDir.canRead()) {
      println("Profile directory is not readable");
      return false;
    }
    if (!profileDir.canWrite()) {
      println("Profile directory is not writable");
      return false;
    }
    if (!validateFile(profileDir + "/prefs.js")) {
      println("prefs.js is not valid");
      return false;
    }
    if (!validateFile(profileDir + "/user.js")) {
      println("user.js is not valid");
      return false;
    }
    if (!validateExtensionDirectory(profileDir + "/extensions")) {
      println("extensions directory is invalid");
      return false;
    }
    return deRestrictHTTPSAndSetupHomepage(profileDir.toString());
  }

  private static boolean deRestrictHTTPSAndSetupHomepage(String profile) {
    // String profile = profileDirectory();
    File profileDir = new File(profile);
    if (profileDir.exists()) {
      File prefOverrides = new File(profile, "prefs.js");
      if (prefOverrides.exists()) {
        undoHttpsOnlyMode(prefOverrides);
        undoHomepage(prefOverrides);
      }
      File userSettings = new File(profile, "user.js");
      if (userSettings.exists()) {
        undoHttpsOnlyMode(userSettings);
        undoHomepage(userSettings);
      }
      File userOverrides = new File(profile, "user-overrides.js");
      if (userOverrides.exists()) {
        undoHttpsOnlyMode(userOverrides);
        undoHomepage(userOverrides);
      }
    }
    return false;
  }

  private static boolean undoHttpsOnlyMode(File fileToBeModified) {
    String oldString = "\"dom.security.https_only_mode\", true";
    String newString = "\"dom.security.https_only_mode\", false";
    return undoValue(oldString, newString, fileToBeModified);
  }

  private static boolean undoHomepage(File fileToBeModified) {
    String oldString = "\"browser.startup.homepage\", true";
    File file = new File("Student.txt");
    String newString = "\"browser.startup.homepage\", \"http://127.0.0.1:7657\"";
    try {
      Scanner scanner = new Scanner(file);
      // now read the file line by line...
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if(line.contains("browser.startup.homepage")) {
          oldString = line.toString();
          return undoValue(oldString, newString, fileToBeModified);
        }
      }
    } catch (FileNotFoundException e) {
      // handle this
    }
    return true;
  }

  private static boolean undoValue(String oldString, String newString,
                                   File fileToBeModified) {
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
      println("User JavaScript file does not exist");
      return false;
    }
    if (!f.isFile()) {
      println("User JavaScript file is not a file");
      return false;
    }
    if (!f.canRead()) {
      println("User JavaScript file is not readable");
      return false;
    }
    if (!f.canWrite()) {
      println("User JavaScript file is not writable");
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
      println("Extension directory does not exist");
      return false;
    }
    if (!extensionDir.isDirectory()) {
      println("Extension directory is not a directory");
      return false;
    }
    if (!extensionDir.canRead()) {
      println("Extension directory is not readable");
      return false;
    }
    if (!extensionDir.canWrite()) {
      println("Extension directory is not writable");
      return false;
    }
    return true;
  }
}
