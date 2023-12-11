#! /usr/bin/env sh

ant distclean
ant jar

./test/test.sh
./test/test-chromium.sh
./test/test-firefox.sh
./test/test-torbrowser.sh
./test/test-unsafe.sh
