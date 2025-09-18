package validation;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Exception Handling / Input Validation");

        // Numeric input validation
        getNumericInput(scanner);

        // Non-empty input validation
        getNonEmptyInput(scanner);

        // Email validation
        getEmailInput(scanner);

        // Mobile number validation
        getMobileInput(scanner);

        scanner.close();
    }
    
    // Numeric input validation
    private static void getNumericInput(Scanner scanner) {
        System.out.print("Enter a number: ");
        String input = scanner.nextLine().trim();
        try {
            if (input.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty.");
            }
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numeric values only.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // Non-empty input validation
    private static void getNonEmptyInput(Scanner scanner) {
        System.out.print("Enter your name: ");
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("Input cannot be empty.");
        }
    }
    
    // Email validation
    private static void getEmailInput(Scanner scanner) {
        System.out.print("Enter your email: ");
        String input = scanner.nextLine().trim();
        Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w+$");
        if (!emailPattern.matcher(input).matches()) {
            System.out.println("Please enter a valid email address.");
        }
    }
    
    // Mobile number validation
    private static void getMobileInput(Scanner scanner) {
        System.out.print("Enter your mobile number: ");
        String input = scanner.nextLine().trim();
        if (!input.matches("\\d{10}")) {
            System.out.println("Mobile number must be 10 digits.");
        }
    }
}