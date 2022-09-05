#! /usr/bin/env sh

. ./config.sh
jpackage --verbose \
    --type deb \
    --linux-deb-maintainer hankhill19580@gmail.com \
    --linux-menu-group "Internet" \
    --linux-app-category "network" \
    --linux-package-deps "firefox|chromium|brave|firefox-esr|librewolf|icecat" \
    --linux-shortcut \
    --name i2pbrowser \
    --app-version "$GITHUB_TAG" \
    --input src/build \
    --main-jar i2pfirefox.jar \
    --main-class net.i2p.i2pfirefox.I2PBrowser
