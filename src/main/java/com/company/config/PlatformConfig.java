package com.company.config;

// Singleton class for PlatformConfig
// Ensures only one instance of PlatformConfig exists throughout the application
public class PlatformConfig {
    private static PlatformConfig instance;
    private String platformName = "Online Learning Platform";
    private String version = "1.0";

    // Private constructor to prevent instantiation
    private PlatformConfig() {
    }

    // Thread-safe singleton getInstance method
    public static synchronized PlatformConfig getInstance() {
        if (instance == null) {
            instance = new PlatformConfig();
        }
        return instance;
    }

    // Getters for platform configuration
    public String getPlatformName() {
        return platformName;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "PlatformConfig{" +
                "platformName='" + platformName + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}