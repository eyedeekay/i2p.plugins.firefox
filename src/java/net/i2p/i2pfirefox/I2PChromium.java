package net.i2p.i2pfirefox;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * I2PChromium.java
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
 * @since 0.0.1
 */
public class I2PChromium extends I2PChromiumProfileUnpacker {
  private final String[] CHROMIUM_SEARCH_PATHS = CHROMIUM_FINDER();
  private Process p = null;

  /**
   * Construct an I2PChromium class which manages an instance of Chromium and
   * an accompanying Chromium profile. This version includes Chromium variants
   * and forks.
   *
   * @since 0.0.1
   */
  public I2PChromium() {
    for (String path : CHROMIUM_SEARCH_PATHS) {
      File f = new File(path);
      if (f.exists()) {
        logger.info("Found Chromium at " + path);
        return;
      }
    }
  }
  public I2PChromium(boolean usability) {
    for (String path : CHROMIUM_SEARCH_PATHS) {
      File f = new File(path);
      if (f.exists()) {
        logger.info("Found Chromium at " + path);
        return;
      }
    }
    this.usability = true;
  }

  public void storeChromiumDefaults() {
    List<String> list = new ArrayList<String>();
    list = Arrays.asList(chromiumPathsWindows());
    getProperties().setProperty("chromium.paths.windows",
                                list.stream().collect(Collectors.joining(",")));
    list = Arrays.asList(chromiumPathsUnix());
    getProperties().setProperty("chromium.paths.linux",
                                list.stream().collect(Collectors.joining(",")));
    list = Arrays.asList(chromiumPathsOSX());
    getProperties().setProperty("chromium.paths.osx",
                                list.stream().collect(Collectors.joining(",")));

    list = Arrays.asList(chromiumBinsWindows());
    getProperties().setProperty("chromium.bins.windows",
                                list.stream().collect(Collectors.joining(",")));
    list = Arrays.asList(chromiumBinsUnix());
    getProperties().setProperty("chromium.bins.linux",
                                list.stream().collect(Collectors.joining(",")));
    list = Arrays.asList(chromiumBinsUnix());
    getProperties().setProperty("chromium.bins.osx",
                                list.stream().collect(Collectors.joining(",")));
    try (OutputStream fos = new FileOutputStream(
             new File(runtimeDirectory(""), "browser.config"))) {
      getProperties().store(fos, "Chromium Configuration Section");
    } catch (IOException ioe) {
      logger.warning(ioe.toString());
    }
  }

  private String[] chromiumPathsUnix() {
    String chromiumPathsProp =
        getProperties().getProperty("chromium.paths.unix");
    if (chromiumPathsProp != null)
      if (!chromiumPathsProp.equals(""))
        return chromiumPathsProp.split(",");
    return new String[] {"/usr/bin", "/usr/local/bin", "/opt/chromium/bin",
                         "/snap/bin"};
  }

  private String[] chromiumBinsUnix() {
    String chromiumPathsProp;
    if (isOSX()) {
      chromiumPathsProp = getProperties().getProperty("chromium.bins.osx");
      if (chromiumPathsProp != null)
        if (!chromiumPathsProp.equals(""))
          return chromiumPathsProp.split(",");
    }
    chromiumPathsProp = getProperties().getProperty("chromium.bins.unix");
    if (chromiumPathsProp != null)
      if (!chromiumPathsProp.equals(""))
        return chromiumPathsProp.split(",");
    return new String[] {
        "ungoogled-chromium", "chromium", "brave", "edge", "msedge", "chrome"};
  }

  private String[] FIND_CHROMIUM_SEARCH_PATHS_UNIX() {
    String[] path = chromiumPathsUnix();
    String[] exes = chromiumBinsUnix();

    String[] exePath = new String[path.length * exes.length];
    int i = 0;
    for (String s : path) {
      for (String exe : exes) {
        exePath[i] = s + "/" + exe;
        i++;
      }
    }
    return exePath;
  }
  private String[] chromiumPathsOSX() {
    String chromiumPathsProp =
        getProperties().getProperty("chromium.paths.osx");
    if (chromiumPathsProp != null)
      if (!chromiumPathsProp.equals(""))
        return chromiumPathsProp.split(",");
    return new String[] {"/Applications/Chromium.app/Contents/MacOS",
                         "/Applications/Chrome.app/Contents/MacOS",
                         "/Applications/Brave.app/Contents/MacOS"};
  }
  private String[] FIND_CHROMIUM_SEARCH_PATHS_OSX() {
    String[] path = chromiumPathsOSX();
    String[] exes = chromiumBinsUnix();
    String[] exePath = new String[path.length * exes.length];
    int i = 0;
    for (String s : path) {
      for (String exe : exes) {
        exePath[i] = s + "/" + exe;
        i++;
      }
    }
    return exePath;
  }

  private String[] chromiumPathsWindows() {
    String chromiumPathsProp =
        getProperties().getProperty("chromium.paths.windows");
    if (chromiumPathsProp != null)
      if (!chromiumPathsProp.equals(""))
        return chromiumPathsProp.split(",");
    String programFiles = System.getenv("ProgramFiles");
    String localAppData = System.getenv("LOCALAPPDATA");
    String programFiles86 = System.getenv("ProgramFiles(x86)");

    if (programFiles == null)
      programFiles = "C:/Program Files/";
    if (programFiles86 == null)
      programFiles86 = "C:/Program Files (x86)/";
    if (!isWindows())
      localAppData = "C:/Users/user/AppData/Local/";

    return new String[] {
        new File(localAppData, "/Google/Chrome/Application/").toString(),
        new File(programFiles, "/Google/Chrome/Application/").toString(),
        new File(programFiles86, "/Google/Chrome/Application/").toString(),
        new File(localAppData, "/Chromium/Application/").toString(),
        new File(programFiles, "/Chromium/Application/").toString(),
        new File(programFiles86, "/Chromium/Application/").toString(),
        new File(localAppData, "/BraveSoftware/Brave Browser/Application/")
            .toString(),
        new File(programFiles, "/BraveSoftware/Brave Browser/Application/")
            .toString(),
        new File(programFiles86, "/BraveSoftware/Brave Browser/Application/")
            .toString(),
        new File(programFiles86, "/Microsoft/Edge/Application/").toString(),
        new File(programFiles, "/Microsoft/Edge/Application/").toString(),
    };
  }
  private String[] chromiumBinsWindows() {
    String chromiumPathsProp =
        getProperties().getProperty("chromium.bins.windows");
    if (chromiumPathsProp != null)
      if (!chromiumPathsProp.equals(""))
        return chromiumPathsProp.split(",");
    return new String[] {"ungoogled-chromium.exe",
                         "chromium.exe",
                         "brave.exe",
                         "edge.exe",
                         "msedge.exe",
                         "chrome.exe"};
  }
  private String[] FIND_CHROMIUM_SEARCH_PATHS_WINDOWS() {
    String[] path = chromiumPathsWindows();
    String[] exes = chromiumBinsWindows();
    String[] exePath = new String[path.length * exes.length];
    int i = 0;
    for (String s : path) {
      for (String exe : exes) {
        exePath[i] = s + "\\" + exe;
        i++;
      }
    }
    return exePath;
  }

  private String[] FIND_ALL_CHROMIUM_SEARCH_PATHS() {
    String[] Unix = FIND_CHROMIUM_SEARCH_PATHS_UNIX();
    String[] Windows = FIND_CHROMIUM_SEARCH_PATHS_WINDOWS();
    String[] Mac = FIND_CHROMIUM_SEARCH_PATHS_OSX();
    String[] exePath = new String[Unix.length + Windows.length + Mac.length];
    int i = 0;
    for (String s : Unix) {
      exePath[i] = s;
      i++;
    }
    for (String s : Windows) {
      exePath[i] = s;
      i++;
    }
    for (String s : Mac) {
      exePath[i] = s;
      i++;
    }
    return exePath;
  }
  private String[] FIND_CHROMIUM_SEARCH_PATHS() {
    switch (getOperatingSystem()) {
    case "Windows":
      return FIND_CHROMIUM_SEARCH_PATHS_WINDOWS();
    case "Linux":
      return FIND_CHROMIUM_SEARCH_PATHS_UNIX();
    case "Mac":
      return FIND_CHROMIUM_SEARCH_PATHS_OSX();
    case "BSD":
      return FIND_CHROMIUM_SEARCH_PATHS_UNIX();
    default:
      return FIND_ALL_CHROMIUM_SEARCH_PATHS();
    }
  }
  private String[] NEARBY_CHROMIUM_SEARCH_PATHS() {
    // obtain the PLUGIN environment variable
    String plugin = System.getenv("PLUGIN");
    if (plugin != null && !plugin.isEmpty()) {
      File pluginDir = new File(plugin);
      if (pluginDir.exists()) {
        if (isWindows()) {
          File searchResult = searchFile(pluginDir, "ungoogled-chromium.exe");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(pluginDir, "brave.exe");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(pluginDir, "chromium.exe");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(pluginDir, "edge.exe");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(pluginDir, "msedge.exe");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(pluginDir, "chrome.exe");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
        } else {
          File searchResult = searchFile(pluginDir, "ungoogled-chromium");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(pluginDir, "brave");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(pluginDir, "chromium");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(pluginDir, "edge");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(pluginDir, "msedge");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(pluginDir, "chrome");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
        }
      }
    }
    // now, do the same thing, but with user.dir instead of plugin
    // list the directories in the user.dir directory
    File userDir = new File(System.getProperty("user.dir"));
    if (userDir.exists()) {
      if (isWindows()) {
        File searchResult = searchFile(userDir, "ungoogled-chromium.exe");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "brave.exe");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "chromium.exe");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "edge.exe");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "msedge.exe");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "chrome.exe");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
      } else {
        File searchResult = searchFile(userDir, "ungoogled-chromium");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "brave");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "chromium");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "edge");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "msedge");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "chrome");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
      }
    }
    return new String[] {};
  }
  private String[] CHROMIUM_FINDER() {
    String[] nearby = NEARBY_CHROMIUM_SEARCH_PATHS();
    String[] all = FIND_CHROMIUM_SEARCH_PATHS();

    if (nearby != null && nearby.length > 0) {
      return nearby;
    } else if (all != null && all.length > 0) {
      return all;
    } else {
      return new String[] {};
    }
  }

  /**
   * Check our list of chrome paths for a valid chrome binary.
   * Just an existence check for now, but should check versions
   * in the future.
   *
   * @return a list of usable Chromiums, or an empty list if none are found.
   * @since 0.0.1
   */
  public String[] onlyValidChromiums() {
    String[] chromees = CHROMIUM_FINDER();
    ArrayList<String> validChromiums = new ArrayList<String>();
    for (String chrome : chromees) {
      File chromeFile = new File(chrome);
      if (chromeFile.exists()) {
        logger.info("Found valid chromium at " + chrome);
        validChromiums.add(chrome);
      }
      logger.info("chrome at " + chrome + "does not exist");
    }
    return validChromiums.toArray(new String[validChromiums.size()]);
  }

  /**
   * Return the best available Chromium from the list of Chromiums we have.
   *
   * @return the path to the best available Chromium, or null if none are found.
   * @since 0.0.1
   */
  public String topChromium() {
    // get the CHROMIUM environment variable
    String chrome = System.getenv("CHROMIUM");
    // if it is not null and not empty
    if (chrome != null && !chrome.isEmpty()) {
      // check if the file exists
      File chromeFile = new File(chrome);
      if (chromeFile.exists()) {
        // if it does, return it
        return chrome;
      }
    }
    String[] chromees = onlyValidChromiums();
    if (chromees.length > 0) {
      return chromees[0];
    } else {
      return "";
    }
  }

  /**
   * Return the best available Chromium from the list of Chromiums we have.
   * if override is passed it will be validated and if it validates, it will
   * be used.
   *
   * @param override the path to a valid Chromium binary to use.
   * @return the path to the best available Chromium, or null if none are found.
   * @since 0.0.1
   */
  public String topChromium(String overrideChromium) {
    if (overrideChromium != null && !overrideChromium.isEmpty()) {
      File chromeFile = new File(overrideChromium);
      if (chromeFile.exists()) {
        return overrideChromium;
      }
    }
    return topChromium();
  }

  /**
   * Build a ProcessBuilder for the top Chromium binary and
   * the default profile.
   *
   * @return a ProcessBuilder for the top Chromium binary and
   * the default profile.
   * @since 0.0.1
   */
  public ProcessBuilder defaultProcessBuilder() {
    return processBuilder(new String[] {});
  }

  /**
   * Build a ProcessBuilder for the top Chromium binary and
   * the default profile.
   *
   * @args the arguments to pass to the Chromium binary
   * @return a ProcessBuilder for the top Chromium binary and
   * the default profile.
   * @since 0.0.1
   */
  public ProcessBuilder defaultProcessBuilder(String[] args) {
    return processBuilder(args);
  }

  /**
   * Build a ProcessBuilder for the top Chromium binary and
   * the default profile.
   *
   * @return a ProcessBuilder for the top Chromium binary and
   * the default profile. Always passes the --incognito flag.
   * @since 0.0.1
   */
  public ProcessBuilder privateProcessBuilder() {
    return processBuilder(new String[] {"--incognito"});
  }

  /**
   * Build a ProcessBuilder for the top Chromium binary and
   * the default profile.
   *
   * @param args the arguments to pass to the Chromium binary.
   * @return a ProcessBuilder for the top Chromium binary and
   * the default profile. Always passes the --incognito flag.
   * @since 0.0.1
   */
  public ProcessBuilder privateProcessBuilder(String[] args) {
    // return processBuilder(new String[]{});
    ArrayList<String> argList = new ArrayList<String>();
    argList.add("--incognito");
    if (args != null) {
      if (args.length > 0) {
        for (String arg : args) {
          argList.add(arg);
        }
      }
    }
    return processBuilder(argList.toArray(new String[argList.size()]));
  }

  /**
   * Build a ProcessBuilder for the top Chromium binary and
   * the default profile.
   *
   * @return a ProcessBuilder for the top Chromium binary and
   * the default profile. Always passes the --app flag.
   * @since 0.0.1
   */
  public ProcessBuilder appProcessBuilder() {
    return processBuilder(new String[] {"--app=http://127.0.0.1:7657"});
  }

  /**
   * Build a ProcessBuilder for the top Chromium binary and
   * the default profile.
   *
   * @param args the arguments to pass to the Chromium binary.
   * @return a ProcessBuilder for the top Chromium binary and
   * the default profile. Always passes the --app flag.
   * @since 0.0.1
   */
  public ProcessBuilder appProcessBuilder(String[] args) {
    ArrayList<String> argList = new ArrayList<String>();
    argList.add("--app=http://127.0.0.1:7657");
    // argList.add("http://127.0.0.1:7657");
    if (args != null) {
      if (args.length > 0) {
        for (String arg : args) {
          argList.add(arg);
        }
      }
    }
    return processBuilder(argList.toArray(new String[argList.size()]));
  }

  /**
   1 --user-data-dir="$CHROMIUM_I2P" \
   2 --proxy-server="http://127.0.0.1:4444" \
   3 --proxy-bypass-list=127.0.0.1:7657 \
   4 --user-data-dir=$HOME/WebApps/i2padmin \
   5 --safebrowsing-disable-download-protection \
   6 --disable-client-side-phishing-detection \
   7 --disable-3d-apis \
   8 --disable-accelerated-2d-canvas \
   9 --disable-remote-fonts \
   10 --disable-sync-preferences \
   11 --disable-sync \
   12 --disable-speech \
   13 --disable-webgl \
   14 --disable-reading-from-canvas \
   15 --disable-gpu \
   16 --disable-32-apis \
   17 --disable-auto-reload \
   18 --disable-background-networking \
   19 --disable-d3d11 \
   20 --disable-file-system \
   */

  /**
   * Build a ProcessBuilder for the top Chromium binary and
   * the default profile, with a specific set of extended
   * arguments.
   *
   * @param args the extended arguments to pass to the Chromium binary.
   * @return a ProcessBuilder for the top Chromium binary and
   * default profile, with a specific set of extended arguments.
   * @since 0.0.1
   */
  public ProcessBuilder processBuilder(String[] args) {
    String chrome = topChromium();
    if (!chrome.isEmpty()) {
      int arglength = 0;
      if (args != null)
        arglength = args.length;
      String[] newArgs = new String[arglength + 32];
      newArgs[0] = chrome;
      newArgs[1] =
          "--user-data-dir=" + this.profileDirectory(this.usabilityMode());
      newArgs[2] = "--proxy-server=http://127.0.0.1:4444";
      newArgs[3] =
          "--proxy-bypass-list=http://localhost:7657,http://127.0.0.1:7657";
      newArgs[4] = "--safebrowsing-disable-download-protection";
      newArgs[5] = "--disable-client-side-phishing-detection";
      newArgs[6] = "--disable-3d-apis";
      newArgs[7] = "--disable-accelerated-2d-canvas";
      newArgs[8] = "--disable-remote-fonts";
      newArgs[9] = "--disable-sync-preferences";
      newArgs[10] = "--disable-sync";
      newArgs[11] = "--disable-speech";
      newArgs[12] = "--disable-webgl";
      newArgs[13] = "--disable-reading-from-canvas";
      newArgs[14] = "--disable-gpu";
      newArgs[15] = "--disable-auto-reload";
      newArgs[16] = "--disable-background-networking";
      newArgs[17] = "--disable-d3d11";
      newArgs[18] = "--disable-file-system";
      newArgs[19] = "--reset-variation-state";
      newArgs[20] = "--disable-beforeunload";
      newArgs[21] = "--disable-grease-tls";
      newArgs[22] = "--disable-search-engine-collection";
      newArgs[23] = "--fingerprinting-canvas-image-data-noise";
      newArgs[24] = "--fingerprinting-canvas-measuretext-noise";
      newArgs[25] = "--fingerprinting-client-rects-noise";
      newArgs[26] = "--omnibox-autocomplete-filtering";
      newArgs[27] = "--popups-to-tabs";
      newArgs[28] = "--referrer-directive=noreferrers";
      newArgs[29] = "--force-punycode-hostnames";
      newArgs[30] = "--disable-sharing-hub";
      if (!this.usability) {
        newArgs[31] =
            "--load-extension=" +
            new File(this.profileDirectory("base"), "extensions/i2pchrome.js")
                .getAbsolutePath() +
            "," +
            new File(this.profileDirectory("base"),
                     "extensions/https-everywhere.js")
                .getAbsolutePath() +
            "," +
            new File(this.profileDirectory("base"), "extensions/noscript.js")
                .getAbsolutePath();

      } else {
        newArgs[31] =
            "--load-extension=" +
            new File(this.profileDirectory("usability"),
                     "extensions/i2pchrome.js")
                .getAbsolutePath() +
            "," +
            new File(this.profileDirectory("usability"),
                     "extensions/https-everywhere.js")
                .getAbsolutePath() +
            "," +
            new File(this.profileDirectory("usability"),
                     "extensions/jshelter.js")
                .getAbsolutePath() +
            "," +
            new File(this.profileDirectory("usability"),
                     "extensions/localcdn.js")
                .getAbsolutePath() +
            "," +
            new File(this.profileDirectory("usability"), "extensions/ublock.js")
                .getAbsolutePath();
      }
      if (args != null) {
        if (arglength > 0) {
          for (int i = 0; i < arglength; i++) {
            newArgs[i + 32] = args[i];
          }
        }
      }
      if (isOSX()) {
        String[] fg = {""};
        String[] lastArgs =
            Stream.concat(Arrays.stream(newArgs), Arrays.stream(fg))
                .toArray(String[] ::new);
        // String[] finalArgs = Stream.concat(Arrays.stream(initArgs),
        // Arrays.stream(lastArgs)).toArray(String[]::new);
        File bashScript = new File("i2pchromium.sh");
        if (bashScript.exists()) {
          bashScript.delete();
        }
        try {
          FileWriter bWriter = new FileWriter(bashScript);
          PrintWriter bpWriter = new PrintWriter(bWriter);
          bpWriter.println("#! /usr/bin/env sh");
          bpWriter.println(join(lastArgs));
          bpWriter.close();
          bWriter.close();
          if (!bashScript.canExecute()) {
            bashScript.setExecutable(true);
          }
          return new ProcessBuilder(bashScript.getAbsolutePath())
              .directory(this.runtimeDirectory(true));
        } catch (IOException e) {
          logger.warning(e.toString());
        }
      } else {
        return new ProcessBuilder(newArgs).directory(
            this.runtimeDirectory(true));
      }
    }
    logger.info("No Chromium found.");
    return new ProcessBuilder(args);
  }

  public Process launchAndDetatch(boolean privateWindow, String[] url) {
    int privateWindowInt = 0;
    if (privateWindow)
      privateWindowInt = 1;
    return launchAndDetatch(privateWindowInt, url);
  }

  public Process launchAndDetatch(int privateWindow, String[] url) {
    validateUserDirectory();
    if (waitForProxy()) {
      String profileDirectory = this.profileDirectory(this.usabilityMode());
      if (this.validateProfileDirectory(profileDirectory)) {
        logger.info("Valid profile directory: " + profileDirectory);
      } else {
        logger.info("Invalid profile directory: " + profileDirectory +
                    " rebuilding...");
        if (!this.copyBaseProfiletoProfile()) {
          logger.info("Failed to rebuild profile directory: " +
                      profileDirectory);
          return null;
        } else {
          logger.info("Rebuilt profile directory: " + profileDirectory);
        }
      }
      if (validateProfileFirstRun(profileDirectory))
        return null;
      ProcessBuilder pb = null;
      switch (privateWindow) {
      case 0:
        pb = this.defaultProcessBuilder(url);
        break;
      case 1:
        pb = this.privateProcessBuilder(url);
        break;
      case 2:
        pb = this.appProcessBuilder(url);
        break;
      default:
        pb = this.defaultProcessBuilder(url);
        break;
      }
      try {
        logger.info(pb.command().toString());
        p = pb.start();
        sleep(2000);
        return p;
      } catch (Throwable e) {
        logger.info(e.toString());
      }
    }
    return null;
  }

  /**
   * Populates a profile directory with a proxy configuration.
   * Waits for an HTTP proxy on the port 4444 to be ready.
   * Launches Chromium with the profile directory.
   *
   * @param bool if true, the profile will be ephemeral(i.e. a --private-window
   *     profile).
   * @param String[] a list of URL's to pass to the browser window
   * @since 0.0.17
   */
  public void launch(boolean privateWindow, String[] url) {
    int privateWindowInt = 0;
    if (privateWindow)
      privateWindowInt = 1;
    launch(privateWindowInt, url);
  }
  public void launch(int privateWindow, String[] url) {
    if (waitForProxy()) {
      p = launchAndDetatch(privateWindow, url);
      if (p == null)
        return;
      logger.info("I2PChromium");
      try {
        logger.info("Waiting for I2PChromium to close...");
        int exit = p.waitFor();
        logger.info("I2PChromium exited with value: " + exit);
      } catch (Exception e) {
        logger.info("Error: " + e.getMessage());
      }
    }
  }

  /**
   * Populates a profile directory with a proxy configuration.
   * Waits for an HTTP proxy on the port 4444 to be ready.
   * Launches Chromium with the profile directory.
   *
   * @param bool if true, the profile will be ephemeral(i.e. a --private-window
   *     profile).
   * @since 0.0.1
   */
  public void launch(boolean privateWindow) { launch(privateWindow, null); }

  /**
   * Populates a profile directory with a proxy configuration.
   * Waits for an HTTP proxy on the port 4444 to be ready.
   * Launches Chromium with the profile directory.
   *
   * @since 0.0.1
   */
  public void launch() { launch(false); }

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
        return inUrl;
      }
    }
    return "";
  }

  public static void main(String[] args) {
    int privateBrowsing = 0;
    I2PChromium i2pChromium = new I2PChromium();
    i2pChromium.validateUserDirectory();
    i2pChromium.logger.info("I2PChromium");
    i2pChromium.logger.info("checking for private browsing");
    ArrayList<String> visitURL = new ArrayList<String>();
    if (args != null) {
      if (args.length > 0) {
        for (String arg : args) {
          if (arg.equals("-private")) {
            privateBrowsing = 1;
            i2pChromium.logger.info(
                "private browsing is true, profile will be discarded at end of session");
          }
          if (arg.equals("-usability")) {
            i2pChromium.usability = true;
          }
          if (arg.equals("-app")) {
            privateBrowsing = 2;
            i2pChromium.usability = true;
          }
          if (arg.equals("-noproxycheck")) {
            i2pChromium.logger.info("zeroing out proxy check");
            i2pChromium.setProxyTimeoutTime(0);
          }
          if (!arg.startsWith("-")) {
            // check if it's a URL
            if (privateBrowsing == 2)
              visitURL.add("--app=" + i2pChromium.ValidURL(arg));
            else
              visitURL.add(i2pChromium.ValidURL(arg));
          }
        }
      }
    }
    i2pChromium.launch(privateBrowsing,
                       visitURL.toArray(new String[visitURL.size()]));
  }
}
