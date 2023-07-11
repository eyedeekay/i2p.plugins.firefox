package net.i2p.i2pfirefox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * I2PCommonBrowser.java
 * Copyright (C) 2022 idk <hankhill19580@gmail.com>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License. See LICENSE.md for details.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 *
 * @author idk
 * @since 0.0.19
 */

public class I2PCommonBrowser {
  public Properties prop = new Properties();
  public Logger logger = Logger.getLogger("browserlauncher");
  private FileHandler fh;
  static int CONFIGURED_TIMEOUT = 200;

  public I2PCommonBrowser() {
    try {
      // This block configure the logger with handler and formatter
      fh = new FileHandler(logFile().toString());
      logger.addHandler(fh);
      SimpleFormatter formatter = new SimpleFormatter();
      fh.setFormatter(formatter);
      // the following statement is used to log any messages
      logger.info("Browser log");
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    loadPropertiesFile(new File(runtimeDirectory(""), "browser.config"));
  }

  public void loadPropertiesFile(File propertiesFile) {
    try (InputStream inputStream = new FileInputStream(propertiesFile)) {
      properties = new Properties();
      properties.load(inputStream);
    } catch (IOException exception) {
      logger.warning(exception.toString());
    }
  }

  public void validateUserDirectory() {
    logger.info("Validating user directory");
    String userDir = System.getProperty("user.dir");
    String userHome = System.getProperty("user.home");
    File userDirFile = new File(userDir);
    File userHomeFile = new File(userHome);

    logger.info("user.dir: " + userDirFile.getAbsolutePath());
    logger.info("user.home: " + userHomeFile.getAbsolutePath());

    if (!userDirFile.getAbsolutePath().contains("Program Files")) {
      if (!userDirFile.getAbsolutePath().equals(userHomeFile.getAbsolutePath())) {
        logger.info("user.dir is not inconvenient");
        if (userDirFile.exists()) {
          logger.info("user.dir exists");
          if (userDirFile.isDirectory()) {
            logger.info("user.dir is a directory");
            if (userDirFile.canWrite()) {
              logger.info("user.dir is writable");
              return;
            } else {
              logger.info("user.dir is not writable");
            }
          } else {
            logger.info("user.dir is not actually a directory");
          }
        } else {
          logger.info("user.dir does not exist");
        }
      } else {
        logger.info("user.dir should not be the same as user.home");
      }
    } else {
      logger.info("user.dir cannot run from inside Program Files");
    }
    if (isWindows()) {
      userHome = new File(userHome, "AppData/Local/I2P").getAbsolutePath();
    }
    File defaultPathFile = new File(userHome, "i2p/i2pbrowser");
    if (!defaultPathFile.exists()) {
      defaultPathFile.mkdirs();
    }
    if (!defaultPathFile.isDirectory()) {
      logger.info("default path exists and is not a directory, get it out of the way");
      logger.info(defaultPathFile.getAbsolutePath());
    }
    System.setProperty("user.dir", defaultPathFile.getAbsolutePath());
  }

  public String getOperatingSystem() {
    String os = System.getProperty("os.name");
    if (isWindows()) {
      return "Windows";
    } else if (isLinux()) {
      return "Linux";
    } else if (isBSD()) {
      return "BSD";
    } else if (isOSX()) {
      return "Mac";
    }
    return "Unknown";
  }

  protected boolean isWindows() {
    String osName = System.getProperty("os.name").toLowerCase();
    return osName.contains("windows");
  }

  protected boolean isOSX() {
    String osName = System.getProperty("os.name").toLowerCase();
    return osName.contains("osx") || osName.contains("mac") || osName.contains("apple") || osName.contains("darwin");
  }

  protected boolean isLinux() {
    String osName = System.getProperty("os.name").toLowerCase();
    return osName.contains("linux");
  }

  protected boolean isBSD() {
    String osName = System.getProperty("os.name").toLowerCase();
    return osName.contains("bsd");
  }

  // public void logger.info(String line) { logger.info(line); }

  private File logFile() {
    validateUserDirectory();
    String userDirectory = System.getProperty("user.dir");
    File logDirectory = new File(userDirectory, "logs");
    if (!logDirectory.exists()) {
      logDirectory.mkdirs();
    }
    return new File(logDirectory, "browserlauncher.log");
  }

  /**
   * Get the runtime directory, creating it if create=true.
   *
   * @param create   If true, create the runtime directory if it does not exist.
   * @param override The runtime directory override.
   * @return The runtime directory, or null if it could not be created.
   * @since 0.0.19
   */
  protected File runtimeDirectory(boolean create, String override) {
    String runtimeDir = runtimeDirectory(override);
    File runtimeDirFile = new File(runtimeDir);
    if (create && !runtimeDirFile.exists()) {
      runtimeDirFile.mkdir();
    }
    return runtimeDirFile;
  }

  protected String runtimeDirectory(String override) {
    String runtimeDir = System.getenv(override);
    if (isDirectoryValid(runtimeDir)) {
      return runtimeDir;
    }

    String pluginDir = System.getenv("PLUGIN");
    if (isDirectoryValid(pluginDir)) {
      return pluginDir;
    }

    String userDir = System.getProperty("user.dir");
    if (isDirectoryValid(userDir)) {
      return userDir;
    }

    String homeDir = System.getProperty("user.home");
    if (isDirectoryValid(homeDir)) {
      String i2pDir = homeDir + "/.i2p";
      if (isDirectoryValid(i2pDir)) {
        return homeDir;
      }

      String altI2pDir = homeDir + "/i2p";
      if (isDirectoryValid(altI2pDir)) {
        return altI2pDir;
      }
    }

    return "";
  }

  private boolean isDirectoryValid(String directory) {
    return directory != null && !directory.isEmpty() && new File(directory).exists();
  }

  /**
   * Retrieves the profile directory, creating it if necessary.
   *
   * @param envVar  the environment variable name
   * @param browser the browser name
   * @param base    the base directory
   * @param app     indicates if it is an app directory
   * @return the profile directory, or null if it could not be created
   * @since 0.0.19
   */
  protected String profileDirectory(String envVar, String browser, String base, boolean app) {
    String profileDir = System.getenv(envVar);
    if (profileDir != null && !profileDir.isEmpty()) {
      File profileDirFile = new File(profileDir);
      if (profileDirFile.exists() && profileDirFile.isDirectory()) {
        return profileDir;
      }
    }
    String runtimeDir = runtimeDirectory("");
    return profileDir(runtimeDir, browser, base, app);
  }

  protected String profileDir(String file, String browser, String base, boolean app) {
    String appString = app ? ".app" : "";
    String profileDirName = String.format("i2p.%s.profile.%s%s", browser, base, appString);
    File profileDir = new File(file, profileDirName);
    return profileDir.getAbsolutePath();
  }

  protected boolean unpackProfile(String profileDirectory, String browser,
      String base) {
    logger.info("Unpacking base profile to " + profileDirectory);
    try {
      final InputStream resources = this.getClass().getClassLoader().getResourceAsStream(
          "i2p." + browser + "." + base + ".profile.zip");
      if (resources == null) {
        logger.info("Could not find resources");
        return false;
      }
      logger.info(resources.toString());
      // InputStream corresponds to a zip file. Unzip it.
      // Files.copy(r, new File(profileDirectory).toPath(),
      // StandardCopyOption.REPLACE_EXISTING);
      ZipInputStream zis = new ZipInputStream(resources);
      ZipEntry entry;
      // while there are entries I process them
      while ((entry = zis.getNextEntry()) != null) {
        logger.info("entry: " + entry.getName() + ", " + entry.getSize());
        // consume all the data from this entry
        if (entry.isDirectory()) {
          logger.info("Creating directory: " + entry.getName());
          File dir = new File(profileDirectory, entry.getName());
          dir.mkdirs();
        } else {
          logger.info("Creating file: " + entry.getName());
          File file = new File(profileDirectory, entry.getName());
          file.createNewFile();
          Files.copy(zis, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        while (zis.available() > 0)
          zis.read();
        // I could close the entry, but getNextEntry does it automatically
        // zis.closeEntry()
      }
      // loop through the Enumeration

    } catch (Exception e) {
      logger.info("Error copying profile files: " + e.getMessage());
      return false;
    }
    return true;
  }

  protected void copyDirectory(File sourceDir, File destDir, String browser, String base) throws IOException {
    destDir = new File(destDir.toString().replace("i2p." + browser + "." + base + ".profile", ""));
    if (!destDir.exists()) {
      destDir.mkdir();
    }
    for (String file : sourceDir.list()) {
      copyDirectoryCompatibilityMode(new File(sourceDir, file), new File(destDir, file), browser, base);
    }
  }

  private void copyDirectoryCompatibilityMode(File sourceDirectory, File destinationDirectory, String browser,
      String base) throws IOException {
    if (sourceDirectory.isDirectory()) {
      copyDirectory(sourceDirectory, destinationDirectory, browser, base);
    } else {
      copyFile(sourceDirectory, destinationDirectory);
    }
  }

  public void copy(InputStream source, OutputStream target) throws IOException {
    byte[] buffer = new byte[8192];
    int bytesRead;
    while ((bytesRead = source.read(buffer)) != -1) {
      target.write(buffer, 0, bytesRead);
    }
  }

  private void copyFile(File sourceFile, File destinationFile) throws IOException {
    try (InputStream in = new FileInputStream(sourceFile);
        OutputStream out = new FileOutputStream(destinationFile)) {
      byte[] buffer = new byte[1024];
      int length;
      while ((length = in.read(buffer)) > 0) {
        out.write(buffer, 0, length);
      }
    }
  }

  public boolean validateProfileFirstRun(String profileDirectory) {
    File profileDir = new File(profileDirectory);
    if (!profileDir.exists()) {
      return false;
    }
    if (!profileDir.isDirectory()) {
      return false;
    }
    File firstRunFile = new File(profileDir, "first-run");
    if (firstRunFile.exists()) {
      firstRunFile.delete();
      return true;
    }
    return false;
  }

  /**
   * Waits for an HTTP proxy on port 4444 to be ready.
   * Returns false on timeout of 200 seconds.
   *
   * @return true if the proxy is ready, false if it is not.
   * @since 0.0.1
   */
  public boolean waitForProxy() {
    return waitForProxy(CONFIGURED_TIMEOUT);
  }

  /**
   * Waits for an HTTP proxy on port 4444 to be ready.
   * Returns false on timeout of the specified number of seconds.
   *
   * @param timeout the number of seconds to wait for the proxy to be ready.
   * @return true if the proxy is ready, false if it is not.
   * @since 0.0.1
   */
  public boolean waitForProxy(int timeout) {
    return waitForProxy(timeout, 4444);
  }

  /**
   * Waits for an HTTP proxy on the specified port to be ready.
   * Returns false on timeout of the specified number of seconds.
   *
   * @param timeout the number of seconds to wait for the proxy to be ready.
   * @param port    the port to wait for the proxy to be ready on.
   * @return true if the proxy is ready, false if it is not.
   * @since 0.0.1
   */
  public boolean waitForProxy(int timeout, int port) {
    return waitForProxy(timeout, port, "localhost");
  }

  /**
   * Waits for an HTTP proxy on the specified port to be ready.
   * Returns false on timeout of the specified number of seconds.
   * If the timeout is zero or less, the check is disabled and always
   * returns true.
   *
   * @param timeout the number of seconds to wait for the proxy to be ready.
   * @param port    the port to wait for the proxy to be ready on.
   * @param host    the host to wait for the proxy to be ready on.
   * @return true if the proxy is ready, false if it is not.
   * @since 0.0.1
   */
  public boolean waitForProxy(int timeout, int port, String host) {
    if (timeout <= 0) {
      return true;
    }
    for (int i = 0; i < timeout; i++) {
      if (isPortOccupied(port, host)) {
        return true;
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    return false;
  }

  public boolean isPortOccupied(int port, String host) {
    try {
      new Socket(host, port).close();
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  /**
   * Alters the proxy timeout to customized value time, in seconds.
   * May be zero.
   *
   * @param time
   */
  public void setProxyTimeoutTime(int time) {
    CONFIGURED_TIMEOUT = time;
  }

  protected String join(String[] arr) {
    StringBuilder result = new StringBuilder();
    for (String item : arr) {
      result.append(" \"").append(item).append("\"");
    }
    return result.toString();
  }

  public void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public File searchFile(File directory, String search) {
    if (directory.isDirectory()) {
      File[] files = directory.listFiles();
      for (File file : files) {
        File foundFile = searchFile(file, search);
        if (foundFile != null)
          return foundFile;
      }
    } else {
      if (directory.getName().equals(search)) {
        return directory;
      }
    }
    return null;
  }
}
