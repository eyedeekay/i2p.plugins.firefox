package net.i2p.i2pfirefox;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
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
    this.cam = listener;
    cam.notify(this, ClientAppState.UNINITIALIZED,
               "Initializing Profile Manager Systray Plugin", null);
    this.context = context;
    this.args = args;
    cam.notify(this, ClientAppState.INITIALIZED,
               "Profile Manager Systray Plugin Initialized", null);
  }
  private static File threadLogFile() {
    validateUserDir();
    String userDir = System.getProperty("user.dir");
    File log = new File(userDir, "logs");
    if (!log.exists())
      log.mkdirs();
    return new File(log, "browserlauncherthreadlogger.log");
  }
  public String getDisplayName() { return "Browser Profile Manager"; }
  public String getName() { return "browserProfileManager"; }
  public void shutdown(String[] args) {
    cam.notify(this, ClientAppState.STOPPING,
               "Shutting down up profile manager systray", null);
    got = true;
    shutdown = true;
    this.shutdownSystray();
    cam.notify(this, ClientAppState.STOPPED,
               "Shutting down up profile manager systray", null);
  }
  private void downloadInBackground() throws IOException {
    try {
      Logger threadLogger = Logger.getLogger("browserlauncherupdatethread");
      FileHandler fh = new FileHandler(threadLogFile().toString());
      threadLogger.addHandler(fh);
      SimpleFormatter formatter = new SimpleFormatter();
      fh.setFormatter(formatter);
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
          threadLogger.warning(err.toString());
        }
      }
    } catch (IOException err) {
      // just re-throw here
      throw err;
    }
  }
  public void startup() {
    cam.notify(this, ClientAppState.STARTING,
               "Starting up profile manager systray", null);
    IOException error;
    Runnable r = new Runnable() {
      public void run() {
        try {
          downloadInBackground();
        } catch (IOException err) {
          error = err;
        }
      }
    };
    new Thread(r).start();
    try {
      this.startup(args);
      if (error != null)
        logger.warning(error.toString());
      cam.notify(this, ClientAppState.RUNNING,
                 "Starting up profile manager systray", null);
    } catch (Exception e) {
      logger.info(e.toString());
      cam.notify(this, ClientAppState.START_FAILED,
                 "Error starting profile manager systray", e);
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
    logger.info("Firefox profile manager systray is stopped");
    return ClientAppState.STOPPED;
  }
}
