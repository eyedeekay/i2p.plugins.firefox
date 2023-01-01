#! /usr/bin/env sh

ant distclean
cd src && ant jar && cd ..

rm -rf i2p.chromium.base.profile i2p.chromium.profile i2p.firefox.base.profile i2p.firefox.profile i2p.firefox.usability.profile tor-browser_en-US

echo "Testing Firefox with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -firefox 2> fox.0.err 1> fox.0.log
echo "Testing Firefox with local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -firefox "http://127.0.0.1:7657" 2> fox.1.err 1> fox.1.log
echo "Testing Firefox with remote URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -firefox "http://idk.i2p" 2> fox.2.err 1> fox.2.log
echo "Testing Firefox with remote AND local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -firefox "http://127.0.0.1:7657" "http://idk.i2p" 2> fox.3.err 1> fox.3.log
echo "Testing Firefox with private browsing parameter"
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -firefox -private "http://127.0.0.1:7657" 2> fox.4.err 1> fox.4.log

echo "Firefox tests completed"
sleep 2s
rm -rf i2p.firefox.usability.profile i2p.firefox.profile

echo "Testing Firefox with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -firefox -usability 2> fox.0.err 1> fox.0.log
echo "Testing Firefox with local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -firefox -usability "http://127.0.0.1:7657" 2> fox.1.err 1> fox.1.log
echo "Testing Firefox with remote URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -firefox -usability "http://idk.i2p" 2> fox.2.err 1> fox.2.log
echo "Testing Firefox with remote AND local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -firefox -usability "http://127.0.0.1:7657" "http://idk.i2p" 2> fox.3.err 1> fox.3.log
echo "Testing Firefox with private browsing parameter"
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -firefox -usability -private "http://127.0.0.1:7657" 2> fox.4.err 1> fox.4.log

echo "Firefox Usability-Mode tests completed"
sleep 2s
rm -rf i2p.chromium.base.profile i2p.chromium.profile i2p.firefox.base.profile i2p.firefox.profile
