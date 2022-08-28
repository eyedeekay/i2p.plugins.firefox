#! /usr/bin/env sh

ant distclean
ant jar

echo "Testing UNSAFE auto-selector with no private and no URL parameters."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PGenericUnsafeBrowser 2> gen.0.err 1> gen.0.log
echo "Testing UNSAFE auto-selector with local URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PGenericUnsafeBrowser "http://127.0.0.1:7657" 2> gen.1.err 1> gen.1.log
echo "Testing UNSAFE auto-selector with remote URL parameter."
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PGenericUnsafeBrowser "http://idk.i2p" 2> gen.2.err 1> gen.2.log
echo "Testing UNSAFE auto-selector with private browsing parameter"
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PGenericUnsafeBrowser -private "http://127.0.0.1:7657" 2> gen.3.err 1> gen.3.log

echo "UNSAFE browser tests complete"