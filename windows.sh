#! /usr/bin/env sh
. ./config.sh
jpackage \
    --verbose \
    --type msi \
    --win-dir-chooser \
    --win-help-url "https://geti2p.net" \
    --win-menu \
    --win-menu-group "I2P Browser Configurer" \
    --win-shortcut \
    --win-shortcut-prompt \
    --win-per-user-install \
    --license-file LICENSE.md \
    --icon src/icon.png \
    --name i2pbrowser \
    --app-version "$GITHUB_TAG" \
    --input src/build \
    --main-jar i2pfirefox.jar \
    --main-class net.i2p.i2pfirefox.I2PBrowser
