package smartshop;

import java.sql.*;

public class AdminService {

    // View Product Stock
    public void viewProductStock(int productId) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT product_name, quantity FROM products WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println(" Product Found:");
                System.out.println("Product: " + rs.getString("product_name"));
                System.out.println("Available Quantity: " + rs.getInt("quantity"));
            } else {
                System.out.println(" Product not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Registered Users
    public void viewRegisteredUsers() {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT user_id, username, email, role FROM users";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("---- Registered Users ----");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("user_id"));
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Role: " + rs.getString("role"));
                System.out.println("--------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
