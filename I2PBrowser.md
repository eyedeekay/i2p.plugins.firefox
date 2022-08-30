# Class I2PBrowser [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java)  

 > */  

Access: public  
Description:  
 > I2PBrowser.java Copyright C 2022 idk <hankhill19580@gmail.com> This program is free software: you can redistribute it and/or modify it under the terms of the MIT License. See LICENSE.md for details. This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. @description I2PBrowser is a that is used to open a browser window to the I2P network. It automatically detects the operating system and available browsers and selects the best one to use with Tor Browser at the top for Firefox and Brave at the top for Chrome.  

Author: idk   
package: net.i2p.i2pfirefox  

## Dependencies

<details>  
  <summary>  
    Show dependencies  
  </summary>  
  <ul>  
<li>java.util.ArrayList</li>
  </ul>  
</details>  

## Member Variables

####  I2PFirefox i2pFirefox  [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L)

 >   

+ Access: private  
+ Modifiers: final 

####  I2PChromium i2pChromium  [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L)

 >   

+ Access: private  
+ Modifiers: final 

####  I2PGenericUnsafeBrowser i2pGeneral  [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L)

 >   

+ Access: private  
+ Modifiers: final 

####  boolean firefox  [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L)

 >   

+ Access: public  

####  boolean chromium  [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L)

 >   

+ Access: public  

####  boolean generic  [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L)

 >   

+ Access: public  

####  boolean chromiumFirst  [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L)

 >   

+ Access: public  

####  boolean usability  [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L)

 >   

+ Access: public  

## Methods

### launchFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L34)

+ Description:   
+ Access: private  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launchChromium [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L39)

+ Description:   
+ Access: private  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launchGeneric [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L44)

+ Description:   
+ Access: private  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### setBrowser [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L67)

+ Description:   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| browserPath | String |  |  


### hasChromium [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L77)

+ Description: Return true if there is a Chromium available   
+ Access: public  
+ return: true if Chromium is available false otherwise   

This method has no parameters.  


### hasFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L94)

+ Description: Return true if there is a Firefox variant available   
+ Access: public  
+ return: true if Firefox variant is available false otherwise   

This method has no parameters.  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L114)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches either Firefox or Chromium with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L156)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches either Firefox or Chromium with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L165)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches either Firefox or Chromium with the profile directory.   
+ Access: public  
+ return: void  

This method has no parameters.  


### ValidURL [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L167)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| inUrl | String |  |  


### main [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L177)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


