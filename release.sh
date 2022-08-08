#! /usr/bin/env sh

GITHUB_USER=eyedeekay
GITHUB_REPO=i2p.plugins.firefox
GITHUB_NAME="Initial Release"
GITHUB_DESCRIPTION=$(cat README.md)
GITHUB_TAG=0.0.1
ant distclean
cd src && \
    ant
github-release release --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --name "${GITHUB_NAME}" \
    --description "${GITHUB_DESCRIPTION}" \
    --tag "${GITHUB_TAG}"
sleep 2s
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --name "i2pfirefox.jar" \
    --file "build/i2pfirefox.jar" \
    --replace