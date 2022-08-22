#! /usr/bin/env sh

ant distclean
ant jar
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -private -chromium