package net.i2p.i2pfirefox;

/**
 * I2PChromiumProfileUnpacker.java
 * Copyright (C) 2022 idk <hankhill19580@gmail.com>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the MIT License. See LICENSE.md for details.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * I2PChromiumProfileUnpacker is a class that unpacks the Chromium profile zip
 * file into the Chromium base profile directory. This is not used by the
 * Chromium browser instance, it's unpacked to the disk to be copied to the
 * active profile directory.
 *
 * @author idk
 * @since 0.0.1
 */
public class I2PChromiumProfileUnpacker extends I2PCommonBrowser {

  public static void main(String[] args) {
    String profileDirectory = I2PChromiumProfileBuilder.profileDirectory();
    if (profileDirectory == null) {
      println("No profile directory found");
      return;
    }
  }

  /**
   * unpack the profile directory
   *
   * @return true if the profile directory was successfully unpacked
   * @since 0.0.1
   */
  public boolean unpackProfile(String profileDirectory, String mode) {
    println("Unpacking base profile to " + profileDirectory);
    return unpackProfile(profileDirectory, "chromium", mode);
  }
}
