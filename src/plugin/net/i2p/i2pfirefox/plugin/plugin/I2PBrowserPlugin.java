package net.i2p.i2pfirefox.plugin;

import net.i2p.i2pfirefox.I2PBrowser;
import net.i2p.app.ClientApp;
import net.i2p.app.ClientAppState;

/**
 * I2PBrowserPlugin.java
 * Copyright (C) 2022 idk <hankhill19580@gmail.com>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License. See LICENSE.md for details.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * @description I2PBrowser is a class that is used to open a browser window to
 * the I2P network. It automatically detects the operating system and available
 * browsers, and selects the best one to use, with Tor Browser at the top for
 * Firefox and Brave at the top for Chrome.
 *
 * @author idk
 * @since 0.0.16
 */
public class I2PBrowserPlugin extends I2PBrowser implements ClientApp {
    public String getDisplayName() {
        return "I2P Browser";
    }
    public String getName() {
        return "I2P Browser";
    }
    public ClientAppState getState() {
        return ClientAppState.RUNNING;
    }
    public void shutdown(String[] args) {

    }
    public void startup() {
        
    }
}