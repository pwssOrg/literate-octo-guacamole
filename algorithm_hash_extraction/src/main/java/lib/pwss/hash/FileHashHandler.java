package lib.pwss.hash;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;

import lib.pwss.hash.model.HashForFilesOutput;

/**
 * The FileHashHandler class provides methods for calculating hash values of a file using various hashing
algorithms.
 * This includes SHA-256, SHA-3 (256), and BLAKE2b. It returns the calculated hash values in a structured format.
 */
public final class FileHashHandler {

    private final DataHash dataHash;

    private final int DIGEST_SIZE_BLAKE_2B = 64;

    // String Constant

    private final String FORMAT_BYTES_TO_HEX_STRING = "%02x";
    private final String ERROR = "error";
    private final String SHA256_PRINT_PREFIX = "SHA-256: ";
    private final String SHA3_PRINT_PREFIX = "SHA-3 (256): ";
    private final String BLAKE_2B_PRINT_PREFIX = "BLAKE2b: ";

    /**
     * Public Constructor
     */
    public FileHashHandler() {
        dataHash = new DataHash();
    }

    /**
     * Calculates hashes of three different algorithms (SHA2,SHA3, BLAKE_2B) of a file
     *
     * @param file to extract hash from
     * @return A wrapper object containing the file and the three resulting hash strings.
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

    /**
     * Calculates the SHA256 Hash of a file
     *
     * @param file to extract hash from
     * @return A string containing the hexadecimal representation of the bytes in SHA2 Hash
     */
    public final String calculateSha256Hash(File file) {
        byte[] sha256Hash;
        try {
            sha256Hash = dataHash.getSHA256(Files.readAllBytes(file.toPath()));
            return SHA256_PRINT_PREFIX + bytesToHex(sha256Hash);
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    /**
     * Calculates the SHA3 Hash of a file
     *
     * @param file to extract hash from
     * @return A string containing the hexadecimal representation of the bytes in SHA3 Hash
     */
    public final String calculateSha3Hash(File file) {
        byte[] sha3Hash;
        try {
            sha3Hash = dataHash.getSHA3_256(Files.readAllBytes(file.toPath()));
            return SHA3_PRINT_PREFIX + bytesToHex(sha3Hash);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    /**
     * Calculates the Blake2B Hash of a file
     *
     * @param file to extract hash from
     * @return A string containing the hexadecimal representation of the bytes in a Blake2B Hash
     */
    public final String calculateBlake2bHash(File file) {
        byte[] blake2bHash;
        try {
            blake2bHash = dataHash.getBLAKE2b(Files.readAllBytes(file.toPath()), DIGEST_SIZE_BLAKE_2B);
            return BLAKE_2B_PRINT_PREFIX + bytesToHex(blake2bHash);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    /**
     * Convert a byte sequence to its hexadecimal string representation.
     * This function takes a bytes object and returns a string containing two
     * hexadecimal digits for each byte in the input, using lowercase letters.
     *
     * @param data (bytes): The byte sequence to be converted to hexadecimal.
     * @return str: A string containing the hexadecimal representation of the bytes.
     */
    private final String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format(FORMAT_BYTES_TO_HEX_STRING, b));
        }
        return sb.toString();
    }


}
