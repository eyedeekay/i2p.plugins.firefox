package net.i2p.i2pfirefox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class I2PCommonBrowser {

  /**
   * get the runtime directory, creating it if create=true
   *
   * @param create if true, create the runtime directory if it does not exist
   * @return the runtime directory, or null if it could not be created
   * @since 0.0.19
   */
  protected static File runtimeDirectory(boolean create, String override) {
    String rtd = runtimeDirectory(override);
    File rtdFile = new File(rtd);
    if (create) {
      if (!rtdFile.exists()) {
        rtdFile.mkdir();
      }
    }
    return new File(rtd);
  }

  /**
   * get the correct runtime directory
   *
   * @return the runtime directory, or null if it could not be created or found
   * @since 0.0.19
   */
  protected static String runtimeDirectory(String override) {
    // get the I2P_BROWSER_DIR environment variable
    String rtd = System.getenv(override);
    // if it is not null and not empty
    if (rtd != null && !rtd.isEmpty()) {
      // check if the file exists
      File rtdFile = new File(rtd);
      if (rtdFile.exists()) {
        // if it does, return it
        return rtd;
      }
    }
    // obtain the PLUGIN environment variable
    String plugin = System.getenv("PLUGIN");
    if (plugin != null && !plugin.isEmpty()) {
      File pluginDir = new File(plugin);
      if (pluginDir.exists()) {
        return pluginDir.toString();
      }
    }
    String userDir = System.getProperty("user.dir");
    if (userDir != null && !userDir.isEmpty()) {
      File userDir1 = new File(userDir);
      if (userDir1.exists()) {
        return userDir1.toString();
      }
    }
    String homeDir = System.getProperty("user.home");
    if (homeDir != null && !homeDir.isEmpty()) {
      File homeDir1 = new File(homeDir + "/.i2p");
      if (homeDir1.exists()) {
        return homeDir.toString();
      }
      File homeDir2 = new File(homeDir + "/i2p");
      if (homeDir2.exists()) {
        return homeDir2.toString();
      }
    }
    return "";
  }

  /**
   * get the profile directory, creating it if necessary
   *
   * @return the profile directory, or null if it could not be created
   * @since 0.0.19
   */
  protected static String profileDirectory(String envVar, String browser) {
    String pd = System.getenv(envVar);
    if (pd != null && !pd.isEmpty()) {
      File pdf = new File(pd);
      if (pdf.exists() && pdf.isDirectory()) {
        return pd;
      }
    }
    String rtd = runtimeDirectory("");
    return profileDir(rtd, browser);
  }

  protected static String profileDir(String file, String browser) {
    File profileDir = new File(file, "i2p." + browser + ".profile");
    return profileDir.getAbsolutePath();
  }

  protected boolean unpackProfile(String profileDirectory, String browser,
                                  String base) {
    System.out.println("Unpacking base profile to " + profileDirectory);
    try {
      final InputStream resources =
          this.getClass().getClassLoader().getResourceAsStream(
              "i2p." + browser + "." + base + ".profile.zip");
      if (resources == null) {
        System.out.println("Could not find resources");
        return false;
      }
      System.out.println(resources.toString());
      // InputStream corresponds to a zip file. Unzip it.
      // Files.copy(r, new File(profileDirectory).toPath(),
      // StandardCopyOption.REPLACE_EXISTING);
      ZipInputStream zis = new ZipInputStream(resources);
      ZipEntry entry;
      // while there are entries I process them
      while ((entry = zis.getNextEntry()) != null) {
        System.out.println("entry: " + entry.getName() + ", " +
                           entry.getSize());
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

  protected static void copyDirectory(File sourceDirectory,
                                      File destinationDirectory, String browser)
      throws IOException {
    destinationDirectory = new File(destinationDirectory.toString().replace(
        "i2p." + browser + ".base.profile", ""));
    if (!destinationDirectory.exists()) {
      destinationDirectory.mkdir();
    }
    for (String f : sourceDirectory.list()) {
      copyDirectoryCompatibityMode(new File(sourceDirectory, f),
                                   new File(destinationDirectory, f), browser);
    }
  }

  private static void
  copyDirectoryCompatibityMode(File source, File destination, String browser)
      throws IOException {
    if (source.isDirectory()) {
      copyDirectory(source, destination, browser);
    } else {
      copyFile(source, destination);
    }
  }

  private static void copyFile(File sourceFile, File destinationFile)
      throws IOException {
    try (InputStream in = new FileInputStream(sourceFile);
         OutputStream out = new FileOutputStream(destinationFile)) {
      byte[] buf = new byte[1024];
      int length;
      while ((length = in.read(buf)) > 0) {
        out.write(buf, 0, length);
      }
    }
  }
}
