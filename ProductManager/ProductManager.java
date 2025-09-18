package smart_shop.ProductManager.com;

import java.util.Scanner;


public class ProductManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("=== Welcome to Smart Shop ===");

        while (keepRunning) {
            System.out.println("\nMain Menu:");
            System.out.println("1. View Product by ID");
            System.out.println("2. Purchase Product (Add to Cart)");
            System.out.println("3. View Product List ");
            System.out.println("4. Search Product by Name");
            System.out.println("5. Exit");

            System.out.print("Enter your choice (1-5): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter Product ID to view >> ");
                    int viewId = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    ProductDetailsViewer.viewProductDetailsById(viewId);
                    break;

                case "2":
                    System.out.print("Enter Product ID to purchase >> ");
                    int purchaseId = scanner.nextInt();
                    System.out.print("Enter Quantity >> ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    AddProductToCart.addToCart(purchaseId, quantity);
                    break;

                case "3":
                    ProductViewer.viewAllProducts();
                    break;

                case "4":
                    System.out.print("Enter product name to search: ");
                    String keyword = scanner.nextLine();
                    ProductSearch.searchByName(keyword);
                    break;

                case "5":
                    keepRunning = false;
                    System.out.println("👋 Exiting... Thank you for using Smart Shop!");
                    break;

                default:
                    System.out.println("❌ Invalid choice. Please enter a number from 1 to 5.");
            }
        }

        scanner.close();
    }
}
