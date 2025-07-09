package lib.pwss.cryptographic_algorithm;

import lib.pwss.cryptographic_algorithm.algorithm_switch.EncryptionAlgorithm;
import lib.pwss.cryptographic_algorithm.algorithm_switch.EncryptionAlgorithmChoice;

public abstract class AbstractEncryptionAlgorithm implements EncryptionAlgorithm {

    /**
     * An Enum that holds cryptographic_algorithm alternatives
     */
    private final EncryptionAlgorithmChoice encryptionAlgorithmChoice;

    public AbstractEncryptionAlgorithm(final EncryptionAlgorithmChoice encryptionAlgorithmChoice){

        this.encryptionAlgorithmChoice=encryptionAlgorithmChoice;
    }

    /**
     * Get the cryptographic_algorithm alternative that should be used by the cryptographic_algorithm
     * @return The cryptographic_algorithm that the system should use
     */
    public final EncryptionAlgorithmChoice getEncryptionAlgorithmChoice() {
        if(encryptionAlgorithmChoice == null){

            // Should not happen , but if it does exit the system!
            System.exit(1);
        }
        return encryptionAlgorithmChoice;
    }

    /**
     * A text description of the function of this library
     * @return String
     */
    public final String getLibraryFunctionDescription(){

        return "A library that includes base classes that make it easier to switch cryptographic algorithms.";

    }
}