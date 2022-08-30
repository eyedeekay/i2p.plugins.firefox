package net.i2p.i2pfirefox;

import java.io.File;

public class I2PCommonBrowser {
    
    /**
     * get the runtime directory, creating it if create=true
     * 
     * @param create if true, create the runtime directory if it does not exist
     * @return the runtime directory, or null if it could not be created
     * @since 0.0.1
     */
    public static File runtimeDirectory(boolean create, String override) {
        String rtd = runtimeDirectory(override);
        File rtdFile = new File(rtd);
        if (create) {
            if (!rtdFile.exists()) {
                rtdFile.mkdir();
            }
        }
        return new File(rtd);
    }

    /**
     * get the correct runtime directory
     * 
     * @return the runtime directory, or null if it could not be created or found
     * @since 0.0.1
     */
    public static String runtimeDirectory(String override) {
        // get the I2P_FIREFOX_DIR environment variable
        String rtd = System.getenv(override);
        // if it is not null and not empty
        if (rtd != null && !rtd.isEmpty()) {
            // check if the file exists
            File rtdFile = new File(rtd);
            if (rtdFile.exists()) {
                // if it does, return it
                return rtd;
            }
        }
        // obtain the PLUGIN environment variable
        String plugin = System.getenv("PLUGIN");
        if (plugin != null && !plugin.isEmpty()) {
            File pluginDir = new File(plugin);
            if (pluginDir.exists()) {
                return pluginDir.toString();
            }
        }
        String userDir = System.getProperty("user.dir");
        if (userDir != null && !userDir.isEmpty()) {
            File userDir1 = new File(userDir);
            if (userDir1.exists()) {
                return userDir1.toString();
            }
        }
        String homeDir = System.getProperty("user.home");
        if (homeDir != null && !homeDir.isEmpty()) {
            File homeDir1 = new File(homeDir+"/.i2p");
            if (homeDir1.exists()) {
                return homeDir.toString();
            }
            File homeDir2 = new File(homeDir+"/i2p");
            if (homeDir2.exists()) {
                return homeDir2.toString();
            }
        }
        return "";
    }
}
