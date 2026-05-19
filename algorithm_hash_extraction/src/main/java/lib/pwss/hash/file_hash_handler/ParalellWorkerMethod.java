package lib.pwss.hash.file_hash_handler;

/**
 * Enum representing different parallel hash calculation methods.
 * Each value in this enumeration corresponds to a specific hashing algorithm
 * that can be used for calculating file hashes in parallel.
 */
enum ParalellWorkerMethod {
    /**
     * The SHA-256 hashing algorithm. This is a cryptographic hash function
     * that takes a string of any length as input and produces a fixed 256-bit
     * output.
     */
    SHA256,

    /**
     * The SHA3 hashing algorithm. This is the latest member of the Secure Hash
     * Algorithm family,
     * and it provides similar security to SHA-2 but with different internal
     * structures.
     */
    SHA3,

    /**
     * The Blake2B hashing algorithm. This is a cryptographic hash function that is
     * designed
     * to be efficient in terms of both speed and security, particularly for large
     * amounts of data.
     */
    BLAKE2_B
}
