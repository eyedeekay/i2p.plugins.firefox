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
public class I2PFirefox extends I2PCommonBrowser {
  private final String[] FIREFOX_SEARCH_PATHS = FIREFOX_FINDER();
  private Process p = null;
  public static boolean usability = false;

  private static String baseMode() {
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

  public static void storeFirefoxDefaults() {
    List<String> list = new ArrayList<String>();
    list = Arrays.asList(firefoxPathsWindows());
    prop.setProperty("firefox.paths.windows",
                     list.stream().collect(Collectors.joining(",")));
    list = Arrays.asList(firefoxPathsUnix());
    prop.setProperty("firefox.paths.linux",
                     list.stream().collect(Collectors.joining(",")));
    list = Arrays.asList(firefoxPathsOSX());
    prop.setProperty("firefox.paths.osx",
                     list.stream().collect(Collectors.joining(",")));

    list = Arrays.asList(firefoxBinsWindows());
    prop.setProperty("firefox.bins.windows",
                     list.stream().collect(Collectors.joining(",")));
    list = Arrays.asList(firefoxBinsUnix());
    prop.setProperty("firefox.bins.linux",
                     list.stream().collect(Collectors.joining(",")));
    list = Arrays.asList(firefoxBinsUnix());
    prop.setProperty("firefox.bins.osx",
                     list.stream().collect(Collectors.joining(",")));
    try (OutputStream fos = new FileOutputStream(
             new File(runtimeDirectory(""), "browser.config"))) {
      prop.store(fos, "Firefox Configuration Section");
    } catch (IOException ioe) {
      logger.warning(ioe.toString());
    }
  }

  public static String[] firefoxPathsUnix() {
    String firefoxPathsProp = prop.getProperty("firefox.paths.unix");
    if (firefoxPathsProp != null)
      if (!firefoxPathsProp.equals(""))
        return firefoxPathsProp.split(",");
    return new String[] {"/usr/bin", "/usr/local/bin", "/opt/firefox/bin",
                         "/snap/bin"};
  }

  public static String[] firefoxBinsUnix() {
    String firefoxPathsProp;
    if (isOSX()) {
      firefoxPathsProp = prop.getProperty("firefox.bins.osx");
      if (firefoxPathsProp != null)
        if (!firefoxPathsProp.equals(""))
          return firefoxPathsProp.split(",");
    }
    firefoxPathsProp = prop.getProperty("firefox.bins.unix");
    if (firefoxPathsProp != null)
      if (!firefoxPathsProp.equals(""))
        return firefoxPathsProp.split(",");

    return new String[] {"firefox",  "firefox-bin",  "firefox-esr",
                         "waterfox", "waterfox-bin", "librewolf"};
  }

  private static String[] FIND_FIREFOX_SEARCH_PATHS_UNIX() {
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
  public static String[] firefoxPathsOSX() {
    String firefoxPathsProp = prop.getProperty("firefox.paths.osx");
    if (firefoxPathsProp != null)
      if (!firefoxPathsProp.equals(""))
        return firefoxPathsProp.split(",");
    return new String[] {"/Applications/Tor Browser.app/Contents/MacOS",
                         "/Applications/Firefox.app/Contents/MacOS",
                         "/Applications/Waterfox.app/Contents/MacOS",
                         "/Applications/Librewolf.app/Contents/MacOS"};
  }
  private static String[] FIND_FIREFOX_SEARCH_PATHS_OSX() {
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
  public static String[] firefoxPathsWindows() {
    String firefoxPathsProp = prop.getProperty("firefox.paths.windows");
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
  private static String[] firefoxBinsWindows() {
    String firefoxPathsProp = prop.getProperty("firefox.bins.windows");
    if (firefoxPathsProp != null)
      if (!firefoxPathsProp.equals(""))
        return firefoxPathsProp.split(",");
    return new String[] {
        "firefox.exe",  "firefox-bin.exe",  "firefox-esr.exe",
        "waterfox.exe", "waterfox-bin.exe", "librewolf.exe",
    };
  }
  private static String[] FIND_FIREFOX_SEARCH_PATHS_WINDOWS() {
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

  private static String[] FIND_ALL_FIREFOX_SEARCH_PATHS() {
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
  private static String[] FIND_FIREFOX_SEARCH_PATHS() {
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
  private static String[] NEARBY_FIREFOX_SEARCH_PATHS() {
    // obtain the PLUGIN environment variable
    String plugin = System.getenv("PLUGIN");
    // search the plugin directory for anything named "firefox", "firefox-bin",
    // "firefox-esr", "waterfox", "waterfox-bin", "librewolf" up to a depth of 2
    // directories deep. list the directories in the plugin directory
    if (plugin != null && !plugin.isEmpty()) {
      File userDir = new File(plugin);
      if (userDir.exists()) {
        File[] userDirs = userDir.listFiles();
        for (File userDir1 : userDirs) {
          File[] userFiles = userDir1.listFiles();
          if (userFiles != null) {
            for (File userFile : userFiles) {
              // logger.info("CHECKING NEARBY" + userFile.getAbsolutePath());
              if (userFile.isDirectory()) {
                File[] userFiles2 = userFile.listFiles();
                for (File userFile2 : userFiles2) {
                  if (userFile2.isDirectory())
                    continue;
                  if (!isWindows()) {
                    if (userFile2.getName().equals("firefox") ||
                        userFile2.getName().equals("firefox-bin") ||
                        userFile2.getName().equals("firefox-esr") ||
                        userFile2.getName().equals("waterfox") ||
                        userFile2.getName().equals("waterfox-bin") ||
                        userFile2.getName().equals("librewolf")) {
                      return new String[] {userFile2.getAbsolutePath()};
                    }
                  } else {
                    if (userFile2.getName().equals("firefox.exe") ||
                        userFile2.getName().equals("firefox-bin.exe") ||
                        userFile2.getName().equals("firefox-esr.exe") ||
                        userFile2.getName().equals("waterfox.exe") ||
                        userFile2.getName().equals("waterfox-bin.exe") ||
                        userFile2.getName().equals("librewolf.exe")) {
                      return new String[] {userFile2.getAbsolutePath()};
                    }
                  }
                }
                if (!isWindows()) {
                  if (userFile.getName().equals("firefox") ||
                      userFile.getName().equals("firefox-bin") ||
                      userFile.getName().equals("firefox-esr") ||
                      userFile.getName().equals("waterfox") ||
                      userFile.getName().equals("waterfox-bin") ||
                      userFile.getName().equals("librewolf")) {
                    return new String[] {userFile.getAbsolutePath()};
                  }
                } else {
                  if (userFile.getName().equals("firefox.exe") ||
                      userFile.getName().equals("firefox-bin.exe") ||
                      userFile.getName().equals("firefox-esr.exe") ||
                      userFile.getName().equals("waterfox.exe") ||
                      userFile.getName().equals("waterfox-bin.exe") ||
                      userFile.getName().equals("librewolf.exe")) {
                    return new String[] {userFile.getAbsolutePath()};
                  }
                }
              }
            }
          }
        }
      }
      // now, do the same thing, but with user.dir instead of plugin
      // list the directories in the user.dir directory
      userDir = new File(System.getProperty("user.dir"));
      if (userDir.exists()) {
        File[] userDirs = userDir.listFiles();
        // list the files in the user.dir directory
        for (File userDir1 : userDirs) {
          File[] userFiles = userDir1.listFiles();
          if (userFiles != null) {
            for (File userFile : userFiles) {
              if (userFile.isDirectory()) {
                File[] userFiles2 = userFile.listFiles();
                for (File userFile2 : userFiles2) {
                  if (userFile2.isDirectory())
                    continue;
                  if (isWindows()) {
                    if (userFile2.getName().equals("firefox") ||
                        userFile2.getName().equals("firefox-bin") ||
                        userFile2.getName().equals("firefox-esr") ||
                        userFile2.getName().equals("waterfox") ||
                        userFile2.getName().equals("waterfox-bin") ||
                        userFile2.getName().equals("librewolf")) {
                      return new String[] {userFile2.getAbsolutePath()};
                    }
                  } else {
                    if (userFile.getName().equals("firefox.exe") ||
                        userFile.getName().equals("firefox-bin.exe") ||
                        userFile.getName().equals("firefox-esr.exe") ||
                        userFile.getName().equals("waterfox.exe") ||
                        userFile.getName().equals("waterfox-bin.exe") ||
                        userFile.getName().equals("librewolf.exe")) {
                      return new String[] {userFile.getAbsolutePath()};
                    }
                  }
                }
              }
              if (!isWindows()) {
                if (userFile.getName().equals("firefox") ||
                    userFile.getName().equals("firefox-bin") ||
                    userFile.getName().equals("firefox-esr") ||
                    userFile.getName().equals("waterfox") ||
                    userFile.getName().equals("waterfox-bin") ||
                    userFile.getName().equals("librewolf")) {
                  return new String[] {userFile.getAbsolutePath()};
                }
              } else {
                if (userFile.getName().equals("firefox.exe") ||
                    userFile.getName().equals("firefox-bin.exe") ||
                    userFile.getName().equals("firefox-esr.exe") ||
                    userFile.getName().equals("waterfox.exe") ||
                    userFile.getName().equals("waterfox-bin.exe") ||
                    userFile.getName().equals("librewolf.exe")) {
                  return new String[] {userFile.getAbsolutePath()};
                }
              }
            }
          }
        }
      }
    }
    return new String[] {};
  }
  private static String[] FIREFOX_FINDER() {
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
    // get the FIREFOX environment variable
    String firefox = System.getenv("FIREFOX");
    // if it is not null and not empty
    if (firefox != null && !firefox.isEmpty()) {
      // check if the file exists
      File firefoxFile = new File(firefox);
      if (firefoxFile.exists()) {
        // if it does, return it
        return firefox;
      }
    }
    String[] firefoxes = onlyValidFirefoxes();
    if (firefoxes.length > 0) {
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
      newArgs[3] = I2PFirefoxProfileBuilder.profileDirectory(app, baseMode());
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
        File bashScript = new File("i2pfirefox.sh");
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
              .directory(I2PFirefoxProfileBuilder.runtimeDirectory(true));
        } catch (IOException e) {
          logger.warning(e.toString());
        }
        return null;
      } else {
        return new ProcessBuilder(newArgs).directory(
            I2PFirefoxProfileBuilder.runtimeDirectory(true));
      }

    } // else {
    logger.info("No Firefox found.");
    return new ProcessBuilder(args);
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
    validateUserDir();
    boolean app = false;
    if (privateWindow == 2)
      app = true;
    if (waitForProxy()) {
      String profileDirectory =
          I2PFirefoxProfileBuilder.profileDirectory(app, baseMode());
      if (I2PFirefoxProfileChecker.validateProfileDirectory(profileDirectory)) {
        logger.info("Valid profile directory: " + profileDirectory);
      } else {
        logger.info("Invalid profile directory: " + profileDirectory +
                    " rebuilding...");
        if (!I2PFirefoxProfileBuilder.copyBaseProfiletoProfile(usabilityMode(),
                                                               app)) {
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
        p = pb.start();
        logger.info("I2PFirefox");
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
      p = launchAndDetatch(privateWindow, url);
      if (p == null)
        return;
      try {
        logger.info("Waiting for I2PFirefox to close...");
        int exit = p.waitFor();
        logger.info("I2PFirefox exited with value: " + exit);
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

  private static String ValidURL(String inUrl) {
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
    validateUserDir();
    int privateBrowsing = 0;
    logger.info("checking for private browsing");
    logger.info("I2PFirefox");
    I2PFirefox i2pFirefox = new I2PFirefox();
    ArrayList<String> visitURL = new ArrayList<String>();
    if (args != null) {
      if (args.length > 0) {
        for (String arg : args) {
          if (arg.equals("-private")) {
            privateBrowsing = 1;
            logger.info(
                "private browsing is true, profile will be discarded at end of session");
          }
          if (arg.equals("-usability")) {
            usability = true;
          }
          if (arg.equals("-app")) {
            usability = true;
            privateBrowsing = 2;
          }
          if (arg.equals("-noproxycheck")) {
            logger.info("zeroing out proxy check");
            i2pFirefox.setProxyTimeoutTime(0);
          }
          if (!arg.startsWith("-")) {
            // check if it's a URL
            visitURL.add(ValidURL(arg));
          }
        }
      }
    }
    i2pFirefox.launch(privateBrowsing,
                      visitURL.toArray(new String[visitURL.size()]));
  }

  private static void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException bad) {
      bad.printStackTrace();
      throw new RuntimeException(bad);
    }
  }
}
