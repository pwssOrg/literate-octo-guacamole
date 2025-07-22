package lib.pwss.algorithm_switch;

public final class EncryptionAlgorithmChoices {

    /**
     * The first cryptographic algorithm.
     */
    private final String algorithm1;

    /**
     * The second cryptographic algorithm.
     */
    private final String algorithm2;

    /**
     * The third cryptographic algorithm.
     */
    private final String algorithm3;

    /**
     * Constructor to initialize the encryption algorithm choices with given values.
     *
     * @param algorithm1 The first algorithm choice.
     * @param algorithm2 The second algorithm choice.
     * @param algorithm3 The third algorithm choice.
     */
    protected EncryptionAlgorithmChoices(String algorithm1, String algorithm2, String algorithm3) {
        this.algorithm1 = algorithm1;
        this.algorithm2 = algorithm2;
        this.algorithm3 = algorithm3;
    }

    /**
     * Gets the first cryptographic algorithm.
     *
     * @return The value of ALGORITHM_1.
     */
    public String getAlgorithm1() {
        return algorithm1;
    }

    /**
     * Gets the second cryptographic algorithm.
     *
     * @return The value of ALGORITHM_2.
     */
    public String getAlgorithm2() {
        return algorithm2;
    }

    /**
     * Gets the third cryptographic algorithm.
     *
     * @return The value of ALGORITHM_3.
     */
    public String getAlgorithm3() {
        return algorithm3;
    }
}