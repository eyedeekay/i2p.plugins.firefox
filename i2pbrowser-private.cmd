:; dir=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd); java -cp "$dir"/src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -private; exit $?
@ECHO OFF
java -cp %cd%/src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser -private