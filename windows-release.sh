#! /usr/bin/env sh
git pull --all
. ../i2p.firefox/config.sh
. ./config.sh
. "${HOME}/github-release-config.sh"
ant distclean jar
./windows.sh
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a Jpackage inside of an MSI package." \
    --name "i2pbrowser_${GITHUB_TAG}.msi" \
    --file "i2pbrowser_${GITHUB_TAG}.msi" \
    --replace
echo "Uploaded Windows MSI package"
