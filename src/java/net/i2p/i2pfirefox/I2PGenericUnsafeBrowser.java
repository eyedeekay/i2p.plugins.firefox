package net.i2p.i2pfirefox;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * I2PChromiumProfileChecker.java
 * Copyright (C) 2022 idk <hankhill19580@gmail.com>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License. See LICENSE.md for details.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * I2PGenericUnsafeBrowser is a wrapper which sets common environment
 * variables for the process controlled by a processbuilder.
 * 
 * @author idk
 * @since 0.0.18
 */

public class I2PGenericUnsafeBrowser {
    private final String unsafeBrowserPath;
    I2PGenericUnsafeBrowser() {
        unsafeBrowserPath = findUnsafeBrowserAnywhere();
    }

    private static String getOperatingSystem() {
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")) {
            return "Windows";
        } else if (os.contains("Linux")) {
            return "Linux";
        } else if (os.contains("BSD")) {
            return "BSD";
        } else if (os.contains("Mac")) {
            return "Mac";
        }
        return "Unknown";
    }

    /**
     * Obtains the default browser for the Windows platform, which by now should be Edgium in
     * the worst-case scenario but in case it isn't, we can use this function to figure it out.
     * Adapted from:
     * https://stackoverflow.com/questions/15852885/method-returning-default-browser-as-a-string
     * and from:
     * https://github.com/i2p/i2p.i2p/blob/master/apps/systray/java/src/net/i2p/apps/systray/UrlLauncher.java
     * 
     * @return path to the default browser ready for execution. Empty string on Linux and OSX.
    */
    public static String getDefaultWindowsBrowser() {
        if (getOperatingSystem() == "Windows"){
            String defaultBrowser = getDefaultOutOfRegistry("HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Windows\\Shell\\Associations\\URLAssociations\\https\\UserChoice") 
            if (defaultBrowser != "")
                return defaultBrowser;
            defaultBrowser = getDefaultOutOfRegistry("HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Windows\\Shell\\Associations\\URLAssociations\\http\\UserChoice") 
                if (defaultBrowser != "")
                    return defaultBrowser;
            defaultBrowser = getDefaultOutOfRegistry("HKEY_LOCAL_MACHINE\\SOFTWARE\\Classes\\microsoft-edge\\shell\\open\\command");
            if (defaultBrowser != "")
                return defaultBrowser;
            defaultBrowser = getDefaultOutOfRegistry("HKEY_CLASSES_ROOT\\http\\shell\\open\\command");
            if (defaultBrowser != "")
                return defaultBrowser;
        }
        return "";
    }

    public static String getDefaultOutOfRegistry(String hkeyquery){
        try {
            // Get registry where we find the default browser
            Process process = Runtime.getRuntime().exec("REG QUERY " + hkeyquery);
            Scanner kb = new Scanner(process.getInputStream());
            while (kb.hasNextLine()) {
                String line = kb.nextLine();
                if (line.contains("(Default")){
                    String[] splitLine = line.split("  ");
                    kb.close();
                    return splitLine[splitLine.length-1].replace("%1", "").replaceAll("\\s+$", "");
                }
            }
            // Match wasn't found, still need to close Scanner
            kb.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //"HKEY_CURRENT_USER\SOFTWARE\Microsoft\Windows\Shell\Associations\URLAssociations\(http|https)\UserChoice"
    public static String findUnsafeBrowserAnywhere() {
        return getDefaultWindowsBrowser();
    }

    public ProcessBuilder baseProcessBuilder(String[] args) {
        return null;
    }

    public static void main(String[] args) {
        String browser = findUnsafeBrowserAnywhere();
        System.out.println(browser);
    }
    
}