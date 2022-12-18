#! /usr/bin/env bash

rm -rf docs

~/.cargo/bin/lojidoc src/java/net/i2p/i2pfirefox/ -s -l > report.log
~/.cargo/bin/lojidoc -c src/java/net/i2p/i2pfirefox/ -s
mkdir -p docs
mv -v generated/net/i2p/i2pfirefox/*.md ./docs/
rm -rf generated
git add ./*.md