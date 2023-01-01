package net.i2p.i2pfirefox;

import java.io.File;

//import org.lobobrowser.main;

public class I2PPureJavaBrowser extends I2PCommonBrowser {
    private final int DEFAULT_TIMEOUT = 200;
    public String BROWSER = "";

    //
    public ProcessBuilder baseProcessBuilder(String[] args) {
        return null;
    }
  
    /**
     * delete the runtime directory
     *
     * @return true if successful, false if not
     */
    public boolean deleteRuntimeDirectory() {
      File rtd = runtimeDirectory(true);
      if (rtd.exists()) {
        rtd.delete();
        return true;
      }
      return false;
    }
  
    /**
     * get the runtime directory, creating it if create=true
     *
     * @param create if true, create the runtime directory if it does not exist
     * @return the runtime directory, or null if it could not be created
     * @since 0.0.18
     */
    public File runtimeDirectory(boolean create) {
      String rtd = runtimeDirectory();
      return runtimeDirectory(create, rtd);
    }
  
    /**
     * get the correct runtime directory
     *
     * @return the runtime directory, or null if it could not be created or
     *     found
     * @since 0.0.18
     */
    public String runtimeDirectory() {
      // get the I2P_BROWSER_DIR environment variable
      String rtd = System.getenv("I2P_BROWSER_DIR");
      // if it is not null and not empty
      if (rtd != null && !rtd.isEmpty()) {
        // check if the file exists
        File rtdFile = new File(rtd);
        if (rtdFile.exists()) {
          // if it does, return it
          return rtd;
        }
      }
      return runtimeDirectory("");
    }
  
    public Process launchAndDetatch(boolean privateWindow, String[] url) {
      return null;
    }
  
    public void launch(boolean privateWindow, String[] url) {

    }

    public static void main(String[] args) {
    }
}
