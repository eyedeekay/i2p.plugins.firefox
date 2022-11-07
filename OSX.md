Using this on OSX
=================

This code will launch an I2P-Configured Browser on OSX, but at this time
there are no packages for it because it's not possible for me to sign OSX
packages. You can use the `.jar` file with any Java greater than Java 8.

```sh
mkdir ~/tmp-i2pfirefox && cd ~/tmp-i2pfirefox
wget https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/1.0.3/i2pfirefox.zip
unzip i2pfirefox.zip
./cmd/i2pfirefox.cmd

#or if you want to use a Chromium

./cmd/i2pchromium.cmd
```

Building a `jpackage`
---------------------

In order to build a `jpackage`, install at least Java 17. To set up Java
17 and configure it to be the Java used by the system for the rest of the
session, use these commands:

```sh
brew install openjdk@17 
sudo ln -sfn /usr/local/opt/openjdk/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk
export JAVA_HOME=`/usr/libexec/java_home -v 17`
```

Once you're finished, run `./osx-dmg.sh` in the repository root to produce a
`.dmg` package.