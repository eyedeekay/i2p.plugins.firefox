#! /usr/bin/env sh

nightly_hash=nightly-$(git log -1 --format=%H)
git tag -s "$nightly_hash"
git push --all
git push --tags