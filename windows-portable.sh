#! /usr/bin/env sh
. ./config.sh
mkdir -p tmp
cp -v LICENSE.md tmp/LICENSE.md
rm -rf i2pbrowser-portable
jpackage \
    --verbose \
    --type app-image \
    --name i2pbrowser-portable \
    --app-version "$GITHUB_TAG" \
    --input src/build \
    --main-jar i2pfirefox.jar \
    --resource-dir tmp \
    --icon src/icon.png \
    --main-class net.i2p.i2pfirefox.I2PBrowser
rm -rf tmp
cp -v LICENSE.md i2pbrowser-portable/LICENSE.md
rm i2pbrowser-portable.zip -f
zip -r i2pbrowser-portable-${GITHUB_TAG}.zip i2pbrowser-portable