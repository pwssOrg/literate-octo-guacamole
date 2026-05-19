package lib.pwss.hash;

import java.io.File;
import java.util.concurrent.Future;

import lib.pwss.hash.model.HashForFilesOutput;

/**
 * Interface for parallel file hash calculation.
 *
 * This interface defines methods to calculate SHA256, SHA3, and Blake2B hashes
 * for a given file
 * in parallel. It provides asynchronous computation using Future objects and
 * supports shutting down
 * the thread pool after all tasks are completed.
 */
public interface ParalellFileHash {

    /**
     * Calculates the SHA256 hash for a given file and returns a
     * Future representing the pending
     * result.
     *
     * @param file The file to be hashed.
     * @return A Future containing the computed SHA256 hash.
     */
    Future<String> calculateSha256HashFuture(File file);

    /**
     * Calculates the SHA3 hash for a given file and returns a
     * Future representing the pending
     * result.
     *
     * @param file The file to be hashed.
     * @return A Future containing the computed SHA3 hash.
     */
    Future<String> calculateSha3HashFuture(File file);

    /**
     * Calculates the Blake2B hash for a given file and returns a
     * Future representing the pending
     * result.
     *
     * @param file The file to be hashed.
     * @return A Future containing the computed Blake2B hash.
     */
    Future<String> calculateBlake2bHashFuture(File file);

    /**
     * Calculates the hashes for a given file in parallel using SHA256, SHA3, and
     * Blake2B algorithms.
     *
     * @param file The file to be hashed.
     * @return An instance of HashForFilesOutput containing the calculated hashes.
     */
    HashForFilesOutput GetAllHasheInParallalel(File file);

    /**
     * Shuts down the thread pool and ensures all tasks are completed.
     */
    void shutdownThreadPool();

}
