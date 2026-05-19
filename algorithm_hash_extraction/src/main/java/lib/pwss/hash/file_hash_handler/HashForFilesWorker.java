package lib.pwss.hash.file_hash_handler;

import java.io.File;
import java.util.ConcurrentModificationException;
import java.util.concurrent.Callable;

/**
 * This final class represents a worker for computing hashes of files using
 * different hash algorithms.
 * It implements the Callable interface to allow the execution of these tasks in
 * parallel.
 */
final class HashForFilesWorker implements Callable<String> {

    /**
     * Instance of BigFileHashHandler which is responsible for calculating hash
     * values.
     */
    private final BigFileHashHandler hashInstance;
    /**
     * Enum representing the hashing algorithm to be used by this worker.
     */
    private final ParalellWorkerMethod algorithm;
    /**
     * The file that will be hashed by this worker instance.
     */
    private final File targetFile;

    /**
     * Constructs a new HashForFilesWorker with the specified file, hash handler,
     * and hashing algorithm to use.
     *
     * @param file               The file to be hashed.
     * @param bigFileHashHandler The BigFileHashHandler instance used for
     *                           calculating hashes.
     * @param algorithm          The hashing algorithm to use (SHA256, SHA3,
     *                           BLAKE2_B).
     */
    HashForFilesWorker(final File file, final BigFileHashHandler bigFileHashHandler,
            ParalellWorkerMethod algorithm) {

        this.targetFile = file;
        this.hashInstance = bigFileHashHandler;
        this.algorithm = algorithm;
    }

    /**
     * Executes the hash calculation for the target file using the specified hashing
     * algorithm.
     *
     * @return The calculated hash value as a string.
     * @throws Exception If an error occurs during the hash computation process.
     */
    @Override
    public String call() throws Exception {

        switch (algorithm) {
            case SHA256:
                return ExtractSHA256Hash(targetFile);
            case SHA3:
                return ExtractSHA3Hash(targetFile);
            case BLAKE2_B:
                return ExtractBlake2bHash(targetFile);
            default:
                throw new ConcurrentModificationException(
                        "An error occoured while executing an paralell hash extraction operation");
        }

    }

    /**
     * Extracts the SHA-256 hash of the specified file using the BigFileHashHandler
     * instance.
     *
     * @param file The file to be hashed with SHA-256 algorithm.
     * @return The computed SHA-256 hash as a string.
     */
    private final String ExtractSHA256Hash(final File file) {

        return hashInstance.calculateSha256Hash(file);
    }

    /**
     * Extracts the SHA3 hash of the specified file using the BigFileHashHandler
     * instance.
     *
     * @param file The file to be hashed with SHA3 algorithm.
     * @return The computed SHA3 hash as a string.
     */
    private final String ExtractSHA3Hash(final File file) {

        return hashInstance.calculateSha3Hash(file);
    }

    /**
     * Extracts the Blake2B hash of the specified file using the BigFileHashHandler
     * instance.
     *
     * @param file The file to be hashed with Blake2B algorithm.
     * @return The computed Blake2B hash as a string.
     */
    private final String ExtractBlake2bHash(final File file) {

        return hashInstance.calculateBlake2bHash(file);
    }

}
