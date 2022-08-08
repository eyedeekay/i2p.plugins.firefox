# Class I2PFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java)  

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
<li>java.io.IOException</li>
<li>java.net.Socket</li>
<li>java.util.ArrayList</li>
  </ul>  
</details>  

## Member Variables

####  String[] FIREFOX_SEARCH_PATHS  [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L)

 >   

+ Access: private  
+ Modifiers: final 

####  int DEFAULT_TIMEOUT  [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L)

 >   

+ Access: private  
+ Modifiers: final 

## Methods

### FIND_FIREFOX_SEARCH_PATHS_UNIX [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L30)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS_OSX [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L43)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS_WINDOWS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L56)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_ALL_FIREFOX_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L84)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L104)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### NEARBY_FIREFOX_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L118)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIREFOX_FINDER [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L162)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### getOperatingSystem [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L174)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

This method has no parameters.  


### onlyValidFirefoxes [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L196)

+ Description:   
+ Access: public  
+ return: String[]  

This method has no parameters.  


### topFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L214)

+ Description:   
+ Access: public  
+ return: String  

This method has no parameters.  


### topFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L243)

+ Description:   
+ Access: public  
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| overrideFirefox | String |  |  


### defaultProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L261)

+ Description:   
+ Access: public  
+ return: ProcessBuilder  

This method has no parameters.  


### processBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L275)

+ Description:   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L298)

+ Description:   
+ Access: public  
+ return: boolean  

This method has no parameters.  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L310)

+ Description:   
+ Access: public  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int |  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L322)

+ Description:   
+ Access: public  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int |  |  
| port | int |  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L335)

+ Description:   
+ Access: public  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int |  |  
| port | int |  |  
| host | String |  |  


### checkifPortIsOccupied [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L348)

+ Description:   
+ Access: private  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| port | int |  |  
| host | String |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L366)

+ Description:   
+ Access: public  
+ return: void  

This method has no parameters.  


### main [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L399)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


