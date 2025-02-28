package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CashRegisterApp {
    private static DBProducts dbProducts = new DBProducts();
    private static DBBank dbBank = new DBBank();
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
        }

        double totalPrice = calculateTotalPrice();
        System.out.println("Cena całkowita: " + totalPrice + " zł");

        System.out.println("Wybierz formę płatności (card/cash):");
        String payment = scanner.nextLine();

        if (payment.equals("card")) {
            System.out.println("Wpisz numer karty: ");
            String NumerKarty = scanner.nextLine();
            processCardPayment(NumerKarty);
        } else if (payment.equals("cash")) {
            System.out.println("Płatność gotówkowa zakończona sukcesem.");
        } else {
            System.out.println("Wybrano złą formę płatności.");
            handlePayment(scanner);
            return;
        }
        resetTransaction();
    }

    private static void processCardPayment(String numerKarty) {
        CardInfo cardInfo = (CardInfo) dbBank.FindCardNumber(numerKarty);
        Scanner scanner = new Scanner(System.in);
        if (cardInfo == null) {
            System.out.println("Wpisano zla karte");
        } else {
            System.out.println("Numer karty: " + numerKarty);
            System.out.println("Podaj kod CVV: ");
            String cvv = scanner.nextLine();
            processCvv(cvv,numerKarty);

        }
    }
    private static void processCvv(String scanner, String numerKarty) {
        System.out.println("numercvv");
    }

    private static double calculateTotalPrice() {
        double total = 0.0;
        for (Product product : productList) {
            total += product.price * product.quantity;
        }
        return total;
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