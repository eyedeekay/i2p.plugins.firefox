package net.i2p.i2pfirefox;


public class I2PBrowser {
    private final I2PFirefox i2pFirefox = new I2PFirefox();
    private final I2PChromium i2pChromium = new I2PChromium();
    public static boolean firefox = false;
    public static boolean chromium = false;
 
    private void launchFirefox(boolean privateWindow) {
        System.out.println("I2PFirefox");
        i2pFirefox.launch(privateWindow);
    }
    private void launchChromium(boolean privateWindow) {
        System.out.println("I2PChromium");
        i2pChromium.launch(privateWindow);
    }
 
    /*
     * Construct an I2PBrowser class which manages an instance of Chromium and
     * an accompanying Chromium profile. This version includes Chromium variants
     * and forks.
     * 
     * @since 0.0.16
     */
    public I2PBrowser() {
    }

    /*
     * Return true if there is a Chromium available
     * 
     * @return true if Chromium is available, false otherwise
     * @since 0.0.16
     */
    public boolean hasChromium() {
        String chrome = i2pChromium.topChromium();
        if (chrome == null) {
            return false;
        }
        if (chrome.isEmpty()) {
            return false;
        }
        return true;
    }

    /*
     * Return true if there is a Firefox variant available
     * 
     * @return true if Firefox variant is available, false otherwise
     * @since 0.0.16
     */
    public boolean hasFirefox() {
        String fox = i2pFirefox.topFirefox();
        if (fox == null) {
            return false;
        }
        if (fox.isEmpty()) {
            return false;
        }
        return true;
    }

    /*
     * Populates a profile directory with a proxy configuration.
     * Waits for an HTTP proxy on the port 4444 to be ready.
     * Launches Chromium with the profile directory.
     * 
     * @param bool if true, the profile will be ephemeral(i.e. a --private-window profile).
     * @since 0.0.16
     */
    public void launch(boolean privateWindow){
        if ((chromium && firefox) || (!chromium && !firefox)) {
            if (this.hasFirefox()) {
                this.launchFirefox(privateWindow);
            } else if (this.hasChromium()) {
                this.launchChromium(privateWindow);
            }
        }
        if (firefox) {
            this.launchFirefox(privateWindow);
            return;
        }
        if (chromium) {
            this.launchChromium(privateWindow);
            return;
        }
    }

    /*
     * Populates a profile directory with a proxy configuration.
     * Waits for an HTTP proxy on the port 4444 to be ready.
     * Launches Chromium with the profile directory.
     * 
     * @since 0.0.16
     */
    public void launch(){
        launch(false);
    }    

    public static void main(String[] args) {
        boolean privateBrowsing = false;
        if (args != null && args.length > 0) {
            for (String arg : args) {
                if (arg.equals("-private")) {
                    privateBrowsing = true;
                }
                if (arg.equals("-chromium")) {
                    chromium = true;
                }
                if (arg.equals("-firefox")) {
                    firefox = true;
                }
            }
        }
        System.out.println("I2PBrowser");
        I2PBrowser I2PBrowser = new I2PBrowser();
        I2PBrowser.launch(privateBrowsing);
    }

}