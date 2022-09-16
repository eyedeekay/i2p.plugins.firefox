#! /usr/bin/env sh

## Run this from inside the `i2p.plugins.firefox` checkout.

docker build -t eyedeekay/i2p.plugins.firefox .
xhost + local:docker
docker run -it --rm \
        --net=host \
        -e DISPLAY=unix$(DISPLAY) \
        -v /tmp/.X11-unix:/tmp/.X11-unix \
        eyedeekay/i2p.plugins.firefox