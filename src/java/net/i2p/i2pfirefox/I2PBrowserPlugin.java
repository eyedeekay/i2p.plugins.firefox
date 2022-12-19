package net.i2p.i2pfirefox;

import net.i2p.I2PAppContext;
import net.i2p.app.ClientApp;
import net.i2p.app.ClientAppManager;
import net.i2p.app.ClientAppState;

public class I2PBrowserPlugin extends I2PBrowser implements ClientApp {
  public I2PBrowserPlugin(I2PAppContext context, ClientAppManager listener,
                          String[] args) {}
  public String getDisplayName() { return "Browser Profile Manager"; }
  public String getName() { return "browserProfileManager"; }
  public void shutdown(String[] args) { this.shutdownSystray(); }
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
