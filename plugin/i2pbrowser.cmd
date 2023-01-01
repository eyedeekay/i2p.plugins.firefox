:; dir=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd); java -cp "$dir"/lib/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser; exit $?
@ECHO OFF
java -cp %cd%/lib/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser