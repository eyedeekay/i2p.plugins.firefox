#! /usr/bin/env sh

. ./config.sh
jpackage --verbose \
    --type rpm \
    --linux-deb-maintainer hankhill19580@gmail.com \
    --linux-menu-group "Network;WebBrowser;P2P" \
    --linux-app-category "Network" \
    --linux-package-deps "firefox|chromium|brave|firefox-esr|librewolf|icecat" \
    --linux-shortcut \
    --license-file LICENSE.md \
    --name i2pbrowser \
    --app-version "$GITHUB_TAG" \
    --input src/build \
    --main-jar i2pfirefox.jar \
    --main-class net.i2p.i2pfirefox.I2PBrowser
