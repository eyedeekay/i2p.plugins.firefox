FROM fedora
RUN yum -y update
RUN yum -y install rpm-build
ADD . /src/i2p.plugins.firefox
WORKDIR /src/i2p.plugins.firefox
CMD ./fedora-docker.sh
