#! /usr/bin/env sh

. ./config.sh
docker build -t eyedeekay/i2p.plugins.firefox .
docker rm -f i2pbrowser-fedora
docker run --name i2pbrowser-fedora eyedeekay/i2p.plugins.firefox
docker cp i2pbrowser-fedora:/src/i2p.plugins.firefox/i2pbrowser-${GITHUB_TAG}-1.x86_64.rpm .
