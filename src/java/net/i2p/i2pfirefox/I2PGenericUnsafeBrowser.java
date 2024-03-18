package net.i2p.i2pfirefox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
 * I2PGenericUnsafeBrowser is a wrapper which sets common environment
 * variables for the process controlled by a processbuilder.
 *
 * ALWAYS ALWAYS ALWAYS try the Firefox and Chromium specific launchers
 * first.
 *
 * @author idk
 * @since 0.0.18
 */

public class I2PGenericUnsafeBrowser extends I2PCommonBrowser {
  private final int DEFAULT_TIMEOUT = 200;
  public String BROWSER = "";
  private Process p = null;
  // Ideally, EVERY browser in this list should honor http_proxy, https_proxy,
  // ftp_proxy and no_proxy. in practice, this is going to be hard to guarantee.
  // For now, we're just assuming. So don't use this until I understand the
  // situation better, unless you think you know better.
  private String[] browsers() {
    String genericPathsProp = getProperties().getProperty("generic.bins.unix");
    if (genericPathsProp != null)
      return genericPathsProp.split(",");
    return new String[] {
        // This debian script tries everything in $BROWSER, then
        // gnome-www-browser
        // and x-www-browser
        // if X is running and www-browser otherwise. Those point to the user's
        // preferred
        // browser using the update-alternatives system.
        "sensible-browser",
        // another one that opens a preferred browser
        "xdg-open",
        // Try x-www-browser directly
        "x-www-browser", "gnome-www-browser",
        // general graphical browsers that aren't Firefox or Chromium based
        "defaultbrowser", // puppy linux
        "dillo", "seamonkey", "konqueror", "galeon", "surf",
        // Text Mode Browsers only below here
        "www-browser", "links", "lynx"};
  }

  public void storeGenericDefaults() {
    List<String> list = new ArrayList<String>();

    list = Arrays.asList(browsers());
    getProperties().setProperty("generic.bins.unix",
                                list.stream().collect(Collectors.joining(",")));
    try (OutputStream fos = new FileOutputStream(
             new File(runtimeDirectory(""), "browser.config"))) {
      getProperties().store(fos, "Chromium Configuration Section");
    } catch (IOException ioe) {
      logger.warning(ioe.toString());
    }
  }

  /**
   * Obtains the default browser for the Windows platform, which by now should
   * be Edgium in the worst-case scenario but in case it isn't, we can use this
   * function to figure it out. It can find:
   *
   * 1. The current user's HTTPS default browser if they configured it to be
   * non-default
   * 2. The current user's HTTP default browser if they configured it to be
   * non-default
   * 3. Edgium if it's available
   * 4. iexplore if it's not
   *
   * and it will return the first one we find in exactly that order.
   *
   * Adapted from:
   * https://stackoverflow.com/questions/15852885/me...
   *
   * @param url containing full scheme, i.e. http://127.0.0.1:7657
   * @return path to command[0] and target URL[1] to the default browser ready
   *     for execution, or null if not found
   * @since 2.0.0
   */
  public String getDefaultWindowsBrowser() {
    String defaultBrowser;
    String key;
    // User-Configured HTTPS Browser
    key =
        "HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Windows\\Shell\\Associations\\URLAssociations\\https\\UserChoice";
    defaultBrowser = getDefaultOutOfRegistry(key);
    if (defaultBrowser != null)
      return defaultBrowser;
    // User-Configure HTTP Browser
    key =
        "HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Windows\\Shell\\Associations\\URLAssociations\\http\\UserChoice";
    defaultBrowser = getDefaultOutOfRegistry(key);
    if (defaultBrowser != null)
      return defaultBrowser;
    // MSEdge on pretty much everything after Windows 7
    key = "HKEY_CLASSES_ROOT\\MSEdgeHTM\\shell\\open\\command";
    defaultBrowser = getDefaultOutOfRegistry(key);
    if (defaultBrowser != null) {
      return defaultBrowser;
    }
    // iexplore usually, depends on the Windows, sometimes Edge
    key = "HKEY_CLASSES_ROOT\\http\\shell\\open\\command";
    defaultBrowser = getDefaultOutOfRegistry(key);
    if (defaultBrowser != null) {
      return defaultBrowser;
    }
    defaultBrowser = "C:\\Program Files\\Internet Explorer\\iexplore.exe";
    return defaultBrowser;
  }

  /**
   * obtains a value matching a key contained in the windows registry at a
   * path represented by hkeyquery
   *
   * @param hkeyquery registry entry to ask for.
   * @param key key to retrieve value from
   * @return either a registry "Default" value or null if one does not
   *     exist/is empty
   * @since 2.0.0
   */
  private String registryQuery(String hkeyquery, String key) {
    try {
      // Get registry where we find the default browser
      String[] cmd = {"REG", "QUERY", hkeyquery};
      Process process = Runtime.getRuntime().exec(cmd);
      Scanner kb = new Scanner(process.getInputStream());
      while (kb.hasNextLine()) {
        String line = kb.nextLine().trim();
        if (line.startsWith(key)) {
          String[] splitLine = line.split("  ");
          kb.close();
          String finalValue = splitLine[splitLine.length - 1].trim();
          if (!finalValue.equals("")) {
            return finalValue;
          }
        }
      }
      // Match wasn't found, still need to close Scanner
      kb.close();
    } catch (Exception e) {
      logger.warning(e.toString());
    }
    return null;
  }

  /**
   * If following a query back to the Default value doesn't work then what
   * we have is a "ProgID" which will be registered in
   * \HKEY_CLASSES_ROOT\%ProgId%, and will have an entry \shell\open\command,
   * where \shell\open\command yields the value that contains the command it
   * needs. This function takes a registry query in the same format as
   * getDefaultOutOfRegistry but instead of looking for the default entry
   *
   * @param hkeyquery
   * @return the command required to run the application referenced in
   *     hkeyquery, or null
   * @since 2.0.0
   */
  private String followUserConfiguredBrowserToCommand(String hkeyquery) {
    String progIdValue = registryQuery(hkeyquery, "ProgId");
    return followProgIdToCommand(progIdValue);
  }

  /**
   * Cross-references a progId obtained by
   * followUserConfiguredBrowserToCommand against
   * HKEY_CLASSES_ROOT\%ProgId%\shell\open\command, which holds the value of
   * the command which we need to run to launch the default browser.
   *
   * @param hkeyquery
   * @return the command required to run the application referenced in
   *     hkeyquery, or null
   * @since 2.0.0
   */
  private String followProgIdToCommand(String progid) {
    String hkeyquery =
        "HKEY_CLASSES_ROOT\\" + progid + "\\shell\\open\\command";
    String finalValue = registryQuery(hkeyquery, "(Default)");
    if (finalValue != null) {
      if (!finalValue.equals(""))
        return finalValue;
    }
    return null;
  }

  /**
   * obtains a default browsing command out of the Windows registry.
   *
   * @param hkeyquery registry entry to ask for.
   * @return either a registry "Default" value or null if one does not
   *     exist/is empty
   * @since 2.0.0
   */
  private String getDefaultOutOfRegistry(String hkeyquery) {
    String defaultValue = registryQuery(hkeyquery, "(Default)");
    if (defaultValue != null) {
      if (!defaultValue.equals(""))
        return defaultValue;
    } else {
      defaultValue = followUserConfiguredBrowserToCommand(hkeyquery);
      if (defaultValue != null) {
        if (!defaultValue.equals(""))
          return defaultValue;
      }
    }
    return null;
  }

  private String scanAPath(String dir) {
    for (String browser : browsers()) {
      File test = new File(dir, browser);
      if (test.exists()) {
        return test.getAbsolutePath();
      }
    }
    return "";
  }

  /**
   * Find any browser in our list within a UNIX path
   *
   * @return
   */
  public String getAnyUnixBrowser() {
    // read the PATH environment variable and split it by ":"
    String[] path = System.getenv("PATH").split(":");
    if (path != null && path.length > 0) {
      for (String p : path) {
        String f = scanAPath(p);
        if (f != "") {
          return f;
        }
      }
    }
    return "";
  }

  /**
   * Find any usable browser and output the whole path
   *
   * @return
   */
  public String findUnsafeBrowserAnywhere() {
    if (BROWSER != "") {
      File f = new File(BROWSER);
      if (f.exists())
        return f.getAbsolutePath();
    }

    if (getOperatingSystem() == "Windows") {
      return getDefaultWindowsBrowser();
    }
    return getAnyUnixBrowser();
  }

  //
  public ProcessBuilder baseProcessBuilder(String[] args) {

    String browser = findUnsafeBrowserAnywhere().split(" -")[0].trim();
    if (browser == null)
      System.exit(1);
    if (browser.contains("edge.exe") || browser.contains("msedge.exe")) {
      ArrayList<String> argsList = new ArrayList<String>(
          Arrays.asList("--user-data-dir=" +
                        profileDirectory("", "generic", "unsafe", false)));
      argsList.addAll(Arrays.asList(args));
      args = argsList.toArray(args);
    }
    if (!browser.isEmpty()) {
      int arglength = 0;
      if (args != null)
        arglength = args.length;
      String[] newArgs = new String[arglength + 1];
      newArgs[0] = browser;
      if (args != null && arglength > 0) {
        for (int i = 0; i < arglength; i++) {
          newArgs[i + 1] = args[i];
        }
      }
      ProcessBuilder pb =
          new ProcessBuilder(newArgs).directory(runtimeDirectory(true));
      pb.environment().put("http_proxy", "http://127.0.0.1:4444");
      pb.environment().put("https_proxy", "http://127.0.0.1:4444");
      pb.environment().put("ftp_proxy", "http://127.0.0.1:0");
      pb.environment().put("all_proxy", "http://127.0.0.1:4444");
      pb.environment().put("no_proxy", "http://127.0.0.1:7657");
      pb.environment().put("HTTP_PROXY", "http://127.0.0.1:4444");
      pb.environment().put("HTTPS_PROXY", "http://127.0.0.1:4444");
      pb.environment().put("FTP_PROXY", "http://127.0.0.1:0");
      pb.environment().put("ALL_PROXY", "http://127.0.0.1:4444");
      pb.environment().put("NO_PROXY", "http://127.0.0.1:7657");
      return pb;
    } else {
      logger.info("No Browser found.");
      return new ProcessBuilder(args);
    }
  }

  /**
   * delete the runtime directory
   *
   * @return true if successful, false if not
   */
  public boolean deleteRuntimeDirectory() {
    File rtd = runtimeDirectory(true);
    if (rtd.exists()) {
      rtd.delete();
      return true;
    }
    return false;
  }

  /**
   * get the runtime directory, creating it if create=true
   *
   * @param create if true, create the runtime directory if it does not exist
   * @return the runtime directory, or null if it could not be created
   * @since 0.0.18
   */
  public File runtimeDirectory(boolean create) {
    String rtd = runtimeDirectory();
    return runtimeDirectory(create, rtd);
  }

  /**
   * get the correct runtime directory
   *
   * @return the runtime directory, or null if it could not be created or
   *     found
   * @since 0.0.18
   */
  public String runtimeDirectory() {
    // get the I2P_BROWSER_DIR environment variable
    String rtd = System.getenv("I2P_BROWSER_DIR");
    // if it is not null and not empty
    if (rtd != null && !rtd.isEmpty()) {
      // check if the file exists
      File rtdFile = new File(rtd);
      if (rtdFile.exists()) {
        // if it does, return it
        return rtd;
      }
    }
    return runtimeDirectory("");
  }

  public Process launchAndDetatch(boolean privateWindow, String[] url) {
    validateUserDirectory();
    if (waitForProxy()) {
      ProcessBuilder pb;
      if (privateWindow) {
        pb = baseProcessBuilder(url);
      } else {
        pb = baseProcessBuilder(url);
      }
      try {
        logger.info(pb.command().toString());
        p = pb.start();
        logger.info("I2PBrowser");
        sleep(2000);
        return p;
      } catch (Throwable e) {
        logger.info(e.toString());
      }
    }
    return null;
  }

  public void launch(boolean privateWindow, String[] url) {
    if (waitForProxy()) {
      p = launchAndDetatch(privateWindow, url);
      try {
        logger.info("Waiting for I2PBrowser to close...");
        int exit = p.waitFor();
        if (privateWindow) {
          if (deleteRuntimeDirectory())
            logger.info(
                "Private browsing enforced, deleting runtime directory");
        }
        logger.info("I2PBrowser exited with value: " + exit);
      } catch (Exception e) {
        logger.info("Error: " + e.getMessage());
      }
    }
  }
  /**
   * Stop all running processes managed by the browser manager.
   *
   * @return true if successful, false if not
   */
  public boolean stop() {
    if (p != null) {
      p.destroy();
      return true;
    }
    return false;
  }

  public boolean running() {
    if (p != null)
      return p.isAlive();
    return false;
  }

  private String ValidURL(String inUrl) {
    String[] schemes = {"http", "https"};
    for (String scheme : schemes) {
      if (inUrl.startsWith(scheme)) {
        logger.info("Valid URL: " + inUrl);
        return inUrl;
      }
    }
    return "";
  }

  public static void main(String[] args) {
    I2PGenericUnsafeBrowser i2pBrowser = new I2PGenericUnsafeBrowser();
    i2pBrowser.validateUserDirectory();
    boolean privateBrowsing = false;
    i2pBrowser.logger.info("checking for private browsing");
    ArrayList<String> visitURL = new ArrayList<String>();
    if (args != null) {
      if (args.length > 0) {
        for (String arg : args) {
          if (arg.equals("-private")) {
            privateBrowsing = true;
            i2pBrowser.logger.info(
                "private browsing is true, profile will be discarded at end of session");
          }
          if (!arg.startsWith("-")) {
            // check if it's a URL
            visitURL.add(i2pBrowser.ValidURL(arg));
          }
        }
      }
    }
    i2pBrowser.logger.info("I2PGenericUnsafeBrowser");
    i2pBrowser.launch(privateBrowsing,
                      visitURL.toArray(new String[visitURL.size()]));
  }
}