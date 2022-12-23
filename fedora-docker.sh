#! /usr/bin/env sh

. ./config.sh

dnf -y update
dnf -y install temurin-19-jdk

jpackage --verbose \
    --type rpm \
    --linux-menu-group "Network;WebBrowser;P2P" \
    --linux-app-category "Network" \
    --linux-package-deps "firefox|chromium|brave|firefox-esr|librewolf|icecat" \
    --linux-shortcut \
    --license-file LICENSE.md \
    --name i2pbrowser \
    --app-version "$GITHUB_TAG" \
    --icon src/icon.png \
    --input src/build \
    --main-jar i2pfirefox.jar \
    --main-class net.i2p.i2pfirefox.I2PBrowser
ls *.rpm