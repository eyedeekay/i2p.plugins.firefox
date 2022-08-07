# i2p.plugins.firefox

A port of the batch scripts from i2p.firefox to Java.

## Getting started

### Building

This is not actually a plugin yet, but it will be soon. The important bit is the jar.
To generate that, you can either generate the full plugin, which will not work but
produces the jar as a by-product, or you can:

```sh
cd src
ant
cd ...
```

To build just the jar. You'll know it worked if you can:

```sh
java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PFirefox
```

and a new Firefox instance comes up with a fresh profile. This is just a default Firefox
profile, configuring it for I2P requires you to put a pre-configured Firefox profile into
the `i2p.firefox.base.profile` directory or otherwise supply a static, pre-configured
profile.
