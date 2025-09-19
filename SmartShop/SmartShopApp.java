package smartshop;

import java.util.Scanner;

public class SmartShopApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AdminService adminService = new AdminService();

        System.out.println("=== Smart Shop Admin Console ===");

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. View Product Stock");
            System.out.println("2. View Registered Users");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int productId = sc.nextInt();
                    adminService.viewProductStock(productId);
                    break;
                case 2:
                    adminService.viewRegisteredUsers();
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
