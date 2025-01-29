package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CashRegisterApp {
    private static DBProducts dbProducts = new DBProducts();
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
        Product product = dbProducts.findProductByBarcode(barcode);

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
        }else {
            System.out.println("Wybierz forme platnosci(card/cash):");
            String payment = scanner.nextLine();
            if(payment.equals("card")) {
                System.out.println("Płatność kartą zakończona.");
            }else if (payment.equals("cash")) {
                System.out.println("Płatność gotówkowazakończona.");
            }else{
                System.out.println("Wybrano zla forme platnosci");
                handlePayment(scanner);
            }

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