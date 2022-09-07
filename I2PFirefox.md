# Class I2PFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java)  

 > */  

Access: public  
Description:  
 > I2PFirefox.java Copyright C 2022 idk <hankhill19580@gmail.com> This program is free software: you can redistribute it and/or modify it under the terms of the MIT License. See LICENSE.md for details. This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  

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
<li>java.util.ArrayList</li>
<li>java.util.concurrent.TimeUnit</li>
  </ul>  
</details>  

## Member Variables

####  String[] FIREFOX_SEARCH_PATHS  [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L)

 >   

+ Access: private  
+ Modifiers: final 

####  Process p  [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L)

 >   

+ Access: private  

####  boolean usability  [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L)

 >   

+ Access: public  
+ Modifiers: static 

## Methods

### FIND_FIREFOX_SEARCH_PATHS_UNIX [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L44)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS_OSX [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L59)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS_WINDOWS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L76)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_ALL_FIREFOX_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L112)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L132)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### NEARBY_FIREFOX_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L146)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIREFOX_FINDER [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L202)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### getOperatingSystem [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L214)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

This method has no parameters.  


### onlyValidFirefoxes [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L236)

+ Description: Check our list of firefox paths for a valid firefox binary. Just an existence check for now but should check versions in the future.   
+ Access: public  
+ return: a list of usable Firefoxes or an empty list if none are found.   

This method has no parameters.  


### topFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L256)

+ Description: Return the best available Firefox from the list of Firefoxes we have.   
+ Access: public  
+ return: the path to the best available Firefox or null if none are found.   

This method has no parameters.  


### topFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L285)

+ Description: Return the best available Firefox from the list of Firefoxes we have. if override is passed it will be validated and if it validates it will be used.   
+ Access: public  
+ return: the path to the best available Firefox or null if none are found.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| overrideFirefox | String |  |  


### defaultProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L303)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Firefox binary and the default profile.   

This method has no parameters.  


### defaultProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L315)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile.   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the args to pass to the Firefox binary  |  


### privateProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L329)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile. Pass the --private-window flag to open a window.   
+ Access: public  
+ return: a ProcessBuilder for the top Firefox binary and the default profile.   

This method has no parameters.  


### privateProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L342)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile. Pass the --private-window flag to open a window.   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the arguments to pass to the Firefox binary  |  


### headlessProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L364)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile. Pass the --headless flag to open without a window.   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the arguments to pass to the Firefox binary  |  


### processBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L387)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile with a specific set of extended arguments.   
+ Access: public  
+ return: a ProcessBuilder for the top Firefox binary and default profile with a specific set of extended arguments.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the extended arguments to pass to the Firefox binary.  |  


### usabilityMode [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L413)

+ Description:   
+ Access: private  
+ return: String  

This method has no parameters.  


### launchAndDetatch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L420)

+ Description:   
+ Access: public  
+ return: Process  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L493)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Firefox with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L517)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Firefox with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L527)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Firefox with the profile directory. Uses a semi-permanent profile.   
+ Access: public  
+ return: void  

This method has no parameters.  


### ValidURL [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L529)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| inUrl | String |  |  


### main [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L540)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### sleep [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L573)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| millis | int |  |  


