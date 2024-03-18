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

**What is this?**

This is a browser profile manager which pre-configures a browser on the host system, usually
Firefox or Tor Browser, for browsing I2P. When acting as an I2P Plugin, it adds GUI elements to
I2P for launching the browser in a highly restricted "Safe" mode(the default) or a more permissive
"Usability" mode.

This package expresses functionality that has existed in many places at many times, sometimes
bundled with other software. As it has developed here, the border of where the Easy-Install
ends and Firefox Profile Manager begins has become clearer. This repository contains the profile
manager and it's buildsystem, the whole profile manager, and nothing but the profile manager.
It can be used independently of other software, provided an I2P proxy on the host system somewhere.

However, in practice, most people probably get it alongside an I2P router, through the **I2P Easy-Install Bundle for Windows**.

**What version numbers should I pay attention to?**
When I decided to port the `.bat` launcher scripts from the Easy-Install bundle to Java, this project
was created and started using it's own version numbers. This was the practice up until version 1.5.0,
which was the last version to use a different version number than I2P itself. Starting in April 2024,
`i2p.plugins.firefox` will follow along with the Java I2P major and minor version numbers. Incremental
changes may become differing point releases.

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

### Building the package:

 - See: [BUILD.md](docs/BUILD.md)/[BUILD.html](docs/BUILD.html)

### Using a Binary

For platform specific instructions, see

- [I2P-PLUGIN.md](docs/I2P-PLUGIN.md)/[I2P-PLUGIN.html](docs/I2P-PLUGIN.html)
- [LINUX.md](docs/LINUX.md)/[LINUX.html](docs/LINUX.html)
- [OSX.md](docs/OSX.md)/[OSX.html](docs/OSX.html)
- [WINDOWS.md](docs/WINDOWS.md)/[WINDOWS.html](docs/WINDOWS.html)

#### All platforms, using a zip: `.zip` and a non-bundled Java

```sh
mkdir ~/tmp-i2pfirefox && cd ~/tmp-i2pfirefox
wget https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/1.3.0/i2pfirefox.zip
unzip i2pfirefox.zip
./i2pfirefox.cmd

#or if you want to use a Chromium

./i2pchromium.cmd
```
