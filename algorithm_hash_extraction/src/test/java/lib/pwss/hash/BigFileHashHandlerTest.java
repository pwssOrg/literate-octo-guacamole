package lib.pwss.hash;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lib.pwss.hash.file_hash_handler.BigFileHashHandler;

public class BigFileHashHandlerTest {

    private File file;
    private final BigFileHashHandler bigFileHashHandler = new BigFileHashHandler(-1L);

    @BeforeEach
    void init() throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        String resourcePath = "hi.txt";

        // Load the resource using the ClassLoader
        java.net.URL resourceUrl = classLoader.getResource(resourcePath);

        file = Paths.get(resourceUrl.toURI()).toFile();
    }

    @Test
    void testFileSizeMaxLimit() {
        final String EXPECTED = "File Size is bigger than the User Defined Max Limit Size";

        bigFileHashHandler.setUserDefinedMaxLimit(1L);
        final String ACTUAL = bigFileHashHandler.calculateSha256Hash(file);
        Assertions.assertEquals(EXPECTED, ACTUAL);

    }

}
