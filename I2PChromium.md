# Class I2PChromium [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java)  

 > */  

Access: public  
Description:  
 > I2PChromium.java Copyright C 2022 idk <hankhill19580@gmail.com> This program is free software: you can redistribute it and/or modify it under the terms of the MIT License. See LICENSE.md for details. This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  

Author: idk   
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
  </ul>  
</details>  

## Member Variables

####  String[] CHROMIUM_SEARCH_PATHS  [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L)

 >   

+ Access: private  
+ Modifiers: final 

####  int DEFAULT_TIMEOUT  [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L)

 >   

+ Access: private  
+ Modifiers: final 

####  Process p  [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L)

 >   

+ Access: private  

## Methods

### FIND_CHROMIUM_SEARCH_PATHS_UNIX [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L44)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_CHROMIUM_SEARCH_PATHS_OSX [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L60)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_CHROMIUM_SEARCH_PATHS_WINDOWS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L78)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_ALL_CHROMIUM_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L115)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_CHROMIUM_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L135)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### NEARBY_CHROMIUM_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L149)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### CHROMIUM_FINDER [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L203)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### getOperatingSystem [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L215)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

This method has no parameters.  


### onlyValidChromiums [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L237)

+ Description: Check our list of chrome paths for a valid chrome binary. Just an existence check for now but should check versions in the future.   
+ Access: public  
+ return: a list of usable Chromiums or an empty list if none are found.   

This method has no parameters.  


### topChromium [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L257)

+ Description: Return the best available Chromium from the list of Chromiums we have.   
+ Access: public  
+ return: the path to the best available Chromium or null if none are found.   

This method has no parameters.  


### topChromium [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L286)

+ Description: Return the best available Chromium from the list of Chromiums we have. if override is passed it will be validated and if it validates it will be used.   
+ Access: public  
+ return: the path to the best available Chromium or null if none are found.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| overrideChromium | String |  |  


### defaultProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L304)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile.   

This method has no parameters.  


### defaultProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L317)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile. @args the arguments to pass to the Chromium binary   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### privateProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L329)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile. Always passes the --incognito flag.   

This method has no parameters.  


### privateProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L342)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile. Always passes the --incognito flag.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the arguments to pass to the Chromium binary.  |  


### processBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L387)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile with a specific set of extended arguments.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and default profile with a specific set of extended arguments.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the extended arguments to pass to the Chromium binary.  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L441)

+ Description: Waits for an HTTP proxy on port 4444 to be ready. Returns false on timeout of 200 seconds.   
+ Access: public  
+ return: true if the proxy is ready false if it is not.   

This method has no parameters.  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L451)

+ Description: Waits for an HTTP proxy on port 4444 to be ready. Returns false on timeout of the specified number of seconds.   
+ Access: public  
+ return: true if the proxy is ready false if it is not.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int | the number of seconds to wait for the proxy to be ready.  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L463)

+ Description: Waits for an HTTP proxy on the specified port to be ready. Returns false on timeout of the specified number of seconds.   
+ Access: public  
+ return: true if the proxy is ready false if it is not.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int | the number of seconds to wait for the proxy to be ready.  |  
| port | int | the port to wait for the proxy to be ready on.  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L476)

+ Description: Waits for an HTTP proxy on the specified port to be ready. Returns false on timeout of the specified number of seconds.   
+ Access: public  
+ return: true if the proxy is ready false if it is not.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int | the number of seconds to wait for the proxy to be ready.  |  
| port | int | the port to wait for the proxy to be ready on.  |  
| host | String | the host to wait for the proxy to be ready on.  |  


### checkifPortIsOccupied [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L489)

+ Description:   
+ Access: private  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| port | int |  |  
| host | String |  |  


### launchAndDetatch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L499)

+ Description:   
+ Access: public  
+ return: Process  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L544)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Chromium with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L567)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Chromium with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L576)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Chromium with the profile directory.   
+ Access: public  
+ return: void  

This method has no parameters.  


### ValidURL [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L578)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| inUrl | String |  |  


### main [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L588)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### sleep [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L610)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| millis | int |  |  


