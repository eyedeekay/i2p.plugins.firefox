#! /usr/bin/env sh
git pull --all
. ../i2p.firefox/config.sh
. ./config.sh
. "${HOME}/github-release-config.sh"
ant distclean jar
ant windows-msi-release
msisum=$(sha256sum "i2pbrowser-${GITHUB_TAG}.msi")
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a Jpackage inside of an MSI package. ${msisum}" \
    --name "i2pbrowser.msi" \
    --file "i2pbrowser-${GITHUB_TAG}.msi" \
    --replace
echo "Uploaded MSI package"
ant windows-exe-release
exesum=$(sha256sum "i2pbrowser-${GITHUB_TAG}.exe")
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a Jpackage inside of an EXE package. ${exesum}" \
    --name "i2pbrowser.exe" \
    --file "i2pbrowser-${GITHUB_TAG}.exe" \
    --replace
echo "Uploaded EXE package"
ant windows-portable-release
zipsum=$(sha256sum "i2pbrowser-portable-${GITHUB_TAG}.zip")
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a Jpackage inside of an zip to be run from a directory on Windows. ${zipsum}" \
    --name "i2pbrowser-portable.zip" \
    --file "i2pbrowser-portable-${GITHUB_TAG}.zip" \
    --replace
echo "Uploaded Windows ZIP package"
