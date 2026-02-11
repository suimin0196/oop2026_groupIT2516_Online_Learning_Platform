package com.company.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class PostgresDB implements IDB {
    // Database credentials
    private final String url = "jdbc:postgresql://localhost:5432/online_learning";
    private final String user = "postgres";
    private final String password = loadPassword();

    private Connection connection;

    // Load password from config.properties file
    private static String loadPassword() {
        Properties props = new Properties();
        try (InputStream input = PostgresDB.class.getClassLoader().getResourceAsStream("com/company/config.properties")) {
            if (input == null) {
                throw new RuntimeException("Config file config.properties not found");
            }
            props.load(input);
            return props.getProperty("DB_PASSWORD");
        } catch (IOException e) {
            throw new RuntimeException("Error loading config.properties", e);
        }
    }

    // Create and return connection
    @Override
    public Connection getConnection() {
        try { 
            if (connection != null && !connection.isClosed()) {
                return connection;
            }

            // Here we load the driver’s class file into memory at the runtime
            try { Class.forName("org.postgresql.Driver");}
            catch (ClassNotFoundException e) {
                throw new RuntimeException("PostgreSQL JDBC Driver not found", e);
            }
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }
    @Override
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close database connection", e);
            }
        }
    }
}
