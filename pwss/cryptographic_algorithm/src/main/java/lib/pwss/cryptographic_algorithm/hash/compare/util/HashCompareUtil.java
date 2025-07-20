package lib.pwss.cryptographic_algorithm.hash.compare.util;

/**
 * Utility class for comparing hashes.
 */
public final class HashCompareUtil {


    // Private constructor to prevent instantiation
    private HashCompareUtil() {
        throw new UnsupportedOperationException("Utility class. Do not instantiate.");
    }

    /**
     * Compares two hashes using an XOR operation on each corresponding character.
     * This method checks if the provided old hash and new hash are identical by
     * performing a bitwise XOR comparison on each corresponding position. If any
     * position differs, it returns false; otherwise, it returns true.
     *
     * <p>This is particularly useful for simple equality checks of fixed-length
     * string representations such as checksums or cryptographic hashes where an
     * exact match is required.</p>
     *
     * @param oldHash the first hash to compare (must not be null)
     * @param newHash the second hash to compare (must not be null and should have the same length as oldHash)
     * @return true if the hashes are identical, false otherwise
     */
    public static boolean compareHashesXor(String oldHash, String newHash) {
        if (oldHash == null || newHash == null || oldHash.length() != newHash.length()) {
            return false;
        }
        for (int i = 0; i < oldHash.length(); i++) {
            // XOR operation on each character
            if ((oldHash.charAt(i) ^ newHash.charAt(i)) != 0) {
                return false;
            }
        }
        return true;
    }

           /**
     * Compares two hashes using an XOR operation on each corresponding byte.
     * This method checks if the provided old hash and new hash are identical by
     * performing a bitwise XOR comparison on each corresponding position. If any
     * position differs, it returns false; otherwise, it returns true.
     *
     * <p>This is particularly useful for simple equality checks of fixed-length
     * string representations such as checksums or cryptographic hashes where an
     * exact match is required.</p>
     *
     * @param oldHash the first hash to compare (must not be null)
     * @param newHash the second hash to compare (must not be null and should have the same length as oldHash)
     * @return true if the hashes are identical, false otherwise
     */
        public static boolean compareHashesXor(byte[] oldHash, byte[] newHash) {
        if (oldHash == null || newHash == null || oldHash.length != newHash.length) {
            return false;
        }
        for (int i = 0; i < oldHash.length; i++) {
            // XOR operation on each byte
            if ((oldHash[i] ^ newHash[i]) != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Compares two hashes using Java's built-in {@code String.equals()} method.
     * This method checks if the provided old hash and new hash are identical by
     * leveraging the standard equality comparison in Java. It returns true only
     * if both hashes are not null and exactly match each other, otherwise it returns false.
     *
     * @param oldHash the first hash to compare (must not be null)
     * @param newHash the second hash to compare (must not be null)
     * @return true if the hashes are identical, false otherwise
     */
    public static boolean compareHashesJavaEquals(String oldHash, String newHash) {
        if (oldHash == null || newHash == null) {
            return false;
        }
        return oldHash.equals(newHash);
    }

    /**
     * Compares two hashes using both XOR operation and Java's built-in {@code String.equals()} method.
     * This method performs a bitwise XOR comparison on each corresponding character of the hashes,
     * then uses Java's standard equality check to ensure the hashes are identical in both ways.
     *
     * <p>This combination ensures that not only do the string representations match exactly, but
     * they also contain identical character codes at every position.</p>
     *
     * @param oldHash the first hash to compare (must not be null)
     * @param newHash the second hash to compare (must not be null and should have the same length as oldHash)
     * @return true if both comparisons are successful, false otherwise
     */
    public static boolean compareUsingXorAndJavaEquals(String oldHash, String newHash) {

        return compareHashesXor(oldHash, newHash) && compareHashesJavaEquals(oldHash, newHash);

    }

}
