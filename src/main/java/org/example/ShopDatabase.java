package org.example;

import java.util.ArrayList;
import java.util.List;

class ShopDatabase {
    private List<Product> products;

    public ShopDatabase() {
        products = new ArrayList<>();
        products.add(new Product("Mleko", "12345/n", 3.50, 0));
        products.add(new Product("Chleb", "54321/n", 2.00, 0));
        products.add(new Product("Masło", "11111/n", 6.00, 0));
    }

    public Product findProductByBarcode(String barcode) {
        for (Product product : products) {
            if (product.barcode.equals(barcode)) {
                product.quantity++;
                return product;
            }
        }
        return null;
    }
}
