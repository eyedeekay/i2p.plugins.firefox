:; dir=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd); java -cp "$dir"/src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PChromium -app http://127.0.0.1:7657/i2psnark; exit $?
@ECHO OFF
java -cp %cd%/src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PChromium -app http://127.0.0.1:7657/i2psnark