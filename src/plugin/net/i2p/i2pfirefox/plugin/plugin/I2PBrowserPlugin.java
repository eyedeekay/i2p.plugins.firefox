package net.i2p.i2pfirefox.plugin;

import java.awt.GraphicsEnvironment;
import net.i2p.I2PAppContext;
import net.i2p.app.ClientApp;
import net.i2p.app.ClientAppState;
import net.i2p.app.MenuCallback;
import net.i2p.app.MenuHandle;
import net.i2p.app.MenuService;
import net.i2p.desktopgui.ExternalMain;
import net.i2p.i2pfirefox.I2PBrowser;
import net.i2p.util.I2PAppThread;
import net.i2p.util.SystemVersion;
/**
 * I2PBrowserPlugin.java
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
public class I2PBrowserPlugin extends I2PBrowser implements ClientApp {
  private final I2PAppContext _context = new I2PAppContext();
  private static final String PROP_DTG_ENABLED = "desktopgui.enabled";
  private final I2PBrowser i2pBrowser = new I2PBrowser();
  public String getDisplayName() { return "I2P Browser"; }
  public String getName() { return "I2P Browser"; }
  public ClientAppState getState() { return ClientAppState.RUNNING; }
  public void shutdown(String[] args) {}
  public void startup() {
    try {
      String url = "http://proxy.i2p";
      System.out.println(
          "Starting I2P Browser tray manager by testing http://proxy.i2p");
      MenuService dtg = startTrayApp();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ie) {
      }
      i2pBrowser.launch(false, new String[] {url});
      if (dtg != null) {
        dtg.addMenu("Launch I2P Browser", new Starter(dtg));
        dtg.addMenu("Quit I2P Browser", new Stopper(dtg));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // Copied directly from I2PSnark-standalone
  private MenuService startTrayApp() {
    try {
      if (isSystrayEnabled(_context)) {
        System.setProperty("java.awt.headless", "false");
        ExternalMain dtg =
            new ExternalMain(_context, _context.clientAppManager(), null);
        dtg.startup();
        return dtg;
      }
    } catch (Throwable t) {
      t.printStackTrace();
    }
    return null;
  }

  // Copied directly from I2PSnark-standalone where it is used to determine
  // whether to launch the tray app Our environment should basically never be
  // headless, that doesn't make any sense, but something tells me I should
  // leave that check in.
  private static boolean isSystrayEnabled(I2PAppContext context) {
    if (GraphicsEnvironment.isHeadless())
      return false;
    // default false except on OSX and Windows,
    // and on Linux KDE and LXDE.
    // Xubuntu XFCE works but doesn't look very good
    // Ubuntu Unity was far too buggy to enable
    // Ubuntu GNOME does not work, SystemTray.isSupported() returns false
    String xdg = System.getenv("XDG_CURRENT_DESKTOP");
    boolean dflt = SystemVersion.isWindows() || SystemVersion.isMac() ||
                   //"XFCE".equals(xdg) ||
                   "KDE".equals(xdg) || "LXDE".equals(xdg);
    return context.getProperty(PROP_DTG_ENABLED, dflt);
  }

  /**
   *  Callback when Start I2PBrowser is clicked in systray
   *  @since 0.9.61
   */
  public class Starter implements MenuCallback {
    private final MenuService _ms;
    public Starter(MenuService ms) { _ms = ms; }
    public void clicked(MenuHandle menu) {
      _ms.disableMenu(menu);
      _ms.updateMenu("I2PBrowser-Launcher starting", menu);
      Thread t = new I2PAppThread(new StarterThread(),
                                  "I2PBrowser-Launcher start", true);
      t.start();
    }
  }

  /**
   *  Threaded startup
   *  @since 0.9.61
   */
  public class StarterThread implements Runnable {
    public void run() { i2pBrowser.launch(false); }
  }

  /**
   *  Callback when Stop I2PBrowser is clicked in systray
   *  @since 0.9.61
   */
  public class Stopper implements MenuCallback {
    private final MenuService _ms;
    public Stopper(MenuService ms) { _ms = ms; }
    public void clicked(MenuHandle menu) {
      _ms.disableMenu(menu);
      _ms.updateMenu("I2PBrowser-Launcher stopping", menu);
      Thread t = new I2PAppThread(new StopperThread(),
                                  "I2PBrowser-Launcher stop", true);
      t.start();
    }
  }

  /**
   *  Threaded startup
   *  @since 0.9.61
   */
  public class StopperThread implements Runnable {
    public void run() { i2pBrowser.stop(); }
  }
}