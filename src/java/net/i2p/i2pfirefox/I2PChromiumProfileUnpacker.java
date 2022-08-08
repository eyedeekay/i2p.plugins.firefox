package net.i2p.i2pfirefox;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class I2PChromiumProfileUnpacker {

    public static void main(String[] args) {
        String profileDirectory = I2PChromiumProfileBuilder.profileDirectory();
        if (profileDirectory == null) {
            System.out.println("No profile directory found");
            return;
        }
        
    }

    /*
     * unpack the profile directory
     * 
     * @return true if the profile directory was successfully unpacked
     * @since 0.0.1
     */
    public boolean unpackProfile(String profileDirectory) {
        System.out.println("Unpacking base profile to " + profileDirectory);
        try {
            final InputStream resources = this.getClass().getClassLoader().getResourceAsStream("i2p.chromium.base.profile.zip");
            if (resources == null) {
                System.out.println("Could not find resources");
                return false;
            }
            System.out.println(resources.toString());
            // InputStream corresponds to a zip file. Unzip it.
            //Files.copy(r, new File(profileDirectory).toPath(), StandardCopyOption.REPLACE_EXISTING);
            ZipInputStream zis = new ZipInputStream(resources);
            ZipEntry entry;
                // while there are entries I process them
            while ((entry = zis.getNextEntry()) != null)
            {
                System.out.println("entry: " + entry.getName() + ", " + entry.getSize());
                // consume all the data from this entry
                if (entry.isDirectory()) {
                    System.out.println("Creating directory: " + entry.getName());
                    File dir = new File(profileDirectory + "/" + entry.getName());
                    dir.mkdirs();
                } else {
                    System.out.println("Creating file: " + entry.getName());
                    File file = new File(profileDirectory + "/" + entry.getName());
                    file.createNewFile();
                    Files.copy(zis, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
                while (zis.available() > 0)
                zis.read();
                    // I could close the entry, but getNextEntry does it automatically
                    // zis.closeEntry()
            }
            // loop through the Enumeration
            
        } catch (Exception e) {
            System.out.println("Error copying profile files: " + e.getMessage());
            return false;
        }
        return true;
    }
    
    
}
