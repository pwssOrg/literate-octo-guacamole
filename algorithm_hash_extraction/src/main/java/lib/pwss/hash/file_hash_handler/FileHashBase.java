package lib.pwss.hash.file_hash_handler;

import java.io.File;

import lib.pwss.hash.model.HashForFilesOutput;

/**
 * Abstract base class for file hash calculations.
 */
abstract class FileHashBase {

    /**
     * Format string for converting bytes to hexadecimal representation.
     */
    private final String FORMAT_BYTES_TO_HEX_STRING = "%02x";
    /**
     * Digest size for BLAKE2b algorithm in bytes.
     */
    protected final int DIGEST_SIZE_BLAKE_2B = 64;

    /**
     * Error string constant.
     */
    protected final String ERROR = "error";
    /**
     * Prefix for SHA-256 hash output.
     */
    protected final String SHA256_PRINT_PREFIX = "SHA-256: ";
    /**
     * Prefix for SHA3 hash output.
     */
    protected final String SHA3_PRINT_PREFIX = "SHA-3 (256): ";
    /**
     * Prefix for BLAKE2b hash output.
     */
    protected final String BLAKE_2B_PRINT_PREFIX = "BLAKE2b: ";

    /**
     * Convert a byte sequence to its hexadecimal string representation.
     * This function takes a bytes object and returns a string containing two
     * hexadecimal digits for each byte in the input, using lowercase letters.
     *
     * @param bytes (bytes): The byte sequence to be converted to hexadecimal.
     * @return str: A string containing the hexadecimal representation of the bytes.
     */
    protected final String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format(FORMAT_BYTES_TO_HEX_STRING, b));
        }
        return sb.toString();
    }

    /**
     * Calculates the SHA-256 hash of a file.
     *
     * @param file The file to calculate the hash for.
     * @return The SHA-256 hash as a hexadecimal string.
     */
    public abstract String calculateSha256Hash(File file);

    /**
     * Calculates the SHA3 hash of a file.
     *
     * @param file The file to calculate the hash for.
     * @return The SHA3 hash as a hexadecimal string.
     */
    public abstract String calculateSha3Hash(File file);

    /**
     * Calculates the BLAKE2b hash of a file.
     *
     * @param file The file to calculate the hash for.
     * @return The BLAKE2b hash as a hexadecimal string.
     */
    public abstract String calculateBlake2bHash(File file);

    /**
     * Calculates hashes of three different algorithms (SHA2,SHA3, BLAKE_2B) of a
     * file
     *
     * @param file The file to extract hash from
     * @return A wrapper object containing the file and the three resulting hash
     *         strings
     */
    public final HashForFilesOutput GetAllHashes(File file) {

        final String sha256HashString;
        final String sha3HashString;
        final String blake2bHashString;

        sha256HashString = calculateSha256Hash(file);
        sha3HashString = calculateSha3Hash(file);
        blake2bHashString = calculateBlake2bHash(file);
        return new HashForFilesOutput(file, sha256HashString, sha3HashString, blake2bHashString);
    }

}
