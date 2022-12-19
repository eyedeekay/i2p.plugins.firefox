# Class I2PBrowser [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java)  

 > */  

Access: public  
Description:  
 > I2PBrowser.java Copyright C 2022 idk <hankhill19580@gmail.com> This program is free software: you can redistribute it and/or modify it under the terms of the MIT License. See LICENSE.md for details. This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. @description I2PBrowser is a that is used to open a browser window to the I2P network. It automatically detects the operating system and available browsers and selects the best one to use with Tor Browser at the top for Firefox and Brave at the top for Chrome.  

Author: idk   
Parent class: I2PCommonBrowser  
package: net.i2p.i2pfirefox  

## Dependencies

<details>  
  <summary>  
    Show dependencies  
  </summary>  
  <ul>  
<li>java.awt.Image</li>
<li>java.awt.Menu</li>
<li>java.awt.MenuItem</li>
<li>java.awt.PopupMenu</li>
<li>java.awt.SystemTray</li>
<li>java.awt.Toolkit</li>
<li>java.awt.TrayIcon</li>
<li>java.awt.event.ActionEvent</li>
<li>java.awt.event.ActionListener</li>
<li>java.io.File</li>
<li>java.io.FileOutputStream</li>
<li>java.io.FileWriter</li>
<li>java.io.IOException</li>
<li>java.io.InputStream</li>
<li>java.io.OutputStream</li>
<li>java.util.ArrayList</li>
<li>java.util.Arrays</li>
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

####  SystemTray tray  [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L)

 >   

+ Access: private  
+ Modifiers: final 

####  TrayIcon icon  [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L)

 >   

+ Access: private  
+ Modifiers: final 

####  PopupMenu menu  [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L)

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

####  int privateBrowsing  [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L)

 >   

+ Access: public  

####  boolean outputConfig  [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L)

 >   

+ Access: private  
+ Modifiers: static 

####  boolean useSystray  [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L)

 >   

+ Access: private  
+ Modifiers: static 

## Methods

### launchFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L56)

+ Description:   
+ Access: private  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | int |  |  
| url | String[] |  |  


### launchChromium [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L63)

+ Description:   
+ Access: private  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | int |  |  
| url | String[] |  |  


### launchGeneric [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L70)

+ Description:   
+ Access: private  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindowInt | int |  |  
| url | String[] |  |  


### setBrowser [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L98)

+ Description:   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| browserPath | String |  |  


### hasChromium [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L108)

+ Description: Return true if there is a Chromium available   
+ Access: public  
+ return: true if Chromium is available false otherwise   

This method has no parameters.  


### hasFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L125)

+ Description: Return true if there is a Firefox variant available   
+ Access: public  
+ return: true if Firefox variant is available false otherwise   

This method has no parameters.  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L145)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches either Firefox or Chromium with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | int |  |  
| url | String[] |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L190)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches either Firefox or Chromium with the profile directory.   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L204)

+ Description: Populates a profile directory with a proxy configuration. Waits for an HTTP proxy on the port 4444 to be ready. Launches either Firefox or Chromium with the profile directory.   
+ Access: public  
+ return: void  

This method has no parameters.  


### ValidURL [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L206)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| inUrl | String |  |  


### main [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L216)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### parseArgs [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L220)

+ Description:   
+ Access: public  
+ return: ArrayList<String>  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### startup [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L268)

+ Description:   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### systrayIsRunningExternally [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L287)

+ Description:   
+ Access: protected  
+ Modifiers: static 
+ return: boolean  

This method has no parameters.  


### shutdownSystray [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L304)

+ Description:   
+ Access: protected  
+ return: void  

This method has no parameters.  


### initTray [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L311)

+ Description:   
+ Access: private  
+ return: SystemTray  

This method has no parameters.  


### initMenu [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L322)

+ Description:   
+ Access: private  
+ return: PopupMenu  

This method has no parameters.  


### initIcon [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L327)

+ Description:   
+ Access: private  
+ return: TrayIcon  

This method has no parameters.  


### systray [[src]](src/java/net/i2p/i2pfirefox/I2PBrowser.java#L346)

+ Description:   
+ Access: public  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


