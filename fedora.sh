#! /usr/bin/env sh

. ./config.sh
docker build -t eyedeekay/i2p.plugins.firefox .
docker run --name i2pbrowser-fedora i2p.plugins.firefox
docker cp i2pbrowser-fedora:/src/i2p.plugins.firefox/i2pbrowser-${GITHUB_TAG}.rpm