package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CashRegisterApp {
    private static ShopDatabase shopDatabase = new ShopDatabase();
    private static List<Product> productList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Rozpocznij skanowanie");
        System.out.print("Wprowadź kod produktu: ");

        while (true) {

            String input = scanner.nextLine();

            processBarcode(input);
            displayProductList();
        }
    }

    private static void processBarcode(String barcode) {
        Product product = shopDatabase.findProductByBarcode(barcode);

        if (product != null) {
            productList.add(product);
            System.out.println("Dodano produkt: " + product.name);
        } else {
            System.out.println("Nie znaleziono produktu o kodzie: " + barcode);
        }
    }

    private static void displayProductList() {
        System.out.println("Aktualna lista zakupów:");
        for (Product product : productList) {
            System.out.println(product);
        }
    }
}