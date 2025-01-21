package org.example;

import java.util.ArrayList;
import java.util.List;

class ShopDatabase {
    private List<Product> products;
    private List<String> presentCards;

    public ShopDatabase() {
        products = new ArrayList<>();
        products.add(new Product("Coca Colastic", "5449000000996/n", 3.50, 0));
        products.add(new Product("Spritetastic", "5449000234636/n", 2.00, 0));
        products.add(new Product("Fantastic", "5449000011527/n", 6.00, 0));
        products.add(new Product("Książka matmastic", "9788375940794/n", 6.00, 0));

        presentCards = new ArrayList<>();
        presentCards.add("CARD12345");
        presentCards.add("CARD67890");
    }

    public Product findProductByBarcode(String barcode) {
        for (Product product : products) {
            if (product.barcode.equals(barcode)) {
                return new Product(product.name, product.barcode, product.price, product.quantity);
            }
        }
        return null;
    }

    public boolean isPresentCard(String cardCode) {
        return presentCards.contains(cardCode);
    }
}