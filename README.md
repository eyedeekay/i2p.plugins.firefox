# i2p.plugins.firefox

A port of the batch scripts from i2p.firefox to Java.

## Getting started

### Using a Binary

```sh

mkdir ~/tmp-i2pfirefox && cd ~/tmp-i2pfirefox
wget https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/0.0.10/i2pfirefox.zip
unzip i2pfirefox.zip
./i2pfirefox.cmd

#or if you want to use a Chromium

./i2pchromium.cmd
```

### Build Dependencies

You will need `ant`, `java` and for building the Chromium profile, a Go application
called `crx3` which is used to interact with the Chrome app store. I've been using Java 17
on Debian mostly, on Debian and Ubuntu, install the dependencies with:

```sh
sudo apt-get install openjdk-17* ant golang-go
go install github.com/mediabuyerbot/go-crx3/crx3@latest
```

For Fedora, use Yum, for Arch use pacman or something else but make sure to tell everyone
about it. Once you have that installed, when building, make sure to add `$GOPATH/bin/`
to your `$PATH`.

```sh
export PATH=$PATH:$HOME/go/bin
```

Will almost always work.

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

// Firefox Example
if (i2pIsRunning()) {
    logger.warning("I2P is already running");
    System.out.println("I2PFirefox");
    I2PFirefox i2pFirefox = new I2PFirefox();
    i2pFirefox.launch();
}
```

```java

// Chromium Example
if (i2pIsRunning()) {
    logger.warning("I2P is already running");
    System.out.println("I2PChromium");
    I2PChromium i2pChromium = new I2PChromium();
    i2pChromium.launch();
}
```

```java

// Auto-Select Example, chooses Firefox first, then Chromium
if (i2pIsRunning()) {
    logger.warning("I2P is already running");
    System.out.println("I2PBrowser");
    I2PBrowser i2pBrowser = new I2PBrowser();
    /*
     * toggle chromium to the top of the order by doing:
    I2PBrowser.chromiumFirst = true;
     * 
    */
    i2pBrowser.launch(privateBrowsing);
}
```

to add a browser management tool to it.
