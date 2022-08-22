package net.i2p.i2pfirefox;

import java.io.File;

/*
 * I2PChromiumProfileChecker.java
 * Copyright (C) 2022 idk <hankhill19580@gmail.com>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License. See LICENSE.md for details.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * I2PChromiumProfileChecker is a class that checks if the Chromium profile directory
 * exists and is valid.
 * 
 * @author idk
 * @since 0.0.1
 */
public class I2PChromiumProfileChecker {
    /*
     * Output feedback if the profile directory is valid or invalid
     * 
     * @description Output feedback if the profile directory is valid or invalid
     * @since 0.0.1
     */
    public static void main(String[] args) {
        String profileDirectory = I2PChromiumProfileBuilder.profileDirectory();
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
    /*
     * Return true if the profile directory is valid.
     * 
     * @param profileDirectory the profile directory to check
     * @return true if the profile directory is valid, false otherwise
     * @since 0.0.1
     */
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
        if (!validateExtensionDirectory(profileDir+"/extensions")){
            System.out.println("extensions directory is invalid");
            return false;
        }
        return true;
    }
    /*
     * Return true if the extension directory is valid.
     * 
     * @param extensionDirectory the extension directory to check
     * @return true if the extension directory is valid, false otherwise
     * @since 0.0.1
     */
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
    /*
     * Return true if the extension directory is valid.
     * 
     * @param extensionDirectory the extension directory to check
     * @return true if the extension directory is valid, false otherwise
     * @since 0.0.1
     */
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
