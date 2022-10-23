#! /usr/bin/env sh

rm browser.config
ant clangFmt jar
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -chromium -outputconfig -nosystray "http://idk.i2p"
#java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -firefox -outputconfig -nosystray
#java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -generic -outputconfig -nosystray