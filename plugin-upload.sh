#! /usr/bin/env sh

. ./config.sh
NUMLINE=`grep release.number build.xml | head -n 1`
sed -i "s|${NUMLINE}|        <property name=\"release.number\" value=\"$GITHUB_TAG\" />|g" build.xml
ant distclean versionMd jar plugin

github-release release --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --name "${GITHUB_NAME}" \
    --description "${GITHUB_DESCRIPTION}" \
    --tag "${GITHUB_TAG}"; true
sleep 2s
susum=$(sha256sum i2pfirefox.su3)
github-release upload --user "${GITHUB_USER}" \
    --replace \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "I2P Browser launcher as an I2P Console Plugin. ${susum}" \
    --name "i2pfirefox.su3" \
    --file "i2pfirefox.su3"
#susum=$(sha256sum i2pfirefox-update.su3)
#github-release upload --user "${GITHUB_USER}" \
#    --replace \
#    --repo "${GITHUB_REPO}" \
#    --tag "${GITHUB_TAG}" \
#    --label "I2P Browser launcher as an I2P Console Plugin, update-only. ${susum}" \
#    --name "i2pfirefox-update.su3" \
#    --file "i2pfirefox-update.su3"
echo "Uploaded su3 package"