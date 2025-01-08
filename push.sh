#! /usr/bin/env sh

nightly_hash=nightly-$(git log -1 --format=%H)
nightly_date=$(date)
git tag -m "$nightly_date" -s "$nightly_hash"
git push --all
git push --tags