# i2p.plugins.firefox

A port of the batch scripts from i2p.firefox to Java.

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

## Getting started

### Using a Binary

```sh

mkdir ~/tmp-i2pfirefox && cd ~/tmp-i2pfirefox
wget https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/0.0.36/i2pfirefox.zip
unzip i2pfirefox.zip
./i2pfirefox.cmd

#or if you want to use a Chromium

./i2pchromium.cmd
```

### Build Dependencies

You will need `ant` and java `java` and for building the jar. You will need
`jpackage` for many of the potential build targets. I've been using Java 17
on Debian mostly, on Debian and Ubuntu, install the dependencies with:

```sh
sudo apt-get install openjdk-17* ant
```

Some of the targets use scripts written in Go to help generate resources. If
you want to update the profiles, you will need them. To install Go on Debian
and Ubuntu:

```sh
sudo apt-get install golang-go
```

Add `$HOME/go/bin` to your `$PATH` so `ant` can find Go applications.

`export PATH=$PATH:$HOME/go/bin`

Then use Go to download the applications you need and add them to `$HOME/go/bin`.

If you want to build the Chromium profiles you will need a Go application
called `crx3` which is used to interact with the Chrome app store to download
and update extensions.

```sh
go install github.com/mediabuyerbot/go-crx3/crx3@latest
```

Another Go application, called `amo-version`, is used to fetch extensions from addons.mozilla.org.
Like the Chrome profiles, generating the Firefox profiles requires this application. If you don't
want to update the profiles, you don't need it.

```sh
go install github.com/eyedeekay/amo-version@latest
```

If you also want to update the `user.js` in use by the Firefox targets, you will need
`github-release` to download the latest release package that is offered by the
Arkenfox project.

```sh
go install github.com/github-release/github-release@latest
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

### Browser Discovery Methods

This tool looks for browsers on the host system, creates a workspace to use for I2P
purposes, and launches the browser inside of that workspace. The details of the
workspace vary from browser to browser but roughly corresponds to a browser profile.
In order to be successful this tool uses 3 main types of browser discovery methods,
in this order:

 1. "Local" discovery, where a browser is in a subdirectory of the directory where you
 ran the launcher. This will only happen if the user unpacked a portable browser into
 the same directory where they ran the launcher.
 2. "Path-Based" discovery, where it scans common browser installation directories
 until it finds one which it can use. On Unix, it simply scans the directories on the
 `PATH` for a browser it knows about. On Windows, default paths to browser install
 directories are hard-coded and included in the binary. This is what usually happens.
 3. "System-Based" discovery, where it defers to the host system to make a choice
 about the browser and counts on browser vendors to honor the system proxy environment
 variables. This is a catch-all solution which works with most browsers, but does
 not apply any customizations.

There is a little subtlety here though.

- The path to Edgium on Windows will **always** resolve during path-based discovery,
resulting in a positive test for Chromium when launching the browser in auto-select
mode. So Windows will never reach stage 3 unless expressly forced to. If Firefox or
a variant is installed, it will be chosen before Edgium unless directed otherwise.
- Linux is unaware of a Tor Browser path because Tor Browser is rarely, if ever,
installed on-path. What is on path is virtually always a wrapper for Tor Browser
which is installed either as the main user or it's own user. Linux will only use
Tor Browser if it's discovered in "Local" mode.
- The above is also true of OSX for now but doesn't have to remain so.
- I really only test Phase 3 with Dillo and Edgium. **YMMV.**

### Usability vs Strict

This is basically a profile-management tool geared toward minimizing the
differences between browser users which are passively discernible while
they are browsing I2P. It assumes that they are part of a highly fragmented
browsing environment where they are already unique, and therefore consolidation
on configuration is a goal. However, this goal sometimes also conflicts with
usability. To allow users a safe set of choices, we offer "Coarse" configuration
in 2 modes. We recommend that you do not deviate from these configurations if
you have browser application fingerprinting as a concern.

#### Usability Mode

TODO: description

Pros: Allows a restricted subset of Javascript
Pros: Less likely to try and reach the clearnet

Cons: Looks very different from Tor Browser
Cons: Plugin updates can create temporary uniqueness

##### Usability Extension Set

- **I2P In Private Browsing**
- **uMatrix**
- **jsRestrictor**
- **LocalCDN**
- **Onion in Container Tabs**
- **HTTPS EveryWhere** in some configurations

##### Usability user.js characteristics

TODO: Summarize differences

#### Strict Mode

TODO: description

Pros: Does not allow Javascript by default
Pros: Looks a lot like Tor Browser especially if you're using Tor Browser

Cons: More work to use
Cons: Temporary uniqueness can be created by enabling Javascript for specific sites
Cons: More likely to try and reach the clearnet

##### Strict Extension Set

- **NoScript**
- **I2P In Private Browsing**
- **HTTPS EveryWhere** in some configurations

##### Strict user.js characteristics

TODO: Summarize differences
