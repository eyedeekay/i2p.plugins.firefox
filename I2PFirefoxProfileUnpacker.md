# Class I2PFirefoxProfileUnpacker [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileUnpacker.java)  

 > */  

Access: public  
Description:  
 > I2PFirefoxProfileUnpacker.java Copyright C 2022 idk <hankhill19580@gmail.com> This program is free software: you can redistribute it and/or modify it under the terms of the MIT License. See LICENSE.md for details. This program is distributed in the hope that it will be useful but WITHOUT ANY WARRANTY without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. I2PFirefoxProfileUnpacker is a that unpacks the I2P Firefox profile from a zip file embedded in the `jar` file. The zip is unpacked to a base directory where it is left untouched and the base profile is copied to the active profile directory.  

Author: idk   
package: net.i2p.i2pfirefox  

## Dependencies

<details>  
  <summary>  
    Show dependencies  
  </summary>  
  <ul>  
<li>java.io.File</li>
<li>java.io.InputStream</li>
<li>java.nio.file.Files</li>
<li>java.nio.file.StandardCopyOption</li>
<li>java.util.zip.ZipEntry</li>
<li>java.util.zip.ZipInputStream</li>
  </ul>  
</details>  

## No member variables in this class

## Methods

### main [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileUnpacker.java#L30)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### unpackProfile [[src]](src/java/net/i2p/i2pfirefox/I2PFirefoxProfileUnpacker.java#L45)

+ Description: unpack the profile directory   
+ Access: public  
+ return: true if the profile directory was successfully unpacked   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| profileDirectory | String |  |  


