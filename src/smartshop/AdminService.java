package smartshop;

import java.sql.*;

public class AdminService {

    // View Product Stock
    public void viewProductStock(int productId) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT product_name, quantity, status FROM products WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println(" Product Found:");
                System.out.println("Product: " + rs.getString("product_name"));
                System.out.println("Available Quantity: " + rs.getInt("quantity"));
                System.out.println("Status: " + rs.getString("status"));
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

    // Hard Delete (safe: only if no orders reference the product)
    public void deleteProduct(int productId) {
        try (Connection con = DBConnection.getConnection()) {
            // Step 1: Check if product is referenced in order_items
            String checkQuery = "SELECT COUNT(*) FROM order_items WHERE product_id=?";
            PreparedStatement checkPs = con.prepareStatement(checkQuery);
            checkPs.setInt(1, productId);
            ResultSet rs = checkPs.executeQuery();
            rs.next();
            int refCount = rs.getInt(1);

            if (refCount > 0) {
                System.out.println(" Cannot delete Product ID " + productId +
                        " because it exists in " + refCount + " order(s).");
                System.out.println(" Suggestion: Mark it as INACTIVE instead of deleting.");
                return;
            }

            // Step 2: Delete only if safe
            String query = "DELETE FROM products WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(" Product deleted successfully!");
            } else {
                System.out.println(" Product not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Soft Delete (mark product as inactive instead of removing)
    public void deactivateProduct(int productId) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "UPDATE products SET status='INACTIVE' WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, productId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Product marked as INACTIVE.");
            } else {
                System.out.println(" Product not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
