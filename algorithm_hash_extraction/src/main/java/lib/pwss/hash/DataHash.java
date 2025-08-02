package lib.pwss.hash;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * The DataHash class provides methods for calculating hash values using various hashing algorithms.
 * This includes SHA-256, SHA-3 (256), and BLAKE2b. It is used internally by other classes to perform
 * the actual hashing operations on data byte arrays.
 */
  final class DataHash {
/**
     * Protected constructor to prevent instantiation of this class outside the package.
     */
    protected DataHash() {
    }

     /**
      * Calculates the SHA256 Hash of a file
      * @param data (bytes)
      * @return bytes
      * @throws NoSuchAlgorithmException
      */
    protected final byte[] getSHA256(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return md.digest(data);
    }
     /**
      * Calculates the SHA3 Hash of a file
      * @param data (bytes)
      * @return bytes
      */
    protected final byte[] getSHA3_256(byte[] data) {
        Digest digest = new SHA3Digest(256);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.update(data, 0, data.length);
        digest.doFinal(hash, 0);
        return hash;
    }

     /**
      * Calculates the Blake2B Hash of a file
      * @param data (bytes)
      * @param digestSize BLAKE2b digest bit length must be a multiple of 8 and not greater than 512
      * @return bytes
      */
    protected final byte[] getBLAKE2b(byte[] data, int digestSize) {
        Digest digest = new org.bouncycastle.crypto.digests.Blake2bDigest(digestSize * 8); // Bits
        byte[] hash = new byte[digest.getDigestSize()];
        digest.update(data, 0, data.length);
        digest.doFinal(hash, 0);
        return hash;
    }


}
