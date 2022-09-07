Tue, September 6
----------------

 - Add support for building an .exe package.
 - Add support for building an .zip Windows portable package.
 - Add support for building an .rpm package.

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