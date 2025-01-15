package org.example;

import java.util.ArrayList;
import java.util.List;

class ShopDatabase {
    private List<Product> products;

    public ShopDatabase() {
        products = new ArrayList<>();
        products.add(new Product("Mleko", "12345", 3.50, 0));
        products.add(new Product("Chleb", "54321", 2.00, 0));
        products.add(new Product("Masło", "11111", 6.00, 0));
    }

    public Product findProductByBarcode(String barcode) {
        for (Product product : products) {
            if (product.barcode.equals(barcode)) {
                return product;
            }
        }
        return null;
    }
}
