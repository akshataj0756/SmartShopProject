package com.user.login;

import java.sql.*;
import java.util.Scanner;

public class Login {
    private static final String DB_Driver_Name = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ecommerce_db";
    private static final String DB_Username = "root";
    private static final String DB_Password = "akshu0756";
    private static final String Select_Query = "SELECT * FROM user WHERE username=? AND password=?";

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
    	System.out.println("User Login");
        System.out.print("Enter Username>> ");
        String username = sc.nextLine();

        System.out.print("Enter Password>> ");
        String password = sc.nextLine();
        
        System.out.println("Verifying Username and Password...");
        Login l1=new Login();
        try {
			if (l1.authenticateUser(username, password)) {
			    System.out.println("Login Successful.");
			   
			} else {
			    System.out.println("Invalid Username or Password. Try again.");
			    loginUser(sc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
   
