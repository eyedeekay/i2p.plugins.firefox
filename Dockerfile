FROM debian:stable
RUN apt-get update && apt-get install -y openjdk-17* ant zip
ADD . /usr/src/i2p.firefox
WORKDIR /usr/src/i2p.firefox
RUN ant jar
CMD ./i2pbrowser.cmd