# i2p.plugins.firefox

A port of the batch scripts from `i2p.firefox` to Java.

**Status:** This package is maintained. It cannot solve all your problems. Fingerprinting
is a reality in modern browsers. Exploits are too. This software attempts to provide a
best-possible baseline of privacy first, security a close second for browsing the web that
exists within I2P. Do not expect it to withstand attacks from very powerful adversaries
who can spend time and money to screw up your life. Do expect it to resist advertisers,
trackers, and jerks using off-the-shelf techniques and exploits. It is fundamentally unable
to make changes which un-trust your browser vendor, your OS, your package manager or any
other system that exists underneath it. **It is just a profile manager.**

**All packages require a running I2P router.**

**[Windows users should see the Easy-Install Bundle](https://i2pgit.org/i2p-hackers/i2p.firefox)**

## Credits

This profile manager makes use of a set of browser extensions which are largely the work of others.
It makes use of dependencies that are the work of others. In many ways, it's merely an elaborate
configuration tool. A smart one, but a configuration tool nonetheless. Many thanks to the following
projects, developers, and communities:

### Firefox and Chrome Extensions

- [NoScript - Giorgio Maone and others](https://noscript.net)
- [HTTPS Everywhere - Electronic Frontier Foundation](https://www.eff.org/https-everywhere)
- [uBlock Origin - Raymond Gorhill and others](https://ublockorigin.com/)
- [LocalCDN - nobody and others](https://www.localcdn.org/)
- [jShelter - Libor Polčák and others](https://jshelter.org/)

### Firefox Configuration Modifiations

- [Arkenfox - Thorin Oakenpants and Others](https://github.com/arkenfox/user.js/)

You can find the license files for each of the these projects in the `src/i2p.firefox.*.profile/extensions/*`
directory for Firefox, and the `src/i2p.chromium.*.profile/extensions/*.js/*` directories for Chromium.

## Getting started

### Using a Binary

For platform specific instructions, see

- [LINUX.md](LINUX.md)/[LINUX.html](LINUX.html)
- [OSX.md](OSX.md)/[OSX.html](OSX.html)
- [WINDOWS.md](WINDOWS.md)/[WINDOWS.html](WINDOWS.html)

#### All platforms, using a zip: `.zip` and a non-bundled Java

```sh
mkdir ~/tmp-i2pfirefox && cd ~/tmp-i2pfirefox
wget https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/1.0.2/i2pfirefox.zip
unzip i2pfirefox.zip
./i2pfirefox.cmd

#or if you want to use a Chromium

./i2pchromium.cmd
```
