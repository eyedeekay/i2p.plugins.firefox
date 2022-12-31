#! /usr/bin/env sh

. ./config.sh
ant distclean clangFmt versionMd
./javadoc.sh
NUMLINE=`grep release.number build.xml | head -n 1`
READMELINE=`grep 'i2p.plugins.firefox/releases/download' README.md | grep i2pfirefox`
sed -i "s|${READMELINE}|wget https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/${GITHUB_TAG}/i2pfirefox.zip|g" README.md
READMELINE=`grep 'i2p.plugins.firefox/releases/download' LINUX.md | grep i2pbrowser`
sed -i "s|${READMELINE}|wget https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/${GITHUB_TAG}/i2pbrowser.zip|g" LINUX.md
READMELINE=`grep 'i2p.plugins.firefox/releases/download' OSX.md | grep i2pfirefox`
sed -i "s|${READMELINE}|wget https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/${GITHUB_TAG}/i2pfirefox.zip|g" OSX.md
sed -i "s|${NUMLINE}|        <property name=\"release.number\" value=\"$GITHUB_TAG\" />|g" build.xml
edgar && git push --all
ant distclean versionMd tarball jar plugin torrent freeZip jpackage debian fedora
