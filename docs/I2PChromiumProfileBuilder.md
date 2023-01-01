# Class I2PChromiumProfileBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromiumProfileBuilder.java)  

 > */  

Access: public  
Description:  
 > I2PChromiumProfileBuilder.java Copyright C 2022 idk <hankhill19580@gmail.com> This program is free software: you can redistribute it and/or modify it under the terms of the MIT License. See LICENSE.md for details. This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. I2PChromiumProfileBuilder is a that builds a profile directory which contains the I2P browser profile for the Chromium browser family. It manages the base profile directory and copies it's contents to the active profile directory which is actually used by Chromium.  

Author: idk   
Parent class: I2PChromiumProfileChecker  
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

## Member Variables

#### boolean usability [[src]](src/java/net/i2p/i2pfirefox/I2PChromiumProfileBuilder.java#L)

 >   

+ Access: public  

## Methods

### baseProfileDir [[src]](src/java/net/i2p/i2pfirefox/I2PChromiumProfileBuilder.java#L26)

+ Description:   
+ Access: private  
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| file | String |  |  
| mode | String |  |  


### baseProfileDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PChromiumProfileBuilder.java#L50)

+ Description:   
+ Access: public  
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| mode | String |  |  


### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PChromiumProfileBuilder.java#L74)

+ Description: get the runtime directory creating it if create=true   
+ Access: public  
+ return: the runtime directory or null if it could not be created   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| create | boolean | if true create the runtime directory if it does not exist  |  


### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PChromiumProfileBuilder.java#L85)

+ Description: get the correct runtime directory   
+ Access: public  
+ return: the runtime directory or null if it could not be created or found   

This method has no parameters.  


### usabilityMode [[src]](src/java/net/i2p/i2pfirefox/I2PChromiumProfileBuilder.java#L100)

+ Description:   
+ Access: public  
+ return: String  

This method has no parameters.  


### copyBaseProfiletoProfile [[src]](src/java/net/i2p/i2pfirefox/I2PChromiumProfileBuilder.java#L111)

+ Description: Copy the inert base profile directory to the runtime profile directory   
+ Access: public  
+ return: boolean  

This method has no parameters.  


