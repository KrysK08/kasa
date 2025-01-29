package org.example;

import java.sql.*;

public class DBBank {
    private static final String URL = "jdbc:mariadb://localhost:3306/bank";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    public DBBank() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Połączono z bazą danych!");
        } catch (SQLException e) {
            System.err.println("Błąd połączenia z bazą danych: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
