# Literate Octo Guacamole

[![Makefile CI](https://github.com/pwssOrg/literate-octo-guacamole/actions/workflows/build.yml/badge.svg)](https://github.com/pwssOrg/literate-octo-guacamole/actions/workflows/build.yml)
[![SCA Scan - Literate Octo Guacamole](https://github.com/pwssOrg/literate-octo-guacamole/actions/workflows/snyk-scan.yml/badge.svg)](https://github.com/pwssOrg/literate-octo-guacamole/actions/workflows/snyk-scan.yml)

The **Literate Octo Guacamole** (cryptographic_algorithm) library is a part of the PWSS Library family. This Java-based library focuses on
extracting cryptographic hash values from `File` objects using various algorithms, providing essential tools for
future-proofing cryptographic applications against quantum computing threats.

## Features

- Extract hashes from Java File Objects
- Supports three strong hash algorithms:
  - SHA-256
  - SHA3 (256-bit)
  - BLAKE_2B (512-bit)
- Converts byte representations to hexadecimal strings using `bytesToHex`
- Compares hash values for equality with XOR and Java equals operations
- Provides data structures for supporting algorithm switching in cryptographic applications

## Installation

To use the `cryptographic_algorithm` library, you need to include it as a dependency in your project. You can find
it on the private GitHub package repository of PWSS. A GitHub packages token is required to access it. Contact
PWSS representatives if you're interested.

### Maven (PWSS Private Github Package)
```xml
<dependency>
  <groupId>lib.pwss.cryptographic_algorithm</groupId>
  <artifactId>cryptographic_algorithm</artifactId>
  <version>1.3</version>
</dependency>
```

## Usage

Here's a basic example to get you started with using the `pwss.cryptographic_algorithm` library for calculating
hash strings:

```java
import lib.pwss.cryptographic_algorithm.hash.FileHashHandler;

public class Example {
    public static void main(String[] args) throws Exception {
        final File file = new File("path/to/your/file");

        
        final FileHashHandler fileHashHandler = new FileHashHandler();

        // Generate SHA-256 hash
        final String sha256Hash = fileHashHandler.calculateSha256Hash(file);
        System.out.println(sha256Hash);

        // Generate SHA3 (256-bit) hash
        final String sha3Hash = fileHashHandler.calculateSha3Hash(file);
        System.out.println(sha3Hash);

        // Generate BLAKE_2B (512-bit) hash
        final String blake2bHash = fileHashHandler.calculateBlake2bHash(file);
        System.out.println(blake2bHash);

       
    }
}
```
### HashComparison
```java
import lib.pwss.cryptographic_algorithm.hash.compare.util.HashCompareUtil;

public class HashCompareExample {
    public static void main(String[] args) throws Exception {
        final String hash1 = "somehash"
        final String hash2 = "somehash"

        // Compare hashes using XOR
        final boolean result = HashCompareUtil.compareHashesXor(hash1, hash2);

        // Compare hashes using equals
        final boolean result = HashCompareUtil.compareHashesJavaEquals(hash1, hash2);
       
    }
}
```

## Data Structures

The library offers various data structures to facilitate the implementation of algorithm-switching capabilities
within cryptographic applications. These features help protect against future quantum computing threats by
allowing easy transitions between hash algorithms.

### Algorithm Switching

When implementing an encryption algorithm like RSA in a project where you want to enable algorithm switching,
follow these steps:

1. **Extend the AbstractEncryptionAlgorithm Class**: The RSA class (with its business logic) will extend the
`AbstractEncryptionAlgorithm` class.

2. **Wrap it in Conditional Statements**: Use conditional statements (e.g., if-else) to determine which encryption
algorithm to use based on specific criteria or configurations (See image).

<img width="1026" height="699" alt="image" src="https://github.com/user-attachments/assets/c5a6a8d5-9b9a-4f57-893a-77868e63d66b" />


The benefit of this setup is that you can change cryptographic algorithms by simply modifying a text value in a
`.properties` file.

### Important Note :heavy_exclamation_mark:

Due to previous requirements from past projects, the algorithm switching part of this library was redacted and
only serves as a guideline for algorithm switching. When version 1.4 is released, the policy file will be
reinstated, and the example above will work with a policy file again.



