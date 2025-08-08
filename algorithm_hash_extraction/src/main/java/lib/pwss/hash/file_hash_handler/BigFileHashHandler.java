package lib.pwss.hash.file_hash_handler;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import lib.pwss.hash.FileHash;

/**
 * This class is designed to handle large files whose sizes exceed the available
 * memory in the Java Heap.
 * It provides an efficient way to calculate file hashes for very large files
 * without consuming excessive
 * heap memory. This implementation is particularly useful when working with
 * large datasets that cannot
 * be loaded entirely into memory at once.
 */
public final class BigFileHashHandler extends FileHashBase implements FileHash {

    /**
     * The data hash object used for calculating file hashes.
     */
    private final DataHash dataHash;
    /**
     * User-defined maximum limit for file size.
     * A value of -1L indicates that there is no maximum limit.
     */
    private final long USER_DEFINED_MAX_LIMIT;

    /**
     * Message to be returned when a file exceeds the user-defined maximum limit.
     */
    private final String FILE_BIGGER_THAN_USER_DEFINED_MAX_LIMIT_MSG = "File Size is bigger than the User Defined Max Limit Size";

    /**
     * Logger instance used for logging messages and errors.
     */
    private final org.slf4j.Logger log;

    /**
     * Constructor for BigFileHashHandler.
     *
     * @param userDefinedMaxLimit The maximum file size limit defined by the user. A
     *                            value of -1L indicates no
     *                            limit.
     */
    public BigFileHashHandler(final long userDefinedMaxLimit) {
        dataHash = new DataHash();
        this.USER_DEFINED_MAX_LIMIT = userDefinedMaxLimit;
        this.log = null;
    }

    /**
     * Calculates the SHA-256 hash of a given file, taking into account any size
     * limits.
     *
     * @param file The file for which to calculate the hash.
     * @return A string representing the SHA-256 hash or an error message if the
     *         file is too large.
     */
    @Override
    public final String calculateSha256Hash(File file) {

        if (USER_DEFINED_MAX_LIMIT != -1L && file.length() > USER_DEFINED_MAX_LIMIT) {
            return FILE_BIGGER_THAN_USER_DEFINED_MAX_LIMIT_MSG;
        }

        try {
            return SHA256_PRINT_PREFIX + bytesToHex(dataHash.getSHA256Big(file.getAbsolutePath()));
        } catch (NoSuchAlgorithmException e) {

            log.error("NoSuchAlgorithmException: {}", e.getMessage());
        } catch (IOException e) {

            log.error("IOException error occurred: {}", e.getMessage());
        }

        return ERROR;
    }

    /**
     * Calculates the SHA-3 hash of a given file, taking into account any size
     * limits.
     *
     * @param file The file for which to calculate the hash.
     * @return A string representing the SHA-3 hash or an error message if the file
     *         is too large.
     */
    @Override
    public final String calculateSha3Hash(File file) {
        if (USER_DEFINED_MAX_LIMIT != -1L && file.length() > USER_DEFINED_MAX_LIMIT) {
            return FILE_BIGGER_THAN_USER_DEFINED_MAX_LIMIT_MSG;
        }

        return SHA3_PRINT_PREFIX + bytesToHex(dataHash.getSHA3_256Big(file.getAbsolutePath()));
    }

    /**
     * Calculates the BLAKE2b hash of a given file, taking into account any size
     * limits.
     *
     * @param file The file for which to calculate the hash.
     * @return A string representing the BLAKE2b hash or an error message if the
     *         file is too large.
     */
    @Override
    public final String calculateBlake2bHash(File file) {
        if (USER_DEFINED_MAX_LIMIT != -1L && file.length() > USER_DEFINED_MAX_LIMIT) {
            return FILE_BIGGER_THAN_USER_DEFINED_MAX_LIMIT_MSG;
        }

        return BLAKE_2B_PRINT_PREFIX + bytesToHex(dataHash.getBLAKE2bBig(file.getAbsolutePath(), DIGEST_SIZE_BLAKE_2B));
    }

}
