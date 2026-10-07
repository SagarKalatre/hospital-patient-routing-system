package com.kiranacademy.hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_routing_db";
    private static final String username = "root";
    private static final String password = "YOUR_MYSQL_PASSWORD";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, username, password);
    }
}
