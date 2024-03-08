package net.i2p.i2pfirefox.plugin;

import java.awt.GraphicsEnvironment;
import java.io.File;
import net.i2p.I2PAppContext;
import net.i2p.app.ClientApp;
import net.i2p.app.ClientAppManager;
import net.i2p.app.ClientAppState;
import net.i2p.app.MenuCallback;
import net.i2p.app.MenuHandle;
import net.i2p.app.MenuService;
import net.i2p.desktopgui.ExternalMain;
import net.i2p.i2pfirefox.I2PBrowser;
import net.i2p.i2pfirefox.I2PFirefox;
import net.i2p.util.I2PAppThread;
import net.i2p.util.Log;
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
  private final I2PAppContext _context;
  private final Log _log;
  private final ClientAppManager _mgr;
  private final String _args[];
  private static final String PROP_DTG_ENABLED = "desktopgui.enabled";
  private final File pluginDir;
  private final File profileDir;
  private MenuHandle lmhs;
  private MenuHandle lmhf;
  /**
   * @since 1.4.0
   * @return
   */
  public I2PBrowserPlugin() {
    _context = new I2PAppContext();
    _mgr = null;
    _args = new String[] {};
    _log = _context.logManager().getLog(I2PBrowserPlugin.class);
    pluginDir = new File(_context.getAppDir(), "plugins/i2pfirefox/");
    profileDir = new File(pluginDir, "profile/");
  }
  /**
   * @since 1.4.0
   * @return
   */
  public I2PBrowserPlugin(I2PAppContext ctx, ClientAppManager mgr,
                          String args[]) {
    _context = ctx;
    _mgr = mgr;
    _args = args;
    _log = ctx.logManager().getLog(I2PBrowserPlugin.class);
    pluginDir = new File(_context.getAppDir(), "plugins/i2pfirefox/");
    profileDir = new File(pluginDir, "profile/");
  }

  /**
   * @since 1.4.0
   * @return
   */
  public String getDisplayName() { return "I2P Browser"; }
  /**
   * @since 1.4.0
   * @return
   */
  public String getName() { return "I2P Browser"; }
  /**
   * @since 1.4.0
   * @return
   */
  public ClientAppState getState() { return ClientAppState.STOPPED; }
  /**
   * @since 1.4.0
   * @return
   */
  public void shutdown(String[] args) {
    if (!isSystrayEnabled()) {
      _log.info("I2P Browser tray manager not supported");
    } else {
      _log.info("I2P Browser tray manager shutting down");
      MenuService dtg = startTrayApp();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ie) {
      }
      if (dtg != null) {
        dtg.removeMenu(lmhs);
        dtg.removeMenu(lmhf);
      }
    }
    changeState(ClientAppState.STOPPED);
  }
  /**
   * @since 1.4.0
   * @return
   */
  public void startup() {
    changeState(ClientAppState.STOPPED);
    if (!isSystrayEnabled()) {
      _log.info("I2P Browser tray manager not supported");
      try {
        I2PBrowser i2pBrowser = new I2PBrowser(profileDir.getAbsolutePath());
        String[] args = {"http://proxy.i2p"};
        i2pBrowser.launchFirefox(0, args);
      } catch (Exception e) {
        _log.error("Error starting I2P Browser", e);
      }
    } else {
      try {
        _log.info(
            "Starting I2P Browser tray manager by testing http://proxy.i2p");
        MenuService dtg = startTrayApp();
        try {
          Thread.sleep(1000);
        } catch (InterruptedException ie) {
        }
        if (dtg != null) {
          _log.info("I2P Browser integrating with I2P tray manager");
          lmhs =
              dtg.addMenu("Launch I2P Browser (Safe Mode)", new Starter(dtg));
          dtg.showMenu(lmhs);
          dtg.enableMenu(lmhs);
          lmhf = dtg.addMenu("Launch I2P Browser (Flexible Mode)",
                             new FlexStarter(dtg));
          dtg.showMenu(lmhf);
          dtg.enableMenu(lmhf);
        } else {
          _log.info("I2P Browser tray manager not found");
        }
        I2PBrowser i2pBrowser = new I2PBrowser(profileDir.getAbsolutePath());
        String[] args = {"http://proxy.i2p"};
        i2pBrowser.launchFirefox(0, args);
      } catch (Exception e) {
        _log.error("Error starting I2P Browser tray manager", e);
      }
    }
  }

  // Copied directly from I2PSnark-standalone
  /**
   * @since 1.4.0
   * @return
   */
  private MenuService startTrayApp() {
    try {
      if (isSystrayEnabled()) {
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
  // whether to launchFirefox the tray app Our environment should basically
  // never be headless, that doesn't make any sense, but something tells me I
  // should leave that check in.
  /**
   * @since 1.4.0
   * @return
   */
  private boolean isSystrayEnabled() {
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
    return _context.getProperty(PROP_DTG_ENABLED, dflt);
  }

  /**
   * Callback when Start I2PBrowser is clicked in systray
   *
   * @since 1.4.0
   * @return
   */
  public class Starter implements MenuCallback {
    private final MenuService _ms;
    public Starter(MenuService ms) { _ms = ms; }
    public void clicked(MenuHandle menu) {
      // Thread t = new I2PAppThread(new StarterThread(),
      //"I2PBrowser-Launcher start", true);
      // t.start();
      _log.info("I2P Browser starting up");
      try {
        I2PBrowser i2pBrowser = new I2PBrowser(profileDir.getAbsolutePath());
        String[] args = {"http://proxy.i2p"};
        i2pBrowser.launchFirefox(0, args);
      } catch (Exception e) {
        _log.error("Error starting I2P Browser", e);
      }
      _log.info("I2P Browser ran");
    }
  }

  /**
   * @since 1.4.0
   * @return
   */
  public class FlexStarter implements MenuCallback {
    private final MenuService _ms;
    public FlexStarter(MenuService ms) { _ms = ms; }
    public void clicked(MenuHandle menu) {
      // Thread t = new I2PAppThread(new StarterThread(),
      //"I2PBrowser-Launcher start", true);
      // t.start();
      _log.info("I2P Browser starting up");
      try {
        I2PBrowser i2pBrowser = new I2PBrowser(profileDir.getAbsolutePath());
        i2pBrowser.usability = true;
        String[] args = {"http://proxy.i2p"};
        i2pBrowser.launchFirefox(0, args);
      } catch (Exception e) {
        _log.error("Error starting I2P Browser", e);
      }
      _log.info("I2P Browser ran");
    }
  }

  /**
   * @since 1.4.0
   * @return
   */
  private synchronized void changeState(ClientAppState state) {
    if (_mgr != null)
      _mgr.notify(this, state, null, null);
  }
  public static void main(String[] args) {
    I2PBrowserPlugin plugin = new I2PBrowserPlugin();
    try {
      plugin.startup();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}