FROM fedora
RUN yum update
RUN yum install -y java-latest-openjdk rpmbuild
ADD . /src/i2p.plugins.firefox
WORKDIR /src/i2p.plugins.firefox
CMD ./fedora-docker.sh