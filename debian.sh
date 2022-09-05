#! /usr/bin/env sh

jpackage --verbose --type deb --name i2pbrowser --app-version 0.0.26 --input src/build --main-jar i2pfirefox.jar --main-class net.i2p.i2pfirefox.I2PBrowser
