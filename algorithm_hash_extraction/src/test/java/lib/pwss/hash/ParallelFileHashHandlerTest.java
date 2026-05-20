package lib.pwss.hash;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import lib.pwss.hash.file_hash_handler.BigFileHashHandler;
import lib.pwss.hash.file_hash_handler.parallel.ParallelFileHashHandler;
import lib.pwss.hash.model.HashForFilesOutput;

@TestInstance(Lifecycle.PER_CLASS)
public class ParallelFileHashHandlerTest {

    BigFileHashHandler coreHashExtractorInstance = new BigFileHashHandler(-1L);

    private File file;
    private final ParallelFileHashHandler parallelFileHashHandler = new ParallelFileHashHandler(
            coreHashExtractorInstance);

    @BeforeEach
    void init() throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = "hi.txt";
        java.net.URL resourceUrl = classLoader.getResource(resourcePath);
        file = Paths.get(resourceUrl.toURI()).toFile();
    }

    @AfterAll
    void cleanup() {

        // Good to use in case of non clean exits of a client app
        parallelFileHashHandler.shutdownThreadPool();
    }

    @Test
    void extractingHashesInParallelMatchesSequential() {

        HashForFilesOutput parallelOutput = parallelFileHashHandler.GetAllHashesInParallel(file);

        HashForFilesOutput sequentialOutput = coreHashExtractorInstance.GetAllHashes(file);

        Assertions.assertEquals(parallelOutput, sequentialOutput);

    }

    @Test
    void shouldCalculateSha256InParallel() throws URISyntaxException, InterruptedException, ExecutionException {

        final String expected = "SHA-256: b952374f7966b97e7ac18228ff7b409a81bf2e7f1094fb557183365a721196dd";

        Future<String> sha256Future = parallelFileHashHandler.calculateSha256HashFuture(file);
        final String actual = sha256Future.get();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateSha3InParallel() throws URISyntaxException, InterruptedException, ExecutionException {

        final String expected = "SHA-3 (256): 326d8a7fbfeb0e2a555d7e229ea1c5c9ed6a6a4bf716c62da6e9c173920d205c";
        Future<String> sha3Future = parallelFileHashHandler.calculateSha3HashFuture(file);
        final String actual = sha3Future.get();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCalculateBlake2bInParallel() throws URISyntaxException, InterruptedException, ExecutionException {

        // Digest size 512 (64*8)
        final String expected = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ea3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";
        Future<String> blake2BFuture = parallelFileHashHandler.calculateBlake2bHashFuture(file);
        final String actual = blake2BFuture.get();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldCompleteSha256HashWithin2Seconds(){

    assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
    parallelFileHashHandler.calculateSha256HashFuture(file).get();
});
    }

    @Test
void shouldHandleMultipleParallelHashRequests() {

    assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {

        List<Future<String>> futures = List.of(
                parallelFileHashHandler.calculateSha256HashFuture(file),
                parallelFileHashHandler.calculateSha3HashFuture(file),
                parallelFileHashHandler.calculateBlake2bHashFuture(file)
        );

        for (Future<String> future : futures) {
            assertNotNull(future.get());
        }
    });
}

}
