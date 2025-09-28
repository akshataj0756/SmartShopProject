package com.smartshop.validation;

import java.sql.SQLException;
import java.util.Scanner;

import com.smartshop.productmanager.AddProductToCart;
import com.smartshop.productmanager.ProductSearch;
import com.smartshop.productmanager.ProductViewer;
import com.smartshop.userinterface.Login;   // import
import com.smartshop.userinterface.UserRegister;    // import
import com.smartshop.view.ViewCartItems;
import com.smartshop.view.ViewUserPurchaseHistory;

public class MenuChoiceValidation {

    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) throws SQLException {
//        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to E-Commerce Console Application");

        while (!exit) {
            displayMenu();
            int choice = getValidChoice(sc);

            switch (choice) {
                case 1:
                    System.out.println("Register selected.");
                    UserRegister ur = new UserRegister();  // changed user register to ur
                    ur.userRegister();
                    break;
                case 2:
                    System.out.println("Login selected.");
                    Login.loginUser(sc);  // add an argument as scanner
                    break;
                case 3:
                    System.out.println("View Products selected.");
                    ProductViewer.viewAllProducts();
                    break;
                case 4:
                    System.out.println("Search Product selected.");
                    System.out.println("Enter the keyword>>");
				    String keyword=sc.next();
				    ProductSearch.searchByName(keyword);
                    break;
                case 5:
                    System.out.println("Add to Cart selected.");
                    System.out.println("Enter purchase id>>");
                    int purchaseId=sc.nextInt();
                    System.out.println("Enter quantity odf product you want>>");
                    int quantity=sc.nextInt();
                    AddProductToCart.addToCart(purchaseId, quantity);
                    break;
                case 6:
                		System.out.println("View Cart selected.");
                     System.out.println("Enter UserId>>");
				    int userId=sc.nextInt();
				    ViewCartItems.displayCart(userId);
                    break;
                case 7:
                    System.out.println("View Purchase History selected.");
                    ViewUserPurchaseHistory.viewUserPurchaseHistory(sc);
                    break;
                case 8:
                    System.out.println("Exit");
                    exit = true;
                    break;
                default:
                    // This shouldn't occur due to validation
                    System.out.println("Unknown error.");
            }
        }

        sc.close();
    }

    private static void displayMenu() {
        System.out.println("\nPlease choose an option:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. View Products");
        System.out.println("4. Search Product");
        System.out.println("5. Add to Cart");
        System.out.println("6. View Cart");
        System.out.println("7. View Purchase History");
        System.out.println("8. Exit");
        System.out.print("Enter your choice >> ");
    }

    private static int getValidChoice(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 8) {
                    return choice;
                } else {
                    System.out.println("Invalid Menu Option");
                    System.out.print("Invalid choice! Please enter a valid option number.\nTry again >> ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Menu Option");
                System.out.print("Invalid choice! Please enter a valid option number.\nTry again >> ");
            }
        }
    }
}