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

## Methods

### println [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L52)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| line | String |  |  


### logFile [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L54)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: File  

This method has no parameters.  


### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L68)

+ Description: get the runtime directory creating it if create=true   
+ Access: protected  
+ Modifiers: static 
+ return: the runtime directory or null if it could not be created   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| create | boolean | if true create the runtime directory if it does not exist  |  
| override | String |  |  


### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L85)

+ Description: get the correct runtime directory   
+ Access: protected  
+ Modifiers: static 
+ return: the runtime directory or null if it could not be created or found   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| override | String |  |  


### profileDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L132)

+ Description: get the profile directory creating it if necessary   
+ Access: protected  
+ Modifiers: static 
+ return: the profile directory or null if it could not be created   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| envVar | String |  |  
| browser | String |  |  


### profileDir [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L144)

+ Description:   
+ Access: protected  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| file | String |  |  
| browser | String |  |  


### unpackProfile [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L150)

+ Description:   
+ Access: protected  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| profileDirectory | String |  |  
| browser | String |  |  
| base | String |  |  


### copyDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L196)

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


### copyDirectoryCompatibityMode [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L212)

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


### copyFile [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L221)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| sourceFile | File |  |  
| destinationFile | File |  |  


### validateProfileFirstRun [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L232)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| profileDirectory | String |  |  


