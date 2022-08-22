#! /usr/bin/env sh

GITHUB_USER=eyedeekay
GITHUB_REPO=i2p.plugins.firefox
GITHUB_NAME="Which adds the ability to pass the -private flag to the command line to launch a private browser instance."
GITHUB_DESCRIPTION=$(cat CHANGES.md)
GITHUB_TAG=0.0.15
ant distclean
ant jar freeZip
github-release release --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --name "${GITHUB_NAME}" \
    --description "${GITHUB_DESCRIPTION}" \
    --tag "${GITHUB_TAG}"; true
sleep 2s
github-release edit --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --name "${GITHUB_NAME}" \
    --description "${GITHUB_DESCRIPTION}" \
    --tag "${GITHUB_TAG}"; true
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --name "i2pfirefox.jar" \
    --file "src/build/i2pfirefox.jar" \
    --replace
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --name "i2pfirefox.zip" \
    --file "i2pfirefox.zip" \
    --replace   
git pull github --tags
git push --all