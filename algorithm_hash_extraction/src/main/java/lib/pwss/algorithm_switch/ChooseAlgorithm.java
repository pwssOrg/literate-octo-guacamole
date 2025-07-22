package lib.pwss.algorithm_switch;
/**
 * Interface designed to aid in the selection and implementation of different encryption algorithms.
 *
 * <p>This interface is part of a library that facilitates algorithm switching. It provides default
 * implementations to help select an appropriate encryption algorithm based on the specified criteria,
 * but its use is optional. Algorithm switching can still be performed without implementing this
 * interface, though using it simplifies the process.</p>
 *
 * <p>The {@code chooseAlgorithmImplementation} method uses a switch statement to determine which
 * implementation of the encryption algorithm should be used based on the value returned by the
 * {@code getSELECTED_ALGORITHM_FOR_PRODUCTION()} method of the provided {@link EncryptionAlgorithm}
 * instance.</p>
 *
 * <p>Implementing classes must provide concrete implementations for the three methods:
 * {@code implementAlgorithm1}, {@code implementAlgorithm2}, and {@code implementAlgorithm3}.
 */
public interface ChooseAlgorithm
{

    /**
     * Chooses the appropriate algorithm implementation based on the provided encryption algorithm.
     *
     * @param encryptionAlgorithm The encryption algorithm whose selected algorithm will determine
     *                            which method to invoke.
     */
    default void chooseAlgorithmImplementation(EncryptionAlgorithm encryptionAlgorithm) {

        switch (encryptionAlgorithm.getSELECTED_ALGORITHM_FOR_PRODUCTION()) {
            case 1:
                implementAlgorithm1();
                break;
            case 2:
                implementAlgorithm2();
                break;
            case 3:
                implementAlgorithm3();
            default:
                break;
        }
    }

    /**
     * Implement the first encryption algorithm.
     */
    void implementAlgorithm1();

    /**
     * Implement the second encryption algorithm.
     */
    void implementAlgorithm2();

    /**
     * Implement the third encryption algorithm.
     */
    void implementAlgorithm3();

}
