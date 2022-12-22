package net.i2p.i2pfirefox;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import net.i2p.I2PAppContext;
import net.i2p.app.ClientApp;
import net.i2p.app.ClientAppManager;
import net.i2p.app.ClientAppState;
import net.i2p.util.EepGet;

public class I2PBrowserPlugin extends I2PBrowser implements ClientApp {
  private final I2PAppContext context;
  private final ClientAppManager cam;
  private final String[] args;
  private volatile boolean got = false;
  private volatile boolean shutdown = false;
  public I2PBrowserPlugin(I2PAppContext context, ClientAppManager listener,
                          String[] args) {
    this.context = context;
    this.cam = listener;
    this.args = args;
  }
  public String getDisplayName() { return "Browser Profile Manager"; }
  public String getName() { return "browserProfileManager"; }
  public void shutdown(String[] args) {
    got = true;
    shutdown = true;
    this.shutdownSystray();
  }
  private void downloadInBackground() {
    Logger threadLogger = Logger.getLogger("browserlauncherupdatethread");
    got = downloadTorrent();
    while (!got) {
      threadLogger.info("Working to download updates in the background");
      if (shutdown) {
        break;
      }
      got = downloadTorrent();
      try {
        Thread.sleep(5000);
      } catch (InterruptedException err) {
        logger.warning(err.toString());
      }
    }
    if (got) {
      try {
        File content = torrentFileContents();
        if (content.exists()) {
          content.delete();
        }
      } catch (IOException err) {
        logger.warning(err.toString());
      }
    }
  }
  public void startup() {
    Runnable r = new Runnable() {
      public void run() { downloadInBackground(); }
    };
    new Thread(r).start();
    try {
      this.startup(args);
    } catch (Exception e) {
      logger.info(e.toString());
    }
  }

  private File torrentDir() throws IOException {
    File configDir = context.getConfigDir();
    if (configDir == null) {
      throw new IOException("The I2P Config directory is null");
    }
    if (!configDir.exists()) {
      throw new IOException("The I2P Config directory does not exist");
    }
    File snarkDir = new File(configDir, "i2psnark");
    if (snarkDir == null) {
      throw new IOException("The Snark directory is null");
    }
    if (!snarkDir.exists()) {
      throw new IOException("The Snark directory does not exist");
    }
    return snarkDir;
  }
  private File torrentFile() throws IOException {
    try {
      File torrents = torrentDir();
      File torrent = new File(torrents, "i2p.plugins.firefox.torrent");
      if (torrent == null) {
        throw new IOException("Cannot download torrent, torrent file is null");
      }
      return torrent;
    } catch (IOException err) {
      logger.warning(err.toString());
    }
    return null;
  }
  private File torrentFileContents() throws IOException {
    try {
      File torrents = torrentDir();
      File torrent = new File(torrents, "i2p.plugins.firefox");
      if (torrent == null) {
        throw new IOException("Torrent directory contents are null");
      }
      return torrent;
    } catch (IOException err) {
      logger.warning(err.toString());
    }
    return null;
  }
  private boolean downloadTorrent() {
    try {
      String url =
          "http://idk.i2p/i2p.plugins.firefox/i2p.plugins.firefox.torrent";
      String name = torrentFile().getAbsolutePath();
      logger.info("Downloading " + url + " to " + name);
      EepGet eepGet =
          new EepGet​(context, true, "127.0.0.1", 4444, 5, name, url);
      if (eepGet.getNotModified()) {
        return false;
      }
      return eepGet.fetch(60, 180, 60);
    } catch (IOException err) {
      logger.warning(err.toString());
    }
    return false;
  }
  public ClientAppState getState() {
    if (systrayRunningExternally()) {
      logger.info("Firefox profile manager systray is running");
      return ClientAppState.RUNNING;
    }
    logger
        .info("Fireox profile manager systray is stopped") return ClientAppState
        .STOPPED;
  }
}
