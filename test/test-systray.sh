#! /usr/bin/env sh

ant distclean
ant jar

rm -rf i2p.chromium.base.profile i2p.chromium.profile i2p.firefox.base.profile i2p.firefox.profile i2p.firefox.usability.profile tor-browser_en-US

echo "Testing auto-selector with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser 2> auto.0.err 1> auto.0.log