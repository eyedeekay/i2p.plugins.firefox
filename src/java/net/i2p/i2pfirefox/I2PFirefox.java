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
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * I2PFirefox.java
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
public class I2PFirefox extends I2PFirefoxProfileBuilder {
  private final String[] FIREFOX_SEARCH_PATHS = FIREFOX_FINDER();
  private Process process = null;
  private String firefoxPath;
  public boolean usability = false;

  private String baseMode() {
    if (usability)
      return "usability";
    return "base";
  }

  /**
   * Construct an I2PFirefox class which manages an instance of Firefox and
   * an accompanying Firefox profile. This version includes Firefox variants
   * and forks.
   *
   * @since 0.0.1
   */
  public I2PFirefox() {
    for (String path : FIREFOX_SEARCH_PATHS) {
      File f = new File(path);
      if (f.exists()) {
        logger.info("Found Firefox at " + path);
        return;
      }
    }
  }

  public void storeFirefoxDefaults() {
    List<String> list = new ArrayList<String>();
    list = Arrays.asList(firefoxPathsWindows());
    getProperties().setProperty("firefox.paths.windows",
                                list.stream().collect(Collectors.joining(",")));
    list = Arrays.asList(firefoxPathsUnix());
    getProperties().setProperty("firefox.paths.linux",
                                list.stream().collect(Collectors.joining(",")));
    list = Arrays.asList(firefoxPathsOSX());
    getProperties().setProperty("firefox.paths.osx",
                                list.stream().collect(Collectors.joining(",")));

    list = Arrays.asList(firefoxBinsWindows());
    getProperties().setProperty("firefox.bins.windows",
                                list.stream().collect(Collectors.joining(",")));
    list = Arrays.asList(firefoxBinsUnix());
    getProperties().setProperty("firefox.bins.linux",
                                list.stream().collect(Collectors.joining(",")));
    list = Arrays.asList(firefoxBinsUnix());
    getProperties().setProperty("firefox.bins.osx",
                                list.stream().collect(Collectors.joining(",")));
    try (OutputStream fos = new FileOutputStream(
             new File(runtimeDirectory(""), "browser.config"))) {
      getProperties().store(fos, "Firefox Configuration Section");
    } catch (IOException ioe) {
      logger.warning(ioe.toString());
    }
  }

  public String[] firefoxPathsUnix() {
    String firefoxPathsProp = getProperties().getProperty("firefox.paths.unix");
    if (firefoxPathsProp != null)
      if (!firefoxPathsProp.equals(""))
        return firefoxPathsProp.split(",");
    return new String[] {"/usr/bin", "/usr/local/bin", "/opt/firefox/bin",
                         "/snap/bin"};
  }

  public String[] firefoxBinsUnix() {
    String firefoxPathsProp;
    if (isOSX()) {
      firefoxPathsProp = getProperties().getProperty("firefox.bins.osx");
      if (firefoxPathsProp != null)
        if (!firefoxPathsProp.equals(""))
          return firefoxPathsProp.split(",");
    }
    firefoxPathsProp = getProperties().getProperty("firefox.bins.unix");
    if (firefoxPathsProp != null)
      if (!firefoxPathsProp.equals(""))
        return firefoxPathsProp.split(",");

    return new String[] {"firefox",  "firefox-bin",  "firefox-esr",
                         "waterfox", "waterfox-bin", "librewolf"};
  }

  private String[] FIND_FIREFOX_SEARCH_PATHS_UNIX() {
    String[] path = firefoxPathsUnix();
    String[] exes = firefoxBinsUnix();
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
  public String[] firefoxPathsOSX() {
    String firefoxPathsProp = getProperties().getProperty("firefox.paths.osx");
    if (firefoxPathsProp != null)
      if (!firefoxPathsProp.equals(""))
        return firefoxPathsProp.split(",");
    return new String[] {"/Applications/Tor Browser.app/Contents/MacOS",
                         "/Applications/Firefox.app/Contents/MacOS",
                         "/Applications/Waterfox.app/Contents/MacOS",
                         "/Applications/Librewolf.app/Contents/MacOS"};
  }
  private String[] FIND_FIREFOX_SEARCH_PATHS_OSX() {
    String[] path = firefoxPathsOSX();
    String[] exes = firefoxBinsUnix();
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
  public String[] firefoxPathsWindows() {
    String firefoxPathsProp =
        getProperties().getProperty("firefox.paths.windows");
    if (firefoxPathsProp != null)
      if (!firefoxPathsProp.equals(""))
        return firefoxPathsProp.split(",");
    String userHome = System.getProperty("user.home");
    String programFiles = System.getenv("ProgramFiles");
    // String localAppData = System.getenv("LOCALAPPDATA");
    // Is there some way Mozilla does adminless installs to LocalAppData? Don't
    // know for sure.
    String programFiles86 = System.getenv("ProgramFiles(x86)");

    if (programFiles == null)
      programFiles = "C:/Program Files/";
    if (programFiles86 == null)
      programFiles86 = "C:/Program Files (x86)/";
    if (!isWindows())
      userHome = "C:/Users/user/";

    String[] tbPath = new String[] {
        new File(userHome, "/OneDrive/Desktop/Tor Browser/Browser/").toString(),
        new File(userHome, "/Desktop/Tor Browser/Browser/").toString()};

    return new String[] {
        tbPath[0],
        tbPath[1],
        new File(programFiles, "Mozilla Firefox/").toString(),
        new File(programFiles86, "Mozilla Firefox/").toString(),
        new File(programFiles, "Waterfox/").toString(),
        new File(programFiles86, "Waterfox/").toString(),
        new File(programFiles, "Librewolf/").toString(),
    };
  }
  private String[] firefoxBinsWindows() {
    String firefoxPathsProp =
        getProperties().getProperty("firefox.bins.windows");
    if (firefoxPathsProp != null)
      if (!firefoxPathsProp.equals(""))
        return firefoxPathsProp.split(",");
    return new String[] {
        "firefox.exe",  "firefox-bin.exe",  "firefox-esr.exe",
        "waterfox.exe", "waterfox-bin.exe", "librewolf.exe",
    };
  }
  private String[] FIND_FIREFOX_SEARCH_PATHS_WINDOWS() {
    String[] path = firefoxPathsWindows();
    String[] exes = firefoxBinsWindows();
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

  private String[] FIND_ALL_FIREFOX_SEARCH_PATHS() {
    String[] Unix = FIND_FIREFOX_SEARCH_PATHS_UNIX();
    String[] Windows = FIND_FIREFOX_SEARCH_PATHS_WINDOWS();
    String[] Mac = FIND_FIREFOX_SEARCH_PATHS_OSX();
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
  private String[] FIND_FIREFOX_SEARCH_PATHS() {
    switch (getOperatingSystem()) {
    case "Windows":
      return FIND_FIREFOX_SEARCH_PATHS_WINDOWS();
    case "Linux":
      return FIND_FIREFOX_SEARCH_PATHS_UNIX();
    case "Mac":
      return FIND_FIREFOX_SEARCH_PATHS_OSX();
    case "BSD":
      return FIND_FIREFOX_SEARCH_PATHS_UNIX();
    default:
      return FIND_ALL_FIREFOX_SEARCH_PATHS();
    }
  }

  private String[] NEARBY_FIREFOX_SEARCH_PATHS() {
    // obtain the PLUGIN environment variable
    // crashreporterFolder := utl.CreateFolder(app.DataPath, "crashreporter")
    // pluginsFolder := utl.CreateFolder(app.DataPath, "plugins")
    String plugin = System.getenv("PLUGIN");
    if (plugin != null && !plugin.isEmpty()) {
      File userDir = new File(plugin);
      if (userDir.exists()) {
        if (isWindows()) {
          File searchResult = searchFile(userDir, "firefox-esr.exe");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(userDir, "firefox.exe");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(userDir, "firefox-bin.exe");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(userDir, "waterfox.exe");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(userDir, "waterfox-bin.exe");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(userDir, "librewolf.exe");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
        } else {
          File searchResult = searchFile(userDir, "firefox-esr");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(userDir, "firefox");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(userDir, "firefox-bin");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(userDir, "waterfox");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(userDir, "waterfox-bin");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
          searchResult = searchFile(userDir, "librewolf");
          if (searchResult != null)
            return new String[] {searchResult.getAbsolutePath()};
        }
      }
    }
    // now, do the same thing, but with user.dir instead of plugin
    // list the directories in the user.dir directory
    File userDir = userHomeDir();
    if (userDir.exists()) {
      if (isWindows()) {
        File searchResult = searchFile(userDir, "firefox-esr.exe");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "firefox.exe");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "firefox-bin.exe");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "waterfox.exe");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "waterfox-bin.exe");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "librewolf.exe");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
      } else {
        File searchResult = searchFile(userDir, "firefox-esr");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "firefox");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "firefox-bin");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "waterfox");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "waterfox-bin");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
        searchResult = searchFile(userDir, "librewolf");
        if (searchResult != null)
          return new String[] {searchResult.getAbsolutePath()};
      }
    }

    return new String[] {};
  }
  private String[] FIREFOX_FINDER() {
    String[] nearby = NEARBY_FIREFOX_SEARCH_PATHS();
    String[] all = FIND_FIREFOX_SEARCH_PATHS();

    if (nearby != null && nearby.length > 0) {
      return nearby;
    } else if (all != null && all.length > 0) {
      return all;
    } else {
      return new String[] {};
    }
  }

  /**
   * Check our list of firefox paths for a valid firefox binary.
   * Just an existence check for now, but should check versions
   * in the future.
   *
   * @return a list of usable Firefoxes, or an empty list if none are found.
   * @since 0.0.1
   */
  public String[] onlyValidFirefoxes() {
    String[] firefoxes = FIREFOX_FINDER();
    ArrayList<String> validFirefoxes = new ArrayList<String>();
    for (String firefox : firefoxes) {
      File firefoxFile = new File(firefox);
      if (firefoxFile.exists()) {
        logger.info("Found valid firefox at " + firefox);
        validFirefoxes.add(firefox);
      }
      logger.info("firefox at " + firefox + "does not exist");
    }
    return validFirefoxes.toArray(new String[validFirefoxes.size()]);
  }

  /**
   * Return the best available Firefox from the list of Firefoxes we have.
   *
   * @return the path to the best available Firefox, or null if none are
   *     found.
   * @since 0.0.1
   */
  public String topFirefox() {
    if (firefoxPath != null) {
      return firefoxPath;
    }
    // get the FIREFOX environment variable
    String firefox = System.getenv("FIREFOX");
    // if it is not null and not empty
    if (firefox != null && !firefox.isEmpty()) {
      // check if the file exists
      File firefoxFile = new File(firefox);
      if (firefoxFile.exists()) {
        // if it does, return it
        firefoxPath = firefox;
        return firefox;
      }
    }
    String[] firefoxes = onlyValidFirefoxes();
    if (firefoxes.length > 0) {
      firefoxPath = firefoxes[0];
      return firefoxes[0];
    } else {
      return "";
    }
  }

  /**
   * Return the best available Firefox from the list of Firefoxes we have.
   * if override is passed it will be validated and if it validates, it will
   * be used.
   *
   * @param override the path to a valid Firefox binary to use.
   * @return the path to the best available Firefox, or null if none are
   *     found.
   * @since 0.0.1
   */
  public String topFirefox(String overrideFirefox) {
    if (overrideFirefox != null && !overrideFirefox.isEmpty()) {
      File firefoxFile = new File(overrideFirefox);
      if (firefoxFile.exists()) {
        return overrideFirefox;
      }
    }
    return topFirefox();
  }

  /**
   * Build a ProcessBuilder for the top Firefox binary and
   * the default profile.
   *
   * @return a ProcessBuilder for the top Firefox binary and
   * the default profile.
   * @since 0.0.1
   */
  public ProcessBuilder defaultProcessBuilder() {
    return processBuilder(new String[] {}, false);
  }

  /**
   * * Build a ProcessBuilder for the top Firefox binary and
   * the default profile.
   *
   * @param args the args to pass to the Firefox binary
   * @return a ProcessBuilder for the top Firefox binary and
   * the default profile.
   */
  public ProcessBuilder defaultProcessBuilder(String[] args) {
    return processBuilder(args, false);
  }

  /**
   * Build a ProcessBuilder for the top Firefox binary and
   * the default profile. Pass the --private-window flag to
   * open a private window.
   *
   * @param args the arguments to pass to the Firefox binary.
   * @return a ProcessBuilder for the top Firefox binary and
   * the default profile.
   * @since 0.0.1
   */
  public ProcessBuilder privateProcessBuilder() {
    return processBuilder(new String[] {"--private-window"}, false);
  }

  /**
   * Build a ProcessBuilder for the top Firefox binary and
   * the default profile. Pass the --private-window flag to
   * open a private window.
   *
   * @param args the arguments to pass to the Firefox binary
   * @return a ProcessBuilder for the top Firefox binary and
   * the default profile.
   */
  public ProcessBuilder privateProcessBuilder(String[] args) {
    ArrayList<String> argList = new ArrayList<String>();
    argList.add("--private-window");
    if (args != null) {
      if (args.length > 0) {
        for (String arg : args) {
          argList.add(arg);
        }
      }
    }
    return processBuilder(argList.toArray(new String[argList.size()]), false);
  }

  /**
   * Build a ProcessBuilder for the top Firefox binary and
   * the default profile. Pass the --private-window flag to
   * open a private window.
   *
   * @param args the arguments to pass to the Firefox binary.
   * @return a ProcessBuilder for the top Firefox binary and
   * the default profile.
   * @since 0.0.1
   */
  public ProcessBuilder appProcessBuilder() {
    return appProcessBuilder(new String[] {});
  }

  /**
   * Build a ProcessBuilder for the top Firefox binary and
   * the default profile. Pass the --private-window flag to
   * open a private window.
   *
   * @param args the arguments to pass to the Firefox binary
   * @return a ProcessBuilder for the top Firefox binary and
   * the default profile.
   */
  public ProcessBuilder appProcessBuilder(String[] args) {
    ArrayList<String> argList = new ArrayList<String>();
    if (args != null) {
      if (args.length > 0) {
        for (String arg : args) {
          argList.add(arg);
        }
      }
    }
    return processBuilder(argList.toArray(new String[argList.size()]), true);
  }

  /**
   * Build a ProcessBuilder for the top Firefox binary and
   * the default profile. Pass the --headless flag to open
   * without a window.
   *
   * @param args the arguments to pass to the Firefox binary
   * @return a ProcessBuilder for the top Firefox binary and
   * the default profile.
   */
  public ProcessBuilder headlessProcessBuilder(String[] args) {
    ArrayList<String> argList = new ArrayList<String>();
    argList.add("--headless");
    if (args != null) {
      if (args.length > 0) {
        for (String arg : args) {
          argList.add(arg);
        }
      }
    }
    return processBuilder(argList.toArray(new String[argList.size()]), false);
  }

  /**
   * Build a ProcessBuilder for the top Firefox binary and
   * the default profile, with a specific set of extended
   * arguments.
   *
   * @param args the extended arguments to pass to the Firefox binary.
   * @return a ProcessBuilder for the top Firefox binary and
   * default profile, with a specific set of extended arguments.
   * @since 0.0.1
   */
  /*
  public ProcessBuilder processBuilder(String[] args ) {
    return processBuilder(args, false);
  }
    */
  public ProcessBuilder processBuilder(String[] args, boolean app) {
    String firefox = topFirefox();
    if (!firefox.isEmpty()) {
      int arglength = 0;
      if (args != null)
        arglength = args.length;
      String[] newArgs = new String[arglength + 4];
      newArgs[0] = firefox;
      newArgs[1] = "--new-instance";
      newArgs[2] = "--profile";
      newArgs[3] = this.profileDirectory(app, baseMode());
      if (args != null) {
        if (arglength > 0) {
          for (int i = 0; i < arglength; i++) {
            newArgs[i + 4] = args[i];
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
        String applicationSupportDirectory =
            System.getProperty("user.home") +
            "/Library/Application Support/i2pbrowser";
        File applicationSupportDirectoryFile =
            new File(applicationSupportDirectory);
        if (!applicationSupportDirectoryFile.exists())
          applicationSupportDirectoryFile.mkdirs();
        File bashScript =
            new File(applicationSupportDirectoryFile, "i2pfirefox.sh");
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
          ProcessBuilder pb = new ProcessBuilder(bashScript.getAbsolutePath());
          File rtd = this.runtimeDirectory(true);
          pb.directory(rtd);
          String crashreporterFolder =
              new File(rtd.getAbsolutePath(), "crashreporter").toString();
          String pluginsFolder =
              new File(rtd.getAbsolutePath(), "plugins").toString();
          pb.environment().put("HOME", rtd.getAbsolutePath());
          pb.environment().put("MOZ_CRASHREPORTER", "0");
          pb.environment().put("MOZ_CRASHREPORTER_DATA_DIRECTORY",
                               crashreporterFolder);
          pb.environment().put("MOZ_CRASHREPORTER_DISABLE", "1");
          pb.environment().put("MOZ_CRASHREPORTER_NO_REPORT", "1");
          pb.environment().put("MOZ_DATA_REPORTING", "0");
          pb.environment().put("MOZ_MAINTENANCE_SERVICE", "0");
          pb.environment().put("MOZ_PLUGIN_PATH", pluginsFolder);
          pb.environment().put("MOZ_UPDATER", "0");
          pb.environment().put("TB_CUSTOM_HOMEPAGE", newArgs[4]);
          pb.environment().put("TOR_FORCE_NET_CONFIG", "0");
          pb.environment().put("TOR_SKIP_LAUNCH", "1");
          pb.environment().put("TOR_SKIP_CONTROLPORTTEST", "1");
          pb.environment().put("TOR_NONTOR_PROXY", "1");
          return pb;
        } catch (IOException e) {
          logger.warning(e.toString());
        }
        return null;
      } else {
        ProcessBuilder pb = new ProcessBuilder(newArgs);
        File rtd = this.runtimeDirectory(true);
        pb.directory(rtd);
        String crashreporterFolder =
            new File(rtd.getAbsolutePath(), "crashreporter").toString();
        String pluginsFolder =
            new File(rtd.getAbsolutePath(), "crashreporter").toString();
        pb.environment().put("HOME", rtd.getAbsolutePath());
        pb.environment().put("MOZ_CRASHREPORTER", "0");
        pb.environment().put("MOZ_CRASHREPORTER_DATA_DIRECTORY",
                             crashreporterFolder);
        pb.environment().put("MOZ_CRASHREPORTER_DISABLE", "1");
        pb.environment().put("MOZ_CRASHREPORTER_NO_REPORT", "1");
        pb.environment().put("MOZ_DATA_REPORTING", "0");
        pb.environment().put("MOZ_MAINTENANCE_SERVICE", "0");
        pb.environment().put("MOZ_PLUGIN_PATH", pluginsFolder);
        pb.environment().put("MOZ_UPDATER", "0");
        if (args.length > 4)
          pb.environment().put("TB_CUSTOM_HOMEPAGE", args[4]);
        pb.environment().put("TOR_FORCE_NET_CONFIG", "0");
        pb.environment().put("TOR_SKIP_LAUNCH", "1");
        pb.environment().put("TOR_SKIP_CONTROLPORTTEST", "1");
        pb.environment().put("TOR_NONTOR_PROXY", "1");
        return pb;
      }

    } // else {
    logger.info("No Firefox found.");
    ProcessBuilder pb = new ProcessBuilder(args);
    File rtd = this.runtimeDirectory(true);
    pb.directory(rtd);
    String crashreporterFolder =
        new File(rtd.getAbsolutePath(), "crashreporter").toString();
    String pluginsFolder =
        new File(rtd.getAbsolutePath(), "crashreporter").toString();
    pb.environment().put("HOME", rtd.getAbsolutePath());
    pb.environment().put("MOZ_CRASHREPORTER", "0");
    pb.environment().put("MOZ_CRASHREPORTER_DATA_DIRECTORY",
                         crashreporterFolder);
    pb.environment().put("MOZ_CRASHREPORTER_DISABLE", "1");
    pb.environment().put("MOZ_CRASHREPORTER_NO_REPORT", "1");
    pb.environment().put("MOZ_DATA_REPORTING", "0");
    pb.environment().put("MOZ_MAINTENANCE_SERVICE", "0");
    pb.environment().put("MOZ_PLUGIN_PATH", pluginsFolder);
    pb.environment().put("MOZ_UPDATER", "0");
    if (args.length > 4)
      pb.environment().put("TB_CUSTOM_HOMEPAGE", args[4]);
    pb.environment().put("TOR_FORCE_NET_CONFIG", "0");
    pb.environment().put("TOR_SKIP_LAUNCH", "1");
    pb.environment().put("TOR_SKIP_CONTROLPORTTEST", "1");
    pb.environment().put("TOR_NONTOR_PROXY", "1");
    return pb;
    //}
    // return null;
  }

  private String usabilityMode() {
    if (usability) {
      return "usability";
    }
    return "base";
  }
  public Process launchAndDetatch(boolean privateWindow, String[] url) {
    int privateWindowInt = 0;
    if (privateWindow)
      privateWindowInt = 1;
    return launchAndDetatch(privateWindowInt, url);
  }
  public Process launchAndDetatch(int privateWindow, String[] url) {
    validateUserDirectory();
    boolean app = false;
    if (privateWindow == 2)
      app = true;
    if (waitForProxy()) {
      String profileDirectory = this.profileDirectory(app, baseMode());
      if (this.validateProfileDirectory(profileDirectory)) {
        logger.info("Valid profile directory: " + profileDirectory);
      } else {
        logger.info("Invalid profile directory: " + profileDirectory +
                    " rebuilding...");
        if (!this.copyBaseProfiletoProfile(usabilityMode(), app)) {
          logger.info("Failed to rebuild profile directory: " +
                      profileDirectory);
          return null;
        } else {
          logger.info("Rebuilt profile directory: " + profileDirectory);
        }
      }
      if (validateProfileFirstRun(profileDirectory)) {
        if (isWindows()) {
          ProcessBuilder hpb = headlessProcessBuilder(url);
          try {
            Process hp = hpb.start();
            try {
              boolean hev = hp.waitFor(5, TimeUnit.SECONDS);
              logger.info("Headless browser run completed, exit: " + hev);
              if (!hev)
                hp.destroy();
              hev = hp.waitFor(5, TimeUnit.SECONDS);
              if (hp.isAlive()) {
                int forcedExitCode = hp.destroyForcibly().waitFor();
                logger.info("Headless browser run forcibly terminated, exit: " +
                            forcedExitCode);
              }
              int exitCode = hp.exitValue();
              logger.info("Headless browser run completed, exit: " + exitCode);
            } catch (InterruptedException e) {
              logger.info("Headless browser error " + e.toString());
            }
          } catch (IOException e) {
            logger.info("Headless browser error " + e.toString());
          }
        }
      }
      ProcessBuilder pb;
      switch (privateWindow) {
      case 0:
        pb = this.defaultProcessBuilder(url);
        break;
      case 1:
        pb = this.privateProcessBuilder(url);
        break;
      case 2:
        logger.info("Setting up app mode " + url.toString());
        if (url == null || url.length == 0) {
          String[] newurl = {"http://127.0.0.1:7657"};
          logger.info("Setting up default urls" + newurl.toString());
          pb = this.appProcessBuilder(newurl);
          break;
        } else {
          pb = this.appProcessBuilder(url);
          break;
        }
      default:
        pb = this.defaultProcessBuilder(url);
        break;
      }
      try {
        logger.info(pb.command().toString());
        process = pb.start();
        logger.info("I2PFirefox");
        sleep(2000);
        return process;
      } catch (Throwable e) {
        logger.info(e.toString());
      }
    }
    return null;
  }

  /**
   * Populates a profile directory with a proxy configuration.
   * Waits for an HTTP proxy on the port 4444 to be ready.
   * Launches Firefox with the profile directory.
   *
   * @param bool if true, the profile will be ephemeral(i.e. a
   *     --private-window profile).
   * @param String[] a list of URL's to pass to the browser window
   * @since 0.0.17
   */
  public void launch(boolean privateWindow, String[] url) {
    int priv = 0;
    if (privateWindow)
      priv = 1;
    launch(priv, url);
  }
  public void launch(int privateWindow, String[] url) {
    if (waitForProxy()) {
      process = launchAndDetatch(privateWindow, url);
      if (process == null)
        return;
      try {
        logger.info("Waiting for I2PFirefox to close...");
        int exit = process.waitFor();
        logger.info("I2PFirefox exited with value: " + exit);
        if (isOSX())
          System.exit(exit);
      } catch (Exception e) {
        logger.info("Error: " + e.getMessage());
      }
    }
  }

  /**
   * Populates a profile directory with a proxy configuration.
   * Waits for an HTTP proxy on the port 4444 to be ready.
   * Launches Firefox with the profile directory.
   *
   * @param bool if true, the profile will be ephemeral(i.e. a
   *     --private-window profile).
   * @since 0.0.1
   */
  public void launch(boolean privateWindow) { launch(privateWindow, null); }

  /**
   * Populates a profile directory with a proxy configuration.
   * Waits for an HTTP proxy on the port 4444 to be ready.
   * Launches Firefox with the profile directory. Uses a semi-permanent
   * profile.
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
    if (process != null) {
      process.destroy();
      return true;
    }
    return false;
  }

  public boolean running() {
    if (process != null)
      return process.isAlive();
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
    int privateBrowsing = 0;
    I2PFirefox i2pFirefox = new I2PFirefox();
    i2pFirefox.validateUserDirectory();
    i2pFirefox.logger.info("checking for private browsing");
    i2pFirefox.logger.info("I2PFirefox");
    ArrayList<String> visitURL = new ArrayList<String>();
    if (args != null) {
      if (args.length > 0) {
        for (String arg : args) {
          if (arg.equals("-private")) {
            privateBrowsing = 1;
            i2pFirefox.logger.info(
                "private browsing is true, profile will be discarded at end of session");
          }
          if (arg.equals("-usability")) {
            i2pFirefox.usability = true;
          }
          if (arg.equals("-app")) {
            i2pFirefox.usability = true;
            privateBrowsing = 2;
          }
          if (arg.equals("-noproxycheck")) {
            i2pFirefox.logger.info("zeroing out proxy check");
            i2pFirefox.setProxyTimeoutTime(0);
          }
          if (!arg.startsWith("-")) {
            // check if it's a URL
            visitURL.add(i2pFirefox.ValidURL(arg));
          }
        }
      }
    }
    i2pFirefox.launch(privateBrowsing,
                      visitURL.toArray(new String[visitURL.size()]));
  }

  /*private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException bad) {
      bad.printStackTrace();
      throw new RuntimeException(bad);
    }
  }*/
}
