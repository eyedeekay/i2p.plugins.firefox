#! /usr/bin/env sh
# debian build which automatically configures Tor Browser.
# I do not think that it has write permission to it's own directory, making Tor Browser updates probably impossible.
# Don't use it yet.
. ./config.sh
ant jpackage
jpackage --verbose \
    --app-image i2pbrowser \
    --type deb \
    --linux-deb-maintainer hankhill19580@gmail.com \
    --linux-menu-group "Network;WebBrowser;P2P" \
    --linux-app-category "Network" \
    --linux-package-deps "firefox|chromium|brave|firefox-esr|librewolf|icecat" \
    --linux-shortcut \
    --license-file LICENSE.md \
    --name i2pbrowser \
    --icon src/icon.png \
    --app-version "$GITHUB_TAG" \
