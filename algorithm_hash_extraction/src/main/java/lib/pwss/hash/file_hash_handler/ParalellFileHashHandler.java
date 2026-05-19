package lib.pwss.hash.file_hash_handler;

import java.io.File;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.LoggerFactory;

import lib.pwss.hash.ParalellFileHash;
import lib.pwss.hash.model.HashForFilesOutput;

/**
 * This class handles parallel calculation of hash values for files using
 * multiple hashing algorithms.
 */
public final class ParalellFileHashHandler implements ParalellFileHash {

    /**
     * The executor service used to manage a pool of threads.
     */
    private final ExecutorService executorService;
    /**
     * The size of the thread pool. Currently set to 3.
     */
    private final int THREAD_POOL_SIZE = 3;
    /**
     * Logger for logging debug and error information.
     */
    private final org.slf4j.Logger log;

    /**
     * Instance of BigFileHashHandler used to delegate actual hash calculation
     * tasks.
     */
    private final BigFileHashHandler hashInstance;

    /**
     * Constructs a new ParalellFileHashHandler with the given instance of
     * BigFileHashHandler.
     *
     * @param hashInstance The instance of BigFileHashHandler to use for calculating
     *                     hashes.
     */
    public ParalellFileHashHandler(BigFileHashHandler hashInstance) {
        executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        this.log = LoggerFactory.getLogger(ParalellFileHashHandler.class);
        this.hashInstance = hashInstance;
    }

    @Override
    public HashForFilesOutput GetAllHasheInParallalel(File file) {
        final Future<String> sha256Hash = executorService
                .submit(new HashForFilesWorker(file, hashInstance, ParalellWorkerMethod.SHA256));
        final Future<String> sha3Hash = executorService
                .submit(new HashForFilesWorker(file, hashInstance, ParalellWorkerMethod.SHA3));
        final Future<String> blake2bHash = executorService
                .submit(new HashForFilesWorker(file, hashInstance, ParalellWorkerMethod.BLAKE2_B));

        log.debug("Extracting hashes from a file using 3 different hash algorithms in paralell");

        while (!sha256Hash.isDone() && !sha3Hash.isDone() && !blake2bHash.isDone()) {
            // Busy-wait until all tasks are done
        }

        log.debug("Hash extraction process completed!");

        try {
            return new HashForFilesOutput(file, sha256Hash.get(), sha3Hash.get(), blake2bHash.get());
        } catch (InterruptedException e) {
            log.error("Interrupted while waiting for hash calculation to complete.", e);
            Thread.currentThread().interrupt(); // Restore interrupted status
            return null;
        } catch (ExecutionException e) {
            log.error("Error occurred during hash calculation.", e);
            return null;

        }
    }

    @Override
    public Future<String> calculateSha256HashFuture(File file) {
        log.debug("Starting a new thread for extrating a SHA256 Hash");
        return executorService
                .submit(new HashForFilesWorker(file, hashInstance, ParalellWorkerMethod.SHA256));
    }

    @Override
    public Future<String> calculateSha3HashFuture(File file) {
        log.debug("Starting a new thread for extrating a SHA3 Hash");
        return executorService
                .submit(new HashForFilesWorker(file, hashInstance, ParalellWorkerMethod.SHA3));
    }

    @Override
    public Future<String> calculateBlake2bHashFuture(File file) {
        log.debug("Starting a new thread for extrating a Blake2B Hash");
        return executorService
                .submit(new HashForFilesWorker(file, hashInstance, ParalellWorkerMethod.BLAKE2_B));
    }

    
    @Override
    public final void shutdownThreadPool() {
        log.debug("shutting down executorService...");
        executorService.shutdownNow();

        while (!executorService.isShutdown()) {
        }
        executorService.close();
        log.debug("executorService Closed!");
    }

}
