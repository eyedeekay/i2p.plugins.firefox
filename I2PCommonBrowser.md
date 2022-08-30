# Class I2PCommonBrowser [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java)  

 >   

Access: public  
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
<li>java.util.zip.ZipEntry</li>
<li>java.util.zip.ZipInputStream</li>
  </ul>  
</details>  

## No member variables in this class

## Methods

### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L23)

+ Description: get the runtime directory creating it if create=true   
+ Access: protected  
+ Modifiers: static 
+ return: the runtime directory or null if it could not be created   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| create | boolean | if true create the runtime directory if it does not exist  |  
| override | String |  |  


### runtimeDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L40)

+ Description: get the correct runtime directory   
+ Access: protected  
+ Modifiers: static 
+ return: the runtime directory or null if it could not be created or found   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| override | String |  |  


### profileDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L87)

+ Description: get the profile directory creating it if necessary   
+ Access: protected  
+ Modifiers: static 
+ return: the profile directory or null if it could not be created   

| Name | Type | Description |  
| ----- | ----- | ----- |  
| envVar | String |  |  
| browser | String |  |  


### profileDir [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L99)

+ Description:   
+ Access: protected  
+ Modifiers: static 
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| file | String |  |  
| browser | String |  |  


### unpackProfile [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L105)

+ Description:   
+ Access: protected  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| profileDirectory | String |  |  
| browser | String |  |  
| base | String |  |  


### copyDirectory [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L152)

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


### copyDirectoryCompatibityMode [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L168)

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


### copyFile [[src]](src/java/net/i2p/i2pfirefox/I2PCommonBrowser.java#L177)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| sourceFile | File |  |  
| destinationFile | File |  |  


