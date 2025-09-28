package com.smartshop.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ViewUserPurchaseHistory {
	
	
	    public static void viewUserPurchaseHistory(Scanner sc) {
	        String url = "jdbc:mysql://localhost:3306/ecommerce_db";
	        String username = "root";   // MySQL username
	        String password = "root";   // MySQL password

	        System.out.print("Enter username to view purchase history: ");
	        String inputUsername = sc.nextLine();

	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            // Step 1: Connect to DB
	            conn = DriverManager.getConnection(url, username, password);

	            // Step 2: SQL to fetch purchase history
	            String sql = "SELECT users.username, product.name AS product_name, purchase.quantity, product.price, " +
	                    "(purchase.quantity * product.price) AS total, purchase.purchase_date " +
	                    "FROM purchase " +
	                    "JOIN users ON purchase.user_id = users.user_id " +
	                    "JOIN product ON purchase.product_id = product.product_id " +
	                    "WHERE users.username = ? " +
	                    "ORDER BY purchase.purchase_date DESC";

	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, inputUsername);

	            // Step 3: Execute query
	            rs = stmt.executeQuery();

	            // Step 4: Display results
	            System.out.println("----- Purchase History of " + inputUsername + " -----");
	            System.out.println("----- User Purchase History -----");
	            System.out.println("Product Name | Quantity | Price | Total | Purchase Date");
	            System.out.println("---------------------------------------------------------");

	            while (rs.next()) {
	                String productName = rs.getString("product_name");
	                int quantity = rs.getInt("quantity");
	                double price = rs.getDouble("price");
	                double total = rs.getDouble("total");
	                String purchaseDate = rs.getString("purchase_date");

	                System.out.println("Product Name : " + productName);
	                System.out.println("Quantity     : " + quantity);
	                System.out.println("Price        : " + price);
	                System.out.println("Total        : " + total);
	                System.out.println("Purchase Date: " + purchaseDate);
	                System.out.println("-----------------------------------------");
	            }

//	            while (rs.next()) {
//	            	
//	                String productName = rs.getString("product_name");
//	                int qty = rs.getInt("quantity");
//	                double price = rs.getDouble("price");
//	                double total = rs.getDouble("total");
//	                Timestamp date = rs.getTimestamp("purchase_date");
//
//	                System.out.printf("%-20s %-10d %-10.2f %-10.2f %-20s\n",productName, qty, price, total, date);
//	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            
	        } finally {
	            // Close resources properly
	            try {
	            	if (rs != null) rs.close(); 
	            	if (stmt != null) stmt.close();
	            	if (conn != null) conn.close();
	            	} 
	            catch (SQLException e) {
	            	e.printStackTrace(); 
	            	}
	          
	            sc.close();
	        }
	   }
}



