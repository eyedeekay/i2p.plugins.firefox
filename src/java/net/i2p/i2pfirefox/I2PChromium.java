package net.i2p.i2pfirefox;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


public class I2PChromium {
    private final String[] CHROMIUM_SEARCH_PATHS = CHROMIUM_FINDER();
    private final int DEFAULT_TIMEOUT = 200;

    /*
     * Construct an I2PChromium class which manages an instance of Chromium and
     * an accompanying Chromium profile. This version includes Chromium variants
     * and forks.
     * 
     * @since 0.0.1
     */
    public I2PChromium() {
        for (String path : CHROMIUM_SEARCH_PATHS) {
            File f = new File(path);
            if (f.exists()) {
                System.out.println("Found Chromium at " + path);
                return;
            }
        }

    }

    private static String[] FIND_CHROMIUM_SEARCH_PATHS_UNIX(){
        String[] path = new String[]{"/usr/bin", "/usr/local/bin", "/opt/chrome/bin","/snap/bin"};
        String[] exes = new String[]{"ungoogled-chromium", "chromium", "brave", "edge", "ungoogled-chromium", "chrome"};
        String[] exePath = new String[path.length * exes.length];
        int i = 0;
        for (String s : path) {
            for (String exe : exes) {
                exePath[i] = s + "/" + exe;
                i++;
            }
        }
        return exePath;
    }
    private static String[] FIND_CHROMIUM_SEARCH_PATHS_OSX() {
        String[] path = new String[]{"/Applications/Chromium.app/Contents/MacOS/", "/Applications/Waterfox.app/Contents/MacOS/", "/Applications/Librewolf.app/Contents/MacOS/"};
        String[] exes = new String[]{"ungoogled-chromium", "chromium", "brave", "edge", "ungoogled-chromium", "chrome"};
        String[] exePath = new String[path.length * exes.length];
        int i = 0;
        for (String s : path) {
            for (String exe : exes) {
                exePath[i] = s + "/" + exe;
                i++;
            }
        }
        return exePath;
    }
    private static String[] FIND_CHROMIUM_SEARCH_PATHS_WINDOWS() {
        String userHome = System.getProperty("user.home");
        String programFiles = System.getenv("ProgramFiles");
        String localAppData = System.getenv("LOCALAPPDATA");
        String programFiles86 = System.getenv("ProgramFiles(x86)");
        String[] path = new String[]{
            new File(localAppData, "/Google/Chrome/Application/").toString(),
            new File(programFiles, "/Google/Chrome/Application/").toString(),
            new File(programFiles86, "/Google/Chrome/Application/").toString(),
            new File(localAppData, "/Chromium/Application/").toString(),
            new File(programFiles, "/Chromium/Application/").toString(),
            new File(programFiles86, "/Chromium/Application/").toString(),
            new File(localAppData, "/BraveSoftware/Brave Browser/Application/").toString(),
            new File(programFiles, "/BraveSoftware/Brave Browser/Application/").toString(),
            new File(programFiles86, "/BraveSoftware/Brave Browser/Application/").toString(),
            new File(programFiles86, "/Microsoft/Edge/Application/").toString(),
            new File(programFiles, "/Microsoft/Edge/Application/").toString(),
        };
        String[] exes = new String[]{"ungoogled-chromium.exe", "chromium.exe", "brave.exe", "edge.exe", "ungoogled-chromium.exe", "chrome.exe"};
        String[] exePath = new String[path.length * exes.length];
        int i = 0;
        for (String s : path) {
            for (String exe : exes) {
                exePath[i] = s + exe;
                i++;
            }
        }
        return exePath;
    }

    private static String[] FIND_ALL_CHROMIUM_SEARCH_PATHS() {
        String[] Unix = FIND_CHROMIUM_SEARCH_PATHS_UNIX();
        String[] Windows = FIND_CHROMIUM_SEARCH_PATHS_WINDOWS();
        String[] Mac = FIND_CHROMIUM_SEARCH_PATHS_OSX();
        String[] exePath = new String[Unix.length + Windows.length + Mac.length];
        int i = 0;
        for (String s : Unix) {
            exePath[i] = s;
            i++;
        }
        for (String s : Windows) {
            exePath[i] = s;
            i++;
        }
        for (String s : Mac) {
            exePath[i] = s;
            i++;
        }
        return exePath;
    }
    private static String[] FIND_CHROMIUM_SEARCH_PATHS() {
        switch (getOperatingSystem()) {
            case "Windows":
                return FIND_CHROMIUM_SEARCH_PATHS_WINDOWS();
            case "Linux":
                return FIND_CHROMIUM_SEARCH_PATHS_UNIX();
            case "Mac":
                return FIND_CHROMIUM_SEARCH_PATHS_OSX();
            case "BSD":
                return FIND_CHROMIUM_SEARCH_PATHS_UNIX();
            default:
                return FIND_ALL_CHROMIUM_SEARCH_PATHS();
        }
    }
    private static String[] NEARBY_CHROMIUM_SEARCH_PATHS() {
        // obtain the PLUGIN environment variable
        String plugin = System.getenv("PLUGIN");
        // search the plugin directory for anything named "ungoogled-chromium", "chromium", "brave", "edge", "ungoogled-chromium", "chrome"
        // up to a depth of 2 directories deep.
        // list the directories in the plugin directory
        if (plugin != null && !plugin.isEmpty()){
            File pluginDir = new File(plugin);
            if (pluginDir.exists()) {
                File[] pluginDirs = pluginDir.listFiles();
                // list the files in the plugin directory
                for (File pluginDir1 : pluginDirs) {
                    File[] pluginFiles = pluginDir1.listFiles();
                    // list the files in the plugin directory
                    if (pluginFiles != null){
                        for (File pluginFile : pluginFiles) {
                            if (pluginFile.getName().equals("ungoogled-chromium") || pluginFile.getName().equals("chromium") || pluginFile.getName().equals("brave") || pluginFile.getName().equals("edge") || pluginFile.getName().equals("ungoogled-chromium") || pluginFile.getName().equals("chrome")) {
                                return new String[]{pluginFile.getAbsolutePath()};
                            }
                        }
                    }  
                }
            }
        }
        // now, do the same thing, but with user.dir instead of plugin
        // list the directories in the user.dir directory
        File userDir = new File(System.getProperty("user.dir"));
        if (userDir.exists()) {
            File[] userDirs = userDir.listFiles();
            // list the files in the user.dir directory
            for (File userDir1 : userDirs) {
                File[] userFiles = userDir1.listFiles();
                // list the files in the user.dir directory
                if (userFiles != null){
                    for (File userFile : userFiles) {
                        if (userFile.getName().equals("ungoogled-chromium") || userFile.getName().equals("chromium") || userFile.getName().equals("brave") || userFile.getName().equals("edge") || userFile.getName().equals("ungoogled-chromium") || userFile.getName().equals("chrome")) {
                            return new String[]{userFile.getAbsolutePath()};
                        }
                    }
                }
            }
        }
        return new String[]{};
    }
    private static String[] CHROMIUM_FINDER() {
        String[] nearby = NEARBY_CHROMIUM_SEARCH_PATHS();
        String[] all = FIND_CHROMIUM_SEARCH_PATHS();

        if (nearby != null && nearby.length > 0) {
            return nearby;
        } else if (all != null && all.length > 0) {
            return all;
        } else {
            return new String[]{};
        }
    }
    private static String getOperatingSystem() {
        String os = System.getProperty("os.name");
        if (os.startsWith("Windows")) {
            return "Windows";
        } else if (os.contains("Linux")) {
            return "Linux";
        } else if (os.contains("BSD")) {
            return "BSD";
        } else if (os.contains("Mac")) {
            return "Mac";
        }
        return "Unknown";
    }

    /*
     * Check our list of chrome paths for a valid chrome binary.
     * Just an existence check for now, but should check versions
     * in the future.
     * 
     * @return a list of usable Chromiums, or an empty list if none are found.
     * @since 0.0.1
     */
    public String[] onlyValidChromiums() {
        String[] chromees = CHROMIUM_FINDER();
        ArrayList<String> validChromiums = new ArrayList<String>();
        for (String chrome : chromees) {
            File chromeFile = new File(chrome);
            if (chromeFile.exists()) {
                validChromiums.add(chrome);
            }
        }
        return validChromiums.toArray(new String[validChromiums.size()]);
    }

    /*
     * Return the best available Chromium from the list of Chromiums we have.
     * 
     * @return the path to the best available Chromium, or null if none are found.
     * @since 0.0.1
     */
    public String topChromium() {
        // get the CHROMIUM environment variable
        String chrome = System.getenv("CHROMIUM");
        // if it is not null and not empty
        if (chrome != null && !chrome.isEmpty()) {
            // check if the file exists
            File chromeFile = new File(chrome);
            if (chromeFile.exists()) {
                // if it does, return it
                return chrome;
            }
        }
        String[] chromees = onlyValidChromiums();
        if (chromees.length > 0) {
            return chromees[0];
        } else {
            return "";
        }
    }

    /*
     * Return the best available Chromium from the list of Chromiums we have.
     * if override is passed it will be validated and if it validates, it will
     * be used.
     * 
     * @param override the path to a valid Chromium binary to use.
     * @return the path to the best available Chromium, or null if none are found.
     * @since 0.0.1
     */
    public String topChromium(String overrideChromium) {
        if (overrideChromium != null && !overrideChromium.isEmpty()) {
            File chromeFile = new File(overrideChromium);
            if (chromeFile.exists()) {
                return overrideChromium;
            }
        }
        return topChromium();
    }

    /*
     * Build a ProcessBuilder for the top Chromium binary and
     * the default profile.
     * 
     * @return a ProcessBuilder for the top Chromium binary and
     * the default profile.
     * @since 0.0.1
     */
    public ProcessBuilder defaultProcessBuilder() {
        return processBuilder(new String[]{});
    }

    /*
     * Build a ProcessBuilder for the top Chromium binary and
     * the default profile.
     * 
     * @param args the arguments to pass to the Chromium binary.
     * @return a ProcessBuilder for the top Chromium binary and
     * the default profile. Always passes the --incognito flag.
     * @since 0.0.1
     */
    public ProcessBuilder privateProcessBuilder() {
        return processBuilder(new String[]{"--incognito"});
    }

    /*
     1 --user-data-dir="$CHROMIUM_I2P" \
     2 --proxy-server="http://127.0.0.1:4444" \
     3 --proxy-bypass-list=127.0.0.1:7657 \
     4 --user-data-dir=$HOME/WebApps/i2padmin \
     5 --safebrowsing-disable-download-protection \
     6 --disable-client-side-phishing-detection \
     7 --disable-3d-apis \
     8 --disable-accelerated-2d-canvas \
     9 --disable-remote-fonts \
     10 --disable-sync-preferences \
     11 --disable-sync \
     12 --disable-speech \
     13 --disable-webgl \
     14 --disable-reading-from-canvas \
     15 --disable-gpu \
     16 --disable-32-apis \
     17 --disable-auto-reload \
     18 --disable-background-networking \
     19 --disable-d3d11 \
     20 --disable-file-system \
     */

    /*
     * Build a ProcessBuilder for the top Chromium binary and
     * the default profile, with a specific set of extended
     * arguments.
     * 
     * @param args the extended arguments to pass to the Chromium binary.
     * @return a ProcessBuilder for the top Chromium binary and
     * default profile, with a specific set of extended arguments.
     * @since 0.0.1
     */
    public ProcessBuilder processBuilder(String[] args) {
        String chrome = topChromium();
        if (!chrome.isEmpty()) {
            String[] newArgs = new String[args.length+20];
            newArgs[0] = chrome;
            newArgs[1] = "--user-data-dir="+I2PChromiumProfileBuilder.profileDirectory();
            newArgs[2] = "--proxy-server=http://127.0.0.1:4444";
            newArgs[3] = "--proxy-bypass-list=http://localhost:7657";
            newArgs[4] = "--safebrowsing-disable-download-protection";
            newArgs[5] = "--disable-client-side-phishing-detection";
            newArgs[6] = "--disable-3d-apis";
            newArgs[7] = "--disable-accelerated-2d-canvas";
            newArgs[8] = "--disable-remote-fonts";
            newArgs[9] = "--disable-sync-preferences";
            newArgs[10] = "--disable-sync";
            newArgs[11] = "--disable-speech";
            newArgs[12] = "--disable-webgl";
            newArgs[13] = "--disable-reading-from-canvas";
            newArgs[14] = "--disable-gpu";
            newArgs[15] = "--disable-auto-reload";
            newArgs[16] = "--disable-background-networking";
            newArgs[17] = "--disable-d3d11";
            newArgs[18] = "--disable-file-system";
            newArgs[19] = "--load-extension="+new File(I2PChromiumProfileBuilder.profileDirectory(),"extensions/i2pchrome.js").getAbsolutePath();
            /*+","+
            new File(I2PChromiumProfileBuilder.profileDirectory(),"extensions/ublock.js").getAbsolutePath()
            +","+
            new File(I2PChromiumProfileBuilder.profileDirectory(),"extensions/scriptsafe.js").getAbsolutePath();*/
            for (int i = 0; i < args.length; i++) {
                newArgs[i+19] = args[i];
            }
            return new ProcessBuilder(newArgs).directory(I2PChromiumProfileBuilder.runtimeDirectory(true));
        } else {
            return new ProcessBuilder(args);
        }
    }

    /*
     * Waits for an HTTP proxy on port 4444 to be ready.
     * Returns false on timeout of 200 seconds.
     * 
     * @return true if the proxy is ready, false if it is not.
     * @since 0.0.1
     */
    public boolean waitForProxy() {
        return waitForProxy(DEFAULT_TIMEOUT);
    }

    /* 
     * Waits for an HTTP proxy on port 4444 to be ready.
     * Returns false on timeout of the specified number of seconds.
     * 
     * @param timeout the number of seconds to wait for the proxy to be ready.
     * @return true if the proxy is ready, false if it is not.
     * @since 0.0.1
     */
    public boolean waitForProxy(int timeout) {
        return waitForProxy(timeout, 4444);
    }
    /*
     * Waits for an HTTP proxy on the specified port to be ready.
     * Returns false on timeout of the specified number of seconds.
     * 
     * @param timeout the number of seconds to wait for the proxy to be ready.
     * @param port the port to wait for the proxy to be ready on.
     * @return true if the proxy is ready, false if it is not.
     * @since 0.0.1
     */
    public boolean waitForProxy(int timeout, int port) {
        return waitForProxy(timeout, port, "localhost");
    }
    /*
     * Waits for an HTTP proxy on the specified port to be ready.
     * Returns false on timeout of the specified number of seconds.
     * 
     * @param timeout the number of seconds to wait for the proxy to be ready.
     * @param port the port to wait for the proxy to be ready on.
     * @param host the host to wait for the proxy to be ready on.
     * @return true if the proxy is ready, false if it is not.
     * @since 0.0.1
     */
    public boolean waitForProxy(int timeout, int port, String host) {
        for (int i = 0; i < timeout; i++) {
            if (checkifPortIsOccupied(port, host)) {
                return true;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    private boolean checkifPortIsOccupied(int port, String host) {
        try {
            Socket socket = new Socket(host, port);
            socket.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    /*
     * Populates a profile directory with a proxy configuration.
     * Waits for an HTTP proxy on the port 4444 to be ready.
     * Launches Chromium with the profile directory.
     * 
     * @param bool if true, the profile will be ephemeral(i.e. a --private-window profile).
     * @since 0.0.1
     */
    public void launch(boolean privateWindow){
        String profileDirectory = I2PChromiumProfileBuilder.profileDirectory();
        if (I2PChromiumProfileChecker.validateProfileDirectory(profileDirectory)) {
            System.out.println("Valid profile directory: "+profileDirectory);
        } else {
            System.out.println("Invalid profile directory: "+profileDirectory+" rebuilding...");
            if (!I2PChromiumProfileBuilder.copyBaseProfiletoProfile()) {
                System.out.println("Failed to rebuild profile directory: "+profileDirectory);
                return;
            } else {
                System.out.println("Rebuilt profile directory: "+profileDirectory);
            }
        }
        if (waitForProxy()){
            ProcessBuilder pb = null;
            if (privateWindow) {
                pb = this.privateProcessBuilder();
            } else {
                pb = this.defaultProcessBuilder();
            }
            
            Process p = null;
            try{
                System.out.println(pb.command());
                p = pb.start();
            }catch(Exception e){
                System.out.println("Error: "+e.getMessage());
            }finally{
                System.out.println("I2PChromium");
                try{
                    System.out.println("Waiting for I2PChromium to close...");
                    p.waitFor();
                }catch(Exception e){
                    System.out.println("Error: "+e.getMessage());
                }
            }
        }
    }
    /*
     * Populates a profile directory with a proxy configuration.
     * Waits for an HTTP proxy on the port 4444 to be ready.
     * Launches Chromium with the profile directory.
     * 
     * @since 0.0.1
     */
    public void launch(){
        launch(false);
    }    

    public static void main(String[] args) {
        System.out.println("I2PChromium");
        I2PChromium i2pChromium = new I2PChromium();
        i2pChromium.launch();
    }    
}
