# Class I2PCommonBrowser [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java)  

 >   

Access: public  
Description:  
 > I2PCommonBrowser.java Copyright C 2022 idk <hankhill19580@gmail.com> This program is free software: you can redistribute it and/or modify it under the terms of the MIT License. See LICENSE.md for details. This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  

Author: idk   
package: net.i2p.i2pfirefox  

## Dependencies

<details>  
  <summary>  
    Show dependencies  
  </summary>  
  <ul>  
<li>java.io.File</li>
<li>java.io.FileInputStream</li>
<li>java.io.FileOutputStream</li>
<li>java.io.IOException</li>
<li>java.io.InputStream</li>
<li>java.io.OutputStream</li>
<li>java.net.Socket</li>
<li>java.nio.file.Files</li>
<li>java.nio.file.StandardCopyOption</li>
<li>java.util.logging.FileHandler</li>
<li>java.util.logging.Logger</li>
<li>java.util.logging.SimpleFormatter</li>
<li>java.util.zip.ZipEntry</li>
<li>java.util.zip.ZipInputStream</li>
  </ul>  
</details>  

## Member Variables

####   [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L36)

 > static FileHandler fh;  

+ Access: private  
+ Modifiers: final 

####   [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L37)

 > // private final int DEFAULT_TIMEOUT = 200;  

+ Access: private  
+ Modifiers: static 

## Methods

### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L131)

+ Description: get the runtime directory creating it if create=true   
+ Access: protected  
+ Modifiers: static 
+ return: the runtime directory or null if it could not be created   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| create | boolean | if true create the runtime directory if it does not exist  |  
| override | String |  |  


### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L148)

+ Description: get the correct runtime directory   
+ Access: protected  
+ Modifiers: static 
+ return: the runtime directory or null if it could not be created or found   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| override | String |  |  


### profileDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L195)

+ Description: get the profile directory creating it if necessary   
+ Access: protected  
+ Modifiers: static 
+ return: the profile directory or null if it could not be created   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| envVar | String |  |  
| browser | String |  |  


### profileDir [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L207)

+ Description:   
+ Access: protected  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| file | String |  |  
| browser | String |  |  


### unpackProfile [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L213)

+ Description:   
+ Access: protected  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| profileDirectory | String |  |  
| browser | String |  |  
| base | String |  |  


### copyDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L259)

+ Description:   
+ Access: protected  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| sourceDirectory | File |  |  
| destinationDirectory | File |  |  
| browser | String |  |  
| base | String |  |  


### copyDirectoryCompatibityMode [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L275)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| source | File |  |  
| destination | File |  |  
| browser | String |  |  
| base | String |  |  


### copyFile [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L284)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| sourceFile | File |  |  
| destinationFile | File |  |  


### validateProfileFirstRun [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L295)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| profileDirectory | String |  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L321)

+ Description: Waits for an HTTP proxy on port 4444 to be ready. Returns false on timeout of 200 seconds.   
+ Access: public  
+ return: true if the proxy is ready false if it is not.   

This method has no parameters.  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L331)

+ Description: Waits for an HTTP proxy on port 4444 to be ready. Returns false on timeout of the specified number of seconds.   
+ Access: public  
+ return: true if the proxy is ready false if it is not.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int | the number of seconds to wait for the proxy to be ready.  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L343)

+ Description: Waits for an HTTP proxy on the specified port to be ready. Returns false on timeout of the specified number of seconds.   
+ Access: public  
+ return: true if the proxy is ready false if it is not.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int | the number of seconds to wait for the proxy to be ready.  |  
| port | int | the port to wait for the proxy to be ready on.  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L358)

+ Description: Waits for an HTTP proxy on the specified port to be ready. Returns false on timeout of the specified number of seconds. If the timeout is zero or less the check is disabled and always returns true.   
+ Access: public  
+ return: true if the proxy is ready false if it is not.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int | the number of seconds to wait for the proxy to be ready.  |  
| port | int | the port to wait for the proxy to be ready on.  |  
| host | String | the host to wait for the proxy to be ready on.  |  


### checkifPortIsOccupied [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L376)

+ Description:   
+ Access: private  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| port | int |  |  
| host | String |  |  


### setProxyTimeoutTime [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L391)

+ Description: Alters the proxy timeout to customized value time in seconds. May be zero.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| time | int |  |  


