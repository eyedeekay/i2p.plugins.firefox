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
   * The main method for executing the Java program.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    I2PFirefoxProfileChecker profileChecker = new I2PFirefoxProfileChecker();
    String profileDirectory = profileChecker.profileDirectory(false, "base");
    if (profileDirectory == null) {
      profileChecker.logger.info("No profile directory found");
      return;
    }
    profileChecker.logger.info("Profile directory: " + profileDirectory);
    boolean isProfileValid = profileChecker.validateProfileDirectory(profileDirectory);
    if (isProfileValid) {
      profileChecker.logger.info("Profile directory is valid");
    } else {
      profileChecker.logger.info("Profile directory is invalid");
    }
  }

  /**
   * get the profile directory, creating it if necessary
   *
   * @return the profile directory, or null if it could not be created
   */
  public String profileDirectory(boolean app, String base) {
    return profileDirectory("I2P_FIREFOX_PROFILE", "firefox", base, app);
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
    if (!profileDir.exists() || !profileDir.isDirectory() || !profileDir.canRead() || !profileDir.canWrite()) {
      return false;
    }
    if (!validateFile(profileDir + "/prefs.js")) {
      return false;
    }
    if (!validateFile(profileDir + "/user.js")) {
      return false;
    }
    if (!validateExtensionDirectory(profileDir + "/extensions")) {
      return false;
    }
    return deRestrictHTTPSAndSetupHomepage(profileDir.toString());
  }

  /**
   * Verify essential values in prefs.js, user.js, and user-overrides.js
   *
   * @param profile profile
   * @return true if successful
   */
  private boolean deRestrictHTTPSAndSetupHomepage(String profile) {
    File profileDir = new File(profile);
    if (profileDir.exists()) {
      cleanUpFile(new File(profile, "prefs.js"));
      cleanUpFile(new File(profile, "user.js"));
      cleanUpFile(new File(profile, "user-overrides.js"));
      return true;
    }
    return false;
  }

  /**
   * Cleans up a file by undoing specific modifications if the file exists.
   *
   * @param file the file to be cleaned up
   */
  private void cleanUpFile(File file) {
    if (file.exists()) {
      undoHttpsOnlyMode(file);
      undoHomepage(file);
    }
  }

  /**
   * Undo the HTTPS-only mode by modifying a file.
   *
   * @param fileToBeModified the file to be modified
   * @return true if the undo operation is successful, false otherwise
   */
  private boolean undoHttpsOnlyMode(File fileToBeModified) {
    String oldString = "\"dom.security.https_only_mode\", true";
    String newString = "\"dom.security.https_only_mode\", false";
    return undoValue(oldString, newString, fileToBeModified);
  }

  /**
   * Undo the modification of the homepage in a file.
   *
   * @param fileToBeModified the file to be modified
   * @return true if the modification was successful, otherwise false
   */
  private boolean undoHomepage(File fileToBeModified) {
    String oldStringToFind = "\"browser.startup.homepage\", true";
    String newStringToReplace = "\"browser.startup.homepage\", \"http://127.0.0.1:7657\"";
    try (Scanner scanner = new Scanner(fileToBeModified)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        if (line.contains(oldStringToFind)) {
          return undoValue(line, newStringToReplace, fileToBeModified);
        }
      }
    } catch (FileNotFoundException e) {
      // handle this
    }
    return true;
  }

  /**
   * Undo the value by replacing the occurrences of the old string with the new
   * string in the given file.
   *
   * @param oldString        the string to be replaced
   * @param newString        the string to replace the old string with
   * @param fileToBeModified the file to be modified
   * @return true if the value was successfully undone, false otherwise
   */
  public boolean undoValue(String oldString, String newString,
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
   * @param filePath the path of the file to check
   * @return true if the file is valid, false otherwise
   * @since 0.0.1
   */
  public boolean validateFile(String filePath) {
    File file = new File(filePath);
    if (!file.exists() || !file.isFile() || !file.canRead() || !file.canWrite()) {
      return false;
    }
    return true;
  }

  /**
   * Validates the extension directory.
   *
   * @param extensionDirectory the extension directory to validate
   * @return true if the extension directory is valid, false otherwise
   * @since 0.0.1
   */
  public boolean validateExtensionDirectory(String extensionDirectory) {
    File extensionDir = new File(extensionDirectory);

    if (!extensionDir.exists() || !extensionDir.isDirectory() ||
        !extensionDir.canRead() || !extensionDir.canWrite()) {
      return false;
    }

    return true;
  }
}
