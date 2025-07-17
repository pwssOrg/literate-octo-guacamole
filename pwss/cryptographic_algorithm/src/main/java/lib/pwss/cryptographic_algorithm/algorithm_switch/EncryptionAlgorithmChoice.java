package lib.pwss.cryptographic_algorithm.algorithm_switch;

public final class EncryptionAlgorithmChoice {

    /**
     * The first cryptographic algorithm.
     */
    public final String ALGORITHM_1;

    /**
     * The second cryptographic algorithm.
     */
    public final String ALGORITHM_2;

    /**
     * The third cryptographic algorithm.
     */
    public final String ALGORITHM_3;

    /**
     * Constructor to initialize the encryption algorithm choices with given values.
     *
     * @param aLGORITHM_1 The first algorithm choice.
     * @param aLGORITHM_2 The second algorithm choice.
     * @param aLGORITHM_3 The third algorithm choice.
     */
    public EncryptionAlgorithmChoice(String aLGORITHM_1, String aLGORITHM_2, String aLGORITHM_3) {
        this.ALGORITHM_1 = aLGORITHM_1;
        this.ALGORITHM_2 = aLGORITHM_2;
        this.ALGORITHM_3 = aLGORITHM_3;
    }

    /**
     * Gets the first cryptographic algorithm.
     *
     * @return The value of ALGORITHM_1.
     */
    public String getALGORITHM_1() {
        return ALGORITHM_1;
    }

    /**
     * Gets the second cryptographic algorithm.
     *
     * @return The value of ALGORITHM_2.
     */
    public String getALGORITHM_2() {
        return ALGORITHM_2;
    }

    /**
     * Gets the third cryptographic algorithm.
     *
     * @return The value of ALGORITHM_3.
     */
    public String getALGORITHM_3() {
        return ALGORITHM_3;
    }
}