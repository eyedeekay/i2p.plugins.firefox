package net.i2p.i2pfirefox;

/**
 * I2PFirefoxProfileUnpacker.java
 * Copyright (C) 2022 idk <hankhill19580@gmail.com>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License. See LICENSE.md for details.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * I2PFirefoxProfileUnpacker is a class that unpacks the I2P Firefox profile
 * from a zip file embedded in the `jar` file. The zip is unpacked to a base
 * directory where it is left untouched, and the base profile is copied to the
 * active profile directory.
 *
 * @author idk
 * @since 0.0.1
 */
public class I2PFirefoxProfileUnpacker extends I2PCommonBrowser {

  public static void main(String[] args) {
    String profileDirectory = I2PFirefoxProfileBuilder.profileDirectory(false);
    if (profileDirectory == null) {
      logger.info("No profile directory found");
      return;
    }
  }

  /**
   * unpack the profile directory
   *
   * @return true if the profile directory was successfully unpacked
   * @since 0.0.1
   */
  public boolean unpackProfile(String profileDirectory, String base) {
    return unpackProfile(profileDirectory, "firefox", base);
  }
}
