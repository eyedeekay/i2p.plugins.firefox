# Class I2PChromium [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java)  

 > */  

Access: public  
Description:  
 > I2PChromium.java Copyright C 2022 idk <hankhill19580@gmail.com> This program is free software: you can redistribute it and/or modify it under the terms of the MIT License. See LICENSE.md for details. This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  

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
<li>java.io.FileWriter</li>
<li>java.io.IOException</li>
<li>java.io.PrintWriter</li>
<li>java.util.ArrayList</li>
<li>java.util.Arrays</li>
<li>java.util.stream.Stream</li>
  </ul>  
</details>  

## Member Variables

####  String[] CHROMIUM_SEARCH_PATHS  [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L)

 >   

+ Access: private  
+ Modifiers: final 

####  Process p  [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L)

 >   

+ Access: private  

## Methods

### FIND_CHROMIUM_SEARCH_PATHS_UNIX [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L56)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_CHROMIUM_SEARCH_PATHS_OSX [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L72)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_CHROMIUM_SEARCH_PATHS_WINDOWS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L84)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_ALL_CHROMIUM_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L121)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_CHROMIUM_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L141)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### NEARBY_CHROMIUM_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L155)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### CHROMIUM_FINDER [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L209)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### getOperatingSystem [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L221)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

This method has no parameters.  


### onlyValidChromiums [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L243)

+ Description: Check our list of chrome paths for a valid chrome binary. Just an existence check for now but should check versions in the future.   
+ Access: public  
+ return: a list of usable Chromiums or an empty list if none are found.   

This method has no parameters.  


### topChromium [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L263)

+ Description: Return the best available Chromium from the list of Chromiums we have.   
+ Access: public  
+ return: the path to the best available Chromium or null if none are found.   

This method has no parameters.  


### topChromium [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L292)

+ Description: Return the best available Chromium from the list of Chromiums we have. if override is passed it will be validated and if it validates it will be used.   
+ Access: public  
+ return: the path to the best available Chromium or null if none are found.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| overrideChromium | String |  |  


### defaultProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L310)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile.   

This method has no parameters.  


### defaultProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L323)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile. @args the arguments to pass to the Chromium binary   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### privateProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L335)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile. Always passes the --incognito flag.   

This method has no parameters.  


### privateProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L348)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile. Always passes the --incognito flag.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the arguments to pass to the Chromium binary.  |  


### appProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L370)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile. Always passes the --app flag.   

This method has no parameters.  


### appProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L383)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile. Always passes the --app flag.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the arguments to pass to the Chromium binary.  |  


### processBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L430)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile with a specific set of extended arguments.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and default profile with a specific set of extended arguments.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the extended arguments to pass to the Chromium binary.  |  


### launchAndDetatch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L548)

+ Description:   
+ Access: public  
+ return: Process  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launchAndDetatch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L555)

+ Description:   
+ Access: public  
+ return: Process  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | int |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L612)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Chromium with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L618)

+ Description:   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | int |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L643)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Chromium with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L652)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Chromium with the profile directory.   
+ Access: public  
+ return: void  

This method has no parameters.  


### ValidURL [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L654)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| inUrl | String |  |  


### main [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L664)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### sleep [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L703)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| millis | int |  |  


