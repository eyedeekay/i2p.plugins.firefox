package net.i2p.i2pfirefox;

import java.io.File;
import java.io.IOException;
import net.i2p.I2PAppContext;
import net.i2p.app.ClientApp;
import net.i2p.app.ClientAppManager;
import net.i2p.app.ClientAppState;
import net.i2p.util.EepGet;

public class I2PBrowserPlugin extends I2PBrowser implements ClientApp {
  private final I2PAppContext context;
  private final ClientAppManager cam;
  private final String[] args;
  public I2PBrowserPlugin(I2PAppContext context, ClientAppManager listener,
                          String[] args) {
    this.context = context;
    this.cam = listener;
    this.args = args;
  }
  public String getDisplayName() { return "Browser Profile Manager"; }
  public String getName() { return "browserProfileManager"; }
  public void shutdown(String[] args) { this.shutdownSystray(); }
  public void startup() {
    try {
      boolean got = downloadTorrent();
      while (!got) {
        logger.info("Working to download updates in the background");
        got = downloadTorrent();
        Thread.sleep(5000);
      }
      if (got) {
        File content = torrentFileContents();
        if (content.exists()) {
          content.delete();
        }
      }
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
      EepGet eepGet = new EepGet​(
          context, true, "127.0.0.1", 4444, 5, torrentFile().getAbsolutePath(),
          "http://idk.i2p/i2p.plugins.firefox/i2p.plugins.firefox.torrent");
      if (eepGet.getNotModified()) {
        return false;
      }
      return eepGet.fetch();
    } catch (IOException err) {
      logger.warning(err.toString());
    }
    return false;
  }
  public ClientAppState getState() {
    if (systrayIsRunningExternally()) {
      return ClientAppState.RUNNING;
    }
    return ClientAppState.STOPPED;
  }
}
