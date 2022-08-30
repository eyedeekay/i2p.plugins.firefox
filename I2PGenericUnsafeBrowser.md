# Class I2PGenericUnsafeBrowser [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java)  

 >   

Access: public  
Description:  
 > I2PChromiumProfileChecker.java Copyright C 2022 idk <hankhill19580@gmail.com> This program is free software: you can redistribute it and/or modify it under the terms of the MIT License. See LICENSE.md for details. This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. I2PGenericUnsafeBrowser is a wrapper which sets common environment variables for the process controlled by a processbuilder. ALWAYS ALWAYS ALWAYS try the Firefox and Chromium specific launchers first.  

Author: idk   
Parent class: I2PCommonBrowser  
package: net.i2p.i2pfirefox  

## Dependencies

<details>  
  <summary>  
    Show dependencies  
  </summary>  
  <ul>  
<li>java.io.File</li>
<li>java.io.IOException</li>
<li>java.net.Socket</li>
<li>java.util.ArrayList</li>
<li>java.util.Scanner</li>
  </ul>  
</details>  

## Member Variables

####  int DEFAULT_TIMEOUT  [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L)

 >   

+ Access: private  
+ Modifiers: final 

####  String BROWSER  [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L)

 >   

+ Access: public  
+ Modifiers: static 

####  Process p  [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L)

 >   

+ Access: private  

## Methods

### getDefaultWindowsBrowser [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L90)

+ Description: Obtains the default browser for the Windows platform which by now should be Edgium in the worst-case scenario but in case it isn't we can use this function to figure it out. It can find: 1. The current user's HTTPS default browser if they configured it to be non-default 2. The current user's HTTP default browser if they configured it to be non-default 3. Edgium if it's available 4. iexplore if it's not and it will return the first one we find in exactly that order. Adapted from: and from:   
+ Access: public  
+ Modifiers: static 
+ return: String  

This method has no parameters.  


### getDefaultOutOfRegistry [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L118)

+ Description: obtains information out of the Windows registry.   
+ Access: public  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| hkeyquery | String | registry entry to ask for.  |  


### scanAPath [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L144)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| dir | String |  |  


### getAnyUnixBrowser [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L159)

+ Description: Find any browser in our list within a UNIX path   
+ Access: public  
+ Modifiers: static 
+ return: String  

This method has no parameters.  


### findUnsafeBrowserAnywhere [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L178)

+ Description: Find any usable browser and output the whole path   
+ Access: public  
+ Modifiers: static 
+ return: String  

This method has no parameters.  


### deleteRuntimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L226)

+ Description: delete the runtime directory   
+ Access: public  
+ Modifiers: static 
+ return: boolean  

This method has no parameters.  


### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L242)

+ Description: get the runtime directory creating it if create=true   
+ Access: public  
+ Modifiers: static 
+ return: the runtime directory or null if it could not be created   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| create | boolean | if true create the runtime directory if it does not exist  |  


### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L253)

+ Description: get the correct runtime directory   
+ Access: public  
+ Modifiers: static 
+ return: the runtime directory or null if it could not be created or found   

This method has no parameters.  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L275)

+ Description: Waits for an HTTP proxy on port 4444 to be ready. Returns false on timeout of 200 seconds.   
+ Access: public  
+ return: true if the proxy is ready false if it is not.   

This method has no parameters.  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L285)

+ Description: Waits for an HTTP proxy on port 4444 to be ready. Returns false on timeout of the specified number of seconds.   
+ Access: public  
+ return: true if the proxy is ready false if it is not.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int | the number of seconds to wait for the proxy to be ready.  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L297)

+ Description: Waits for an HTTP proxy on the specified port to be ready. Returns false on timeout of the specified number of seconds.   
+ Access: public  
+ return: true if the proxy is ready false if it is not.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int | the number of seconds to wait for the proxy to be ready.  |  
| port | int | the port to wait for the proxy to be ready on.  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L311)

+ Description: Waits for an HTTP proxy on the specified port to be ready. Returns false on timeout of the specified number of seconds.   
+ Access: public  
+ return: true if the proxy is ready false if it is not.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int | the number of seconds to wait for the proxy to be ready.  |  
| port | int | the port to wait for the proxy to be ready on.  |  
| host | String | the host to wait for the proxy to be ready on.  |  


### launchAndDetatch [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L325)

+ Description:   
+ Access: public  
+ return: Process  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L346)

+ Description:   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### sleep [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L364)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| millis | int |  |  


### ValidURL [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L372)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| inUrl | String |  |  


### checkifPortIsOccupied [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L382)

+ Description:   
+ Access: private  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| port | int |  |  
| host | String |  |  


