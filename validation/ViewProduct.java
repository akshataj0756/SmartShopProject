package validation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ViewProduct {
	private static Map<Integer, Product> productDatabase = new HashMap<>();

    public static void main(String[] args) {
        initializeProducts();
        Scanner scanner = new Scanner(System.in);
        
        //Handle product
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
        } finally {
            scanner.close();
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
