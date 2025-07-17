package lib.pwss.cryptographic_algorithm.algorithm_switch;

import java.util.Properties;
import lib.pwss.cryptographic_algorithm.algorithm_switch.EncryptionAlgorithmChoice;

public final class EncryptionAlgorithm {

    /**
     * Holds the cryptographic algorithm alternatives.
     */
    private final EncryptionAlgorithmChoice encryptionAlgorithmChoice;

    /**
     * Constructor to initialize the EncryptionAlgorithm with configuration values.
     */
    public EncryptionAlgorithm() {
        // Read properties from config file
        ReadFromConfigFile reading = new ReadFromConfigFile();
        Properties config = reading.ReadValuesFromConfigFile();

        // Initialize algorithm choice with values from config
        this.encryptionAlgorithmChoice = new EncryptionAlgorithmChoice(
            config.getProperty("1"),
            config.getProperty("2"),
            config.getProperty("3"));
    }

    /**
     * Gets the cryptographic algorithm alternative to be used.
     *
     * @return The encryption algorithm choice that should be used by the system.
     */
    public final EncryptionAlgorithmChoice getEncryptionAlgorithmChoice() {
        if (this.encryptionAlgorithmChoice == null) {
            // Should not happen, but if it does, exit the system!
            System.exit(1);
        }
        return this.encryptionAlgorithmChoice;
    }

    /**
     * Sets the encryption algorithm to be used in production.
     *
     * @return The current encryption algorithm choice.
     */
    private final EncryptionAlgorithmChoice setEncryptionAlgorithmToUseInProd() {
        // Returning current configuration
        return this.encryptionAlgorithmChoice;
    }

    /**
     * Returns a description of the library's function.
     *
     * @return A string describing the library's purpose.
     */
    public final String getLibraryFunctionDescription() {
        return "A library that includes helper classes to simplify switching cryptographic algorithms.";
    }
}