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
     * 
     * @return path to the default browser ready for execution. Empty string on Linux and OSX.
    */
    public static String getDefaultWindowsBrowser() {
        if (getOperatingSystem() == "Windows"){
            try {
                // Get registry where we find the default browser
                Process process = Runtime.getRuntime().exec("REG QUERY HKEY_CLASSES_ROOT\\http\\shell\\open\\command");
                Scanner kb = new Scanner(process.getInputStream());
                while (kb.hasNextLine()) {
                    String line = kb.nextLine();
                    if (line.contains("(Default")){
                        String[] splitLine = line.split("\\t+");
                        kb.close();
                        return splitLine[splitLine.length-1];
                    }
                }
                // Match wasn't found, still need to close Scanner
                kb.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

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