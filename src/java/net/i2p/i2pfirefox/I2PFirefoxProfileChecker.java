package net.i2p.i2pfirefox;

import java.io.File;

public class I2PFirefoxProfileChecker {
    public static void main(String[] args) {
        String profileDirectory = I2PFirefoxProfileBuilder.profileDirectory();
        if (profileDirectory == null) {
            System.out.println("No profile directory found");
            return;
        }
        System.out.println("Profile directory: " + profileDirectory);
        boolean ok = validateProfileDirectory(profileDirectory);
        if (ok) {
            System.out.println("Profile directory is valid");
        } else {
            System.out.println("Profile directory is invalid");
        }
    }    
    public static boolean validateProfileDirectory(String profileDirectory) {
        File profileDir = new File(profileDirectory);
        if (!profileDir.exists()) {
            System.out.println("Profile directory does not exist");
            return false;
        }
        if (!profileDir.isDirectory()) {
            System.out.println("Profile directory is not a directory");
            return false;
        }
        if (!profileDir.canRead()) {
            System.out.println("Profile directory is not readable");
            return false;
        }
        if (!profileDir.canWrite()) {
            System.out.println("Profile directory is not writable");
            return false;
        }
        if (!validateFile(profileDir+"/prefs.js")){
            System.out.println("prefs.js is not valid");
            return false;
        }
        if (!validateFile(profileDir+"/user.js")){
            System.out.println("user.js is not valid");
            return false;
        }
        if (!validateExtensionDirectory(profileDir+"/extensions")){
            System.out.println("extensions directory is invalid");
            return false;
        }
        return true;
    }
    public static boolean validateFile(String file) {
        File f = new File(file);
        if (!f.exists()) {
            System.out.println("User JavaScript file does not exist");
            return false;
        }
        if (!f.isFile()) {
            System.out.println("User JavaScript file is not a file");
            return false;
        }
        if (!f.canRead()) {
            System.out.println("User JavaScript file is not readable");
            return false;
        }
        if (!f.canWrite()) {
            System.out.println("User JavaScript file is not writable");
            return false;
        }
        return true;
    }
    public static boolean validateExtensionDirectory(String extensionDirectory) {
        File extensionDir = new File(extensionDirectory);
        if (!extensionDir.exists()) {
            System.out.println("Extension directory does not exist");
            return false;
        }
        if (!extensionDir.isDirectory()) {
            System.out.println("Extension directory is not a directory");
            return false;
        }
        if (!extensionDir.canRead()) {
            System.out.println("Extension directory is not readable");
            return false;
        }
        if (!extensionDir.canWrite()) {
            System.out.println("Extension directory is not writable");
            return false;
        }
        return true;
    }
}
