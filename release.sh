#! /usr/bin/env sh

. ./config.sh
ant distclean clangFmt
./javadoc.sh
NUMLINE=`grep release.number build.xml | head -n 1`
sed -i "s|$NUMLINE|        <property name=\"release.number\" value=\"$GITHUB_TAG\" />|g" build.xml
edgar && git push --all
ant jar freeZip jpackage debian fedora
github-release release --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --name "${GITHUB_NAME}" \
    --description "${GITHUB_DESCRIPTION}" \
    --tag "${GITHUB_TAG}"; true
sleep 2s
github-release edit --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --name "${GITHUB_NAME}" \
    --description "${GITHUB_DESCRIPTION}" \
    --tag "${GITHUB_TAG}"; true
echo "Relase ${GITHUB_TAG} setup"
jarsum=$(sha256sum "src/build/i2pfirefox.jar")
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a .jar. Useful as a library or for advanced users. ${jarsum}" \
    --name "i2pfirefox.jar" \
    --file "src/build/i2pfirefox.jar" \
    --replace
echo "Uploaded jar"
zipsum=$(sha256sum "i2pfirefox.zip")
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a .jar and a set of semi-univeral launcher scripts. ${zipsum}" \
    --name "i2pfirefox.zip" \
    --file "i2pfirefox.zip" \
    --replace
echo "Uploaded freestanding zip"
jvmsum=$(sha256sum "i2pbrowser.zip")
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a Jpackage, does not require a JVM. ${jvmsum}" \
    --name "i2pbrowser.zip" \
    --file "i2pbrowser.zip" \
    --replace
echo "Uploaded jpackage zip"
debsum=$(sha256sum "i2pbrowser_${GITHUB_TAG}_amd64.deb")
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a Jpackage inside of a Debian package. ${debsum}" \
    --name "i2pbrowser_${GITHUB_TAG}_amd64.deb" \
    --file "i2pbrowser_${GITHUB_TAG}_amd64.deb" \
    --replace
echo "Uploaded debian package"
rpmsum=$(sha256sum i2pbrowser-${GITHUB_TAG}-1.x86_64.rpm)
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a Jpackage inside of a Fedora package. ${rpmsum}" \
    --name "i2pbrowser-${GITHUB_TAG}-1.x86_64.rpm" \
    --file "i2pbrowser-${GITHUB_TAG}-1.x86_64.rpm" \
    --replace
echo "Uploaded fedora package"
git pull github --tags
git push --all
