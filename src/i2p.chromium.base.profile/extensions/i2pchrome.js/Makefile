default: zip

install: uninstall
	mkdir -p /usr/share/webext/i2pchrome.js@eyedeekay.github.io \
		/usr/share/mozilla/extensions/{ec8030f7-c20a-464f-9b0e-13a3a9e97384}/
	cp -rv options /usr/share/webext/i2pchrome.js@eyedeekay.github.io/options
	cp -rv icons /usr/share/webext/i2pchrome.js@eyedeekay.github.io/icons
	cp -rv _locales /usr/share/webext/i2pchrome.js@eyedeekay.github.io/_locales
	cp background.js /usr/share/webext/i2pchrome.js@eyedeekay.github.io
	cp proxy.js /usr/share/webext/i2pchrome.js@eyedeekay.github.io
	cp info.js /usr/share/webext/i2pchrome.js@eyedeekay.github.io
	cp content.js /usr/share/webext/i2pchrome.js@eyedeekay.github.io
	cp info.css /usr/share/webext/i2pchrome.js@eyedeekay.github.io
	cp window.html /usr/share/webext/i2pchrome.js@eyedeekay.github.io
	cp manifest.json /usr/share/webext/i2pchrome.js@eyedeekay.github.io/
	cp README.md /usr/share/webext/i2pchrome.js@eyedeekay.github.io
	cp LICENSE /usr/share/webext/i2pchrome.js@eyedeekay.github.io
	ln -sf /usr/share/webext/i2pchrome.js@eyedeekay.github.io \
		/usr/share/mozilla/extensions/{ec8030f7-c20a-464f-9b0e-13a3a9e97384}/i2pchrome.js@eyedeekay.github.io

uninstall:
	rm -rf /usr/share/webext/i2pchrome.js@eyedeekay.github.io \
		/usr/share/mozilla/extensions/{ec8030f7-c20a-464f-9b0e-13a3a9e97384}/i2pchrome.js@eyedeekay.github.io

clobber:
	rm -f ../i2pchrome.js.zip ../i2p_proxy*.xpi

VERSION=1.29

xpi:
	mv ~/Downloads/i2p_proxy-$(VERSION)-an+fx.xpi ../i2pchrome.js@eyedeekay.github.io.xpi

cp:
	cp ../i2pchrome.js@eyedeekay.github.io.xpi ./i2pchrome.js@eyedeekay.github.io.xpi

version:
	sed -i 's|$(shell grep "\"version\": " manifest.json)|  \"version\": \"$(VERSION)\",|g' manifest.json

zip: version
	zip --exclude="./i2pchrome.js@eyedeekay.github.io.xpi" \
		--exclude="i2pchrome.js.png" -r -FS ../i2pchrome.js.zip *

profile-install:
	cp ./i2pchrome.js@eyedeekay.github.io.xpi $(HOME)/.mozilla/firefox/firefox.profile.i2p/firefox.profile.i2p/extensions
	cp ./i2pchrome.js@eyedeekay.github.io.xpi $(HOME)/.mozilla/firefox/.firefox.profile.i2p.default/extensions

to-profile:
	 cp ./i2pchrome.js@eyedeekay.github.io.xpi /usr/local/lib/firefox.profile.i2p/firefox.profile.i2p/extensions/

pi: profile-install

DESC="A simple plugin for configuring an i2p browser."

release:
	gothub release -u eyedeekay -r i2pchrome.js -t $(VERSION) -n $(VERSION) -d $(DESC)

upload:
	gothub upload -u eyedeekay -r i2pchrome.js -t $(VERSION) -n "i2pchrome.js@eyedeekay.github.io.xpi" -f "../i2pchrome.js@eyedeekay.github.io.xpi"

fmt:
	#cleancss -O1 all -O2 all --format beautify home.css -o .home.css && mv .home.css home.css
	#cleancss -O1 all -O2 all --format beautify info.css -o .info.css && mv .info.css info.css
	find . -path ./node_modules -prune -o -name '*.js' -exec prettier --write {} \;
	find . -path ./node_modules -prune -o -name '*.json' -exec prettier --write {} \;
