package lib.pwss.cryptographic_algorithm.algorithm_switch;

public interface EncryptionAlgorithm {

    /**
     * Encrypt a given object
     * @param objectToEncrypt
     * @return The encrypted object or a String stating that encryption is not allowed (Ex. If Server only decrypts RSA)
     * @param <T>
     */
    public abstract <T> T Encrypt (T objectToEncrypt);

    /**
     * Decrypt a given object
     * @param objectToDecrypt
     * @return The encrypted object or a String stating that encryption is not allowed (Ex. If Server only decrypts RSA)
     *
     * @param <T>
     */
    public abstract  <T> T Decrypt(T objectToDecrypt);

    /**
     * Generate a single symmetric key
     * @return a symmetric key object
     * @param <T>
     * @info An Abstract method for generating a symmetric key. If this is not supported by the cryptographic_algorithm, throw NoSuchMethodError (in an implementing class).
     */
    public abstract <T> T GenerateSingleKey();

    /**
     * Generate an asymmetric keypair
     * @return an asymmetric keypair object
     * @param <T>
     * @info An Abstract method for generating an asymmetric key pair. If this is not supported by the cryptographic_algorithm, throw NoSuchMethodError (in an implementing class) (in an implementing class).
     */
    public abstract <T> T GenerateKeyPair();


}