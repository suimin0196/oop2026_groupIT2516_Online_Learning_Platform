package com.company.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {
    // Database credentials
    private final String url = "jdbc:postgresql://localhost:5432/online_learning";
    private final String user = "DB_User";
    private final String password = System.getenv("DB_PASSWORD");

    private Connection connection;
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
