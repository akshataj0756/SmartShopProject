package com.smartshop.validation;

import java.sql.*;
import java.util.Scanner;

public class UpdateProductApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Update Product Details");
            System.out.print("Enter the Product ID to update >> ");
            int productId = Integer.parseInt(scanner.nextLine().trim());

            System.out.println("Select field to update:");
            System.out.println("1. Name");
            System.out.println("2. Description");
            System.out.println("3. Price");
            System.out.println("4. Quantity");
            System.out.print("Enter your choice >> ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter new value >> ");
            String newValue = scanner.nextLine().trim();

            updateProductInDatabase(productId, choice, newValue);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numeric values for ID and choice.");
        } finally {
            scanner.close();
        }
    }

    private static void updateProductInDatabase(int productId, int choice, String newValue) {
        String url = "jdbc:mysql://localhost:3306/ecommerce_db";
        String user = "root";
        String password = "root";

        String field = null;
        switch (choice) {
            case 1: field = "name"; break;
            case 2: field = "description"; break;
            case 3: field = "price"; break;
            case 4: field = "quantity"; break;
            default:
                System.out.println("Invalid choice. Please select between 1–4.");
                return;
        }

        // validate input values
        if ((choice == 1 || choice == 2) && newValue.isEmpty()) {
            System.out.println("Value cannot be empty!");
            return;
        }
        if (choice == 3) {
            try {
                double priceVal = Double.parseDouble(newValue);
                if (priceVal <= 0) {
                    System.out.println("Price must be greater than 0.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid price format.");
                return;
            }
        }
        if (choice == 4) {
            try {
                int qtyVal = Integer.parseInt(newValue);
                if (qtyVal < 0) {
                    System.out.println("Quantity cannot be negative.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity format.");
                return;
            }
        }

        System.out.println("Updating product in database...");

        String sql = "UPDATE product SET " + field + " = ? WHERE product_id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
            return;
        }

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (choice == 3) { // price
                pstmt.setBigDecimal(1, new java.math.BigDecimal(newValue));
            } else if (choice == 4) { // quantity
                pstmt.setInt(1, Integer.parseInt(newValue));
            } else { // name or description
                pstmt.setString(1, newValue);
            }
            pstmt.setInt(2, productId);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Product not found or no changes made.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}