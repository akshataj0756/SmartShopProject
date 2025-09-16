package com.user.registration;

import java.sql.*;
import java.util.Scanner;


public class UserRegister {
	private static final String DB_Driver_Name="com.mysql.cj.jdbc.Driver";
	private static final String DB_URL="jdbc:mysql://localhost:3306/smartshop";
	private static final String DB_Username="root";
	private static final String DB_Password="akshu0756";
	private static final String Insert_Query="INSERT INTO users(first_name,last_name,username,password,city,email,mobile) VALUES(?,?,?,?,?,?,?)";
    private static final String Select_Query="SELECT * FROM users WHERE username=?";
	
    
    // DB Connection
    public static Connection getConnection() throws Exception {
    	Connection con=null;
        Class.forName(DB_Driver_Name);
        con=DriverManager.getConnection(DB_URL,DB_Username,DB_Password);
        return con;
    }

   // Check UserName Exist or Not
    public static boolean isUsernameExists(String username) throws Exception {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(Select_Query);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        return rs.next(); 
    }

    // Save User to DB
    public static void saveUser(String fname, String lname, String username,
                                String password, String city, String email, String mobile) throws Exception {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(Insert_Query);
        ps.setString(1, fname);
        ps.setString(2, lname);
        ps.setString(3, username);
        ps.setString(4, password);
        ps.setString(5, city);
        ps.setString(6, email);
        ps.setString(7, mobile);

        ps.executeUpdate();
        System.out.println("User registered successfully.");
    }

  // Check Validation
    private static boolean isValidEmail(String email) {
    	if(email.contains("@")&&(email.endsWith(".com")||email.endsWith(".in")||email.endsWith(".edu")))
    	{
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    private static boolean isValidMobile(String mobile) {
    	
           if(mobile.length()==10) {
        	return true;
           }
           else {
        	 return false;
    }
    }
    // Main Method
    	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Welcome to User Registration");

            System.out.print("Enter First Name: ");
            String fname = sc.nextLine();

            System.out.print("Enter Last Name: ");
            String lname = sc.nextLine();

            // Username check
            String username;
            while (true) {
                System.out.print("Enter Username: ");
                username = sc.nextLine();
                if (isUsernameExists(username)) {
                    System.out.println("Username already exists! Try another.");
                } else {
                    break;
                }
            }

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            System.out.print("Enter City: ");
            String city = sc.nextLine();

            // Email validation
            System.out.print("Enter Email: ");
                String email = sc.nextLine();
                if (!isValidEmail(email)) {
                	System.out.println("Invalid email format. Try again.");
                }
              

            // Mobile validation
                while(true) {
                System.out.print("Enter Mobile Number: ");
                String mobile1 = sc.nextLine();
                if (isValidMobile(mobile1)) {
                	 saveUser(fname,lname,username,password,city,email,mobile1);
                }
                else {
                	System.out.println("Invalid mobile number. Enter 10 digits.");
                }
                }

            // Save user in DB
           
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
