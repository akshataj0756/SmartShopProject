package com.smart.shop.project;

import java.sql.*;
import java.util.Scanner;

public class AddProductDynamic {
	
	public static void main(String[] args) {
	        String url = "jdbc:mysql://localhost:3306/ecommerce_db";
	        String username = "root";  // your MySQL username
	        String password = "root";  // your MySQL password
	        
	        
	     // Scanner object to take input from user
	        Scanner sc = new Scanner(System.in);
	
	        try {
	        	  // Load and register MySQL JDBC driver
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            
	             // Establish connection with database
	            Connection conn = DriverManager.getConnection(url, username, password);
	            
	            boolean addMore = true;
	            
	              // Loop to add multiple products 
	            while(addMore) {
	            	
	            	
	                System.out.println("Enter Product ID: ");
	                
	                int id = sc.nextInt();
	                sc.nextLine(); 
	                System.out.println("Enter Product Name: ");
	                String name = sc.nextLine();
	                System.out.println("Enter Product Description: ");
	                String description = sc.nextLine();
	                System.out.println("Enter Product Price: ");
	                double price = sc.nextDouble();
	                System.out.println("Enter Product Quantity: ");
	                int quantity = sc.nextInt();
	                sc.nextLine(); 

	                String sql = "INSERT INTO product (product_id, name, description, price, quantity) VALUES (?, ?, ?, ?, ?)";
	                PreparedStatement pst = conn.prepareStatement(sql);
	                pst.setInt(1, id);
	                pst.setString(2, name);
	                pst.setString(3, description);
	                pst.setDouble(4, price);
	                pst.setInt(5, quantity);

	                int rows = pst.executeUpdate();
	                if(rows > 0) 
	                System.out.println("Product added successfully!");

	                System.out.println("Do you want to add another product? (yes/no)");
	                String choice = sc.nextLine();
	                if(choice.equalsIgnoreCase("no")) addMore = false;

	                pst.close();
	            }

	            conn.close();
	            System.out.println("All products added. Program ended.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	       

	        sc.close();
	        
	    }
	}



