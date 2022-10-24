#! /usr/bin/env sh
. ./config.sh
jpackage \
    --verbose \
    --type dmg \
    --name i2pbrowser \
    --app-version "$GITHUB_TAG" \
    --icon src/icon.png \
    --input src/build \
    --main-jar i2pfirefox.jar \
    --main-class net.i2p.i2pfirefox.I2PBrowser
