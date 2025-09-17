package validation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MenuChoiceValidation {

    private static Map<Integer, Product> productDatabase = new HashMap<>();

    public static void main(String[] args) {
        initializeProducts();
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            displayMenu();
            int choice = getValidMenuChoice(scanner);

            if (choice == 1) {
                viewProduct(scanner);
            } else if (choice == 2) {
                System.out.println("Exiting the application. Goodbye!");
                scanner.close();
                break;
            }
        }
    }
    
    //menu
    private static void displayMenu() {
        System.out.println("===== Main Menu =====");
        System.out.println("1. View Product Details by ID");
        System.out.println("2. Exit");
    }
    
    //Get valid menu choice 
    private static int getValidMenuChoice(Scanner scanner) {
        while (true) {
            System.out.print("Enter your choice (1 or 2): ");
            String input = scanner.nextLine();

            if (input == null || input.trim().isEmpty()) {
                System.out.println("Warning: Choice cannot be empty. Please try again.");
                continue;
            }

            try {
                int choice = Integer.parseInt(input);
                if (choice == 1 || choice == 2) {
                    return choice;
                } else {
                    System.out.println("Warning: Invalid option. Please enter 1 or 2.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Warning: Please enter a valid number (1 or 2).");
            }
        }
    }
    
    //View Product
    private static void viewProduct(Scanner scanner) {
        try {
            System.out.print("Enter Product ID: ");
            String input = scanner.nextLine();
            int productId = Integer.parseInt(input);

            Product product = productDatabase.get(productId);

            if (product != null) {
                displayProduct(product);
            } else {
                System.out.println("Product not found. Please check the ID and try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Please enter a numeric product ID.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. Please try again.");
        }
    }
    
    //Product
    private static void initializeProducts() {
        productDatabase.put(1, new Product(1, "Laptop", "High-performance laptop", 85000.0, 5));
        productDatabase.put(2, new Product(2, "Smartphone", "Latest model smartphone", 35000.0, 10));
        productDatabase.put(3, new Product(3, "Headphones", "Noise-cancelling headphones", 5000.0, 15));
    }
    
    //display product
    private static void displayProduct(Product product) {
        System.out.println("Product ID: " + product.getId());
        System.out.println("Name: " + product.getName());
        System.out.println("Description: " + product.getDescription());
        System.out.println("Price: ₹" + product.getPrice());
        System.out.println("Available Quantity: " + product.getQuantity());
    }
}