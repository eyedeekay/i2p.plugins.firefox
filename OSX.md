Using this on OSX
=================

This code will launch an I2P-Configured Browser on OSX, but at this time
there are no packages for it because it's not possible for me to sign OSX
packages. You can use the `.jar` file with any Java greater than Java 8.

Building a `jpackage`
---------------------

In order to build a `jpackage`, install at least Java 17.

```sh
brew install openjdk@17 
sudo ln -sfn /usr/local/opt/openjdk/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk.jdk
export JAVA_HOME=`/usr/libexec/java_home -v 17`
```