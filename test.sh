#! /usr/bin/env sh

ant distclean
ant jar
echo "Testing auto-selector with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser
echo "Testing auto-selector with local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser "http://127.0.0.1:7657"
echo "Testing auto-selector with remote URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser "http://idk.i2p"
echo "Testing auto-selector with private browsing parameter"
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -private "http://127.0.0.1:7657"


echo "Testing Chromium with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -chromium
echo "Testing Chromium with local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -chromium "http://127.0.0.1:7657"
echo "Testing Chromium with remote URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -chromium "http://idk.i2p"
echo "Testing Chromium with private browsing parameter"
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -chromium -private "http://127.0.0.1:7657"



echo "Testing Firefox with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -firefox
echo "Testing Firefox with local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -firefox "http://127.0.0.1:7657"
echo "Testing Firefox with remote URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -firefox "http://idk.i2p"
echo "Testing Firefox with private browsing parameter"
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -firefox -private "http://127.0.0.1:7657"