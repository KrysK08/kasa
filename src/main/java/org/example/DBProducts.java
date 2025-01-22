package org.example;

import java.sql.*;

public class DBProducts {
    private static final String URL = "jdbc:mariadb://localhost:3306/products";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    public DBProducts() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Połączono z bazą danych!");
        } catch (SQLException e) {
            System.err.println("Błąd połączenia z bazą danych: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Połączenie z bazą danych zamknięte.");
            }
        } catch (SQLException e) {
            System.err.println("Błąd zamykania połączenia: " + e.getMessage());
        }
    }
    public void getProducts() {
        String query = "SELECT * FROM produkty";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String IdProduktu = rs.getString("IdProduktu");
                String Nazwa = rs.getString("Nazwa");
                double Cena = rs.getDouble("Cena");

                System.out.println("ID: " + IdProduktu + ", Nazwa: " + Nazwa + ", Cena: " + Cena);
            }

        } catch (SQLException e) {
            System.err.println("Błąd podczas pobierania produktów: " + e.getMessage());
        }
    }




}
