package smart_shop.ProductManager.com;

//View Product Details (2.5)
import java.sql.*;
import java.util.Scanner;

public class ProductDetailsViewer {

  private static final String DB_URL = "jdbc:mysql://localhost:3306/ecommerce_db";
  private static final String DB_USER = "root";      // 🔁 Replace with your DB username
  private static final String DB_PASSWORD ="Pass@123";  // 🔁 Replace with your DB password

  public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      boolean keepRunning = true;

      System.out.println("\033[1m\033[43mView Product Details\033[0m by ID");

      while (keepRunning) {
          System.out.print("\nID to view details >> ");
          int productId = scanner.nextInt();
          scanner.nextLine(); // Consume newline left by nextInt()

          viewProductDetailsById(productId);

          System.out.print("\nDo you want to continue? (yes/no) >> ");
          String choice = scanner.nextLine().trim().toLowerCase();

          if (!choice.equals("yes")) {
              keepRunning = false;
              System.out.println("Thank you for using the product viewer. Goodbye!");
          }
      }

      scanner.close();
  }

  public static void viewProductDetailsById(int productId) {
      String query = "SELECT name, description, price, quantity FROM products WHERE product_id = ?";

      try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
           PreparedStatement pstmt = conn.prepareStatement(query)) {

          pstmt.setInt(1, productId);
          ResultSet rs = pstmt.executeQuery();

          if (rs.next()) {
              String name = rs.getString("name");
              String desc = rs.getString("description");
              double price = rs.getDouble("price");
              int qty = rs.getInt("quantity");

              System.out.println("\nProduct Name       : " + name);
              System.out.println("Description        : " + desc);
              System.out.println("Price              : " + price);
              System.out.println("Available Quantity : " + qty);
          } else {
              System.out.println("\n❌ Product with ID " + productId + " not found.");
          }

      } catch (SQLException e) {
          System.out.println("⚠️ Database error: " + e.getMessage());
      }
  }
}