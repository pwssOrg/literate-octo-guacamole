package lib.pwss.algorithm_switch;

/**
 * This final class contains constant values used in configuration for algorithm
 * switching.
 * These constants are marked as protected and static to allow access within the
 * package
 * while maintaining encapsulation.
 * 
 */
final class ConfigConstant {
    /**
     * The name of the configuration file that holds various settings for algorithm
     * switching.
     * The file is expected to be named "switch_algorithm.properties".
     */
    protected final static String CONFIG_FILE_NAME = "switch_algorithm.properties";
    /**
     * A constant representing the value "1". This could be used as a key or a value
     * in configuration properties to denote a specific algorithm version or option.
     */
    protected final static String ONE = "1";
    /**
     * A constant representing the value "2". Similar to {@link #ONE}, this might
     * represent
     * another version of an algorithm, or a different configuration option.
     */
    protected final static String TWO = "2";
    /**
     * A constant representing the value "3". This follows the same pattern as
     * {@link #ONE}
     * and {@link #TWO} for different configurations or algorithms.
     */
    protected final static String THREE = "3";
    /**
     * A constant string used to denote a production-ready configuration or
     * algorithm setting.
     * This is intended for marking settings that should be used in production
     * environments.
     */
    protected final static String FOUR = "USE_FOR_PROD";
}
