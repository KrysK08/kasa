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
    public void DecreaseBalance(Double cena, Integer id){
        String query2 = "UPDATE konta SET Saldo TO Saldo - ? WHERE IdProduktu = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query2)){
            stmt.setDouble(1, cena);
            stmt.setInt(2, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Nie znaleziono rekordu do zaktualizowania.");
            } else {
                System.out.println("Zaktualizowano saldo pomyślnie.");
            }
        } catch (SQLException e) {
            System.err.println("Błąd podczas aktualizacji salda: " + e.getMessage());
        }
    }
}

