package net.i2p.i2pfirefox;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
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
 * ALWAYS ALWAYS ALWAYS try the Firefox and Chromium specific launchers
 * first.
 * 
 * @author idk
 * @since 0.0.18
 */

public class I2PGenericUnsafeBrowser {
    private final String unsafeBrowserPath = findUnsafeBrowserAnywhere();
    private final int DEFAULT_TIMEOUT = 200;
    // Ideally, EVERY browser in this list should honor http_proxy, https_proxy, ftp_proxy and no_proxy.
    // in practice, this is going to be hard to guarantee. For now, we're just assuming. So don't use this until
    // I understand the situation better, unless you think you know better.
    private static final String[] browsers = {
            // This debian script tries everything in $BROWSER, then gnome-www-browser and x-www-browser
            // if X is running and www-browser otherwise. Those point to the user's preferred
            // browser using the update-alternatives system.
            "sensible-browser",
            // another one that opens a preferred browser
            "xdg-open",
            // Try x-www-browser directly
            "x-www-browser",
            "gnome-www-browser",
            // general graphical browsers that aren't Firefox or Chromium based
            "defaultbrowser",  // puppy linux
            "dillo",
            "seamonkey",
            "konqueror",
            "galeon",
            "surf",
            // Text Mode Browsers only below here
            "www-browser",
            "links",
            "lynx"
    };

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
     * It can find:
     * 
     * 1. The current user's HTTPS default browser if they configured it to be non-default
     * 2. The current user's HTTP default browser if they configured it to be non-default
     * 3. Edgium if it's available
     * 4. iexplore if it's not
     * 
     * and it will return the first one we find in exactly that order.
     * 
     * Adapted from:
     * https://stackoverflow.com/questions/15852885/method-returning-default-browser-as-a-string
     * and from:
     * https://github.com/i2p/i2p.i2p/blob/master/apps/systray/java/src/net/i2p/apps/systray/UrlLauncher.java
     * 
     * @return path to the default browser ready for execution. Empty string on Linux and OSX.
    */
    public static String getDefaultWindowsBrowser() {
        if (getOperatingSystem() == "Windows"){
            String defaultBrowser = getDefaultOutOfRegistry("HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Windows\\Shell\\Associations\\URLAssociations\\https\\UserChoice");
            if (defaultBrowser != "")
                return defaultBrowser;
            defaultBrowser = getDefaultOutOfRegistry("HKEY_CURRENT_USER\\SOFTWARE\\Microsoft\\Windows\\Shell\\Associations\\URLAssociations\\http\\UserChoice");
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

    /**
     * obtains information out of the Windows registry.
     * 
     * @param hkeyquery registry entry to ask for.
     * @return
     */
    public static String getDefaultOutOfRegistry(String hkeyquery){
        if (getOperatingSystem() == "Windows"){
            try {
                // Get registry where we find the default browser
                Process process = Runtime.getRuntime().exec("REG QUERY " + hkeyquery);
                Scanner kb = new Scanner(process.getInputStream());
                while (kb.hasNextLine()) {
                    String line = kb.nextLine();
                    if (line.contains("(Default")){
                        String[] splitLine = line.split("  ");
                        kb.close();
                        return splitLine[splitLine.length-1].replace("%1", "").replaceAll("\\s+$", "").replaceAll("\"\"", "");
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

    private static String scanAPath(String dir) {
        for (String browser : browsers) {
            File test = new File(dir, browser);
            if (test.exists()){
                return test.getAbsolutePath();
            }
        }
        return "";
    }

    public static String getAnyUnixBrowser() {
        // read the PATH environment variable and split it by ":"
        String[] path = System.getenv("PATH").split(":");
        if (path != null && path.length > 0){
            for (String p : path){
                String f = scanAPath(p);
                if (f != "") {
                    return f;
                }
            }
        }
        return "";
    }

    //
    public static String findUnsafeBrowserAnywhere() {
        if (getOperatingSystem() == "Windows"){
            return getDefaultWindowsBrowser();
        }
        return getAnyUnixBrowser();
    }

    //
    public ProcessBuilder baseProcessBuilder(String[] args) {
        String browser = findUnsafeBrowserAnywhere();
        if (!browser.isEmpty()) {
            String[] newArgs = new String[args.length+1];
            newArgs[0] = browser;
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    newArgs[i+1] = args[i];
                }
            }
            ProcessBuilder pb = new ProcessBuilder(newArgs).directory(runtimeDirectory(true));
            pb.environment().put("http_proxy","http://127.0.0.1:4444");
            pb.environment().put("https_proxy","http://127.0.0.1:4444");
            pb.environment().put("ftp_proxy","http://127.0.0.1:0");
            pb.environment().put("all_proxy","http://127.0.0.1:4444");
            pb.environment().put("no_proxy","http://127.0.0.1:7657");
            pb.environment().put("HTTP_PROXY","http://127.0.0.1:4444");
            pb.environment().put("HTTPS_PROXY","http://127.0.0.1:4444");
            pb.environment().put("FTP_PROXY","http://127.0.0.1:0");
            pb.environment().put("ALL_PROXY","http://127.0.0.1:4444");
            pb.environment().put("NO_PROXY","http://127.0.0.1:7657");
            return pb;
        } else {
            System.out.println("No Browser found.");
            return new ProcessBuilder(args);
        }
    }

    /**
     * get the runtime directory, creating it if create=true
     * 
     * @param create if true, create the runtime directory if it does not exist
     * @return the runtime directory, or null if it could not be created
     * @since 0.0.18
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

    /**
     * get the correct runtime directory
     * 
     * @return the runtime directory, or null if it could not be created or found
     * @since 0.0.18
     */
    public static String runtimeDirectory() {
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

    /**
     * Waits for an HTTP proxy on port 4444 to be ready.
     * Returns false on timeout of 200 seconds.
     * 
     * @return true if the proxy is ready, false if it is not.
     * @since 0.0.1
     */
    public boolean waitForProxy() {
        return waitForProxy(DEFAULT_TIMEOUT);
    }

    /** 
     * Waits for an HTTP proxy on port 4444 to be ready.
     * Returns false on timeout of the specified number of seconds.
     * 
     * @param timeout the number of seconds to wait for the proxy to be ready.
     * @return true if the proxy is ready, false if it is not.
     * @since 0.0.1
     */
    public boolean waitForProxy(int timeout) {
        return waitForProxy(timeout, 4444);
    }
    /**
     * Waits for an HTTP proxy on the specified port to be ready.
     * Returns false on timeout of the specified number of seconds.
     * 
     * @param timeout the number of seconds to wait for the proxy to be ready.
     * @param port the port to wait for the proxy to be ready on.
     * @return true if the proxy is ready, false if it is not.
     * @since 0.0.1
     */
    public boolean waitForProxy(int timeout, int port) {
        return waitForProxy(timeout, port, "localhost");
    }

    /**
     * Waits for an HTTP proxy on the specified port to be ready.
     * Returns false on timeout of the specified number of seconds.
     * 
     * @param timeout the number of seconds to wait for the proxy to be ready.
     * @param port the port to wait for the proxy to be ready on.
     * @param host the host to wait for the proxy to be ready on.
     * @return true if the proxy is ready, false if it is not.
     * @since 0.0.1
     */
    public boolean waitForProxy(int timeout, int port, String host) {
        for (int i = 0; i < timeout; i++) {
            if (checkifPortIsOccupied(port, host)) {
                return true;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    private boolean checkifPortIsOccupied(int port, String host) {
        try {
            Socket socket = new Socket(host, port);
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public void launch(boolean privateWindow, String[] url){
        if (waitForProxy()){
            ProcessBuilder pb;
            if (privateWindow) {
                pb = baseProcessBuilder(url);
            } else {
                pb = baseProcessBuilder(url);
            }
            try{
                System.out.println(pb.command());
                Process p = pb.start();
                System.out.println("I2PBrowser");
                sleep(2000);
                try{
                    System.out.println("Waiting for I2PBrowser to close...");
                    int exit = p.waitFor();
                    System.out.println("I2PBrowser exited with value: "+exit);
                }catch(Exception e){
                    System.out.println("Error: "+e.getMessage());
                }
            }catch(Exception e){
                System.out.println("Error: "+e.getMessage());
            }
        }
    }
    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException bad) {
            bad.printStackTrace();
            throw new RuntimeException(bad);
        }
    }

    private static String ValidURL(String inUrl){
        String[] schemes = {"http", "https"};
        for (String scheme: schemes) {
            if (inUrl.startsWith(scheme)) {
                System.out.println("Valid URL: " + inUrl);
                return inUrl;
            }
        }
        return "";
    }

    //
    public static void main(String[] args) {
        boolean privateBrowsing = false;
        System.out.println("checking for private browsing");
        ArrayList<String> visitURL = new ArrayList<String>();
        if (args != null && args.length > 0) {
            for (String arg : args) {
                if (arg.equals("-private")) {
                    privateBrowsing = true;
                    System.out.println("private browsing is true, profile will be discarded at end of session");
                }
                if (!arg.startsWith("-")){
                    // check if it's a URL
                    visitURL.add(ValidURL(arg));
                }
            }
        }
        System.out.println("I2PGenericUnsafeBrowser");
        I2PGenericUnsafeBrowser i2pBrowser = new I2PGenericUnsafeBrowser();
        i2pBrowser.launch(privateBrowsing, visitURL.toArray(new String[visitURL.size()]));
    }
    
}