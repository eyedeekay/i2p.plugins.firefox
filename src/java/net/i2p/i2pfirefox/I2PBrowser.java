package net.i2p.i2pfirefox;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;

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
public class I2PBrowser extends I2PCommonBrowser {
  private final I2PFirefox i2pFirefox = new I2PFirefox();
  private final I2PChromium i2pChromium = new I2PChromium();
  private final I2PGenericUnsafeBrowser i2pGeneral =
      new I2PGenericUnsafeBrowser();
  private final Toolkit toolkit = Toolkit.getDefaultToolkit();
  private final SystemTray tray = initTray();
  private final Image image = toolkit.getImage("icon.png");
  private final TrayIcon icon = initIcon();
  private final PopupMenu menu = initMenu();

  private final Menu submenuStrict = new Menu("Strict Mode");
  private final MenuItem launchRegularBrowserStrict =
      new MenuItem("Launch I2P Browser");
  private final MenuItem launchPrivateBrowserStrict =
      new MenuItem("Launch I2P Browser - Throwaway Session");
  private final Menu submenuUsability = new Menu("Usability Mode");
  private final MenuItem launchRegularBrowserUsability =
      new MenuItem("Launch I2P Browser");
  private final MenuItem launchPrivateBrowserUsability =
      new MenuItem("Launch I2P Browser - Throwaway Session");
  private final MenuItem launchConfigBrowserUsability =
      new MenuItem("Launch I2P Console");
  private final MenuItem closeItem = new MenuItem("Close");

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
  public I2PBrowser() {
    initIconFile();
  }

  /**
   * Construct an I2PBrowser class which automatically determines which browser
   * to use.
   *
   * @since 0.0.18
   */
  public I2PBrowser(String browserPath) {
    I2PGenericUnsafeBrowser.BROWSER = browserPath;
    initIconFile();
  }

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
        startupSystray();

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
  protected static boolean systrayIsRunningExternally() {
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

  private File initIconFile() {
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
    return iconFile;
  }

  private TrayIcon initIcon() {
    TrayIcon icon = new TrayIcon(image, "I2P Browser Profile Controller", menu);
    icon.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
          logger.info("Menu clicked");
          icon.getPopupMenu().show(null, 100, 100);
        }
      }
    });
    icon.setImageAutoSize(true);
    return icon;
  }
  protected void startupSystray() {
    logger.info("Setting up systray");
    File systrayIsRunningFile =
        new File(runtimeDirectory(""), "systray.running");
    if (systrayIsRunningFile.exists()) {
      try {
        if (useSystray) {
          logger.info("Starting systray");
          try {
            if (systray()) {
              logger.info("Systray started");
            }
          } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        logger.info("Adding icon to systray");
        tray.add(icon);
      } catch (AWTException e) {
        logger.warning(e.toString());
      }
    }
  }
  protected void shutdownSystray() {
    tray.remove(icon);
    File systrayIsRunningFile =
        new File(runtimeDirectory(""), "systray.running");
    if (systrayIsRunningFile.exists())
      systrayIsRunningFile.delete();
  }
  public boolean systray() throws Exception {
    if (tray == null)
      throw new Exception("System Tray is Null Exception");
    launchRegularBrowserStrict.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String[] args = {"-strict"};
        main(args);
      }
    });
    submenuStrict.add(launchRegularBrowserStrict);
    logger.info("Added strict mode browser");
    launchPrivateBrowserStrict.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String[] args = {"-private", "-strict"};
        main(args);
      }
    });
    submenuStrict.add(launchPrivateBrowserStrict);
    logger.info("Added strict+private mode browser");
    menu.add(submenuStrict);
    logger.info("Added strict mode submenu");
    launchRegularBrowserUsability.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String[] args = {"-usability"};
        main(args);
      }
    });
    submenuUsability.add(launchRegularBrowserUsability);
    logger.info("Added usability mode browser");
    launchPrivateBrowserUsability.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String[] args = {"-private", "-strict"};
        main(args);
      }
    });
    submenuUsability.add(launchPrivateBrowserUsability);
    logger.info("Added usability+private mode browser");
    launchConfigBrowserUsability.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String[] args = {"-usability", "-app", "http://127.0.0.1:7657"};
        main(args);
      }
    });
    menu.add(submenuUsability);
    menu.add(launchConfigBrowserUsability);
    logger.info("Added config-only browser");
    logger.info("Added usability mode submenu");
    closeItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) { shutdownSystray(); }
    });
    menu.add(closeItem);
    icon.setPopupMenu(menu);
    logger.info("Added close menu item");
    return true;
  }
}