package org.example;

import java.sql.*;

class DBProducts {
    private static final String URL = "jdbc:mariadb://localhost:3306/products";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    public DBProducts() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Błąd połączenia z bazą danych: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Product findProductByBarcode(String barcode) {
        String query = "SELECT Nazwa, IdProduktu, Cena FROM produkty WHERE IdProduktu = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, barcode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getString("Nazwa"),
                        rs.getString("IdProduktu"),
                        rs.getDouble("Cena"),
                        0
                );
            }
        } catch (SQLException e) {
            System.err.println("Błąd przy wyszukiwaniu produktu: " + e.getMessage());
        }
        return null;
    }

}