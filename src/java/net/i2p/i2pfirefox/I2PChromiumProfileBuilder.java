package net.i2p.i2pfirefox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class I2PChromiumProfileBuilder {
    private static boolean strict;

    private static String profileDir(String file) {
        File profileDir = new File(file, "i2p.chromium.profile");
        return profileDir.getAbsolutePath();
    }

    /*
     * get the profile directory, creating it if necessary
     *
     * @return the profile directory, or null if it could not be created
     */
    public static String profileDirectory() {
        String pd = System.getenv("I2P_FIREFOX_PROFILE");
        if (pd != null && !pd.isEmpty()) {
            File pdf = new File(pd);
            if (pdf.exists() && pdf.isDirectory()) {
                return pd;
            }
        }
        String rtd = runtimeDirectory();
        return profileDir(rtd);
    }
    
    private static String baseProfileDir(String file) {
        File profileDir = new File(file, "i2p.chromium.base.profile");
        // make sure the directory exists
        if (profileDir.exists()) {
            return profileDir.getAbsolutePath();
        } else {
            // create the directory
            I2PChromiumProfileUnpacker unpacker = new I2PChromiumProfileUnpacker();
            if (!unpacker.unpackProfile(profileDir.getAbsolutePath())) {
                return null;
            }
            return profileDir.getAbsolutePath();
        }
    }

    /*
     * get the base profile directory, creating it if necessary
     *
     * @return the base profile directory, or null if it could not be created
     */
    public static String baseProfileDirectory() {
        String pd = System.getenv("I2P_FIREFOX_BASE_PROFILE");
        if (pd != null && !pd.isEmpty()) {
            File pdf = new File(pd);
            if (pdf.exists() && pdf.isDirectory()) {
                return pd;
            }else{
                I2PChromiumProfileUnpacker unpacker = new I2PChromiumProfileUnpacker();
                if (!unpacker.unpackProfile(pdf.getAbsolutePath())) {
                    return null;
                }
            }
        }
        String rtd = runtimeDirectory();
        return baseProfileDir(rtd);
    }

    /*
     * get the runtime directory, creating it if create=true
     * 
     * @param create if true, create the runtime directory if it does not exist
     * @return the runtime directory, or null if it could not be created
     * @since 0.0.1
     */
    public static File runtimeDirectory(boolean create) {
        String rtd = runtimeDirectory();
        File rtdFile = new File(rtd);
        if (create) {
            if (!rtdFile.exists()) {
                rtdFile.mkdir();
            }
        }
        return new File(rtd);
    }

    /*
     * get the correct runtime directory
     * 
     * @return the runtime directory, or null if it could not be created or found
     * @since 0.0.1
     */
    public static String runtimeDirectory() {
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

    /*
     * Copy the inert base profile directory to the runtime profile directory
     *
     * @since 0.0.1
     */
    public static boolean copyBaseProfiletoProfile() {
        String baseProfile = baseProfileDirectory();
        String profile = profileDirectory();
        System.out.println("Copying base profile to profile directory: " + baseProfile + " -> " + profile);
        if (baseProfile.isEmpty() || profile.isEmpty()) {
            return false;
        }
        File baseProfileDir = new File(baseProfile);
        File profileDir = new File(profile);
        
        try {
            System.out.println("Copying base profile to profile directory");
            copyDirectory(baseProfileDir, profileDir);
        } catch (Exception e) {
            System.out.println("Error copying base profile to profile"+e);
            return false;
        }
        System.out.println("Copied base profile to profile directory");
        // if user.js does not exist yet, make an empty one.
        //if (!touch(profileDir.toString(), "user.js")) {
            //return false;
        //}
        // if extensions does not exist yet, make an empty one.
        //if (!mkExtensionsDir(profileDir.toString())){
            //return false;
        //}

        
        return copyStrictOptions();
    }
    private static void copyDirectory(File sourceDirectory, File destinationDirectory) throws IOException {
        destinationDirectory = new File(destinationDirectory.toString().replace("i2p.chromium.base.profile", ""));
        if (!destinationDirectory.exists()) {
            destinationDirectory.mkdir();
        }
        for (String f : sourceDirectory.list()) {
            copyDirectoryCompatibityMode(new File(sourceDirectory, f), new File(destinationDirectory, f));
        }
    }

    public static void copyDirectoryCompatibityMode(File source, File destination) throws IOException {
        if (source.isDirectory()) {
            copyDirectory(source, destination);
        } else {
            copyFile(source, destination);
        }
    }

    private static void copyFile(File sourceFile, File destinationFile) throws IOException {
        try (InputStream in = new FileInputStream(sourceFile); 
            OutputStream out = new FileOutputStream(destinationFile)) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
        }
    }

    /*
     * Copy the strict options from the base profile to the profile
     *
     * @return true if successful, false otherwise
     * @since 0.0.1
     */
    public static boolean copyStrictOptions() {
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
        // if user-overrides.js does not exist yet, make an empty one.
        //if (!touch(profileDir.toString(), "user-overrides.js")) {
            //return false;
        //}
        return true;
    }

    /*
     * Construct a new Profile Builder
     * 
     * @since 0.0.1
     */
    public I2PChromiumProfileBuilder() {
        I2PChromiumProfileBuilder.strict = false;
    }

    /*
     * Construct a new Profile Builder
     * @param strict if true, the strict overrides will be copied to the profile
     * 
     * @since 0.0.1
     */
    public I2PChromiumProfileBuilder(boolean strict) {
        I2PChromiumProfileBuilder.strict = strict;
    }
}
