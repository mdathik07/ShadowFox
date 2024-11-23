import java.util.Scanner;

public class EnhancedCalculator {


    public static void basicArithmetic(Scanner scanner) {
        System.out.println("---- Basic Arithmetic ----");
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();
        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();
    
        System.out.println("Choose operation:");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        int operation = scanner.nextInt();
    
        switch (operation) {
            case 1:
                System.out.println("Result: " + (num1 + num2));
                break;
            case 2:
                System.out.println("Result: " + (num1 - num2));
                break;
            case 3:
                System.out.println("Result: " + (num1 * num2));
                break;
            case 4:
                if (num2 != 0) {
                    System.out.println("Result: " + (num1 / num2));
                } else {
                    System.out.println("Error: Division by zero!");
                }
                break;
            default:
                System.out.println("Invalid operation!");
        }
    }
    

    public static void scientificCalculations(Scanner scanner) {
        System.out.println("---- Scientific Calculations ----");
        System.out.println("1. Square Root");
        System.out.println("2. Exponentiation");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
    
        switch (choice) {
            case 1:
                System.out.print("Enter number: ");
                double num = scanner.nextDouble();
                System.out.println("Square Root: " + Math.sqrt(num));
                break;
            case 2:
                System.out.print("Enter base: ");
                double base = scanner.nextDouble();
                System.out.print("Enter exponent: ");
                double exponent = scanner.nextDouble();
                System.out.println("Result: " + Math.pow(base, exponent));
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }


    public static void unitConversions(Scanner scanner) {
        System.out.println("---- Unit Conversions ----");
        System.out.println("1. Temperature Conversion");
        System.out.println("2. Currency Conversion");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
    
        switch (choice) {
            case 1:
                temperatureConversion(scanner);
                break;
            case 2:
                currencyConversion(scanner);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    public static void temperatureConversion(Scanner scanner) {
        System.out.println("1. Celsius to Fahrenheit");
        System.out.println("2. Fahrenheit to Celsius");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
    
        System.out.print("Enter temperature: ");
        double temp = scanner.nextDouble();
        switch (choice) {
            case 1:
                System.out.println("Fahrenheit: " + (temp * 9/5 + 32));
                break;
            case 2:
                System.out.println("Celsius: " + ((temp - 32) * 5/9));
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    public static void currencyConversion(Scanner scanner) {
        System.out.println("1. USD to INR");
        System.out.println("2. INR to EUR");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
    
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        switch (choice) {
            case 1:
                System.out.println("INR: " + (amount * 74.50));  // Example rate
                break;
            case 2:
                System.out.println("EUR: " + (amount * 0.012));  // Example rate
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("---- Enhanced Calculator ----");
            System.out.println("1. Basic Arithmetic Operations");
            System.out.println("2. Scientific Calculations");
            System.out.println("3. Unit Conversions");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    basicArithmetic(scanner);
                    break;
                case 2:
                    scientificCalculations(scanner);
                    break;
                case 3:
                    unitConversions(scanner);
                    break;
                case 4:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
