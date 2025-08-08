package lib.pwss.hash.file_hash_handler;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA3Digest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.LoggerFactory;

/**
 * The DataHash class provides methods for calculating hash values using various
 * hashing algorithms.
 * This includes SHA-256, SHA-3 (256), and BLAKE2b. It is used internally by
 * other classes to perform
 * the actual hashing operations on data byte arrays.
 */
final class DataHash {

    private final int BUFFER_SIZE_FOR_BUFFERED_STREAM = 8192;
    private final String SHA_256_ALGORITHM = "SHA-256";
    private final org.slf4j.Logger log;

    /**
     * Protected constructor to prevent instantiation of this class outside the
     * package.
     */
    protected DataHash() {

        this.log = LoggerFactory.getLogger(DataHash.class);
    }

    /**
     * Calculates the SHA256 Hash of a file
     * 
     * @param data (bytes)
     * @return bytes
     * @throws NoSuchAlgorithmException
     */
    protected final byte[] getSHA256(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(SHA_256_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException: {}", e.getMessage());
            throw new RuntimeException(e);
        }
        return md.digest(data);
    }

    /**
     * Calculates the SHA3 Hash of a file
     * 
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
     * 
     * @param data       (bytes)
     * @param digestSize BLAKE2b digest bit length must be a multiple of 8 and not
     *                   greater than 512
     * @return bytes
     */
    protected final byte[] getBLAKE2b(byte[] data, int digestSize) {
        Digest digest = new org.bouncycastle.crypto.digests.Blake2bDigest(digestSize * 8); // Bits
        byte[] hash = new byte[digest.getDigestSize()];
        digest.update(data, 0, data.length);
        digest.doFinal(hash, 0);
        return hash;
    }

    /**
     * Calculates the SHA-256 hash of a file whose size exceeds the available Java
     * heap space.
     * This method uses a BufferedInputStream to efficiently read large files in
     * chunks,
     * ensuring that it doesn't load the entire file into memory at once.
     *
     * @param filePath The path to the file for which the SHA-256 hash is to be
     *                 calculated.
     *                 This should be a valid path to an existing file.
     * @return A byte array containing the SHA-256 hash of the file's contents.
     *         The length of this array will always be 32 bytes (256 bits).
     * @throws IOException              If there is an error accessing or reading
     *                                  the specified file,
     *                                  such as if the file does not exist, cannot
     *                                  be opened, or read.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available on
     *                                  this system
     *                                  (highly unlikely for most modern Java
     *                                  environments).
     */
    protected final byte[] getSHA256Big(String filePath) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(SHA_256_ALGORITHM);
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            byte[] buffer = new byte[BUFFER_SIZE_FOR_BUFFERED_STREAM];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        }
        return md.digest();
    }

    /**
     * Calculates the BLAKE2b hash of a file whose size exceeds the available Java
     * heap space.
     * This method uses a BufferedInputStream to efficiently read large files in
     * chunks,
     * ensuring that it doesn't load the entire file into memory at once. The
     * BLAKE2b
     * algorithm is implemented using the BouncyCastle cryptographic library.
     *
     * @param filePath   The path to the file for which the BLAKE2b hash is to be
     *                   calculated.
     *                   This should be a valid path to an existing file.
     * @param digestSize BLAKE2b digest bit length must be a multiple of 8 and not
     *                   greater than 512
     * @return A byte array containing the BLAKE2b hash of the file's contents.
     *         The length of this array will match the specified digest size.
     * @throws FileNotFoundException If the specified file does not exist or cannot
     *                               be opened.
     * @throws IOException           If there is an error reading from the file.
     */
    protected final byte[] getBLAKE2bBig(String filePath, int digestSize) {
        Digest digest = new org.bouncycastle.crypto.digests.Blake2bDigest(digestSize * 8); // Bits
        byte[] hash = new byte[digest.getDigestSize()];

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {

            byte[] buffer = new byte[BUFFER_SIZE_FOR_BUFFERED_STREAM];
            int bytesRead;
            try {
                while ((bytesRead = bis.read(buffer)) != -1) {

                    digest.update(buffer, 0, bytesRead);

                }
            } catch (IOException e) {
                log.error("IOException error occurred: {}", e.getMessage());
            }

        } catch (FileNotFoundException e1) {
            log.error("FileNotFoundException error occurred: {}", e1.getMessage());
        } catch (IOException e1) {
            log.error("IOException error occurred: {}", e1.getMessage());
        }

        digest.doFinal(hash, 0);
        return hash;
    }

    /**
     * Calculates the SHA-3 256-bit hash of a file.
     *
     * This method reads the file specified by the given path in chunks, updates the
     * SHA-3 256-bit digest,
     * and finally returns the computed hash value as an array of bytes. The method
     * handles various IO
     * exceptions gracefully by logging appropriate error messages.
     *
     * @param filePath The full path to the file for which the hash needs to be
     *                 calculated.
     *                 If the file does not exist or cannot be read, errors will be
     *                 logged but no exception is thrown.
     * @return A byte array representing the SHA-3 256-bit hash of the specified
     *         file.
     */
    protected final byte[] getSHA3_256Big(String filePath) {
        Digest digest = new SHA3Digest(256);

        byte[] hash = new byte[digest.getDigestSize()];
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {
            byte[] buffer = new byte[BUFFER_SIZE_FOR_BUFFERED_STREAM];
            int bytesRead;
            try {
                while ((bytesRead = bis.read(buffer)) != -1) {
                    digest.update(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                log.error("IOException error occurred: {}", e.getMessage());
            }

            digest.doFinal(hash, 0);

        } catch (FileNotFoundException e1) {
            log.error("FileNotFoundException error occurred: {}", e1.getMessage());
        } catch (IOException e1) {
            log.error("IOException error occurred: {}", e1.getMessage());
        }
        return hash;

    }
}