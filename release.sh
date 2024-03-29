#! /usr/bin/env sh

. ./config.sh
#./preprelease.sh
github-release release --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --name "${GITHUB_NAME}" \
    --description "${GITHUB_DESCRIPTION}" \
    --tag "${GITHUB_TAG}"; true
sleep 2s
./profiles-upload.sh
sleep 2s
github-release edit --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --name "${GITHUB_NAME}" \
    --description "${GITHUB_DESCRIPTION}" \
    --tag "${GITHUB_TAG}"; true
echo "Relase ${GITHUB_TAG} setup"
tarsum=$(sha256sum i2p.plugins.firefox.tar.gz)
github-release upload --user "${GITHUB_USER}" \
    --replace \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher source tarball. ${tarsum}" \
    --name "i2p.plugins.firefox-${GITHUB_TAG}.tar.gz" \
    --file "i2p.plugins.firefox.tar.gz" 
jarsum=$(sha256sum "src/build/i2pfirefox.jar")
github-release upload --user "${GITHUB_USER}" \
    --replace \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a .jar. Useful as a library or for advanced users. ${jarsum}" \
    --name "i2pfirefox.jar" \
    --file "src/build/i2pfirefox.jar" 
echo "Uploaded jar"
zipsum=$(sha256sum "i2pfirefox.zip")
github-release upload --user "${GITHUB_USER}" \
    --replace \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a .jar and a set of semi-univeral launcher scripts. ${zipsum}" \
    --name "i2pfirefox.zip" \
    --file "i2pfirefox.zip" 
echo "Uploaded freestanding zip"
jvmsum=$(sha256sum "i2pbrowser.tar.gz")
github-release upload --user "${GITHUB_USER}" \
    --replace \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a Jpackage, does not require a JVM. ${jvmsum}" \
    --name "i2pbrowser.tar.gz" \
    --file "i2pbrowser.tar.gz" 
echo "Uploaded jpackage zip"
debsum=$(sha256sum "i2pbrowser_${GITHUB_TAG}_amd64.deb")
github-release upload --user "${GITHUB_USER}" \
    --replace \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a Jpackage inside of a Debian package. ${debsum}" \
    --name "i2pbrowser_${GITHUB_TAG}_amd64.deb" \
    --file "i2pbrowser_${GITHUB_TAG}_amd64.deb" 
echo "Uploaded debian package"
rpmsum=$(sha256sum i2pbrowser-${GITHUB_TAG}-1.x86_64.rpm)
github-release upload --user "${GITHUB_USER}" \
    --replace \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a Jpackage inside of a Fedora package. ${rpmsum}" \
    --name "i2pbrowser-${GITHUB_TAG}-1.x86_64.rpm" \
    --file "i2pbrowser-${GITHUB_TAG}-1.x86_64.rpm"
echo "Uploaded fedora package"
susum=$(sha256sum i2pfirefox.su3)
github-release upload --user "${GITHUB_USER}" \
    --replace \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as an I2P Console Plugin. ${susum}" \
    --name "i2pfirefox.su3" \
    --file "i2pfirefox.su3"
susum=$(sha256sum i2pfirefox-update.su3)
github-release upload --user "${GITHUB_USER}" \
    --replace \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as an I2P Console Plugin, update-only. ${susum}" \
    --name "i2pfirefox-update.su3" \
    --file "i2pfirefox-update.su3"
echo "Uploaded su3 package"
torsum=$(sha256sum i2p.plugins.firefox.torrent.zip)
github-release upload --user "${GITHUB_USER}" \
    --replace \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as an I2P Console Plugin, release torrent. ${torsum}" \
    --name "i2p.plugins.firefox.torrent.zip" \
    --file "i2p.plugins.firefox.torrent.zip"
echo "Uploaded torrent package"

git pull github --tags
git push --all
