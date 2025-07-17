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

### Maven 
```xml
<dependency>
  <groupId>lib.pwss.cryptographic_algorithm</groupId>
  <artifactId>cryptographic_algorithm</artifactId>
  <version>1.4</version>
</dependency>
```
<sub>(PWSS Private Github Package)</sub>
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

# Data Structures


In addition to its capabilities in extracting hashes, this library provides data structures that facilitate easy
switching between cryptographic algorithms and hash functions. 


## Algorithm Switching

This feature protects against future quantum computing threats by enabling seamless transitions between different
hash or cryptographic algorithms.

### Enable Algorithm Switching

To enable algorithm switching, you need to call `initAlgorithmSwitchingFunction` in an implementing class. Here’s
how you can do it:

```java
import lib.pwss.cryptographic_algorithm.algorithm_switch.AlgorithmSwitchingInitializer;
import lib.pwss.cryptographic_algorithm.algorithm_switch.EncryptionAlgorithm;

public class MainApp {
    public static void main(String[] args) {
        AlgorithmSwitchingInitializer initializer = new AlgorithmSwitchingInitializer();
        initializer.initAlgorithmSwitchingFunction(); // This only needs to be done once : ) 

           }
}
```

### Implement Mock Algorithms in the MainApp Class

Here's a complete example including mock implementations for two algorithms:

```java
import lib.pwss.cryptographic_algorithm.algorithm_switch.AlgorithmSwitchingInitializer;
import lib.pwss.cryptographic_algorithm.algorithm_switch.EncryptionAlgorithm;

public class MainApp {
    public static void main(String[] args) {
        AlgorithmSwitchingInitializer initializer = new AlgorithmSwitchingInitializer();
        initializer.initAlgorithmSwitchingFunction();

        // Example of getting algorithm2 using EncryptionAlgorithmChoice
        EncryptionAlgorithm encryptionAlgorithm = new EncryptionAlgorithm();
        String algorithm2 = encryptionAlgorithm.getEncryptionAlgorithmChoice().ALGORITHM_2;
        System.out.println("Selected Algorithm 2: " + algorithm2);

        // Implement your own choice logic based on the retrieved algorithms
        chooseAlgorithm(algorithm2);
    }

    private static void chooseAlgorithm(String algorithm) {
        switch (algorithm.toUpperCase()) {
            case "RSA":
                System.out.println("Using RSA algorithm.");
                performRSAEncryption();
                break;
            case "KYBER":
                System.out.println("Using Kyber algorithm.");
                performKyberEncryption();
                break;
            default:
                System.err.println("Unknown algorithm: " + algorithm);
                break;
        }
    }

    private static void performRSAEncryption() {
        // Mock implementation of RSA encryption
        System.out.println("RSA Encryption is being performed...");
    }

    private static void performKyberEncryption() {
        // Mock implementation of Kyber encryption
        System.out.println("Kyber Encryption is being performed...");
    }
}
```
### Key Points

1. **Add the dependency to your Maven project** by updating `pom.xml`.
2. **Initialize algorithm switching** with `initAlgorithmSwitchingFunction()`.
3. **Implement logic** to choose and use the selected algorithm.

### Guide for Changing Algorithm Name in switch_algorithm.properties
You can change the algorithm name by editing the `switch_algorithm.properties` file located in the folder of your project that has the pom file. This file maps numeric values
to specific algorithms:

```
# Thu Jul 17 06:14:44 CEST 2025
1=RSA
2=Kyber
3=Blake_2B
```

To change an algorithm, simply update the value associated with the corresponding key. For example, if you want to
change algorithm 2 from "Kyber" to "NewAlgorithm":

```
# Thu Jul 17 06:14:44 CEST 2025
1=RSA
2=NewAlgorithm
3=Blake_2B
```



