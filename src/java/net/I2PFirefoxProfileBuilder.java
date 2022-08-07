package net;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class I2PFirefoxProfileBuilder {
    private static boolean strict;

    private static String profileDir(String file) {
        File profileDir = new File(file, "i2p.firefox.profile");
        // make sure the directory exists
        if (profileDir.exists()) {
            return profileDir.getAbsolutePath();
        } else {
            // create the directory
            profileDir.mkdir();
            return profileDir.getAbsolutePath();
        }
    }

    public static String profileDirectory() {
        String pd = System.getenv("I2P_FIREFOX_PROFILE");
        if (pd != null && !pd.isEmpty()) {
            File pdf = new File(pd);
            if (pdf.exists() && pdf.isDirectory()) {
                return pd;
            }
        }
        String rtd = RuntimeDirectory();
        return profileDir(rtd);
    }
    
    private static String baseProfileDir(String file) {
        File profileDir = new File(file, "i2p.firefox.base.profile");
        // make sure the directory exists
        if (profileDir.exists()) {
            return profileDir.getAbsolutePath();
        } else {
            // create the directory
            profileDir.mkdir();
            return profileDir.getAbsolutePath();
        }
    }

    public static String baseProfileDirectory() {
        String pd = System.getenv("I2P_FIREFOX_BASE_PROFILE");
        if (pd != null && !pd.isEmpty()) {
            File pdf = new File(pd);
            if (pdf.exists() && pdf.isDirectory()) {
                return pd;
            }
        }
        String rtd = RuntimeDirectory();
        return baseProfileDir(rtd);
    }

    public static File RuntimeDirectory(boolean create) {
        String rtd = RuntimeDirectory();
        File rtdFile = new File(rtd);
        if (create) {
            if (!rtdFile.exists()) {
                rtdFile.mkdir();
            }
        }
        return new File(rtd);
    }

    public static String RuntimeDirectory() {
        // get the I2P_FIREFOX_DIR environment variable
        String rtd = System.getenv("I2P_FIREFOX_DIR");
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
    public static boolean CopyBaseProfiletoProfile() {
        String baseProfile = baseProfileDirectory();
        String profile = profileDirectory();
        if (baseProfile.isEmpty() || profile.isEmpty()) {
            return false;
        }
        File baseProfileDir = new File(baseProfile);
        File profileDir = new File(profile);
        if (!baseProfileDir.exists() || !profileDir.exists()) {
            return false;
        }
        try {
            Files.copy(baseProfileDir.toPath(), profileDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Error copying base profile to profile"+e);
            return false;
        }
        if (!CopyStrictOptions()){
            return false;
        }
        return true;
    }
    public static boolean CopyStrictOptions() {
        if (!strict){
            return true;
        }
        String baseProfile = baseProfileDirectory();
        String profile = profileDirectory();
        if (baseProfile.isEmpty() || profile.isEmpty()) {
            return false;
        }
        File baseProfileDir = new File(baseProfile);
        File profileDir = new File(profile);
        if (!baseProfileDir.exists() || !profileDir.exists()) {
            return false;
        }
        File baseOverrides = new File(baseProfile, "strict-overrides.js");
        File userOverrides = new File(baseProfile, "user-overrides.js");
        if (!baseOverrides.exists()) {
            return false;
        }
        try {
            Files.copy(baseOverrides.toPath(), userOverrides.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            System.out.println("Error copying base profile to profile"+e);
            return false;
        }
        return true;
    }

    I2PFirefoxProfileBuilder() {
        I2PFirefoxProfileBuilder.strict = false;
    }
    I2PFirefoxProfileBuilder(boolean strict) {
        I2PFirefoxProfileBuilder.strict = strict;
    }
}
