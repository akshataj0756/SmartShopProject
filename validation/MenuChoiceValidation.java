package validation;

import java.util.Scanner;

public class MenuChoiceValidation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Welcome to E-Commerce Console Application");

        while (!exit) {
            displayMenu();
            int choice = getValidChoice(scanner);

            switch (choice) {
                case 1:
                    System.out.println("Register selected.");
                    UserRegister userregister = new UserRegister();
                    ur.userRegister();
                    break;
                case 2:
                    System.out.println("Login selected.");
                    Login login = new Login();
                    login.loginUser();
                    break;
                case 3:
                    System.out.println("View Products selected.");
                    ProductViewer productviewer = new ProductViewer();
                    pv.viewAllProducts()
                    break;
                case 4:
                    System.out.println("Search Product selected.");
                    ProductSearch productsearch = new ProductSearch();
                    ps.searchByName(keyword);
                    break;
                case 5:
                    System.out.println("Add to Cart selected.");
                    AddProductToCart addproducttocart = new AddProductToCart();
                    ap.addToCart(productId, quantity);
                    break;
                case 6:
                    System.out.println("View Cart selected.");
                    ViewCartItems viewcartitems = new ViewCartItems();
                    break;
                case 7:
                    System.out.println("View Purchase History selected.");
                    ViewUserPurchaseHistory viewuserpurchasehistory = new ViewUserPurchaseHistory();
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

        scanner.close();
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