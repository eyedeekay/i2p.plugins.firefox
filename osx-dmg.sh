#! /usr/bin/env sh
. ./config.sh

if [ -z "$I2P_SIGNER_USERPHRASE" ]; then
    I2P_SIGNER_USERPHRASE=$(security find-identity -v -p codesigning | cut -d ' ' -f 4)
    echo "Warning: using automatically configured signer ID, make sure this is the one you want: $I2P_SIGNER_USERPHRASE"
    echo "continuing in 10 seconds"
    sleep 10
fi

jpackage \
    --verbose \
    --type dmg \
    --name i2pbrowser \
    --app-version "$GITHUB_TAG" \
    --icon src/icon.icns \
    --input src/build \
    --main-jar i2pfirefox.jar \
    --mac-sign \
    --mac-signing-key-user-name "$I2P_SIGNER_USERPHRASE" \
    --mac-entitlements resources/entitlements.xml \
    --main-class net.i2p.i2pfirefox.I2PBrowser
