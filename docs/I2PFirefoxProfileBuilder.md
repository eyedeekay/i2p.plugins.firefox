# Class I2PFirefoxProfileBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java)  

 > */  

Access: public  
Description:  
 > I2PFirefoxProfileBuilder.java Copyright C 2022 idk <hankhill19580@gmail.com> This program is free software: you can redistribute it and/or modify it under the terms of the MIT License. See LICENSE.md for details. This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. I2PFirefoxProfileBuilder is a that builds a profile directory which contains the I2P browser profile for the Firefox browser family. It manages the base profile directory and copies it's contents to the active profile directory which is actually used by Firefox.  

Author: idk   
Parent class: I2PFirefoxProfileChecker  
package: net.i2p.i2pfirefox  

## Dependencies

<details>  
  <summary>  
    Show dependencies  
  </summary>  
  <ul>  
<li>java.io.File</li>
<li>java.io.IOException</li>
<li>java.nio.file.Files</li>
<li>java.nio.file.StandardCopyOption</li>
  </ul>  
</details>  

## Member Variables

#### boolean strict [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L)

 >   

+ Access: private  

## Methods

### userChromeCSS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L28)

+ Description:   
+ Access: private  
+ return: String  

This method has no parameters.  


### baseProfileDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L125)

+ Description: get the base profile directory creating it if necessary   
+ Access: public  
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| base | String |  |  


### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L149)

+ Description: get the runtime directory creating it if create=true   
+ Access: public  
+ return: the runtime directory or null if it could not be created   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| create | boolean | if true create the runtime directory if it does not exist  |  


### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L160)

+ Description: get the correct runtime directory   
+ Access: public  
+ return: the runtime directory or null if it could not be created or found   

This method has no parameters.  


### copyBaseProfiletoProfile [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L180)

+ Description: Copy the inert base profile directory to the runtime profile directory   
+ Access: public  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| base | String |  |  
| app | boolean |  |  


### writeAppChrome [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L213)

+ Description:   
+ Access: protected  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| profile | String |  |  


### deleteAppChrome [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L226)

+ Description:   
+ Access: protected  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| profile | String |  |  


### copyStrictOptions [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L241)

+ Description: Copy the strict options from the base profile to the profile   
+ Access: public  
+ return: true if successful false otherwise   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| base | String |  |  
| app | boolean |  |  


### setupUserChrome [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileBuilder.java#L276)

+ Description:   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| profileDir | File |  |  
| app | boolean |  |  


