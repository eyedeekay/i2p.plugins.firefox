#! /usr/bin/env sh
export GITHUB_USER=eyedeekay
export GITHUB_REPO=i2p.plugins.firefox
export GITHUB_NAME="Fixes the build system"
export GITHUB_DESCRIPTION=$(cat CHANGES.md VERSION.md)
export GITHUB_TAG=$(git describe --tags --abbrev=0)