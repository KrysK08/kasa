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
        } catch (SQLException e) {
            System.err.println("Błąd połączenia z bazą danych: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public DBBank FindCardNumber(String cardNumber) {
        String query = "SELECT * FROM karty WHERE NumerKarty = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cardNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new CardInfo(rs.getString("NumerKarty"),
                        rs.getDouble("LimitKarty"),
                        rs.getDate("TerminWaznosci"),
                        rs.getString("CVV"));
            }
        } catch (SQLException e) {
            System.err.println("Błąd przy wyszukiwaniu karty: " + e.getMessage());
        }
        return null;
    }

}
