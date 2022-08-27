# Class I2PFirefoxProfileChecker [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileChecker.java)  

 > */  

Access: public  
Description:  
 > I2PFirefoxProfileChecker.java Copyright C 2022 idk <hankhill19580@gmail.com> This program is free software: you can redistribute it and/or modify it under the terms of the MIT License. See LICENSE.md for details. This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. I2PFirefoxProfileChecker is a that checks if the Firefox profile directory exists and is valid.  

Author: idk   
package: net.i2p.i2pfirefox  

## Dependencies

<details>  
  <summary>  
    Show dependencies  
  </summary>  
  <ul>  
<li>java.io.File</li>
  </ul>  
</details>  

## No member variables in this class

## Methods

### main [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileChecker.java#L26)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### validateProfileDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileChecker.java#L47)

+ Description: Return true if the profile directory is valid.   
+ Access: public  
+ Modifiers: static 
+ return: true if the profile directory is valid false otherwise   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| profileDirectory | String | the profile directory to check  |  


### validateFile [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileChecker.java#L86)

+ Description: Return true if the file is valid.   
+ Access: public  
+ Modifiers: static 
+ return: true if the file is valid false otherwise   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| file | String | the file to check  |  


### validateExtensionDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileChecker.java#L113)

+ Description: Return true if the extension directory is valid.   
+ Access: public  
+ Modifiers: static 
+ return: true if the extension directory is valid false otherwise   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| extensionDirectory | String | the extension directory to check  |  


