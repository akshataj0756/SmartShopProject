package com.smartshop.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ViewCartItems {
	public static Connection getConnection() throws Exception {
		Connection con=null;
        String url = "jdbc:mysql://localhost:3306/ecommerce_db";
        String user = "root";
        String password = "root";
        con=DriverManager.getConnection(url, user, password);
        return con;
    }
    // Show all cart items for a given user
    public static void displayCart(int userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT product.name, purchase.quantity, product.price,"
        			+ "(purchase.quantity * product.price) AS subtotal "
                   + "FROM purchase "
                   + "JOIN product ON purchase.product_id = product.product_id "
                   + "WHERE purchase.user_id = ?";

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userId);
            rs = pstmt.executeQuery();

            double totalAmount = 0;

            System.out.println("Your Cart Items>>");

            while (rs.next()) {
                String productName = rs.getString("name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                double subtotal = rs.getDouble("subtotal");

                System.out.println("Product: " + productName);
                System.out.println("Quantity: " + quantity);
                System.out.println("Price: " + price);
                System.out.println("Subtotal: " + subtotal);
                System.out.println("---------------------------");

                totalAmount = totalAmount+subtotal;
            }

            System.out.println("Total Amount = " + totalAmount);

        } catch (Exception e) {
            System.out.println("Error while fetching cart: " + e.getMessage());
        } finally {
            if (rs != null) {
            	rs.close();			// if rs is not null then executes
            	}
            if (pstmt != null) {
            	pstmt.close();		// if pstmt is not null then executes
            }
            if (con != null) {
            	con.close();			// if con is not null then executes
            }
        }
    }
}
