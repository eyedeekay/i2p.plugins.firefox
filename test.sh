#! /usr/bin/env sh

ant distclean
ant jar

rm -rf i2p.chromium.base.profile i2p.chromium.profile i2p.firefox.base.profile i2p.firefox.profile

echo "Testing auto-selector with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser 2> auto.0.err 1> auto.0.log
echo "Testing auto-selector with local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser "http://127.0.0.1:7657" 2> auto.1.err 1> auto.1.log
echo "Testing auto-selector with remote URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser "http://idk.i2p" 2> auto.2.err 1> auto.2.log
echo "Testing auto-selector with remote AND local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser "http://127.0.0.1:7657" "http://idk.i2p" 2> auto.3.err 1> auto.3.log
echo "Testing auto-selector with private browsing parameter"
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -private "http://127.0.0.1:7657" 2> auto.4.err 1> auto.4.log

echo "Auto-Selector tests completed"
sleep 2s
rm -rf i2p.chromium.base.profile i2p.chromium.profile

echo "Testing Chromium with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -chromium 2> chrome.1.err 1> chrome.1.log
echo "Testing Chromium with local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -chromium "http://127.0.0.1:7657" 2> chrome.1.err 1> chrome.1.log
echo "Testing Chromium with remote URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -chromium "http://idk.i2p" 2> chrome.2.err 1> chrome.2.log
echo "Testing Chromium with remote AND local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -chromium "http://127.0.0.1:7657" "http://idk.i2p" 2> fox.3.err 1> fox.3.log
echo "Testing Chromium with private browsing parameter"
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -chromium -private "http://127.0.0.1:7657" 2> chrome.4.err 1> chrome.4.log

echo "Chromium tests completed"
sleep 2s
rm -rf i2p.firefox.base.profile i2p.firefox.profile

echo "Testing Firefox with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -firefox 2> fox.0.err 1> fox.0.log
echo "Testing Firefox with local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -firefox "http://127.0.0.1:7657" 2> fox.1.err 1> fox.1.log
echo "Testing Firefox with remote URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -firefox "http://idk.i2p" 2> fox.2.err 1> fox.2.log
echo "Testing Firefox with remote AND local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -firefox "http://127.0.0.1:7657" "http://idk.i2p" 2> fox.3.err 1> fox.3.log
echo "Testing Firefox with private browsing parameter"
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -firefox -private "http://127.0.0.1:7657" 2> fox.4.err 1> fox.4.log

echo "Firefox tests completed"
sleep 2s
rm -rf i2p.firefox.base.profile i2p.firefox.profile

echo "Testing Firefox with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -firefox -usability 2> fox.0.err 1> fox.0.log
echo "Testing Firefox with local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -firefox -usability "http://127.0.0.1:7657" 2> fox.1.err 1> fox.1.log
echo "Testing Firefox with remote URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -firefox -usability "http://idk.i2p" 2> fox.2.err 1> fox.2.log
echo "Testing Firefox with remote AND local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -firefox -usability "http://127.0.0.1:7657" "http://idk.i2p" 2> fox.3.err 1> fox.3.log
echo "Testing Firefox with private browsing parameter"
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -firefox -usability -private "http://127.0.0.1:7657" 2> fox.4.err 1> fox.4.log

echo "Firefox Usability-Mode tests completed"
sleep 2s
rm -rf i2p.chromium.base.profile i2p.chromium.profile i2p.firefox.base.profile i2p.firefox.profile

echo "Testing UNSAFE auto-selector with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PGenericUnsafeBrowser 2> gen.0.err 1> gen.0.log
echo "Testing UNSAFE auto-selector with local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PGenericUnsafeBrowser "http://127.0.0.1:7657" 2> gen.1.err 1> gen.1.log
echo "Testing UNSAFE auto-selector with remote URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PGenericUnsafeBrowser "http://idk.i2p" 2> gen.2.err 1> gen.2.log
echo "Testing UNSAFE auto-selector with remote AND local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PGenericUnsafeBrowser "http://127.0.0.1:7657" "http://idk.i2p" 2> gen.3.err 1> gen.3.log
echo "Testing UNSAFE auto-selector with private browsing parameter"
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PGenericUnsafeBrowser -private "http://127.0.0.1:7657" 2> gen.4.err 1> gen.4.log

echo "UNSAFE browser tests complete"
