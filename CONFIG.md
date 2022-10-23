# Configuring the Browser Launcher

It is possible to configure the Browser Launcher differently using a
`browser.config` in the `user.dir` for
`.jar` and `jpackage` installs, or the
`$PLUGIN` directory for I2P Plugin installs.

## bins

`         bins       ` properties determine which Firefox or Chromium
variants to use by matching the binary name. You can use these to
configure custom Firefox or Chromium variants or disable others.

``` properties
firefox.bins.*
chromium.bins.*
generic.bins.*
```

`         generic.bins       ` is only used on Unixes, and usually
refers to `         sensible-browser       ` on common Linux
distributions.

## paths

`         paths       ` properties determine where to search for Firefox
or Chromium variants to use by trying to find a file matching the binary
name by looking in a series of directories. You can use these to
configure Firefox or Chromium in non-default install locations.

``` properties
firefox.paths.*
chromium.paths.*
```

### Example Properties File

This properties file represents the defaults.


```properties
#Chromium Configuration Section
#Sun Oct 23 11:29:45 EDT 2022
chromium.bins.linux=ungoogled-chromium,chromium,brave,edge,ungoogled-chromium,chrome
chromium.bins.osx=ungoogled-chromium,chromium,brave,edge,ungoogled-chromium,chrome
chromium.bins.windows=ungoogled-chromium.exe,chromium.exe,brave.exe,edge.exe,ungoogled-chromium.exe,chrome.exe
chromium.paths.linux=/usr/bin,/usr/local/bin,/opt/chromium/bin,/snap/bin
chromium.paths.osx=/Applications/Chromium.app/Contents/MacOS,/Applications/Chrome.app/Contents/MacOS,/Applications/Brave.app/Contents/MacOS
chromium.paths.windows=C\:\\Users\\user\\AppData\\Local\\/Google/Chrome/Application,C\:\\Program Files (x86)\\/Google/Chrome/Application,/Google/Chrome/Application,C\:\\Users\\user\\AppData\\Local\\/Chromium/Application,C\:\\Program Files (x86)\\/Chromium/Application,/Chromium/Application,C\:\\Users\\user\\AppData\\Local\\/BraveSoftware/Brave Browser/Application,C\:\\Program Files (x86)\\/BraveSoftware/Brave Browser/Application,/BraveSoftware/Brave Browser/Application,/Microsoft/Edge/Application,C\:\\Program Files (x86)\\/Microsoft/Edge/Application
```

```properties
firefox.bins.linux=firefox,firefox-bin,firefox-esr,waterfox,waterfox-bin,librewolf
firefox.bins.osx=firefox,firefox-bin,firefox-esr,waterfox,waterfox-bin,librewolf
firefox.bins.windows=firefox.exe,firefox-bin.exe,firefox-esr.exe,waterfox.exe,waterfox-bin.exe,librewolf.exe
firefox.paths.linux=/usr/bin,/usr/local/bin,/opt/firefox/bin,/snap/bin
firefox.paths.osx=/Applications/Tor Browser.app/Contents/MacOS,/Applications/Firefox.app/Contents/MacOS,/Applications/Waterfox.app/Contents/MacOS,/Applications/Librewolf.app/Contents/MacOS
firefox.paths.windows=C\:\\Users\\user\\/OneDrive/Desktop/Tor Browser/Browser,C\:\\Users\\user\\/Desktop/Tor Browser/Browser,C\:\\Program Files (x86)\\/Mozilla Firefox,Mozilla Firefox,C\:\\Program Files (x86)\\/Waterfox,Waterfox,C\:\\Program Files (x86)\\/Librewolf
```

```properties
generic.bins.unix=sensible-browser,xdg-open,x-www-browser,gnome-www-browser,defaultbrowser,dillo,seamonkey,konqueror,galeon,surf,www-browser,links,lynx
```