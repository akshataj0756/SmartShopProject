package smartshop;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SmartShopApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AdminService adminService = new AdminService();
        UserService userService = new UserService();

        System.out.println("=== Welcome to Smart Shop ===");

        while (true) {
            int roleChoice = -1;
            try {
                System.out.println("\nSelect Role:");
                System.out.println("1. Admin");
                System.out.println("2. Registered User");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");
                roleChoice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine(); // clear invalid input
                continue;
            }

            switch (roleChoice) {
                case 1: // Admin Menu
                    while (true) {
                        int adminChoice = -1;
                        try {
                            System.out.println("\n--- Admin Menu ---");
                            System.out.println("1. View Product Stock");
                            System.out.println("2. View Registered Users");
                            System.out.println("3. Delete/Deactivate Product");
                            System.out.println("4. Back");
                            System.out.print("Enter choice: ");
                            adminChoice = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a number.");
                            sc.nextLine();
                            continue;
                        }

                        switch (adminChoice) {
                            case 1:
                                System.out.print("Enter Product ID: ");
                                try {
                                    int productId = sc.nextInt();
                                    adminService.viewProductStock(productId);
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input! Product ID must be a number.");
                                    sc.nextLine();
                                }
                                break;

                            case 2:
                                adminService.viewRegisteredUsers();
                                break;

                            case 3:
                                System.out.print("Enter Product ID: ");
                                try {
                                    int productId = sc.nextInt();

                                    System.out.println("\nChoose Delete Option:");
                                    System.out.println("1. Hard Delete (Remove permanently if not in any orders)");
                                    System.out.println("2. Soft Delete (Mark as INACTIVE)");
                                    System.out.print("Enter choice: ");
                                    int deleteChoice = sc.nextInt();

                                    if (deleteChoice == 1) {
                                        System.out.print("Are you sure you want to permanently delete Product ID "
                                                + productId + "? (Y/N): ");
                                        String confirm = sc.next();
                                        if (confirm.equalsIgnoreCase("Y")) {
                                            adminService.deleteProduct(productId);
                                        } else {
                                            System.out.println("Deletion cancelled.");
                                        }
                                    } else if (deleteChoice == 2) {
                                        System.out.print("Are you sure you want to deactivate Product ID "
                                                + productId + "? (Y/N): ");
                                        String confirm = sc.next();
                                        if (confirm.equalsIgnoreCase("Y")) {
                                            adminService.deactivateProduct(productId);
                                        } else {
                                            System.out.println("Deactivation cancelled.");
                                        }
                                    } else {
                                        System.out.println("Invalid option!");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input! Product ID must be a number.");
                                    sc.nextLine();
                                }
                                break;

                            case 4:
                                break; // back to role selection

                            default:
                                System.out.println("Invalid choice!");
                        }

                        if (adminChoice == 4) break;
                    }
                    break;

                case 2: // User Menu
                    System.out.print("Enter your User ID: ");
                    int userId = -1;
                    try {
                        userId = sc.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input! User ID must be a number.");
                        sc.nextLine();
                        continue;
                    }

                    while (true) {
                        int userChoice = -1;
                        try {
                            System.out.println("\n--- User Menu ---");
                            System.out.println("1. View My Past Orders");
                            System.out.println("2. Back");
                            System.out.print("Enter choice: ");
                            userChoice = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter a number.");
                            sc.nextLine();
                            continue;
                        }

                        switch (userChoice) {
                            case 1:
                                userService.viewPastOrders(userId);
                                break;
                            case 2:
                                break; // back to role selection
                            default:
                                System.out.println("Invalid choice!");
                        }

                        if (userChoice == 2) break;
                    }
                    break;

                case 3:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
