#! /usr/bin/env sh

. ./config.sh
docker build -f Dockerfile.fedora -t eyedeekay/i2p.plugins.firefox.fedora .
docker rm -f i2pbrowser-fedora
docker run --name i2pbrowser-fedora eyedeekay/i2p.plugins.firefox.fedora
docker cp i2pbrowser-fedora:/src/i2p.plugins.firefox/i2pbrowser-${GITHUB_TAG}-1.x86_64.rpm .
