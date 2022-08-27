# Class I2PChromiumProfileChecker [[src]](src/java/net/i2p/i2pfirefox/I2PChromiumProfileChecker.java)  

 > */  

Access: public  
Description:  
 > I2PChromiumProfileChecker.java Copyright C 2022 idk <hankhill19580@gmail.com> This program is free software: you can redistribute it and/or modify it under the terms of the MIT License. See LICENSE.md for details. This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. I2PChromiumProfileChecker is a that checks if the Chromium profile directory exists and is valid.  

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

### main [[src]](src/java/net/i2p/i2pfirefox/I2PChromiumProfileChecker.java#L28)

+ Description: Output feedback if the profile directory is valid or invalid @description Output feedback if the profile directory is valid or invalid   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### validateProfileDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PChromiumProfileChecker.java#L49)

+ Description: Return true if the profile directory is valid.   
+ Access: public  
+ Modifiers: static 
+ return: true if the profile directory is valid false otherwise   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| profileDirectory | String | the profile directory to check  |  


### validateFile [[src]](src/java/net/i2p/i2pfirefox/I2PChromiumProfileChecker.java#L80)

+ Description: Return true if the extension directory is valid.   
+ Access: public  
+ Modifiers: static 
+ return: true if the extension directory is valid false otherwise   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| file | String |  |  


### validateExtensionDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PChromiumProfileChecker.java#L107)

+ Description: Return true if the extension directory is valid.   
+ Access: public  
+ Modifiers: static 
+ return: true if the extension directory is valid false otherwise   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| extensionDirectory | String | the extension directory to check  |  


