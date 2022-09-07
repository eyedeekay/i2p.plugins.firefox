package net.i2p.i2pfirefox;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
  private final int DEFAULT_TIMEOUT = 200;
  private Process p = null;
  public static boolean usability = false;

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
        println("Found Firefox at " + path);
        return;
      }
    }
  }

  private static String[] FIND_FIREFOX_SEARCH_PATHS_UNIX() {
    String[] path = new String[] {"/usr/bin", "/usr/local/bin",
                                  "/opt/firefox/bin", "/snap/bin"};
    String[] exes = new String[] {"firefox",  "firefox-bin",  "firefox-esr",
                                  "waterfox", "waterfox-bin", "librewolf"};
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
  private static String[] FIND_FIREFOX_SEARCH_PATHS_OSX() {
    String[] path =
        new String[] {"/Applications/Firefox.app/Contents/MacOS/",
                      "/Applications/Waterfox.app/Contents/MacOS/",
                      "/Applications/Librewolf.app/Contents/MacOS/"};
    String[] exes = new String[] {"firefox",  "firefox-bin",  "firefox-esr",
                                  "waterfox", "waterfox-bin", "librewolf"};
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
  private static String[] FIND_FIREFOX_SEARCH_PATHS_WINDOWS() {
    String userHome = System.getProperty("user.home");
    String programFiles = System.getenv("ProgramFiles");
    // String localAppData = System.getenv("LOCALAPPDATA");
    // Is there some way Mozilla does adminless installs to LocalAppData? Don't
    // know for sure.
    String programFiles86 = System.getenv("ProgramFiles(x86)");

    String[] tbPath = new String[] {
        new File(userHome, "/OneDrive/Desktop/Tor Browser/Browser/").toString(),
        new File(userHome, "/Desktop/Tor Browser/Browser/").toString()};

    String[] path = new String[] {
        tbPath[0],
        tbPath[1],
        new File(programFiles, "Mozilla Firefox/").toString(),
        new File(programFiles86, "Mozilla Firefox/").toString(),
        new File(programFiles, "Waterfox/").toString(),
        new File(programFiles86, "Waterfox/").toString(),
        new File(programFiles, "Librewolf/").toString(),
    };
    String[] exes = new String[] {
        "firefox.exe",  "firefox-bin.exe",  "firefox-esr.exe",
        "waterfox.exe", "waterfox-bin.exe", "librewolf.exe",
    };
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
      File pluginDir = new File(plugin);
      if (pluginDir.exists()) {
        File[] pluginDirs = pluginDir.listFiles();
        // list the files in the plugin directory
        for (File pluginDir1 : pluginDirs) {
          File[] pluginFiles = pluginDir1.listFiles();
          // list the files in the plugin directory
          if (pluginFiles != null) {
            for (File pluginFile : pluginFiles) {
              if (pluginFile.getName().equals("firefox") ||
                  pluginFile.getName().equals("firefox-bin") ||
                  pluginFile.getName().equals("firefox-esr") ||
                  pluginFile.getName().equals("waterfox") ||
                  pluginFile.getName().equals("waterfox-bin") ||
                  pluginFile.getName().equals("librewolf")) {
                return new String[] {pluginFile.getAbsolutePath()};
              }
            }
          }
        }
      }
    }
    // now, do the same thing, but with user.dir instead of plugin
    // list the directories in the user.dir directory
    File userDir = new File(System.getProperty("user.dir"));
    if (userDir.exists()) {
      File[] userDirs = userDir.listFiles();
      // list the files in the user.dir directory
      for (File userDir1 : userDirs) {
        File[] userFiles = userDir1.listFiles();
        // list the files in the user.dir directory
        if (userFiles != null) {
          for (File userFile : userFiles) {
            if (userFile.isDirectory())
              continue;
            if (userFile.getName().equals("firefox") ||
                userFile.getName().equals("firefox-bin") ||
                userFile.getName().equals("firefox-esr") ||
                userFile.getName().equals("waterfox") ||
                userFile.getName().equals("waterfox-bin") ||
                userFile.getName().equals("librewolf")) {
              return new String[] {userFile.getAbsolutePath()};
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
  private static String getOperatingSystem() {
    String os = System.getProperty("os.name");
    if (os.startsWith("Windows")) {
      return "Windows";
    } else if (os.contains("Linux")) {
      return "Linux";
    } else if (os.contains("BSD")) {
      return "BSD";
    } else if (os.contains("Mac")) {
      return "Mac";
    }
    return "Unknown";
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
        println("Found valid firefox at " + firefox);
        validFirefoxes.add(firefox);
      }
      println("firefox at " + firefox + "does not exist");
    }
    return validFirefoxes.toArray(new String[validFirefoxes.size()]);
  }

  /**
   * Return the best available Firefox from the list of Firefoxes we have.
   *
   * @return the path to the best available Firefox, or null if none are found.
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
   * @return the path to the best available Firefox, or null if none are found.
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
    return processBuilder(new String[] {});
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
    return processBuilder(args);
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
    return processBuilder(new String[] {"--private-window"});
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
    return processBuilder(argList.toArray(new String[argList.size()]));
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
    return processBuilder(argList.toArray(new String[argList.size()]));
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
  public ProcessBuilder processBuilder(String[] args) {
    String firefox = topFirefox();
    if (!firefox.isEmpty()) {
      int arglength = 0;
      if (args != null)
        arglength = args.length;
      String[] newArgs = new String[arglength + 5];
      newArgs[0] = firefox;
      newArgs[1] = "-attach-console";
      newArgs[2] = "--new-instance";
      newArgs[3] = "--profile";
      newArgs[4] = I2PFirefoxProfileBuilder.profileDirectory();
      if (args != null) {
        if (arglength > 0) {
          for (int i = 0; i < arglength; i++) {
            newArgs[i + 5] = args[i];
          }
        }
      }
      return new ProcessBuilder(newArgs).directory(
          I2PFirefoxProfileBuilder.runtimeDirectory(true));
    } else {
      println("No Firefox found.");
      return new ProcessBuilder(args);
    }
  }

  /**
   * Waits for an HTTP proxy on port 4444 to be ready.
   * Returns false on timeout of 200 seconds.
   *
   * @return true if the proxy is ready, false if it is not.
   * @since 0.0.1
   */
  public boolean waitForProxy() { return waitForProxy(DEFAULT_TIMEOUT); }

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
   * @param port the port to wait for the proxy to be ready on.
   * @return true if the proxy is ready, false if it is not.
   * @since 0.0.1
   */
  public boolean waitForProxy(int timeout, int port) {
    return waitForProxy(timeout, port, "localhost");
  }
  /**
   * Waits for an HTTP proxy on the specified port to be ready.
   * Returns false on timeout of the specified number of seconds.
   *
   * @param timeout the number of seconds to wait for the proxy to be ready.
   * @param port the port to wait for the proxy to be ready on.
   * @param host the host to wait for the proxy to be ready on.
   * @return true if the proxy is ready, false if it is not.
   * @since 0.0.1
   */
  public boolean waitForProxy(int timeout, int port, String host) {
    for (int i = 0; i < timeout; i++) {
      println("Waiting for proxy");
      if (checkifPortIsOccupied(port, host)) {
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
  private boolean checkifPortIsOccupied(int port, String host) {
    try {
      Socket socket = new Socket(host, port);
      socket.close();
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  private String usabilityMode() {
    if (usability) {
      return "usability";
    }
    return "base";
  }

  public Process launchAndDetatch(boolean privateWindow, String[] url) {
    validateUserDir();
    if (waitForProxy()) {
      String profileDirectory = I2PFirefoxProfileBuilder.profileDirectory();
      if (I2PFirefoxProfileChecker.validateProfileDirectory(profileDirectory)) {
        println("Valid profile directory: " + profileDirectory);
      } else {
        println("Invalid profile directory: " + profileDirectory +
                " rebuilding...");
        if (!I2PFirefoxProfileBuilder.copyBaseProfiletoProfile(
                usabilityMode())) {
          println("Failed to rebuild profile directory: " + profileDirectory);
          return null;
        } else {
          println("Rebuilt profile directory: " + profileDirectory);
        }
      }
      if (validateProfileFirstRun(profileDirectory)) {
        if (isWindows()) {
          ProcessBuilder hpb = headlessProcessBuilder(url);
          try {
            Process hp = hpb.start();
            try {
              boolean hev = hp.waitFor(20, TimeUnit.SECONDS);
              if (!hev)
                hp.destroy();
              println("Headless browser run completed, exit: " + hev);
            } catch (InterruptedException e) {
              println("Headless browser error "+e.toString());
            }
          } catch (IOException e) {
            println("Headless browser error "+e.toString());
          }
        }
      }

      ProcessBuilder pb;
      if (privateWindow) {
        pb = privateProcessBuilder(url);
      } else {
        pb = defaultProcessBuilder(url);
      }
      try {
        System.out.println(pb.command());
        p = pb.start();
        println("I2PFirefox");
        sleep(2000);
        return p;
      } catch (Throwable e) {
        System.out.println(e);
      }
    }
    return null;
  }

  /**
   * Populates a profile directory with a proxy configuration.
   * Waits for an HTTP proxy on the port 4444 to be ready.
   * Launches Firefox with the profile directory.
   *
   * @param bool if true, the profile will be ephemeral(i.e. a --private-window
   *     profile).
   * @param String[] a list of URL's to pass to the browser window
   * @since 0.0.17
   */
  public void launch(boolean privateWindow, String[] url) {
    if (waitForProxy()) {
      p = launchAndDetatch(privateWindow, url);
      if (p == null)
        return;
      try {
        println("Waiting for I2PFirefox to close...");
        int exit = p.waitFor();
        println("I2PFirefox exited with value: " + exit);
      } catch (Exception e) {
        println("Error: " + e.getMessage());
      }
    }
  }

  /**
   * Populates a profile directory with a proxy configuration.
   * Waits for an HTTP proxy on the port 4444 to be ready.
   * Launches Firefox with the profile directory.
   *
   * @param bool if true, the profile will be ephemeral(i.e. a --private-window
   *     profile).
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
        println("Valid URL: " + inUrl);
        return inUrl;
      }
    }
    return "";
  }

  public static void main(String[] args) {
    validateUserDir();
    boolean privateBrowsing = false;
    println("checking for private browsing");
    ArrayList<String> visitURL = new ArrayList<String>();
    if (args != null) {
      if (args.length > 0) {
        for (String arg : args) {
          if (arg.equals("-private")) {
            privateBrowsing = true;
            println(
                "private browsing is true, profile will be discarded at end of session");
          }
          if (arg.equals("-usability")) {
            usability = true;
          }
          if (!arg.startsWith("-")) {
            // check if it's a URL
            visitURL.add(ValidURL(arg));
          }
        }
      }
    }
    println("I2PFirefox");
    I2PFirefox i2pFirefox = new I2PFirefox();
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
