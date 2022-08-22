package net.i2p.i2pfirefox;


/*
 * I2PBrowser.java
 * Copyright (C) 2022 idk <hankhill19580@gmail.com>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License. See LICENSE.md for details.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * @description I2PBrowser is a class that is used to open a browser window to the I2P network.
 * It automatically detects the operating system and available browsers, and selects
 * the best one to use, with Tor Browser at the top for Firefox and Brave at the top
 * for Chrome.
 * 
 * @author idk
 * @since 0.0.16
 */
public class I2PBrowser {
    private final I2PFirefox i2pFirefox = new I2PFirefox();
    private final I2PChromium i2pChromium = new I2PChromium();
    public boolean firefox = false;
    public boolean chromium = false;
    public boolean chromiumFirst = false;
 
    private void launchFirefox(boolean privateWindow) {
        System.out.println("I2PFirefox");
        i2pFirefox.launch(privateWindow);
    }
    private void launchChromium(boolean privateWindow) {
        System.out.println("I2PChromium");
        i2pChromium.launch(privateWindow);
    }
 
    /*
     * Construct an I2PBrowser class which automatically determines which browser to use.
     * 
     * @since 0.0.16
     */
    public I2PBrowser() {
    }

    /*
     * Return true if there is a Chromium available
     * 
     * @return true if Chromium is available, false otherwise
     * @since 0.0.16
     */
    public boolean hasChromium() {
        String chrome = i2pChromium.topChromium();
        if (chrome == null) {
            return false;
        }
        if (chrome.isEmpty()) {
            return false;
        }
        return true;
    }

    /*
     * Return true if there is a Firefox variant available
     * 
     * @return true if Firefox variant is available, false otherwise
     * @since 0.0.16
     */
    public boolean hasFirefox() {
        String fox = i2pFirefox.topFirefox();
        if (fox == null) {
            return false;
        }
        if (fox.isEmpty()) {
            return false;
        }
        return true;
    }

    /*
     * Populates a profile directory with a proxy configuration.
     * Waits for an HTTP proxy on the port 4444 to be ready.
     * Launches either Firefox or Chromium with the profile directory.
     * 
     * @param bool if true, the profile will be ephemeral(i.e. a --private-window profile).
     * @since 0.0.16
     */
    public void launch(boolean privateWindow){
        if ((chromium && firefox) || (!chromium && !firefox)) {
            if (this.hasFirefox()) {
                this.launchFirefox(privateWindow);
            } else if (this.hasChromium()) {
                this.launchChromium(privateWindow);
            }
            return;
        }
        if (chromiumFirst){
            if (chromium) {
                this.launchChromium(privateWindow);
            }else if (firefox) {
                this.launchFirefox(privateWindow);
            }
            return;
        }
        if (firefox) {
            this.launchFirefox(privateWindow);
        }else if (chromium) {
            this.launchChromium(privateWindow);
        }
        return;
    }

    /*
     * Populates a profile directory with a proxy configuration.
     * Waits for an HTTP proxy on the port 4444 to be ready.
     * Launches either Firefox or Chromium with the profile directory.
     * 
     * @since 0.0.16
     */
    public void launch(){
        launch(false);
    }    

    public static void main(String[] args) {
        boolean privateBrowsing = false;
        System.out.println("I2PBrowser");
        I2PBrowser i2pBrowser = new I2PBrowser();
        if (args != null && args.length > 0) {
            for (String arg : args) {
                if (arg.equals("-private")) {
                    privateBrowsing = true;
                }
                if (arg.equals("-chromium")) {
                    i2pBrowser.chromium = true;
                }
                if (arg.equals("-firefox")) {
                    i2pBrowser.firefox = true;
                }
            }
        }
        i2pBrowser.launch(privateBrowsing);
    }

}