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
<li>java.io.FileOutputStream</li>
<li>java.io.IOException</li>
<li>java.io.OutputStream</li>
<li>java.net.Socket</li>
<li>java.util.ArrayList</li>
<li>java.util.Arrays</li>
<li>java.util.List</li>
<li>java.util.Scanner</li>
<li>java.util.stream.Collectors</li>
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

####  Process p  [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L)

 >   

+ Access: private  

## Methods

### getDefaultWindowsBrowser [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L101)

+ Description: Obtains the default browser for the Windows platform which by now should be Edgium in the worst-case scenario but in case it isn't we can use this function to figure it out. It can find: 1. The current user's HTTPS default browser if they configured it to be non-default 2. The current user's HTTP default browser if they configured it to be non-default 3. Edgium if it's available 4. iexplore if it's not and it will return the first one we find in exactly that order. Adapted from:   
+ Access: public  
+ return: path to command[0] and target URL[1] to the default browser ready for execution or null if not found   

This method has no parameters.  


### registryQuery [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L142)

+ Description: obtains a value matching a key contained in the windows registry at a path represented by hkeyquery   
+ Access: private  
+ return: either a registry "Default" value or null if one does not exist/is empty   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| hkeyquery | String | registry entry to ask for.  |  
| key | String | key to retrieve value from  |  


### followUserConfiguredBrowserToCommand [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L180)

+ Description: If following a query back to the Default value doesn't work then what we have is a "ProgID" which will be registered in \HKEY_CLASSES_ROOT\%ProgId% and will have an entry \shell\open\command where \shell\open\command yields the value that contains the command it needs. This function takes a registry query in the same format as getDefaultOutOfRegistry but instead of looking for the default entry   
+ Access: private  
+ return: the command required to run the application referenced in hkeyquery or null   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| hkeyquery | String |  |  


### followProgIdToCommand [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L196)

+ Description: Cross-references a progId obtained by followUserConfiguredBrowserToCommand against HKEY_CLASSES_ROOT\%ProgId%\shell\open\command which holds the value of the command which we need to run to launch the default browser.   
+ Access: private  
+ return: the command required to run the application referenced in hkeyquery or null   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| progid | String |  |  


### getDefaultOutOfRegistry [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L215)

+ Description: obtains a default browsing command out of the Windows registry.   
+ Access: private  
+ return: either a registry "Default" value or null if one does not exist/is empty   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| hkeyquery | String | registry entry to ask for.  |  


### scanAPath [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L230)

+ Description:   
+ Access: private  
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| dir | String |  |  


### getAnyUnixBrowser [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L245)

+ Description: Find any browser in our list within a UNIX path   
+ Access: public  
+ return: String  

This method has no parameters.  


### findUnsafeBrowserAnywhere [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L264)

+ Description: Find any usable browser and output the whole path   
+ Access: public  
+ return: String  

This method has no parameters.  


### deleteRuntimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L325)

+ Description: delete the runtime directory   
+ Access: public  
+ return: boolean  

This method has no parameters.  


### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L341)

+ Description: get the runtime directory creating it if create=true   
+ Access: public  
+ return: the runtime directory or null if it could not be created   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| create | boolean | if true create the runtime directory if it does not exist  |  


### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L353)

+ Description: get the correct runtime directory   
+ Access: public  
+ return: the runtime directory or null if it could not be created or found   

This method has no parameters.  


### launchAndDetatch [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L368)

+ Description:   
+ Access: public  
+ return: Process  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L390)

+ Description:   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### ValidURL [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L408)

+ Description:   
+ Access: private  
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| inUrl | String |  |  


### main [[src]](src/java/net/i2p/i2pfirefox/I2PGenericUnsafeBrowser.java#L419)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


