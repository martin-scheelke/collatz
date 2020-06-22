package collatz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Util {

  /**
   * Access a properties file on the local classpath
   */
  public static String getProp(String propName) throws IOException {
    Properties prop = new Properties();
    String propFileName = ".properties";
    InputStream inputStream = Util.class.getClassLoader().getResourceAsStream(propFileName);
    if (inputStream != null) {
      prop.load(inputStream);
    } else {
      throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
    }
    return prop.getProperty(propName);
  }
}
