package com.smart.shop.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class ViewUserPurchaseHistory {
	
	
	    public static void main(String[] args) {
	        String url = "jdbc:mysql://localhost:3306/ecommerce_db";
	        String username = "root";   // MySQL username
	        String password = "root";   // MySQL password

	        Scanner sc = new Scanner(System.in);

	        System.out.print("Enter username to view purchase history: ");
	        String inputUsername = sc.nextLine();

	        Connection conn = null;
	        PreparedStatement stmt = null;
	        ResultSet rs = null;

	        try {
	            // Step 1: Connect to DB
	            conn = DriverManager.getConnection(url, username, password);

	            // Step 2: SQL to fetch purchase history
	            String sql = "SELECT u.username, p.name AS product_name, c.quantity, p.price, " +
	                         "(c.quantity * p.price) AS total, c.purchase_date " +
	                         "FROM cart c " +
	                         "JOIN users u ON c.user_id = u.user_id " +
	                         "JOIN products p ON c.product_id = p.product_id " +
	                         "WHERE u.username = ? " +
	                         "ORDER BY c.purchase_date DESC";

	            stmt = conn.prepareStatement(sql);
	            stmt.setString(1, inputUsername);

	            // Step 3: Execute query
	            rs = stmt.executeQuery();

	            // Step 4: Display results
	            System.out.println("----- Purchase History of " + inputUsername + " -----");
	            System.out.printf("%-20s %-10s %-10s %-10s %-20s\n",
	                    "Product Name", "Quantity", "Price", "Total", "Purchase Date");

	            while (rs.next()) {
	            	
	                String productName = rs.getString("product_name");
	                int qty = rs.getInt("quantity");
	                double price = rs.getDouble("price");
	                double total = rs.getDouble("total");
	                Timestamp date = rs.getTimestamp("purchase_date");

	                System.out.printf("%-20s %-10d %-10.2f %-10.2f %-20s\n",productName, qty, price, total, date);
	            }

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



