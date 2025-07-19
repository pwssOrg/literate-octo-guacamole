package lib.pwss.cryptographic_algorithm.algorithm_switch;

import java.util.Properties;

import static lib.pwss.cryptographic_algorithm.algorithm_switch.ConfigConstant.*;

public final class EncryptionAlgorithm {

    /**
     * Holds the instance count ## Max instances allowed is 1
     */
    private static int instanceCount = 0;

    /**
     * The encryption algorithm choices object, which contains available cryptographic algorithms for selection.
     *
     * <p>This field is immutable once set and will remain constant throughout the lifecycle of an instance.
     */
    private final EncryptionAlgorithmChoices encryptionAlgorithmChoices;

    private final int SELECTED_ALGORITHM_FOR_PRODUCTION;

    /**
     * Constructor to initialize the EncryptionAlgorithm with configuration values.
     */
    public EncryptionAlgorithm() {

        if (instanceCount > 0) {
            encryptionAlgorithmChoices = null;
            SELECTED_ALGORITHM_FOR_PRODUCTION = 0;
            System.exit(2);
            // It is only allowed to have one instance of this class

        } else {
            // Read properties from config file
            ReadFromConfigFile reading = new ReadFromConfigFile();
            Properties config = reading.ReadValuesFromConfigFile();

            // Initialize algorithm choice with values from config
            this.encryptionAlgorithmChoices = new EncryptionAlgorithmChoices(
                    config.getProperty(ONE),
                    config.getProperty(TWO),
                    config.getProperty(THREE));

            int algorithmSelected = 0; // 0 is equal to System.Exit

            try {
                algorithmSelected = Integer.parseInt(config.getProperty(FOUR));
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }

            final boolean result = checkIfEncryptionAlgorithmToUseInProIsSafe(algorithmSelected);

            if (result)
                SELECTED_ALGORITHM_FOR_PRODUCTION = algorithmSelected;
            else {
                SELECTED_ALGORITHM_FOR_PRODUCTION = 0;
                System.exit(3);
            }
        }
    }

    /**
     * Returns the encryption algorithm choices object.
     * This method returns the current instance of {@link EncryptionAlgorithmChoices} associated with this class.
     * If, unexpectedly, the encryptionAlgorithmChoices object is null (which should not happen in normal operation),
     * it will exit the system by calling {@code System.exit(1)}.
     *
     * @return The encryption algorithm choices object containing the available encryption algorithms.
     */
    public final EncryptionAlgorithmChoices getEncryptionAlgorithmChoices() {
        if (this.encryptionAlgorithmChoices == null) {
            // Should not happen, but if it does, exit the system!
            System.exit(1);
        }
        return this.encryptionAlgorithmChoices;
    }

    /**
     * Returns the selected algorithm identifier for production.
     * <p>
     * This method returns the current value of SELECTED_ALGORITHM_FOR_PRODUCTION, which represents
     * the chosen encryption algorithm for production purposes. The integer returned corresponds to
     * predefined choices in the system (e.g., 1, 2, or 3) that map to specific algorithms.
     *
     * @return An integer representing the selected algorithm identifier for production.
     */
    public int getSELECTED_ALGORITHM_FOR_PRODUCTION() {
        return SELECTED_ALGORITHM_FOR_PRODUCTION;
    }

    /**
     * Returns the selected algorithm for production as a string based on a predefined selection.
     * This method checks the value of SELECTED_ALGORITHM_FOR_PRODUCTION and returns the corresponding
     * encryption algorithm name from the encryptionAlgorithmChoices object. If none of the predefined
     * values match, it returns "error".
     *
     * @return The selected algorithm for production as a string, or "error" if the selection is invalid.
     */
    public final String getSELECTED_ALGORITHM_FOR_PRODUCTION_STRING() {

        if (SELECTED_ALGORITHM_FOR_PRODUCTION == 1)
            return encryptionAlgorithmChoices.getAlgorithm1();
        if (SELECTED_ALGORITHM_FOR_PRODUCTION == 2)
            return encryptionAlgorithmChoices.getAlgorithm2();
        if (SELECTED_ALGORITHM_FOR_PRODUCTION == 3)
            return encryptionAlgorithmChoices.getAlgorithm3();
        else
            return "error";


    }

    // The selected algorithm can only be an integer in interval 1-3
    private final boolean checkIfEncryptionAlgorithmToUseInProIsSafe(int algorithmSelected) {
        return algorithmSelected == 1 || algorithmSelected == 2 || algorithmSelected == 3;
    }

}