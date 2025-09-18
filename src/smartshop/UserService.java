package smartshop;

import java.sql.*;
import java.util.Scanner;

public class UserService {

    public void viewPastOrders(int userId) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT o.order_id, o.order_date, p.product_name, oi.quantity, oi.price " +"FROM orders o " +
            		"JOIN order_items oi ON o.order_id = oi.order_id " +"JOIN products p ON oi.product_id = p.product_id " +
                  "WHERE o.user_id = ? " + "ORDER BY o.order_date DESC";

        PreparedStatement ps = con.prepareStatement(query);
          ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            int currentOrderId = -1;
            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                if (orderId != currentOrderId) {
                    currentOrderId = orderId;
                    System.out.println("\nOrder ID: " + orderId + " | Date: " + rs.getDate("order_date"));
                }
                System.out.println("Product: " + rs.getString("product_name") +" | Qty: " + rs.getInt("quantity") +
                		" | Price: " + rs.getDouble("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
