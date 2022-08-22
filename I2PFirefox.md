# Class I2PFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java)  

 > */  

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

### FIND_FIREFOX_SEARCH_PATHS_UNIX [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L44)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS_OSX [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L57)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS_WINDOWS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L70)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_ALL_FIREFOX_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L106)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_FIREFOX_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L126)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### NEARBY_FIREFOX_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L140)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIREFOX_FINDER [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L184)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### getOperatingSystem [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L196)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

This method has no parameters.  


### onlyValidFirefoxes [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L218)

+ Description:   
+ Access: public  
+ return: String[]  

This method has no parameters.  


### topFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L238)

+ Description:   
+ Access: public  
+ return: String  

This method has no parameters.  


### topFirefox [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L267)

+ Description:   
+ Access: public  
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| overrideFirefox | String |  |  


### defaultProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L285)

+ Description:   
+ Access: public  
+ return: ProcessBuilder  

This method has no parameters.  


### privateProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L299)

+ Description:   
+ Access: public  
+ return: ProcessBuilder  

This method has no parameters.  


### processBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L313)

+ Description:   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L339)

+ Description:   
+ Access: public  
+ return: boolean  

This method has no parameters.  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L351)

+ Description:   
+ Access: public  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int |  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L363)

+ Description:   
+ Access: public  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int |  |  
| port | int |  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L376)

+ Description:   
+ Access: public  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int |  |  
| port | int |  |  
| host | String |  |  


### checkifPortIsOccupied [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L389)

+ Description:   
+ Access: private  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| port | int |  |  
| host | String |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L408)

+ Description:   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L454)

+ Description:   
+ Access: public  
+ return: void  

This method has no parameters.  


### main [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L458)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### sleep [[src]](src/java/net/i2p/i2pfirefox/I2PFirefox.java#L472)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| millis | int |  |  


