#! /usr/bin/env sh

. ./config.sh

cat <<EOF > /etc/yum.repos.d/adoptium.repo
[Adoptium]
name=Adoptium
baseurl=https://packages.adoptium.net/artifactory/rpm/${DISTRIBUTION_NAME:-$(. /etc/os-release; echo $ID)}/\$releasever/\$basearch
enabled=1
gpgcheck=1
gpgkey=https://packages.adoptium.net/artifactory/api/gpg/key/public
EOF
yum -y update
yum -y install temurin-18-jdk

jpackage --verbose \
    --type rpm \
    --linux-menu-group "Network;WebBrowser;P2P" \
    --linux-app-category "Network" \
    --linux-package-deps "firefox|chromium|brave|firefox-esr|librewolf|icecat" \
    --linux-shortcut \
    --license-file LICENSE.md \
    --name i2pbrowser \
    --app-version "$GITHUB_TAG" \
    --input src/build \
    --main-jar i2pfirefox.jar \
    --main-class net.i2p.i2pfirefox.I2PBrowser
ls *.rpm