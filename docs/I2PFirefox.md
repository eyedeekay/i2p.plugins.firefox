# Class I2PFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java)  

 > */  

Access: public  
Description:  
 > I2PFirefox.java Copyright C 2022 idk <hankhill19580@gmail.com> This program is free software: you can redistribute it and/or modify it under the terms of the MIT License. See LICENSE.md for details. This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  

Author: idk   
Parent class: I2PFirefoxProfileUnpacker  
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

####  Process process  [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L)

 >   

+ Access: private  

####  boolean usability  [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L)

 >   

+ Access: public  

## Methods

### baseMode [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L35)

+ Description:   
+ Access: private  
+ return: String  

This method has no parameters.  


### storeFirefoxDefaults [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L58)

+ Description:   
+ Access: public  
+ return: void  

This method has no parameters.  


### firefoxPathsUnix [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L87)

+ Description:   
+ Access: public  
+ return: String[]  

This method has no parameters.  


### firefoxBinsUnix [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L96)

+ Description:   
+ Access: public  
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS_UNIX [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L113)

+ Description:   
+ Access: private  
+ return: String[]  

This method has no parameters.  


### firefoxPathsOSX [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L126)

+ Description:   
+ Access: public  
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS_OSX [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L136)

+ Description:   
+ Access: private  
+ return: String[]  

This method has no parameters.  


### firefoxPathsWindows [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L149)

+ Description:   
+ Access: public  
+ return: String[]  

This method has no parameters.  


### firefoxBinsWindows [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L182)

+ Description:   
+ Access: private  
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS_WINDOWS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L192)

+ Description:   
+ Access: private  
+ return: String[]  

This method has no parameters.  


### FIND_ALL_FIREFOX_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L206)

+ Description:   
+ Access: private  
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L226)

+ Description:   
+ Access: private  
+ return: String[]  

This method has no parameters.  


### NEARBY_FIREFOX_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L241)

+ Description:   
+ Access: private  
+ return: String[]  

This method has no parameters.  


### FIREFOX_FINDER [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L337)

+ Description:   
+ Access: private  
+ return: String[]  

This method has no parameters.  


### onlyValidFirefoxes [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L358)

+ Description: Check our list of firefox paths for a valid firefox binary. Just an existence check for now but should check versions in the future.   
+ Access: public  
+ return: a list of usable Firefoxes or an empty list if none are found.   

This method has no parameters.  


### topFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L379)

+ Description: Return the best available Firefox from the list of Firefoxes we have.   
+ Access: public  
+ return: the path to the best available Firefox or null if none are found.   

This method has no parameters.  


### topFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L409)

+ Description: Return the best available Firefox from the list of Firefoxes we have. if override is passed it will be validated and if it validates it will be used.   
+ Access: public  
+ return: the path to the best available Firefox or null if none are found.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| overrideFirefox | String |  |  


### defaultProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L427)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Firefox binary and the default profile.   

This method has no parameters.  


### defaultProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L439)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile.   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the args to pass to the Firefox binary  |  


### privateProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L453)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile. Pass the --private-window flag to open a window.   
+ Access: public  
+ return: a ProcessBuilder for the top Firefox binary and the default profile.   

This method has no parameters.  


### privateProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L466)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile. Pass the --private-window flag to open a window.   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the arguments to pass to the Firefox binary  |  


### appProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L489)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile. Pass the --private-window flag to open a window.   
+ Access: public  
+ return: a ProcessBuilder for the top Firefox binary and the default profile.   

This method has no parameters.  


### appProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L502)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile. Pass the --private-window flag to open a window.   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the arguments to pass to the Firefox binary  |  


### headlessProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L523)

+ Description: Build a ProcessBuilder for the top Firefox binary and the default profile. Pass the --headless flag to open without a window.   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the arguments to pass to the Firefox binary  |  


### processBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L551)

+ Description:   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  
| app | boolean |  |  


### usabilityMode [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L682)

+ Description:   
+ Access: private  
+ return: String  

This method has no parameters.  


### launchAndDetatch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L688)

+ Description:   
+ Access: public  
+ return: Process  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launchAndDetatch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L694)

+ Description:   
+ Access: public  
+ return: Process  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | int |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L786)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Firefox with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L792)

+ Description:   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | int |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L818)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Firefox with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L828)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Firefox with the profile directory. Uses a semi-permanent profile.   
+ Access: public  
+ return: void  

This method has no parameters.  


### ValidURL [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L830)

+ Description:   
+ Access: private  
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| inUrl | String |  |  


### main [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L841)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


