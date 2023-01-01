#! /usr/bin/env sh

ant distclean
cd src && ant jar && cd ..

rm -rf i2p.chromium.base.profile i2p.chromium.profile i2p.firefox.base.profile i2p.firefox.profile i2p.firefox.usability.profile tor-browser_en-US

echo "Testing Chromium with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -chromium 2> chrome.1.err 1> chrome.1.log
echo "Testing Chromium with local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -chromium "http://127.0.0.1:7657" 2> chrome.1.err 1> chrome.1.log
echo "Testing Chromium with remote URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -chromium "http://idk.i2p" 2> chrome.2.err 1> chrome.2.log
echo "Testing Chromium with remote AND local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -chromium "http://127.0.0.1:7657" "http://idk.i2p" 2> fox.3.err 1> fox.3.log
echo "Testing Chromium with private browsing parameter"
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -chromium -private "http://127.0.0.1:7657" 2> chrome.4.err 1> chrome.4.log

echo "Chromium tests completed"
sleep 2s
rm -rf i2p.chromium.base.profile i2p.chromium.profile

echo "Testing Chromium with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -chromium -usability 2> chrome.1.err 1> chrome.1.log
echo "Testing Chromium with local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -chromium -usability "http://127.0.0.1:7657" 2> chrome.1.err 1> chrome.1.log
echo "Testing Chromium with remote URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -chromium -usability "http://idk.i2p" 2> chrome.2.err 1> chrome.2.log
echo "Testing Chromium with remote AND local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -chromium -usability "http://127.0.0.1:7657" "http://idk.i2p" 2> fox.3.err 1> fox.3.log
echo "Testing Chromium with private browsing parameter"
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -chromium -usability -private "http://127.0.0.1:7657" 2> chrome.4.err 1> chrome.4.log

echo "Chromium Usability-Mode tests completed"
rm -rf i2p.chromium.base.profile i2p.chromium.profile i2p.firefox.base.profile i2p.firefox.profile i2p.firefox.usability.profile
