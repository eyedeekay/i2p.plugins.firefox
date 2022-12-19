package net.i2p.i2pfirefox;

import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import net.i2p.I2PAppContext;
import net.i2p.app.ClientApp;
import net.i2p.app.ClientAppManager;
import net.i2p.app.ClientAppState;

/**
 * I2PBrowser.java
 * Copyright (C) 2022 idk <hankhill19580@gmail.com>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License. See LICENSE.md for details.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * @description I2PBrowser is a class that is used to open a browser window to
 * the I2P network. It automatically detects the operating system and available
 * browsers, and selects the best one to use, with Tor Browser at the top for
 * Firefox and Brave at the top for Chrome.
 *
 * @author idk
 * @since 0.0.16
 */
public class I2PBrowser extends I2PCommonBrowser implements ClientApp {
  private final I2PFirefox i2pFirefox = new I2PFirefox();
  private final I2PChromium i2pChromium = new I2PChromium();
  private final I2PGenericUnsafeBrowser i2pGeneral =
      new I2PGenericUnsafeBrowser();
  private final SystemTray tray = initTray();
  private final TrayIcon icon = initIcon();
  private final PopupMenu menu = initMenu();
  public boolean firefox = false;
  public boolean chromium = false;
  public boolean generic = false;
  public boolean chromiumFirst = false;
  public boolean usability = false;
  public int privateBrowsing = 0;
  static private boolean outputConfig = false;
  static private boolean useSystray = true;

  private void launchFirefox(int privateWindow, String[] url) {
    logger.info("I2PFirefox" + privateWindow);
    I2PFirefox.usability = usability;
    if (outputConfig)
      i2pFirefox.storeFirefoxDefaults();
    i2pFirefox.launch(privateWindow, url);
  }
  private void launchChromium(int privateWindow, String[] url) {
    logger.info("I2PChromium" + privateWindow);
    I2PChromiumProfileBuilder.usability = usability;
    if (outputConfig)
      i2pChromium.storeChromiumDefaults();
    i2pChromium.launch(privateWindow, url);
  }
  private void launchGeneric(int privateWindowInt, String[] url) {
    boolean privateWindow = false;
    if (privateWindowInt == 1)
      privateWindow = true;
    if (outputConfig)
      i2pGeneral.storeGenericDefaults();
    logger.info("I2PGeneric" + privateWindowInt);
    i2pGeneral.launch(privateWindow, url);
  }

  /**
   * Construct an I2PBrowser class which automatically determines which browser
   * to use.
   *
   * @since 0.0.16
   */
  public I2PBrowser() {}

  /**
   * Construct an I2PBrowser class which automatically determines which browser
   * to use.
   *
   * @since 0.0.18
   */
  public I2PBrowser(String browserPath) {
    I2PGenericUnsafeBrowser.BROWSER = browserPath;
  }
  public I2PBrowser(I2PAppContext context, ClientAppManager listener,
                    String[] args) {}

  public void setBrowser(String browserPath) {
    I2PGenericUnsafeBrowser.BROWSER = browserPath;
  }

  /**
   * Return true if there is a Chromium available
   *
   * @return true if Chromium is available, false otherwise
   * @since 0.0.16
   */
  public boolean hasChromium() {
    String chrome = i2pChromium.topChromium();
    if (chrome == null) {
      return false;
    }
    if (chrome.isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * Return true if there is a Firefox variant available
   *
   * @return true if Firefox variant is available, false otherwise
   * @since 0.0.16
   */
  public boolean hasFirefox() {
    String fox = i2pFirefox.topFirefox();
    if (fox == null) {
      return false;
    }
    if (fox.isEmpty()) {
      return false;
    }
    return true;
  }

  /**
   * Populates a profile directory with a proxy configuration.
   * Waits for an HTTP proxy on the port 4444 to be ready.
   * Launches either Firefox or Chromium with the profile directory.
   *
   * @param bool if true, the profile will be ephemeral(i.e. a --private-window
   *     profile).
   * @since 0.0.17
   */
  public void launch(int privateWindow, String[] url) {
    validateUserDir();
    if (generic) {
      this.launchGeneric(privateWindow, url);
      return;
    }
    if ((chromium && firefox) || (!chromium && !firefox)) {
      if (this.hasFirefox()) {
        this.launchFirefox(privateWindow, url);
      } else if (this.hasChromium()) {
        this.launchChromium(privateWindow, url);
      } else {
        this.launchGeneric(privateWindow, url);
      }
      return;
    }
    if (chromiumFirst) {
      if (chromium) {
        this.launchChromium(privateWindow, url);
      } else if (firefox) {
        this.launchFirefox(privateWindow, url);
      } else {
        this.launchGeneric(privateWindow, url);
      }
      return;
    }
    if (firefox) {
      this.launchFirefox(privateWindow, url);
    } else if (chromium) {
      this.launchChromium(privateWindow, url);
    } else {
      this.launchGeneric(privateWindow, url);
    }
    return;
  }

  /**
   * Populates a profile directory with a proxy configuration.
   * Waits for an HTTP proxy on the port 4444 to be ready.
   * Launches either Firefox or Chromium with the profile directory.
   *
   * @param bool if true, the profile will be ephemeral(i.e. a --private-window
   *     profile).
   * @since 0.0.16
   */
  public void launch(boolean privateWindow) {
    int privateWindowInt = 0;
    if (privateWindow)
      privateWindowInt = 1;
    launch(privateWindowInt, null);
  }

  /**
   * Populates a profile directory with a proxy configuration.
   * Waits for an HTTP proxy on the port 4444 to be ready.
   * Launches either Firefox or Chromium with the profile directory.
   *
   * @since 0.0.16
   */
  public void launch() { launch(false); }

  private static String ValidURL(String inUrl) {
    String[] schemes = {"http", "https"};
    for (String scheme : schemes) {
      if (inUrl.startsWith(scheme)) {
        return inUrl;
      }
    }
    return "";
  }

  public static void main(String[] args) {
    I2PBrowser i2pBrowser = new I2PBrowser();
    i2pBrowser.startup(args);
  }
  public ArrayList<String> parseArgs(String[] args) {
    validateUserDir();
    int privateBrowsing = 0;
    logger.info("I2PBrowser");
    ArrayList<String> visitURL = new ArrayList<String>();
    if (args != null) {
      if (args.length > 0) {
        for (String arg : args) {
          if (arg.equals("-private")) {
            this.privateBrowsing = 1;
          }
          if (arg.equals("-chromium")) {
            this.chromium = true;
          }
          if (arg.equals("-firefox")) {
            this.firefox = true;
          }
          if (arg.equals("-usability")) {
            this.usability = true;
          }
          if (arg.equals("-strict")) {
            this.usability = false;
          }
          if (arg.equals("-generic")) {
            this.generic = true;
          }
          if (arg.equals("-app")) {
            this.usability = true;
            privateBrowsing = 2;
          }
          if (arg.equals("-outputconfig")) {
            outputConfig = true;
          }
          if (arg.equals("-nosystray")) {
            useSystray = false;
          }
          if (arg.equals("-noproxycheck")) {
            logger.info("zeroing out proxy check");
            this.setProxyTimeoutTime(0);
          }
          if (!arg.startsWith("-")) {
            visitURL.add(ValidURL(arg));
          }
        }
      }
    }
    return visitURL;
  }
  public void startup(String[] args) {
    ArrayList<String> visitURL = parseArgs(args);
    try {
      if (useSystray) {
        logger.info("Starting systray");
        systray(args);
        Runtime.getRuntime().addShutdownHook(new Thread() {
          @Override
          public void run() {
            shutdownSystray();
          }
        });
      }
    } catch (Exception e) {
      logger.warning(e.toString());
    }
    this.launch(this.privateBrowsing,
                visitURL.toArray(new String[visitURL.size()]));
  }
  private static boolean systrayIsRunningExternally() {
    File systrayIsRunningFile =
        new File(runtimeDirectory(""), "systray.running");
    if (systrayIsRunningFile.exists()) {
      logger.info("Systray is already running in another process");
      return true;
    }
    try {
      FileWriter myWriter = new FileWriter(systrayIsRunningFile);
      myWriter.write("systray is running");
      myWriter.close();
    } catch (IOException ioe) {
      logger.warning(ioe.toString());
      return true;
    }
    return false;
  }
  private void shutdownSystray() {
    File systrayIsRunningFile =
        new File(runtimeDirectory(""), "systray.running");
    if (systrayIsRunningFile.exists())
      systrayIsRunningFile.delete();
    tray.remove(icon);
  }
  private SystemTray initTray() {
    if (systrayIsRunningExternally()) {
      return null;
    }
    if (!SystemTray.isSupported()) {
      logger.warning("SystemTray is not supported");
      return null;
    }
    return SystemTray.getSystemTray();
  }

  private PopupMenu initMenu() {
    PopupMenu menu = new PopupMenu();
    return menu;
  }

  private TrayIcon initIcon() {
    File iconFile = new File(runtimeDirectory(""), "icon.png");
    if (!iconFile.exists()) {
      InputStream resources =
          I2PBrowser.class.getClassLoader().getResourceAsStream("icon.png");
      try {
        OutputStream fos = new FileOutputStream(iconFile);
        copy(resources, fos);
      } catch (IOException e) {
        logger.warning(e.toString());
      }
    }
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image image = toolkit.getImage("icon.png");

    TrayIcon icon = new TrayIcon(image, "I2P Browser Profile Controller", menu);
    icon.setImageAutoSize(true);
    return icon;
  }
  public boolean systray(String[] args) throws Exception {
    if (tray == null)
      throw new Exception("System Tray is Null Exception");

    tray.add(icon);
    Menu submenuStrict = new Menu("Strict Mode");
    MenuItem launchRegularBrowserStrict = new MenuItem("Launch I2P Browser");
    launchRegularBrowserStrict.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ArrayList<String> argsList = new ArrayList<String>();
        argsList.addAll(Arrays.asList(args));
        argsList.add("-strict");
        main(argsList.toArray(args));
      }
    });
    submenuStrict.add(launchRegularBrowserStrict);

    MenuItem launchPrivateBrowserStrict =
        new MenuItem("Launch I2P Browser - Throwaway Session");
    launchPrivateBrowserStrict.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ArrayList<String> argsList =
            new ArrayList<String>(Arrays.asList(new String[] {"-private"}));
        argsList.addAll(Arrays.asList(args));
        argsList.add("-strict");
        main(argsList.toArray(args));
      }
    });
    submenuStrict.add(launchPrivateBrowserStrict);

    MenuItem launchConfigBrowserStrict = new MenuItem("Launch I2P Console");
    launchConfigBrowserStrict.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ArrayList<String> argsList = new ArrayList<String>(
            Arrays.asList(new String[] {"-app", "http://127.0.0.1:7657"}));
        argsList.addAll(Arrays.asList(args));
        argsList.add("-strict");
        main(argsList.toArray(args));
      }
    });
    submenuStrict.add(launchConfigBrowserStrict);
    menu.add(submenuStrict);

    Menu submenuUsability = new Menu("Usability Mode");
    MenuItem launchRegularBrowserUsability = new MenuItem("Launch I2P Browser");
    launchRegularBrowserUsability.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ArrayList<String> argsList = new ArrayList<String>();
        argsList.addAll(Arrays.asList(args));
        argsList.add("-usability");
        main(argsList.toArray(args));
      }
    });
    submenuUsability.add(launchRegularBrowserUsability);

    MenuItem launchPrivateBrowserUsability =
        new MenuItem("Launch I2P Browser - Throwaway Session");
    launchPrivateBrowserUsability.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ArrayList<String> argsList =
            new ArrayList<String>(Arrays.asList(new String[] {"-private"}));
        argsList.addAll(Arrays.asList(args));
        argsList.add("-usability");
        main(argsList.toArray(args));
      }
    });
    submenuUsability.add(launchPrivateBrowserUsability);

    MenuItem launchConfigBrowserUsability = new MenuItem("Launch I2P Console");
    launchConfigBrowserUsability.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        ArrayList<String> argsList = new ArrayList<String>(
            Arrays.asList(new String[] {"-app", "http://127.0.0.1:7657"}));
        argsList.addAll(Arrays.asList(args));
        argsList.add("-usability");
        main(argsList.toArray(args));
      }
    });
    submenuUsability.add(launchConfigBrowserUsability);
    menu.add(submenuUsability);

    MenuItem closeItem = new MenuItem("Close");
    closeItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        shutdownSystray();

        // System.exit(0);
      }
    });
    menu.add(closeItem);
    return true;
  }
  public String getDisplayName() { return "Browser Profile Manager"; }
  public String getName() { return "browserProfileManager"; }
  public void shutdown(String[] args) { shutdownSystray(); }
  public void startup() {
    String[] args = {};
    try {
      this.startup(args);
    } catch (Exception e) {
      logger.info(e.toString());
    }
  }
  public ClientAppState getState() {
    if (systrayIsRunningExternally()) {
      return ClientAppState.RUNNING;
    }
    return ClientAppState.STOPPED;
  }
}