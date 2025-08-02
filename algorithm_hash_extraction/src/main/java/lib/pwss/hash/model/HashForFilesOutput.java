package lib.pwss.hash.model;

import java.io.File;
/**
 * A record representing the output of a hash operation for files.
 * Contains the original file along with its SHA-256, SHA-3 (256), and BLAKE2b hashes.
 *
 * @param file The original file that was hashed.
 * @param sha256 The SHA-256 hash value of the file.
 * @param sha3 The SHA-3 (256) hash value of the file.
 * @param blake2 The BLAKE2b hash value of the file.
 */
 public record HashForFilesOutput(File file, String sha256, String sha3, String blake2) {
}
