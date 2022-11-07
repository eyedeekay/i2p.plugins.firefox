# Class I2PFirefoxProfileChecker [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileChecker.java)  

 > */  

Access: public  
Description:  
 > I2PFirefoxProfileChecker.java Copyright C 2022 idk <hankhill19580@gmail.com> This program is free software: you can redistribute it and/or modify it under the terms of the MIT License. See LICENSE.md for details. This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. I2PFirefoxProfileChecker is a that checks if the Firefox profile directory exists and is valid.  

Author: idk   
Parent class: I2PCommonBrowser  
package: net.i2p.i2pfirefox  

## Dependencies

<details>  
  <summary>  
    Show dependencies  
  </summary>  
  <ul>  
<li>java.io.BufferedReader</li>
<li>java.io.File</li>
<li>java.io.FileNotFoundException</li>
<li>java.io.FileReader</li>
<li>java.io.FileWriter</li>
<li>java.io.IOException</li>
<li>java.util.Scanner</li>
  </ul>  
</details>  

## No member variables in this class

## Methods

### main [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileChecker.java#L32)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### validateProfileDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileChecker.java#L54)

+ Description: Return true if the profile directory is valid.   
+ Access: public  
+ Modifiers: static 
+ return: true if the profile directory is valid false otherwise   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| profileDirectory | String | the profile directory to check  |  


### deRestrictHTTPSAndSetupHomepage [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileChecker.java#L87)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| profile | String |  |  


### undoHttpsOnlyMode [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileChecker.java#L110)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| fileToBeModified | File |  |  


### undoHomepage [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileChecker.java#L116)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| fileToBeModified | File |  |  


### undoValue [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileChecker.java#L139)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| oldString | String |  |  
| newString | String |  |  
| fileToBeModified | File |  |  


### validateFile [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileChecker.java#L173)

+ Description: Return true if the file is valid.   
+ Access: public  
+ Modifiers: static 
+ return: true if the file is valid false otherwise   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| file | String | the file to check  |  


### validateExtensionDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileChecker.java#L200)

+ Description: Return true if the extension directory is valid.   
+ Access: public  
+ Modifiers: static 
+ return: true if the extension directory is valid false otherwise   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| extensionDirectory | String | the extension directory to check  |  


