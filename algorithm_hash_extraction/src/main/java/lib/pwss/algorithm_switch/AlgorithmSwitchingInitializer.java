package lib.pwss.algorithm_switch;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class AlgorithmSwitchingInitializer {

    /**
     * Initializes the algorithm switching function by writing configuration values to a properties file.
     */
    public final void initAlgorithmSwitchingFunction() {
        // Define the path for the properties file in the root folder of the Maven project
        Path currentDir = Paths.get(System.getProperty("user.dir"));
        Path propertiesFilePath = currentDir.resolve(ConfigConstant.CONFIG_FILE_NAME);

        try {
            // Check if the file already exists
            if (Files.exists(propertiesFilePath)) {
                return;
            }

            Properties properties = new Properties();

            try (FileOutputStream fos = new FileOutputStream(propertiesFilePath.toString())) {
                // Add keys and values to the properties
                properties.setProperty("1", "RSA");
                properties.setProperty("2", "Kyber");
                properties.setProperty("3", "Blake_2B");
                properties.setProperty("USE_FOR_PROD","1");

                // Save properties to file with no header comment
                properties.store(fos, null);
            } catch (IOException e) {
                return;
            }
        } catch (SecurityException e) {
            return;
        }
    }
}