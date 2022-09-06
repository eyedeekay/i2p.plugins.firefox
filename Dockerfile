FROM fedora
RUN yum -y update
RUN yum -y install java-latest-openjdk rpm-build
ADD . /src/i2p.plugins.firefox
WORKDIR /src/i2p.plugins.firefox
CMD ./fedora-docker.sh
