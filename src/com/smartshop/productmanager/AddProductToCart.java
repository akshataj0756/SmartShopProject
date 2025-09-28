package com.smartshop.productmanager;


import java.sql.*;
import java.util.Scanner;

public class AddProductToCart {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ecommerce_db";
    private static final String DB_USER = "root";     
    private static final String DB_PASSWORD = "root"; 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\033[1m\033[43mAdd Product to Cart\033[0m (Save as Purchase)");

        boolean continueShopping = true;

        while (continueShopping) {
            System.out.print("\nEnter the Product ID to purchase >> ");
            int productId = scanner.nextInt();

            System.out.print("Enter the Quantity >> ");
            int quantity = scanner.nextInt();

            addToCart(productId, quantity);

            System.out.print("\n🔁 Do you want to continue shopping? (yes/no): ");
            scanner.nextLine(); // consume leftover newline
            String answer = scanner.nextLine().trim().toLowerCase();

            if (!answer.equals("yes") && !answer.equals("y")) {
                continueShopping = false;
                System.out.println("\n🛒 Thank you for shopping with us!");
            }
        }

        scanner.close();
    }

    public static void addToCart(int productId, int quantity) {
        String selectQuery = "SELECT name, quantity FROM product WHERE product_id = ?";
        String insertPurchaseQuery = "INSERT INTO purchase (product_id, quantity, purchase_date) VALUES (?, ?, NOW())";
        String updateProductQuery = "UPDATE product SET quantity = quantity - ? WHERE product_id = ?";

        try (
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            PreparedStatement selectStmt = conn.prepareStatement(selectQuery);
            PreparedStatement insertStmt = conn.prepareStatement(insertPurchaseQuery);
            PreparedStatement updateStmt = conn.prepareStatement(updateProductQuery)
        ) {
            // Step 1: Check if product exists and get available stock
            selectStmt.setInt(1, productId);
            ResultSet rs = selectStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("❌ Product with ID " + productId + " does not exist.");
                return;
            }

            String productName = rs.getString("name");
            int availableStock = rs.getInt("quantity");

            // Step 2: Validate stock
            System.out.println("\nChecking stock availability...");
            if (quantity > availableStock) {
                System.out.println("❌ Only " + availableStock + " units of '" + productName + "' available.");
                return;
            }

            // Step 3: Insert purchase
            insertStmt.setInt(1, productId);
            insertStmt.setInt(2, quantity);
            insertStmt.executeUpdate();

            // Step 4: Update stock
            updateStmt.setInt(1, quantity);
            updateStmt.setInt(2, productId);
            updateStmt.executeUpdate();

            System.out.println("Adding product to your cart and saving purchase in database...");
            System.out.println("✅ Product added successfully!");

        } catch (SQLException e) {
            System.out.println("⚠️ Database Error: " + e.getMessage());
        }
    }
}