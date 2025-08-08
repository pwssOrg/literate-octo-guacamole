package lib.pwss.hash;

import java.io.File;

import lib.pwss.hash.model.HashForFilesOutput;

/**
 * The FileHash interface provides methods to compute various cryptographic hash
 * values (SHA-256, SHA-3,
 * and BLAKE_2B) for a given file. It serves as an abstraction layer for
 * different hashing algorithms
 * that can be used to verify file integrity or uniqueness.
 */
public interface FileHash {

    /**
     * Calculates hashes of three different algorithms (SHA2,SHA3, BLAKE_2B) of a
     * file
     *
     * @param file to extract hash from
     * @return A wrapper object containing the file and the three resulting hash
     *         strings.
     */
    HashForFilesOutput GetAllHashes(File file);

    /**
     * Calculates the SHA256 Hash of a file
     *
     * @param file to extract hash from
     * @return A string containing the hexadecimal representation of the bytes in
     *         SHA2 Hash
     */
    String calculateSha256Hash(File file);

    /**
     * Calculates the SHA3 Hash of a file
     *
     * @param file to extract hash from
     * @return A string containing the hexadecimal representation of the bytes in
     *         SHA3 Hash
     */
    String calculateSha3Hash(File file);

    /**
     * Calculates the Blake2B Hash of a file
     *
     * @param file to extract hash from
     * @return A string containing the hexadecimal representation of the bytes in a
     *         Blake2B Hash
     */
    String calculateBlake2bHash(File file);

}