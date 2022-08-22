# Class I2PChromium [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java)  

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

####  String[] CHROMIUM_SEARCH_PATHS  [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L)

 >   

+ Access: private  
+ Modifiers: final 

####  int DEFAULT_TIMEOUT  [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L)

 >   

+ Access: private  
+ Modifiers: final 

## Methods

### FIND_CHROMIUM_SEARCH_PATHS_UNIX [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L44)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_CHROMIUM_SEARCH_PATHS_OSX [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L57)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_CHROMIUM_SEARCH_PATHS_WINDOWS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L70)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_ALL_CHROMIUM_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L99)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### FIND_CHROMIUM_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L119)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### NEARBY_CHROMIUM_SEARCH_PATHS [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L133)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### CHROMIUM_FINDER [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L177)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String[]  

This method has no parameters.  


### getOperatingSystem [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L189)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: String  

This method has no parameters.  


### onlyValidChromiums [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L211)

+ Description:   
+ Access: public  
+ return: String[]  

This method has no parameters.  


### topChromium [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L231)

+ Description:   
+ Access: public  
+ return: String  

This method has no parameters.  


### topChromium [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L260)

+ Description:   
+ Access: public  
+ return: String  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| overrideChromium | String |  |  


### defaultProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L278)

+ Description:   
+ Access: public  
+ return: ProcessBuilder  

This method has no parameters.  


### privateProcessBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L291)

+ Description:   
+ Access: public  
+ return: ProcessBuilder  

This method has no parameters.  


### processBuilder [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L328)

+ Description:   
+ Access: public  
+ return: ProcessBuilder  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L375)

+ Description:   
+ Access: public  
+ return: boolean  

This method has no parameters.  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L387)

+ Description:   
+ Access: public  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int |  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L399)

+ Description:   
+ Access: public  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int |  |  
| port | int |  |  


### waitForProxy [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L412)

+ Description:   
+ Access: public  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| timeout | int |  |  
| port | int |  |  
| host | String |  |  


### checkifPortIsOccupied [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L425)

+ Description:   
+ Access: private  
+ return: boolean  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| port | int |  |  
| host | String |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L444)

+ Description:   
+ Access: public  
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| privateWindow | boolean |  |  


### launch [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L488)

+ Description:   
+ Access: public  
+ return: void  

This method has no parameters.  


### main [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L492)

+ Description:   
+ Access: public  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| args | String[] |  |  


### sleep [[src]](src/java/net/i2p/i2pfirefox/I2PChromium.java#L505)

+ Description:   
+ Access: private  
+ Modifiers: static 
+ return: void  

| Name | Type | Description |  
| ----- | ----- | ----- |  
| millis | int |  |  


