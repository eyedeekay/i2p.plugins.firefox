FROM debian:stable
RUN apt-get update && apt-get install -y openjdk-17* ant zip firefox-esr
ADD . /usr/src/i2p.firefox
WORKDIR /usr/src/i2p.firefox
RUN ant jar
CMD java -cp ./src/build/i2pfirefox.jar net.i2p.i2pfirefox.I2PBrowser