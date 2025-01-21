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

        while (true) {
            System.out.print("Wprowadź kod produktu lub komendę (reset/pay): ");
            String input = scanner.nextLine();

            if (input.equals("reset")) {
                resetTransaction();
            } else if (input.equals("pay")) {
                handlePayment(scanner);
            } else {
                processBarcode(input);
            }
            displayProductList();
        }
    }

    private static void processBarcode(String barcode) {
        Product product = shopDatabase.findProductByBarcode(barcode);

        if (product != null) {
            boolean productExists = false;
            for (Product existingProduct : productList) {
                if (existingProduct.barcode.equals(barcode)) {
                    existingProduct.quantity++;
                    productExists = true;
                    break;
                }
            }
            if (!productExists) {
                product.quantity = 1;
                productList.add(product);
            }
            System.out.println("Dodano produkt: " + product.name);
        } else {
            System.out.println("Nie znaleziono produktu o kodzie: " + barcode);
        }
    }

    private static void resetTransaction() {
        productList.clear();
        System.out.println("Transakcja została zresetowana.");
    }

    private static void handlePayment(Scanner scanner) {
        if (productList.isEmpty()) {
            System.out.println("Lista produktów jest pusta. Nie można przeprowadzić płatności.");
            return;
        }

        System.out.println("Wybierz metodę płatności lub zeskanuj kartę podarunkową:");
        String paymentInput = scanner.nextLine();

        if (shopDatabase.isPresentCard(paymentInput)) {
            System.out.println("Zastosowano kartę podarunkową. Płatność zakończona.");
            resetTransaction();
        } else {
            System.out.println("Płatność gotówkowa lub kartą zakończona.");
            resetTransaction();
        }
    }

    private static void displayProductList() {
        if (productList.isEmpty()) {
            System.out.println("Lista produktów jest pusta.");
        } else {
            System.out.println("Aktualna lista zakupów:");
            for (Product product : productList) {
                System.out.println(product);
            }
        }
    }
}
