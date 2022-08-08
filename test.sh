#! /usr/bin/env sh

GITHUB_USER=eyedeekay
GITHUB_REPO=i2p.plugins.firefox
ant distclean
cd src && \
    ant
./i2pbrowser.cmd