package lib.pwss.hash.file_hash_handler;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;

import java.security.NoSuchAlgorithmException;

import lib.pwss.hash.FileHash;
import org.slf4j.LoggerFactory;

/**
 * The FileHashHandler class provides methods for calculating hash values of a
 * file using various hashing
 * algorithms.
 * This includes SHA-256, SHA-3 (256), and BLAKE2b. It returns the calculated
 * hash values in a structured format.
 */
public final class FileHashHandler extends FileHashBase implements FileHash {

    /**
     * A utility class for performing data hashing operations.
     */
    private final DataHash dataHash;

    /**
     * Logger instance used for logging messages and errors.
     */
    private final org.slf4j.Logger log;

    /**
     * Public Constructor
     */
    public FileHashHandler() {
        dataHash = new DataHash();
        this.log = LoggerFactory.getLogger(FileHashHandler.class);
    }

    /**
     * Calculates the SHA256 Hash of a file
     *
     * @param file to extract hash from
     * @return A string containing the hexadecimal representation of the bytes in
     *         SHA2 Hash
     */
    @Override
    public final String calculateSha256Hash(File file) {
        byte[] sha256Hash;
        try {
            sha256Hash = dataHash.getSHA256(Files.readAllBytes(file.toPath()));
            return SHA256_PRINT_PREFIX + bytesToHex(sha256Hash);
        } catch (IOException | NoSuchAlgorithmException e) {
            log.error("Error in calculateSha256Hash: {}", e.getMessage());
        }
        return ERROR;
    }

    /**
     * Calculates the SHA3 Hash of a file
     *
     * @param file to extract hash from
     * @return A string containing the hexadecimal representation of the bytes in
     *         SHA3 Hash
     */
    @Override
    public final String calculateSha3Hash(File file) {
        byte[] sha3Hash;
        try {
            sha3Hash = dataHash.getSHA3_256(Files.readAllBytes(file.toPath()));
            return SHA3_PRINT_PREFIX + bytesToHex(sha3Hash);
        } catch (IOException e) {
            log.error("IOException error occurred: {}", e.getMessage());
        }
        return ERROR;
    }

    /**
     * Calculates the Blake2B Hash of a file
     *
     * @param file to extract hash from
     * @return A string containing the hexadecimal representation of the bytes in a
     *         Blake2B Hash
     */
    @Override
    public final String calculateBlake2bHash(File file) {
        byte[] blake2bHash;
        try {
            blake2bHash = dataHash.getBLAKE2b(Files.readAllBytes(file.toPath()), DIGEST_SIZE_BLAKE_2B);
            return BLAKE_2B_PRINT_PREFIX + bytesToHex(blake2bHash);
        } catch (IOException e) {
            log.error("IOException error occurred: {}", e.getMessage());
        }
        return ERROR;
    }

}
