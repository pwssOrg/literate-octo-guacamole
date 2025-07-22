package lib.pwss.hash.model;

import java.io.File;

 public record HashForFilesOutput(File file, String sha256, String sha3, String blake2) {
}
