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
<li>java.io.FileOutputStream</li>
<li>java.io.FileWriter</li>
<li>java.io.IOException</li>
<li>java.io.OutputStream</li>
<li>java.io.PrintWriter</li>
<li>java.util.ArrayList</li>
<li>java.util.Arrays</li>
<li>java.util.List</li>
<li>java.util.concurrent.TimeUnit</li>
<li>java.util.stream.Collectors</li>
<li>java.util.stream.Stream</li>
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

### baseMode [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L35)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

This method has no parameters.  


### storeFirefoxDefaults [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L58)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

This method has no parameters.  


### firefoxPathsUnix [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L87)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### firefoxBinsUnix [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L96)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS_UNIX [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L113)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### firefoxPathsOSX [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L126)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS_OSX [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L136)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### firefoxPathsWindows [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L149)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### firefoxBinsWindows [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L175)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS_WINDOWS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L185)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_ALL_FIREFOX_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L199)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L219)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### NEARBY_FIREFOX_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L233)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIREFOX_FINDER [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L359)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### onlyValidFirefoxes [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L380)

+ Description: Check our list of firefox paths for a valid firefox binary. Just an existence check for now but should check versions in the future.   
+ Access: public  
+ return: a list of usable Firefoxes or an empty list if none are found.   

This method has no parameters.  


### topFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L401)

+ Description: Return the best available Firefox from the list of Firefoxes we have.   
+ Access: public  
+ return: the path to the best available Firefox or null if none are found.   

This method has no parameters.  


### topFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L431)

+ Description: Return the best available Firefox from the list of Firefoxes we have. if override is passed it will be validated and if it validates it will be used.   
+ Access: public  
+ return: the path to the best available Firefox or null if none are found.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| overrideFirefox | String |  |  


### defaultProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L449)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Firefox binary and the default profile.   

This method has no parameters.  


### defaultProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L461)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile.   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the args to pass to the Firefox binary  |  


### privateProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L475)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile. Pass the --private-window flag to open a window.   
+ Access: public  
+ return: a ProcessBuilder for the top Firefox binary and the default profile.   

This method has no parameters.  


### privateProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L488)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile. Pass the --private-window flag to open a window.   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the arguments to pass to the Firefox binary  |  


### appProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L511)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile. Pass the --private-window flag to open a window.   
+ Access: public  
+ return: a ProcessBuilder for the top Firefox binary and the default profile.   

This method has no parameters.  


### appProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L524)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile. Pass the --private-window flag to open a window.   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the arguments to pass to the Firefox binary  |  


### headlessProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L545)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile. Pass the --headless flag to open without a window.   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the arguments to pass to the Firefox binary  |  


### processBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L573)

+ Description:   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  
| app | boolean |  |  


### usabilityMode [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L630)

+ Description:   
+ Access: private  
+ return: String  

This method has no parameters.  


### launchAndDetatch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L636)

+ Description:   
+ Access: public  
+ return: Process  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launchAndDetatch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L642)

+ Description:   
+ Access: public  
+ return: Process  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | int |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L736)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Firefox with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L742)

+ Description:   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | int |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L766)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Firefox with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L776)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Firefox with the profile directory. Uses a semi-permanent profile.   
+ Access: public  
+ return: void  

This method has no parameters.  


### ValidURL [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L778)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| inUrl | String |  |  


### main [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L789)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### sleep [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L826)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| millis | int |  |  


