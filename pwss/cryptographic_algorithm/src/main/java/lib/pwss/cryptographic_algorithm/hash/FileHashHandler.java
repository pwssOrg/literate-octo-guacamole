package lib.pwss.cryptographic_algorithm.hash;

import lib.pwss.cryptographic_algorithm.hash.algorithm.DataHash;
import lib.pwss.cryptographic_algorithm.hash.model.HashForFilesOutput;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;

public final class FileHashHandler {

    private final DataHash dataHash;

    private final int DIGEST_SIZE = 256;

    // String Constant

    private final String FORMAT_BYTES_TO_HEX_STRING = "%02x";
    private final String ERROR = "error";
    private final String SHA256_PRINT_PREFIX = "SHA-256: ";
    private final String SHA3_PRINT_PREFIX = "SHA-3 (256): ";
    private final String BLAKE_2B_PRINT_PREFIX = "BLAKE2b: ";

    public FileHashHandler() {
        dataHash = new DataHash();
    }

    public final HashForFilesOutput GetAllHashes(File file) {


        final String sha256HashString;
        final String sha3HashString;
        final String blake2bHashString;

        sha256HashString = calculateSha256Hash(file);
        sha3HashString = calculateSha3Hash(file);
        blake2bHashString = calculateBlake2bHash(file);
        return new HashForFilesOutput(file, sha256HashString, sha3HashString, blake2bHashString);

    }

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

    public final String calculateBlake2bHash(File file) {
        byte[] blake2bHash;
        try {
            blake2bHash = dataHash.getBLAKE2b(Files.readAllBytes(file.toPath()), DIGEST_SIZE);
            return BLAKE_2B_PRINT_PREFIX + bytesToHex(blake2bHash);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ERROR;
    }

    private final String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format(FORMAT_BYTES_TO_HEX_STRING, b));
        }
        return sb.toString();
    }


}
