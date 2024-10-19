Sat, October 19
---------------

 - Ignore most changes when running in Tor Browser, only set the proxy
 - 2.7.0 release

Mon, July 8
-----------

 - Fix a bug which caused the profile manager to fail to detect Firefox on some systems
 - Fix a bug which caused the profile manager to use an unwritable directory on some systems
 - Fix a bug where if user tried to run it from the home directory, the application would make sure that no firefoxes existed in any subdirectory before running

Thu, March 7
------------

 - re-implement Plugin functions with Destop GUI API.
 - Fix several NPE's
 - Delete redundant code
 - CI/CD improvements, packaging improvements

Tue, January 17
---------------

 - Remove HTTPS everywhere from all profiles
 - Generate a Tor Browser install to go inside Linux jpackage portables

Sat, December 31
----------------

 - Add environment variables which customize behavior of Tor Browser

Sun, December 18
----------------

 - Enable use as a Java I2P Console Plugin

Tue, November 22
----------------

 - Fix some minor issues from buildsystem refactor that surfaced during release
 - update to version 1.0.5

Sat, October 22
---------------

 - Add systray for launching browsers from our own GUI
 - Add `browser.config` file for customizing configuration

Thu, October 20
---------------

 - Complete the PWA/App mode

Sat, October 1
--------------

 - Work around anti-automation measures on OSX by writing final command out to shell script and running that
 - Deterministically generate .zip files
 - Add Tor Browser support to OSX
 - Improve Tor Browser support on Linux

Sun, September 25
-----------------

 - Become version 1.0.0

Wed, September 15
-----------------

 - Add ant targets which auto-update the user.js

Wed, September 14
-----------------

 - Add ant targets which fetch Firefox addons from addons.mozilla.org

Sun, September 11
-----------------

 - Make the logging a little more sensible

Tue, September 6
----------------

 - Wait up to 5 seconds for the initializing headless Firefox instance to die on Windows
 - Add option to disable proxy pre-check by setting timeout to zero or less

Tue, September 6
----------------

 - Run headlessly on first-run on Windows.
 - Add support for building an .exe package.
 - Add support for building an .zip Windows portable package.
 - Add support for building an .rpm package.
 - Destroy headless process if it goes too long.

Mon, September 5
----------------

 - Fix several issues with running on Windows 11
 - Make tunnel nicknames different to avoid double shared tunnels
 - Add support for building a .deb package.
 - Add support for building an .msi package.

Fri, September 2
----------------

 - Disable HTTPS only mode in favor of HTTPS-first mode to remove the interstitial on I2P sites.
 - Clearnet sites still choose HTTPS first and will silently deny downgrades but will not warn HTTP-only users.

Thu, September 1
----------------

 - Add a jpackage target. Add descriptions to release items.

Wed, August 31
--------------

 - Add unpacked extensions for Chromium profiles
 - Add Chromium flags from Ungoogled-Chromium documentation
 - Add Usability/Strict mode switching for Chromium

Tue, August 30
--------------

 - Add Usability/Strict mode switching for Firefox
 - Factor out common functions to own class

Sun, August 28
--------------

 - Adds support for generically configuring a browser with environment variables.
 - Bugfix for MSEdgium support
 - Add detachable version of launchers

Sat, August 27
--------------

 - Added support for passing a URL as a parameter to the cli's
 - revised test.sh

Mon, August 22
--------------

 - Completes support for auto-selecting *either* a Firefox or Chrome instance, automatically

Sat, August 21
--------------

 - Adds support for passing the -private flag to start Firefox with --private-window and Chromium --incognito

Fri, August 20
--------------

 - Chromium is now a first-class citizen

Fri, August 19
--------------

 - Adds the ability to pass --private-window to Firefoxes and --incognito to Chromiums
 - Switch Tor Browser to the top of the order on Windows to match i2p.firefox behavior

Mon, August 8
-------------

 - Add Chromium support. You heard me. Chromium support.
 - Update launcher script so it self-discovers the location of the script and uses that to find the bundled jar.

Sun, August 7
-------------

 - Wrote the Javadoc
 - Embedded the Firefox profile in the Jar so it always unpacks correctly
 - re-added Tor Browser support on Windows
 - Fix false positives in validation routine
 - Make constructors public
 - Wait on processbuilder to close

Saturday, August 6
------------------

 - Implemented firefox detection
 - Implemented directory setup
 - Implemented processBuilder generator
## Checksums

```
dc8797cfae23640e27ed7b32a3e6ccec232d24411d64cd9e5b107f8c5384a70a  CHANGES.md
9a36e15def2a95077027cd0db5f95b345a87a8edfe5e89ea7f83aaa3c94f5ebb  i2p.chromium.base.profile.zip
97ae21d621f813fd2d91f5921fe1eee95ec0d01782bf93db7b3689f959a9f3b8  i2p.chromium.usability.profile.zip
5cd673bb27827e67796b52f26194b7b1504dddf1630ad9d9084ab6a318c6d20a  i2p.firefox.base.profile.zip
556fc2df825e64b1463e88ec77a5de46bce61516a475e1136110363d5badabd2  i2p.firefox.usability.profile.zip
6d3882ac113116b8f40ce07985e779859a68f43f14278b40f1628ea5362acca5  i2pbrowser-1.0.0-1.x86_64.rpm
aa7185799b17fd7893818fe7728f3e3cb6b4c8022e033fc0f33f6be81e7b2c2f  i2pbrowser-1.0.0.dmg
c307fb6556821258520a3d522cf7fbe2031aa421161fcac3e409f5ce20a1637a  i2pbrowser-1.0.0.exe
5eae853ff7f8b9a05c4c7111147882a54aabd1cf581d21396878055a54a3efc7  i2pbrowser-1.0.0.msi
cda1bb2058048a4303bbc65543a0f340ade1c88881ee60704333e767bae10db6  i2pbrowser-1.0.0.pkg
183197aa39e891d5763ef5449957b9d9790bdfaaa06760288526f6283b865d97  i2pbrowser-portable.zip
f017951df66f2e3d2d57886085a95bd2463cc8f7d793683960da30b842b9b17b  i2pbrowser.tar.gz
4538172d9753967485566e52f064dbc06a5123971044d973766de28051d53eb1  i2pbrowser_1.0.0_amd64.deb
6b5dd3e4ab1bcb3df4a5a758b5469239453c713bb6266bc5eea8b0d9ec7aa413  i2pfirefox-plugin.jar
85ce64a3c03c72421947c777a72d42be6fc568c5317070927463accb347b703f  i2pfirefox.jar
9856613f8229a773ef54a9e7e2a37a6c788755d96393478afce8db7439404e42  plugin.zip
```

```
CHANGES.md:                         ASCII text
i2p.chromium.base.profile.zip:      Zip archive data, at least v2.0 to extract, compression method=store
i2p.chromium.usability.profile.zip: Zip archive data, at least v2.0 to extract, compression method=store
i2p.firefox.base.profile.zip:       Zip archive data, at least v2.0 to extract, compression method=store
i2p.firefox.usability.profile.zip:  Zip archive data, at least v2.0 to extract, compression method=store
i2pbrowser-1.0.0-1.x86_64.rpm:      RPM v3.0 bin i386/x86_64
i2pbrowser-1.0.0.dmg:               zlib compressed data
i2pbrowser-1.0.0.exe:               PE32+ executable (GUI) x86-64, for MS Windows
i2pbrowser-1.0.0.msi:               Composite Document File V2 Document, Little Endian, Os: Windows, Version 6.2, MSI Installer, Code page: 1252, Title: Installation Database, Subject: i2pbrowser, Author: Unknown, Keywords: Installer, Comments: This installer database contains the logic and data required to install i2pbrowser., Template: x64;1033, Revision Number: {6D1D56D8-CB72-4709-BD0B-0FDBF71C6D32}, Create Time/Date: Sun Mar 10 06:04:32 2024, Last Saved Time/Date: Sun Mar 10 06:04:32 2024, Number of Pages: 200, Number of Words: 10, Name of Creating Application: Windows Installer XML Toolset (3.14.0.8606), Security: 2
i2pbrowser-1.0.0.pkg:               xar archive compressed TOC: 1244, SHA-1 checksum, contains  zlib compressed data
i2pbrowser-portable.zip:            Zip archive data, at least v2.0 to extract, compression method=deflate
i2pbrowser.tar.gz:                  gzip compressed data, from Unix, original size modulo 2^32 725985280
i2pbrowser_1.0.0_amd64.deb:         Debian binary package (format 2.0), with control.tar.zs, data compression zst
i2pfirefox-plugin.jar:              Java archive data (JAR)
i2pfirefox.jar:                     Java archive data (JAR)
plugin.zip:                         Zip archive data, at least v1.0 to extract, compression method=store
```

