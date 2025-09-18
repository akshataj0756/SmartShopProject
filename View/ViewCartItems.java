package com.smart.shop.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewCartItems {
	
	public static void main(String[] args) {
				
				String url = "jdbc:mysql://localhost:3306/ecommerce_db";
		        String username = "root";   // your MySQL username
		        String password = "root";   // your MySQL password
		        
		        int loggedInUserId = 101; // Example: assume user 101 is logged in

		        try {
		            // Step 1: Connect to DB
		            Connection conn = DriverManager.getConnection(url, username, password);

		            // Step 2: SQL Query to fetch cart items with product details
		            String sql = "SELECT p.name, c.quantity, p.price, (c.quantity * p.price) AS total " +
		                         "FROM cart c " +
		                         "JOIN products p ON c.product_id = p.product_id " +
		                         "WHERE c.user_id = ?";

		            PreparedStatement stmt = conn.prepareStatement(sql);
		            stmt.setInt(1, loggedInUserId);

		            // Step 3: Execute query
		            ResultSet rs = stmt.executeQuery();

		            System.out.println("----- Your Cart Items -----");
		            System.out.printf("%-20s %-10s %-10s %-10s\n", "Product Name", "Quantity", "Price", "Total");

		            // Step 4: Display results
		            while (rs.next()) {
		                String productName = rs.getString("name");
		                int quantity = rs.getInt("quantity");
		                double price = rs.getDouble("price");
		                double total = rs.getDouble("total");

		                System.out.printf("%-20s %-10d %-10.2f %-10.2f\n", productName, quantity, price, total);
		            }

		            // Close resources
		            rs.close();
		            stmt.close();
		            conn.close();

		        }
		        catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
	}

