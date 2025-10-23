package lib.pwss.hash;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lib.pwss.hash.compare.util.HashCompareUtil;
import lib.pwss.hash.file_hash_handler.BigFileHashHandler;
import lib.pwss.hash.file_hash_handler.FileHashHandler;
import lib.pwss.hash.model.HashForFilesOutput;

public class FileHashTest {

    private File file;
    private final FileHash fileHashHandler = new FileHashHandler();
    private final FileHash bigFileHashHandler = new BigFileHashHandler(-1L);

    @BeforeEach
     void init() throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = "hi.txt";

        // Load the resource using the ClassLoader
        java.net.URL resourceUrl = classLoader.getResource(resourcePath);

        file = Paths.get(resourceUrl.toURI()).toFile();
    }

    @Test
     void testGetWrapperWith3HashesFromAFileImpl2() {
        final String expected = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ea3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";
        HashForFilesOutput wrapperInstance = bigFileHashHandler.GetAllHashes(file);
        final String actual = wrapperInstance.blake2();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testCalculateSha256HashImpl2() {

        final String expected = "SHA-256: b952374f7966b97e7ac18228ff7b409a81bf2e7f1094fb557183365a721196dd";
        final String actual = bigFileHashHandler.calculateSha256Hash(file);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testCalculateSha3HashImpl2() {

        final String expected = "SHA-3 (256): 326d8a7fbfeb0e2a555d7e229ea1c5c9ed6a6a4bf716c62da6e9c173920d205c";
        final String actual = bigFileHashHandler.calculateSha3Hash(file);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testCalculateBlake2bHashImpl2() {

        // Digest size 512 (64*8)
        final String expected = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ea3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";

        final String actual = bigFileHashHandler.calculateBlake2bHash(file);
        Assertions.assertEquals(expected, actual);
    }

    @Test
     void SHA256Test() throws URISyntaxException {

        final String expected = "SHA-256: b952374f7966b97e7ac18228ff7b409a81bf2e7f1094fb557183365a721196dd";
        final String actual = fileHashHandler.calculateSha256Hash(file);
        Assertions.assertEquals(expected, actual);
    }

    @Test
     void SHA3Test() throws URISyntaxException {

        final String expected = "SHA-3 (256): 326d8a7fbfeb0e2a555d7e229ea1c5c9ed6a6a4bf716c62da6e9c173920d205c";
        final String actual = fileHashHandler.calculateSha3Hash(file);
        Assertions.assertEquals(expected, actual);
    }

    @Test
     void Blake2bTest() throws URISyntaxException {

        // Digest size 512 (64*8)
        final String expected = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ea3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";

        final String actual = fileHashHandler.calculateBlake2bHash(file);
        Assertions.assertEquals(expected, actual);
    }

    @Test
     void GetWrapperWith3HashesFromAFileTest() {
        final String expected = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ea3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";
        HashForFilesOutput wrapperInstance = fileHashHandler.GetAllHashes(file);
        final String actual = wrapperInstance.blake2();
        Assertions.assertEquals(expected, actual);
    }

    @Test
     void testCompareHashesXOR_Positive() {
        final String hash1 = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ea3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";
        final String hash2 = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ea3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";
        final boolean result = HashCompareUtil.compareHashesXor(hash1, hash2);
        Assertions.assertTrue(result);
    }

    @Test
     void testCompareHashesXOR_Negative() {
        final String hash1 = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ea3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";
        final String hash2 = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ef3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";
        final boolean result = HashCompareUtil.compareHashesXor(hash1, hash2);
        Assertions.assertFalse(result);
    }

    @Test
     void testCompareHashesJavaEquals_Positive() {
        final String hash1 = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ea3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";
        final String hash2 = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ea3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";
        boolean result = HashCompareUtil.compareHashesJavaEquals(hash1, hash2);
        Assertions.assertTrue(result);
    }

    @Test
     void testCompareHashesJavaEquals_Negative() {
        final String hash1 = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ea3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";
        final String hash2 = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ef3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";
        final boolean result = HashCompareUtil.compareHashesJavaEquals(hash1, hash2);
        Assertions.assertFalse(result);
    }

    @Test
     void testCompareHashesCombinedMethod_Positive() {
        final String hash1 = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ea3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";
        final String hash2 = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ea3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";
        final boolean result = HashCompareUtil.compareUsingXorAndJavaEquals(hash1, hash2);
        Assertions.assertTrue(result);
    }

    @Test
     void testCompareHashesCombinedMethod_Negative() {
        final String hash1 = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ea3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";
        final String hash2 = "BLAKE2b: 868f1b00d1e1045b03a539792f9d0dcf9d39dc9e54ccf378ecc7d65a35ef3bb256f1a2b055d1778ff519ded0d59ca341792fdaca96a87634d14d68093b5f0833";
        final boolean result = HashCompareUtil.compareUsingXorAndJavaEquals(hash1, hash2);
        Assertions.assertFalse(result);
    }
}
