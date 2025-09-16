package com.user.login;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Login {
    private static final String DB_Driver_Name = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/smartshop";
    private static final String DB_Username = "root";
    private static final String DB_Password = "akshu0756";
    private static final String Select_Query = "SELECT * FROM users WHERE username=? AND password=?";

    // DB Connection
    public static Connection getConnection() throws Exception {
    	Connection con=null;
        Class.forName(DB_Driver_Name);
        con=DriverManager.getConnection(DB_URL,DB_Username,DB_Password);
        return con;
    }
    
    // Authenticate User
    public boolean authenticateUser(String username, String password) throws Exception {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(Select_Query);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    
    public static void loginUser(Scanner sc) {
    	System.out.println(" User Login ");
        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        
        System.out.println("Verifying credentials...");
        Login l1=new Login();
        try {
			if (l1.authenticateUser(username, password)) {
			    System.out.println("Login Successful!");
			    showMenu(username,sc); // show menu after login
			} else {
			    System.out.println("Invalid Username or Password. Try again.");
			    loginUser(sc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }

    // Menu after login
    public static void showMenu(String username,Scanner sc) {
        //Scanner sc = new Scanner(System.in);
        String choice;
        int ch=0;
        System.out.println("Welcome " + username + "\n");
        do {
            System.out.println("1. View Products\n"+"2. Search Product\n"
        +"3. Add to Cart\n"+"4. View Cart\n"+"5. View Purchase History\n"+ "6. Exit\n");
            
//            System.out.print("Enter your choice: ");
            
            boolean valid = false; // flag to control retry
            while (!valid) {
                try {
                    System.out.print("Enter your choice: ");
                    ch = sc.nextInt();

                    if (ch < 1 || ch > 5) {
                        throw new InputMismatchException("❌ Invalid choice! Please enter 1–5.");
                    }else {
                    valid = true; // input is valid
                }} catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                    sc.nextLine(); // clear wrong input
                }
            }
            
            switch (ch) {
                case 1:
                    System.out.println("👉 View Products (feature pending)");
                    break;
                case 2:
                    System.out.println("👉 Search Product (feature pending)");
                    break;
                case 3:
                    System.out.println("👉 Add to Cart (feature pending)");
                    break;
                case 4:
                    System.out.println("👉 View Cart (feature pending)");
                    break;
                case 5:
                    System.out.println("👉 View Purchase History (feature pending)");
                    break;
                case 6:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
            System.out.println("Do you want to continue?");
            choice=sc.next();
            
        } while (choice.equals(choice));
    }

    // Main Method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            loginUser(sc);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
