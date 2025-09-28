package com.smartshop.userinterface;

import java.sql.*;
import java.util.Scanner;
import com.smartshop.validation.InputValidator;

public class UserRegister {
	private static final String DB_Driver_Name = "com.mysql.cj.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ecommerce_db";
	private static final String DB_Username = "root";
	private static final String DB_Password = "root";
	private static final String Insert_Query = "INSERT INTO users(first_name,last_name ,username ,password ,city ,email ,mobile) VALUES(?,?,?,?,?,?,?)";
	private static final String Select_Query = "SELECT * FROM users WHERE username=?";

	// DB Connection
	public static Connection getConnection() throws Exception {
		Connection con = null;
		Class.forName(DB_Driver_Name);
		con = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
		return con; // returns the connection object
	}

	// Epic 1) 1.3 Check UserName Exist or Not
	public static boolean isUsernameExists(String username) throws Exception {
		Connection con = getConnection(); // Calling getConnection() Method
		PreparedStatement ps = con.prepareStatement(Select_Query); // selects data of user from DB
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}

	// Save User to DB
	public static void saveUser(String fname, String lname, String username, String password, String city, String email,
			String mobile) throws Exception {
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement(Insert_Query); // inserts the data of user into DB
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

	// User Registration Method
	public void userRegister() {
		Scanner sc = new Scanner(System.in);

		try {
			System.out.println("Welcome to User Registration");

			System.out.print("Enter First Name: ");
			String fname = sc.nextLine();
			if (fname.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty.");
            }
			System.out.print("Enter Last Name: ");
			String lname = sc.nextLine();
			if (lname.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty.");
            }
			// Username check
			String username;
			while (true) {
				System.out.print("Enter Username: ");
				username = sc.nextLine();
				if (isUsernameExists(username)) {
					System.out.println("Username already exists! Try another.");
				}else if (username.isEmpty()) {
	                throw new IllegalArgumentException("Input cannot be empty.");
	            }
				else {
					break;
				}
			}

			System.out.print("Enter Password: ");
			String password = sc.nextLine();
			if (password.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty.");
            }

			System.out.print("Enter City: ");
			String city = sc.nextLine();
			if (city.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty.");
            }
			// Email validation
			System.out.print("Enter Email: ");
			String email = sc.nextLine();
			if (!InputValidator.isValidEmail(email)) {
				System.out.println("Invalid email format. Try again.");
			}else if (email.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty.");
            }

			// Mobile validation
			while (true) {
				System.out.print("Enter Mobile Number: ");
				String mobile1 = sc.nextLine();
				if (InputValidator.isValidMobile(mobile1)) {
					// Save user in DB
					saveUser(fname, lname, username, password, city, email, mobile1);
					break;
				}else if (mobile1.isEmpty()) {
	                throw new IllegalArgumentException("Input cannot be empty.");
	            }
				else {
					System.out.println("Invalid mobile number. Enter 10 digits.");
				}
			}

		} catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Something went wrong: " + e.getMessage());
		} finally {
			sc.close();
		}
	}
}
