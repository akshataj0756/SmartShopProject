package com.smartshop.userinterface;

import java.sql.*;
import java.util.Scanner;

public class Login {
	private static final String DB_Driver_Name = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ecommerce_db";
	private static final String DB_Username = "root";
	private static final String DB_Password = "root";
	private static final String Select_Query = "SELECT * FROM users WHERE username=? AND password=?";
	private static final String Select_Query1 = "SELECT * FROM users WHERE password=?";

	// DB Connection
	public static Connection getConnection() throws Exception {
		Connection con = null;
		Class.forName(DB_Driver_Name);
		con = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
		return con;
	}

	// Authenticate User
	public boolean authenticateUser(String username, String password) throws Exception {
		Connection con = getConnection(); // calling the getConnection() method
		PreparedStatement ps = con.prepareStatement(Select_Query); // Selects the data of User Matching with username
																	// and password
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}

	// Checks foe the password in DB
	public static boolean isPasswordExists(String password) throws Exception {
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(Select_Query1);
		ps.setString(1, password);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}

	public static void loginUser(Scanner sc) throws InvalidInputException {
		System.out.println("User Login");
		try {
			System.out.print("Enter Username>> ");
			String username = sc.nextLine();
			if (username.trim().isEmpty()) {
				throw new IllegalArgumentException("Input cannot be empty.");
			} else if (!UserRegister.isUsernameExists(username)) {
				// throws an exception if username is not exist in DB
				throw new InvalidInputException("Invalid Username. Please Register first then try Again.");
			}

			System.out.print("Enter Password>> ");
			String password = sc.nextLine();
			if (username.trim().isEmpty()) {
				throw new IllegalArgumentException("Input cannot be empty.");
			} else if (!isPasswordExists(password)) {
				// throws an exception if Correct Password is not exist in DB
				throw new InvalidInputException("Invalid Password. Please Enter Correct Password.");
			}
			System.out.println("Verifying Username and Password...");
			Login l1 = new Login();
			if (l1.authenticateUser(username, password)) {
				System.out.println("Login Successful.");

			} else {
				loginUser(sc);
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e.getMessage());
		}
	}
}
