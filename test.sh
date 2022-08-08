#! /usr/bin/env sh

GITHUB_USER=eyedeekay
GITHUB_REPO=i2p.plugins.firefox
ant distclean
cd src && \
    ant && cd ../
./i2pbrowser.cmd