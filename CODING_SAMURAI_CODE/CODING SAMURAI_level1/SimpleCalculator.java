import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char choice;

        System.out.println("Simple Calculator - Java Version");
        System.out.println("--------------------------------");

        do {
            System.out.print("Enter first number: ");
            double num1 = scanner.nextDouble();

            System.out.print("Enter an operator (+, -, *, /): ");
            char operator = scanner.next().charAt(0);

            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();

            double result;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    System.out.println("Result: " + result);
                    break;
                case '-':
                    result = num1 - num2;
                    System.out.println("Result: " + result);
                    break;
                case '*':
                    result = num1 * num2;
                    System.out.println("Result: " + result);
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                        System.out.println("Result: " + result);
                    } else {
                        System.out.println("Error: Division by zero is not allowed.");
                    }
                    break;
                default:
                    System.out.println("Invalid operator. Please use +, -, *, or /.");
            }

            System.out.print("\nDo you want to perform another calculation? (y/n): ");
            choice = scanner.next().charAt(0);
            System.out.println();

        } while (choice == 'y' || choice == 'Y');

        System.out.println("Calculator closed.");
        scanner.close();
    }
}
