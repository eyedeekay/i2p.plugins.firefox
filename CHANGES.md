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