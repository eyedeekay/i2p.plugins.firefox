# i2p.plugins.firefox

A port of the batch scripts from i2p.firefox to Java.

## Getting started

### Using a Binary

```sh

mkdir ~/tmp-i2pfirefox && cd ~/tmp-i2pfirefox
wget https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/0.0.8/i2pfirefox.zip
unzip i2pfirefox.zip
./i2pbrowser.cmd

#or if you want to use a Chromium

./i2pchromium.sh
```

### Building

This is not actually a plugin yet, but it will be soon. The important bit is the jar.
To generate that, you can either generate the full plugin, which will not work but
produces the jar as a by-product, or you can:

```sh

ant jar
```

To build just the jar. You'll know it worked if you can:

```sh

java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PFirefox
```

and a new Firefox instance comes up with a fresh profile, ready-to-use for I2P browsing.

The cooler thing you can do with it is add it to an I2P distribution and somewhere in it,
add a UI element that triggers something along the lines of this:

```java

if (i2pIsRunning()) {
    logger.warning("I2P is already running");
    System.out.println("I2PFirefox");
    I2PFirefox i2pFirefox = new I2PFirefox();
    i2pFirefox.launch();
}
```

to add a browser management tool to it.
