#! /usr/bin/env sh

GITHUB_USER=eyedeekay
GITHUB_REPO=i2p.plugins.firefox
ant distclean
ant jar
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PChromium