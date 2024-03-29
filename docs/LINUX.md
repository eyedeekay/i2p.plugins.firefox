### Linux Support

It's possible to use this package in the following binary formats on
Linux. These packages are for the **`amd64`** architecture.

#### Linux jpackage: `.tar.gz`

```sh
mkdir ~/tmp-i2pbrowser && cd ~/tmp-i2pbrowser
wget https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/1.3.0/i2pbrowser.tar.gz
tar xvzf i2pbrowser.tar.gz && cd i2pbrowser
## THIS STEP IS OPTIONAL but it will force the system to use Tor Browser from within the i2pbrowser directory.
# This probably offers better security than vanilla Firefox.
# This will also make the directory the launcher is in relocatable to a flash drive, for instance.
# This is commonly referred to as being portable.
# If tor and torsocks are on the host system, Tor Browser will be downloaded over Tor.
./lib/torbrowser.sh 
# end of optional step.
./bin/i2pbrowser
```

#### Debian/Ubuntu and variants Jpackage: `.deb`

1. Start your I2P Router
2. Download the latest release `.deb` from [Github](https://github.com/eyedeekay/i2p.plugins.firefox/releases) and verify it's hash.
3. Run `sudo apt-get install ./i2pbrowser*.deb`(Only needs to be done once per update)
4. Use the applications menu shortcut to launch the pre-configured I2P browser

#### Fedora and variants Jpackage: `.rpm`

1. Start your I2P Router
2. Download the latest release `.r[m` from [Github](https://github.com/eyedeekay/i2p.plugins.firefox/releases) and verify it's hash.
3. Run `sudo rpm -i ./i2pbrowser*.rpm`(Only needs to be done the first time)
4. Run `sudo rpm -U ./i2pbrowser*.rpm`(Only needs to be done once per update)
5. Use the applications menu shortcut to launch the pre-configured I2P browser
