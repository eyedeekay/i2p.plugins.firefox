#! /usr/bin/env sh

. ./config.sh
#./preprelease.sh
github-release release --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --name "Build Tools" \
    --description "binaries required for generating the profiles" \
    --tag "build-tools"; true
echo "Uploading generated build-tools"
sleep 2s
torsum=$(sha256sum $HOME/go/bin/crx3 | sed "s|$HOME||g")
echo "$torsum"
github-release upload --user "${GITHUB_USER}" \
    --replace \
    --repo "${GITHUB_REPO}" \
    --tag "build-tools" \
    --label "crx3 for Linux. ${torsum}" \
    --name "crx3-linux" \
    --file "$HOME/go/bin/crx3"
echo "Uploaded crx3 linux package"
torsum=$(sha256sum $HOME/go/bin/darwin_amd64/crx3 | sed "s|$HOME||g")
echo "$torsum"
github-release upload --user "${GITHUB_USER}" \
    --replace \
    --repo "${GITHUB_REPO}" \
    --tag "build-tools" \
    --label "crx3 for Mac. ${torsum}" \
    --name "crx3-osx" \
    --file "$HOME/go/bin/darwin_amd64/crx3"
echo "Uploaded crx3 mac package"
torsum=$(sha256sum $HOME/go/bin/windows_amd64/crx3.exe | sed "s|$HOME||g")
echo "$torsum"
github-release upload --user "${GITHUB_USER}" \
    --replace \
    --repo "${GITHUB_REPO}" \
    --tag "build-tools" \
    --label "crx3 for Windows. ${torsum}" \
    --name "crx3.exe" \
    --file "$HOME/go/bin/windows_amd64/crx3.exe"
echo "Uploaded crx3 windows package"
git pull github --tags
git push --all
