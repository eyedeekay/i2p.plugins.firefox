#! /usr/bin/env sh

. ./config.sh
profilezipsum=$(sha256sum src/i2p.chromium.base.profile.zip)
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "Chromium strict extensions ${profilezipsum}" \
    --name "i2p.chromium.base.profile.zip" \
    --file "src/i2p.chromium.base.profile.zip" \
    --replace
echo "Uploaded Chromium base profile"
profilezipsum=$(sha256sum src/i2p.chromium.usability.profile.zip)
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "Chromium usability extensions ${profilezipsum}" \
    --name "i2p.chromium.usability.profile.zip" \
    --file "src/i2p.chromium.usability.profile.zip" \
    --replace
echo "Uploaded Chromium usability profile"
profilezipsum=$(sha256sum src/i2p.firefox.base.profile.zip)
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "Firefox strict profile ${profilezipsum}" \
    --name "i2p.firefox.base.profile.zip" \
    --file "src/i2p.firefox.base.profile.zip" \
    --replace
echo "Uploaded Firefox base profile"
profilezipsum=$(sha256sum src/i2p.firefox.usability.profile.zip)
github-release upload --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --label "Firefox usability profile ${profilezipsum}" \
    --name "i2p.firefox.usability.profile.zip" \
    --file "src/i2p.firefox.usability.profile.zip" \
    --replace
echo "Uploaded Firefox usability profile"