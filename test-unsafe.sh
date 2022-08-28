#! /usr/bin/env sh

ant distclean
ant jar

echo "Testing UNSAFE auto-selector with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PGenericUnsafeBrowser 2> gen.0.err 1> gen.0.log