#! /usr/bin/env sh

. ./config.sh
github-release download --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --name "i2pfirefox.jar"
echo "Downloaded jar"
github-release download --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --name "i2pfirefox.zip" 
echo "Downloaded freestanding zip"
github-release download --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --name "i2pbrowser.zip" 
echo "Downloaded jpackage zip"
github-release download --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --name "i2pbrowser_${GITHUB_TAG}_.deb"
echo "Downloaded debian package"
github-release download --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --name "i2pbrowser-${GITHUB_TAG}_.rpm"
echo "Downloaded fedora package"
github-release download --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --name "i2pbrowser.msi"
echo "Downloaded MSI package"
github-release download --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --name "i2pbrowser.exe"
echo "Downloaded EXE package"
github-release download --user "${GITHUB_USER}" \
    --repo "${GITHUB_REPO}" \
    --tag "${GITHUB_TAG}" \
    --name "i2pbrowser-portable.zip"
echo "Downloaded Windows ZIP package"

echo "Sums for release ${GITHUB_TAG}" | tee RELEASES.md
echo "==============================" | tee -a RELEASES.md
echo "" | tee -a RELEASES.md
echo "[$(sha256sum i2pfirefox.jar)](https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/${GITHUB_TAG}/i2pfirefox.jar)" | tee -a RELEASES.md
echo "[$(sha256sum i2pfirefox.zip)](https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/${GITHUB_TAG}/i2pfirefox.zip)" | tee -a RELEASES.md
echo "[$(sha256sum i2pbrowser.zip)](https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/${GITHUB_TAG}/i2pbrowser.zip)" | tee -a RELEASES.md
echo "[$(sha256sum i2pbrowser_${GITHUB_TAG}_.deb)](https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/${GITHUB_TAG}/i2pbrowser_${GITHUB_TAG}_.deb)" | tee -a RELEASES.md
echo "[$(sha256sum i2pbrowser-${GITHUB_TAG}_.rpm)](https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/${GITHUB_TAG}/i2pbrowser-${GITHUB_TAG}_.rpm)" | tee -a RELEASES.md
echo "[$(sha256sum i2pbrowser.msi)](https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/${GITHUB_TAG}/i2pbrowser.msi)" | tee -a RELEASES.md
echo "[$(sha256sum i2pbrowser.exe)](https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/${GITHUB_TAG}/i2pbrowser.exe)" | tee -a RELEASES.md
echo "[$(sha256sum i2pbrowser-portable.zip)](https://github.com/eyedeekay/i2p.plugins.firefox/releases/download/${GITHUB_TAG}/i2pbrowser-portable.zip)" | tee -a RELEASES.md
echo "" | tee -a RELEASES.md

git add RELEASES.md
edgar
git commit -am "Update packages page"
git push --all