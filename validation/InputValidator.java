package validation;

import java.util.Scanner;

public class InputValidator {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // validation 
        try {
            System.out.print("Enter your age (1-120): ");
            String ageInput = scanner.nextLine();
            int age = validateAge(ageInput);

            System.out.print("Enter your name (3-15 characters): ");
            String name = scanner.nextLine();
            validateName(name);

            System.out.println("All inputs are valid!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: Age must be a number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred. Please try again.");
        } finally {
            scanner.close();
        }
    }
	
	//validate age
    private static int validateAge(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Age cannot be empty.");
        }
        int age = Integer.parseInt(input);
        if (age < 1 || age > 120) {
            throw new IllegalArgumentException("Age must be between 1 and 120.");
        }
        return age;
    }
    
    //validate name
    private static void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (name.length() < 3 || name.length() > 15) {
            throw new IllegalArgumentException("Name must be between 3 and 15 characters.");
        }
    }
}
