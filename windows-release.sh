#! /usr/bin/env sh
git pull --all
. ../i2p.firefox/config.sh
. ./config.sh
. "${HOME}/github-release-config.sh"
ant distclean jar
./windows.sh
./windows-exe.sh
./windows-portable.sh
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a Jpackage inside of an MSI package." \
    --name "i2pbrowser-${GITHUB_TAG}.msi" \
    --file "i2pbrowser-${GITHUB_TAG}.msi" \
    --replace
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a Jpackage inside of an EXE package." \
    --name "i2pbrowser-${GITHUB_TAG}.exe" \
    --file "i2pbrowser-${GITHUB_TAG}.exe" \
    --replace
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a Jpackage inside of an zip to be run from a directory on Windows." \
    --name "i2pbrowser-portable-${GITHUB_TAG}.zip" \
    --file "i2pbrowser-portable-${GITHUB_TAG}.zip" \
    --replace
echo "Uploaded Windows ZIP package"
