#! /usr/bin/env sh

. ./config.sh
ant distclean clangFmt
./javadoc.sh
NUMLINE=`grep release.number build.xml | head -n 1`
sed -i "s|$NUMLINE|        <property name=\"release.number\" value=\"$GITHUB_TAG\" />|g" build.xml
edgar && git push --all
ant jar freeZip jpackage debian
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
echo "Relase $GITHUB_TAG setup"
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a .jar." \
    --name "i2pfirefox.jar" \
    --file "src/build/i2pfirefox.jar" \
    --replace
echo "Uploaded jar"
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a .jar and a set of semi-univeral launcher scripts." \
    --name "i2pfirefox.zip" \
    --file "i2pfirefox.zip" \
    --replace
echo "Uploaded freestanding zip"
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a Jpackage, does not require a JVM, Linux Only for now unless you BYO." \
    --name "i2pbrowser.zip" \
    --file "i2pbrowser.zip" \
    --replace
echo "Uploaded jpackage zip"
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as a Jpackage inside of a Debian package." \
    --name "i2pbrowser.deb" \
    --file "i2pbrowser.deb" \
    --replace  
echo "Uploaded debian zip"
git pull github --tags
git push --all
