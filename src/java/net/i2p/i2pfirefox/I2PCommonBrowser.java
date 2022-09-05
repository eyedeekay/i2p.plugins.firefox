package net.i2p.i2pfirefox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
  static Logger logger = Logger.getLogger("browserlauncher");
  static FileHandler fh;

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
  }

  public static void println(String line) { logger.info(line); }

  private static File logFile() {
    File log = new File("logs");
    if (!log.exists())
      log.mkdirs();
    return new File(log, "browserlauncher.log");
  }

  /**
   * get the runtime directory, creating it if create=true
   *
   * @param create if true, create the runtime directory if it does not exist
   * @return the runtime directory, or null if it could not be created
   * @since 0.0.19
   */
  protected static File runtimeDirectory(boolean create, String override) {
    String rtd = runtimeDirectory(override);
    File rtdFile = new File(rtd);
    if (create) {
      if (!rtdFile.exists()) {
        rtdFile.mkdir();
      }
    }
    return new File(rtd);
  }

  /**
   * get the correct runtime directory
   *
   * @return the runtime directory, or null if it could not be created or found
   * @since 0.0.19
   */
  protected static String runtimeDirectory(String override) {
    // get the I2P_BROWSER_DIR environment variable
    String rtd = System.getenv(override);
    // if it is not null and not empty
    if (rtd != null && !rtd.isEmpty()) {
      // check if the file exists
      File rtdFile = new File(rtd);
      if (rtdFile.exists()) {
        // if it does, return it
        return rtd;
      }
    }
    // obtain the PLUGIN environment variable
    String plugin = System.getenv("PLUGIN");
    if (plugin != null && !plugin.isEmpty()) {
      File pluginDir = new File(plugin);
      if (pluginDir.exists()) {
        return pluginDir.toString();
      }
    }
    String userDir = System.getProperty("user.dir");
    if (userDir != null && !userDir.isEmpty()) {
      File userDir1 = new File(userDir);
      if (userDir1.exists()) {
        return userDir1.toString();
      }
    }
    String homeDir = System.getProperty("user.home");
    if (homeDir != null && !homeDir.isEmpty()) {
      File homeDir1 = new File(homeDir + "/.i2p");
      if (homeDir1.exists()) {
        return homeDir.toString();
      }
      File homeDir2 = new File(homeDir + "/i2p");
      if (homeDir2.exists()) {
        return homeDir2.toString();
      }
    }
    return "";
  }

  /**
   * get the profile directory, creating it if necessary
   *
   * @return the profile directory, or null if it could not be created
   * @since 0.0.19
   */
  protected static String profileDirectory(String envVar, String browser) {
    String pd = System.getenv(envVar);
    if (pd != null && !pd.isEmpty()) {
      File pdf = new File(pd);
      if (pdf.exists() && pdf.isDirectory()) {
        return pd;
      }
    }
    String rtd = runtimeDirectory("");
    return profileDir(rtd, browser);
  }

  protected static String profileDir(String file, String browser) {
    File profileDir = new File(file, "i2p." + browser + ".profile");
    return profileDir.getAbsolutePath();
  }

  protected boolean unpackProfile(String profileDirectory, String browser,
                                  String base) {
    println("Unpacking base profile to " + profileDirectory);
    try {
      final InputStream resources =
          this.getClass().getClassLoader().getResourceAsStream(
              "i2p." + browser + "." + base + ".profile.zip");
      if (resources == null) {
        println("Could not find resources");
        return false;
      }
      println(resources.toString());
      // InputStream corresponds to a zip file. Unzip it.
      // Files.copy(r, new File(profileDirectory).toPath(),
      // StandardCopyOption.REPLACE_EXISTING);
      ZipInputStream zis = new ZipInputStream(resources);
      ZipEntry entry;
      // while there are entries I process them
      while ((entry = zis.getNextEntry()) != null) {
        println("entry: " + entry.getName() + ", " + entry.getSize());
        // consume all the data from this entry
        if (entry.isDirectory()) {
          println("Creating directory: " + entry.getName());
          File dir = new File(profileDirectory + "/" + entry.getName());
          dir.mkdirs();
        } else {
          println("Creating file: " + entry.getName());
          File file = new File(profileDirectory + "/" + entry.getName());
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
      println("Error copying profile files: " + e.getMessage());
      return false;
    }
    return true;
  }

  protected static void copyDirectory(File sourceDirectory,
                                      File destinationDirectory, String browser,
                                      String base) throws IOException {
    destinationDirectory = new File(destinationDirectory.toString().replace(
        "i2p." + browser + "." + base + ".profile", ""));
    if (!destinationDirectory.exists()) {
      destinationDirectory.mkdir();
    }
    for (String f : sourceDirectory.list()) {
      copyDirectoryCompatibityMode(new File(sourceDirectory, f),
                                   new File(destinationDirectory, f), browser,
                                   base);
    }
  }

  private static void copyDirectoryCompatibityMode(File source,
                                                   File destination,
                                                   String browser, String base)
      throws IOException {
    if (source.isDirectory()) {
      copyDirectory(source, destination, browser, base);
    } else {
      copyFile(source, destination);
    }
  }

  private static void copyFile(File sourceFile, File destinationFile)
      throws IOException {
    try (InputStream in = new FileInputStream(sourceFile);
         OutputStream out = new FileOutputStream(destinationFile)) {
      byte[] buf = new byte[1024];
      int length;
      while ((length = in.read(buf)) > 0) {
        out.write(buf, 0, length);
      }
    }
  }

  public static boolean validateProfileFirstRun(String profileDirectory) {
    File profileDir = new File(profileDirectory);
    if (!profileDir.exists()) {
      println("Profile directory does not exist");
      return false;
    }
    if (!profileDir.isDirectory()) {
      println("Profile directory is not a directory");
      return false;
    }
    File frf = new File(profileDir, "first-run");
    if (frf.exists()) {
      frf.delete();
      // is a first run
      return true;
    }
    return false;
  }
}
