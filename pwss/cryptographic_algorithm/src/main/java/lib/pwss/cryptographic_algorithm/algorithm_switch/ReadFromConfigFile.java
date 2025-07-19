package lib.pwss.cryptographic_algorithm.algorithm_switch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

 final class ReadFromConfigFile {

public ReadFromConfigFile(){}


     /**
     * Reads the configuration values from a properties file and returns them as a Properties object.
     *
     * @return A Properties object containing the configuration values.
     */
    protected final Properties ReadValuesFromConfigFile(){

     // Define the path for the properties file in the root folder of the Maven project
        Path currentDir = Paths.get(System.getProperty("user.dir"));
        Path propertiesFilePath = currentDir.resolve(ConfigConstant.CONFIG_FILE_NAME);

         Properties loadedProperties = new Properties();
        try {
            loadedProperties.load(Files.newInputStream(propertiesFilePath));
        } catch (IOException e) {
          
            e.printStackTrace();
        }

        return loadedProperties;

    }
    
}
