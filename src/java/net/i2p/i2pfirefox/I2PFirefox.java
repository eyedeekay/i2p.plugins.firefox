package net.i2p.i2pfirefox;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class I2PFirefox {
    private final String[] FIREFOX_SEARCH_PATHS = FIREFOX_FINDER();
    private final int DEFAULT_TIMEOUT = 200;

    /*
     * Construct an I2PFirefox class which manages an instance of Firefox and
     * an accompanying Firefox profile. This version includes Firefox variants
     * and forks.
     * 
     * @since 0.0.1
     */
    I2PFirefox() {
        for (String path : FIREFOX_SEARCH_PATHS) {
            File f = new File(path);
            if (f.exists()) {
                System.out.println("Found Firefox at " + path);
                return;
            }
        }

    }

    private static String[] FIND_FIREFOX_SEARCH_PATHS_UNIX(){
        String[] path = new String[]{"/usr/bin", "/usr/local/bin", "/opt/firefox/bin","/snap/bin"};
        String[] exes = new String[]{"firefox", "firefox-bin", "firefox-esr", "waterfox", "waterfox-bin", "librewolf"};
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
    private static String[] FIND_FIREFOX_SEARCH_PATHS_OSX() {
        String[] path = new String[]{"/Applications/Firefox.app/Contents/MacOS/", "/Applications/Waterfox.app/Contents/MacOS/", "/Applications/Librewolf.app/Contents/MacOS/"};
        String[] exes = new String[]{"firefox", "firefox-bin", "firefox-esr", "waterfox", "waterfox-bin", "librewolf"};
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
    private static String[] FIND_FIREFOX_SEARCH_PATHS_WINDOWS() {
        String[] path = new String[]{"C:/Program Files/Mozilla Firefox/", "C:/Program Files (x86)/Mozilla Firefox/", "C:/Program Files/Waterfox/", "C:/Program Files (x86)/Waterfox/", "C:/Program Files/Librewolf/"};
        String[] exes = new String[]{"firefox.exe", "firefox-bin.exe", "firefox-esr.exe", "waterfox.exe", "waterfox-bin.exe", "librewolf.exe"};
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

    private static String[] FIND_ALL_FIREFOX_SEARCH_PATHS() {
        String[] Unix = FIND_FIREFOX_SEARCH_PATHS_UNIX();
        String[] Windows = FIND_FIREFOX_SEARCH_PATHS_WINDOWS();
        String[] Mac = FIND_FIREFOX_SEARCH_PATHS_OSX();
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
    private static String[] FIND_FIREFOX_SEARCH_PATHS() {
        switch (getOperatingSystem()) {
            case "Windows":
                return FIND_FIREFOX_SEARCH_PATHS_WINDOWS();
            case "Linux":
                return FIND_FIREFOX_SEARCH_PATHS_UNIX();
            case "Mac":
                return FIND_FIREFOX_SEARCH_PATHS_OSX();
            case "BSD":
                return FIND_FIREFOX_SEARCH_PATHS_UNIX();
            default:
                return FIND_ALL_FIREFOX_SEARCH_PATHS();
        }
    }
    private static String[] NEARBY_FIREFOX_SEARCH_PATHS() {
        // obtain the PLUGIN environment variable
        String plugin = System.getenv("PLUGIN");
        // search the plugin directory for anything named "firefox", "firefox-bin", "firefox-esr", "waterfox", "waterfox-bin", "librewolf"
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
                            if (pluginFile.getName().equals("firefox") || pluginFile.getName().equals("firefox-bin") || pluginFile.getName().equals("firefox-esr") || pluginFile.getName().equals("waterfox") || pluginFile.getName().equals("waterfox-bin") || pluginFile.getName().equals("librewolf")) {
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
                        if (userFile.getName().equals("firefox") || userFile.getName().equals("firefox-bin") || userFile.getName().equals("firefox-esr") || userFile.getName().equals("waterfox") || userFile.getName().equals("waterfox-bin") || userFile.getName().equals("librewolf")) {
                            return new String[]{userFile.getAbsolutePath()};
                        }
                    }
                }
            }
        }
        return new String[]{};
    }
    private static String[] FIREFOX_FINDER() {
        String[] nearby = NEARBY_FIREFOX_SEARCH_PATHS();
        String[] all = FIND_FIREFOX_SEARCH_PATHS();

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
     * Check our list of firefox paths for a valid firefox binary.
     * Just an existence check for now, but should check versions
     * in the future.
     * 
     * @return a list of usable Firefoxes, or an empty list if none are found.
     * @since 0.0.1
     */
    public String[] onlyValidFirefoxes() {
        String[] firefoxes = FIREFOX_FINDER();
        ArrayList<String> validFirefoxes = new ArrayList<String>();
        for (String firefox : firefoxes) {
            File firefoxFile = new File(firefox);
            if (firefoxFile.exists()) {
                validFirefoxes.add(firefox);
            }
        }
        return validFirefoxes.toArray(new String[validFirefoxes.size()]);
    }

    /*
     * Return the best available Firefox from the list of Firefoxes we have.
     * 
     * @return the path to the best available Firefox, or null if none are found.
     * @since 0.0.1
     */
    public String topFirefox() {
        // get the FIREFOX environment variable
        String firefox = System.getenv("FIREFOX");
        // if it is not null and not empty
        if (firefox != null && !firefox.isEmpty()) {
            // check if the file exists
            File firefoxFile = new File(firefox);
            if (firefoxFile.exists()) {
                // if it does, return it
                return firefox;
            }
        }
        String[] firefoxes = onlyValidFirefoxes();
        if (firefoxes.length > 0) {
            return firefoxes[0];
        } else {
            return "";
        }
    }

    /*
     * Return the best available Firefox from the list of Firefoxes we have.
     * if override is passed it will be validated and if it validates, it will
     * be used.
     * 
     * @param override the path to a valid Firefox binary to use.
     * @return the path to the best available Firefox, or null if none are found.
     * @since 0.0.1
     */
    public String topFirefox(String overrideFirefox) {
        if (overrideFirefox != null && !overrideFirefox.isEmpty()) {
            File firefoxFile = new File(overrideFirefox);
            if (firefoxFile.exists()) {
                return overrideFirefox;
            }
        }
        return topFirefox();
    }

    /*
     * Build a ProcessBuilder for the top Firefox binary and
     * the default profile.
     * 
     * @return a ProcessBuilder for the top Firefox binary and
     * the default profile.
     * @since 0.0.1
     */
    public ProcessBuilder defaultProcessBuilder() {
        return processBuilder(new String[]{});
    }

    /*
     * Build a ProcessBuilder for the top Firefox binary and
     * the default profile, with a specific set of extended
     * arguments.
     * 
     * @param args the extended arguments to pass to the Firefox binary.
     * @return a ProcessBuilder for the top Firefox binary and
     * default profile, with a specific set of extended arguments.
     * @since 0.0.1
     */
    public ProcessBuilder processBuilder(String[] args) {
        String firefox = topFirefox();
        if (!firefox.isEmpty()) {
            String[] newArgs = new String[args.length+3];
            newArgs[0] = firefox;
            newArgs[1] = "--profile";
            newArgs[2] = I2PFirefoxProfileBuilder.profileDirectory();
            for (int i = 0; i < args.length; i++) {
                newArgs[i+3] = args[i];
            }
            return new ProcessBuilder(newArgs).directory(I2PFirefoxProfileBuilder.runtimeDirectory(true));
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
     * Launches Firefox with the profile directory.
     * 
     * @since 0.0.1
     */
    public void launch(){
        String profileDirectory = I2PFirefoxProfileBuilder.profileDirectory();
        if (I2PFirefoxProfileChecker.validateProfileDirectory(profileDirectory)) {
            System.out.println("Valid profile directory: "+profileDirectory);
        } else {
            System.out.println("Invalid profile directory: "+profileDirectory+" rebuilding...");
            if (!I2PFirefoxProfileBuilder.copyBaseProfiletoProfile()) {
                System.out.println("Failed to rebuild profile directory: "+profileDirectory);
                return;
            } else {
                System.out.println("Rebuilt profile directory: "+profileDirectory);
            }
        }
        if (waitForProxy()){
            ProcessBuilder pb = this.defaultProcessBuilder();
            try{
                pb.start();
            }catch(Exception e){
                System.out.println("Error: "+e.getMessage());
            }finally{
                System.out.println("I2PFirefox");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("I2PFirefox");
        I2PFirefox i2pFirefox = new I2PFirefox();
        i2pFirefox.launch();
    }    
}
