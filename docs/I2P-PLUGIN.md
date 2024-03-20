### I2P Plugin Support

Since version `1.5.0`, this package has had basic plugin support in Java I2P.
This means it can be integrated with your I2P installation or portable package, with
the benefit of reduced size requirements, automatic updates, and integration with the I2P UI.
Installation in this way is **platform-independent**, the same package is used for Linux, Windows, and OSX.
In this type of installation, the profile manager install is entirely managed by I2P.
Uninstalling I2P will also uninstall the profile manager.

#### Downloading the Plugin

Right now, the best way to download the plugin is from the Github releases page.
First, download the `i2pfirefox.su3` file from [the latest release](https://github.com/eyedeekay/i2p.plugins.firefox/releases).
Versions ending in `0` will always have a plugin release, starting with `1.5.0`.
Dev builds will not have a reliable plugin release.

#### Install the plugin "From File"

Once you have downloaded the `i2pfirefox.su3` file, open [`http://127.0.0.1:7657/configplugins`](http://127.0.0.1:7657/configplugins) and scroll to the bottom of the page.
Under the heading **Installation from File**, click the "Browse" button.
In the window that appears, select the `i2pfirefox.su3` file you just downloaded.
Finally, click the "Install plugin from File" button.
The plugin will install and start shortly.
