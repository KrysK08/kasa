package org.example;

public class Product {
    String name;
    public String barcode;
    double price;
    int quantity;

    public Product(String name, String barcode, double price, int quantity) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " - " + quantity + " szt. - " + price + " zł";
    }
}
