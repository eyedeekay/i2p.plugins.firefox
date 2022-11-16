#! /usr/bin/env sh

ant distclean
ant jar

rm -rf i2p.chromium.base.profile i2p.chromium.profile i2p.firefox.base.profile i2p.firefox.profile i2p.firefox.usability.profile tor-browser_en-US

echo "Testing auto-selector with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray 2> auto.0.err 1> auto.0.log
echo "Testing auto-selector with local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray "http://127.0.0.1:7657" 2> auto.1.err 1> auto.1.log
echo "Testing auto-selector with remote URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray "http://idk.i2p" 2> auto.2.err 1> auto.2.log
echo "Testing auto-selector with remote AND local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray "http://127.0.0.1:7657" "http://idk.i2p" 2> auto.3.err 1> auto.3.log
echo "Testing auto-selector with private browsing parameter"
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -private "http://127.0.0.1:7657" 2> auto.4.err 1> auto.4.log
echo "Auto-Selector tests completed"

rm -rf i2p.chromium.base.profile i2p.chromium.profile i2p.firefox.base.profile i2p.firefox.profile i2p.firefox.usability.profile

echo "Testing UNSAFE auto-selector with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -generic 2> gen.0.err 1> gen.0.log
echo "Testing UNSAFE auto-selector with local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -generic "http://127.0.0.1:7657" 2> gen.1.err 1> gen.1.log
echo "Testing UNSAFE auto-selector with remote URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -generic "http://idk.i2p" 2> gen.2.err 1> gen.2.log
echo "Testing UNSAFE auto-selector with remote AND local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -generic "http://127.0.0.1:7657" "http://idk.i2p" 2> gen.3.err 1> gen.3.log
echo "Testing UNSAFE auto-selector with private browsing parameter"
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -nosystray -generic -private "http://127.0.0.1:7657" 2> gen.4.err 1> gen.4.log

echo "UNSAFE browser tests complete"

rm -rf i2p.chromium.base.profile i2p.chromium.profile i2p.firefox.base.profile i2p.firefox.profile i2p.firefox.usability.profile
