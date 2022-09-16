#! /usr/bin/env sh

. ./config.sh
ant distclean clangFmt
./javadoc.sh
NUMLINE=`grep release.number build.xml | head -n 1`
READMELINE=`grep 'i2p.plugins.firefox/releases/download' README.md`
sed -i "s|${READMELINE}|wget https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/${GITHUB_TAG}/i2pfirefox.zip|g" README.md
sed -i "s|${NUMLINE}|        <property name=\"release.number\" value=\"$GITHUB_TAG\" />|g" build.xml
edgar && git push --all
ant jar freeZip jpackage debian fedora
