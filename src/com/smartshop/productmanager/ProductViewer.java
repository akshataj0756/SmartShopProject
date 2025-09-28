package com.smartshop.productmanager;

import java.sql.*;

public class ProductViewer {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ecommerce_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void viewAllProducts() {
        String query = "SELECT product_id, name, price, quantity FROM product";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n📦 Available Products:");
            System.out.println("-------------------------------------------------");
            System.out.printf("%-10s %-25s %-10s %-10s%n", "ID", "Name", "Price", "Stock");
            System.out.println("-------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-10d %-25s ₹%-9.2f %-10d%n",
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"));
            }

        } catch (SQLException e) {
            System.out.println("⚠️ Database Error: " + e.getMessage());
        }
    }
}
