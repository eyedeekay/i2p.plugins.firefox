Packages for various operating systems
======================================

Windows
-------

All Windows packages require building on a Windows machine. In order to build
them, check out i2p.firefox in an adjacent directory and `source` i2p.firefox/config.sh
to set the path to the Java toolchain you want to use.

run: `ant jar` before any of these scripts.

run: `windows-release.sh` only *after* release.sh on a Linux machine.

### MSI

#### Status: Works. Maintained.

Requires the Wixl toolset.

run: `./windows.sh`

### EXE: Works. Maintained.

No special requirements.

run: `./windows-exe.sh`

### Portable(.zip): Unknown. Maintained.

No special requirements.

run: `./windows-portable.sh`

Linux
-----

All Linux packages require building on a Linux machines. Debian packages must
be built on Debian, Fedora packages must be built on Fedora. Some leeway for
derivative distros. Portable can be b build anywhere with a jpackage. Have
Java tools in your `PATH`.

### Debian: Works. Maintained.

Make sure you have a recent Java and jpackage.

run: `ant debian`

### Fedora: Unknown. Maintained.

I'm a Debian user and don't have a Fedora machine set up right now.  Therefore,
I build Fedora packages in a container.

Fedora doesn't have a jpackage in their repositories as far as I can tell so I use
Adoptium's third-party repository to supply the JDK I use to build the fedora
package.

run: `ant fedora`

### Portable(.zip): Works. Maintained.

Details are platform dependent. Same build-deps as everything else.

run: `ant jpackage`

OSX
---

### TODO