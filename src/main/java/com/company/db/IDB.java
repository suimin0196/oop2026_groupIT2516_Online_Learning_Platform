package com.company.db;

import java.sql.Connection;

public interface IDB {
    // Returns a database connection
    Connection getConnection();
    void closeConnection(Connection connection);
}
