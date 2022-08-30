# Class I2PFirefoxProfileBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java)  

 > */  

Access: public  
Description:  
 > I2PFirefoxProfileBuilder.java Copyright C 2022 idk <hankhill19580@gmail.com> This program is free software: you can redistribute it and/or modify it under the terms of the MIT License. See LICENSE.md for details. This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. I2PFirefoxProfileBuilder is a that builds a profile directory which contains the I2P browser profile for the Firefox browser family. It manages the base profile directory and copies it's contents to the active profile directory which is actually used by Firefox.  

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
<li>java.nio.file.Files</li>
<li>java.nio.file.StandardCopyOption</li>
  </ul>  
</details>  

## Member Variables

#### boolean strict [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L)

 >   

+ Access: private  
+ Modifiers: static 

## Methods

### profileDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L33)

+ Description: get the profile directory creating it if necessary   
+ Access: public  
+ Modifiers: static 
+ return: String  

This method has no parameters.  


### baseProfileDir [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L37)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| file | String |  |  


### baseProfileDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L57)

+ Description: get the base profile directory creating it if necessary   
+ Access: public  
+ Modifiers: static 
+ return: String  

This method has no parameters.  


### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L81)

+ Description: get the runtime directory creating it if create=true   
+ Access: public  
+ Modifiers: static 
+ return: the runtime directory or null if it could not be created   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| create | boolean | if true create the runtime directory if it does not exist  |  


### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L92)

+ Description: get the correct runtime directory   
+ Access: public  
+ Modifiers: static 
+ return: the runtime directory or null if it could not be created or found   

This method has no parameters.  


### copyBaseProfiletoProfile [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L112)

+ Description: Copy the inert base profile directory to the runtime profile directory   
+ Access: public  
+ Modifiers: static 
+ return: boolean  

This method has no parameters.  


### copyStrictOptions [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L149)

+ Description: Copy the strict options from the base profile to the profile   
+ Access: public  
+ Modifiers: static 
+ return: true if successful false otherwise   

This method has no parameters.  


