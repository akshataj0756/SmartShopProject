package com.smartshop.productmanager;

import java.sql.*;

public class ProductSearch {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/ecommerce_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void searchByName(String keyword) {
        String query = "SELECT product_id, name, price, quantity FROM product WHERE name LIKE ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            System.out.println("\n Search Results:");
            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println("--------------------------------");
                System.out.println("ID    : " + rs.getInt("product_id"));
                System.out.println("Name  : " + rs.getString("name"));
                System.out.println("Price : ₹" + rs.getDouble("price"));
                System.out.println("Stock : " + rs.getInt("quantity"));
            }

            if (!found) {
                System.out.println(" No products found for: " + keyword);
            }

        } catch (SQLException e) {
            System.out.println(" Database Error: " + e.getMessage());
        }
    }
}
