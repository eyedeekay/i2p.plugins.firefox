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
<li>java.io.FileOutputStream</li>
<li>java.io.FileWriter</li>
<li>java.io.IOException</li>
<li>java.io.OutputStream</li>
<li>java.io.PrintWriter</li>
<li>java.util.ArrayList</li>
<li>java.util.Arrays</li>
<li>java.util.List</li>
<li>java.util.stream.Collectors</li>
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

### storeChromiumDefaults [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L60)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

This method has no parameters.  


### chromiumPathsUnix [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L89)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### chromiumBinsUnix [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L98)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_CHROMIUM_SEARCH_PATHS_UNIX [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L114)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### chromiumPathsOSX [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L128)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_CHROMIUM_SEARCH_PATHS_OSX [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L137)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### chromiumPathsWindows [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L151)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### chromiumBinsWindows [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L176)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_CHROMIUM_SEARCH_PATHS_WINDOWS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L185)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_ALL_CHROMIUM_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L199)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_CHROMIUM_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L219)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### NEARBY_CHROMIUM_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L233)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### CHROMIUM_FINDER [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L287)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### onlyValidChromiums [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L308)

+ Description: Check our list of chrome paths for a valid chrome binary. Just an existence check for now but should check versions in the future.   
+ Access: public  
+ return: a list of usable Chromiums or an empty list if none are found.   

This method has no parameters.  


### topChromium [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L328)

+ Description: Return the best available Chromium from the list of Chromiums we have.   
+ Access: public  
+ return: the path to the best available Chromium or null if none are found.   

This method has no parameters.  


### topChromium [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L357)

+ Description: Return the best available Chromium from the list of Chromiums we have. if override is passed it will be validated and if it validates it will be used.   
+ Access: public  
+ return: the path to the best available Chromium or null if none are found.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| overrideChromium | String |  |  


### defaultProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L375)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile.   

This method has no parameters.  


### defaultProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L388)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile. @args the arguments to pass to the Chromium binary   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### privateProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L400)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile. Always passes the --incognito flag.   

This method has no parameters.  


### privateProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L413)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile. Always passes the --incognito flag.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the arguments to pass to the Chromium binary.  |  


### appProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L435)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile. Always passes the --app flag.   

This method has no parameters.  


### appProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L448)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and the default profile. Always passes the --app flag.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the arguments to pass to the Chromium binary.  |  


### processBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L495)

+ Description: Build a ProcessBuilder for the top Chromium binary and the default profile with a specific set of extended arguments.   
+ Access: public  
+ return: a ProcessBuilder for the top Chromium binary and default profile with a specific set of extended arguments.   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] | the extended arguments to pass to the Chromium binary.  |  


### launchAndDetatch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L616)

+ Description:   
+ Access: public  
+ return: Process  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launchAndDetatch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L623)

+ Description:   
+ Access: public  
+ return: Process  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | int |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L681)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Chromium with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L687)

+ Description:   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | int |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L712)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Chromium with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L721)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches Chromium with the profile directory.   
+ Access: public  
+ return: void  

This method has no parameters.  


### ValidURL [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L723)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| inUrl | String |  |  


### main [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L733)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### sleep [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L772)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| millis | int |  |  


