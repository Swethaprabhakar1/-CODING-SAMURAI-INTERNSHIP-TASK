import java.util.Scanner;

class Account {
    private int accountNumber;
    private int pin;
    private double balance;

    public Account(int accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean verifyPin(int inputPin) {
        return this.pin == inputPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("₹" + amount + " withdrawn successfully.");
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }
}

public class ATMSystem {
    private static Account[] accounts = {
        new Account(1001, 1234, 5000.0),
        new Account(1002, 5678, 10000.0),
        new Account(1003, 4321, 7500.0)
    };

    private static Account login(Scanner scanner) {
        System.out.print("Enter your Account Number: ");
        int accNum = scanner.nextInt();
        System.out.print("Enter your PIN: ");
        int pin = scanner.nextInt();

        for (Account acc : accounts) {
            if (acc.getAccountNumber() == accNum && acc.verifyPin(pin)) {
                System.out.println("Login successful!\n");
                return acc;
            }
        }
        System.out.println("Invalid account number or PIN.\n");
        return null;
    }

    private static void showMenu(Account acc, Scanner scanner) {
        int choice;
        do {
            System.out.println("ATM Main Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.printf("Your current balance is: ₹%.2f%n%n", acc.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ₹");
                    double dep = scanner.nextDouble();
                    acc.deposit(dep);
                    System.out.println();
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ₹");
                    double wd = scanner.nextDouble();
                    acc.withdraw(wd);
                    System.out.println();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!\n");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.\n");
            }
        } while (choice != 4);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----- Welcome to Java ATM Banking System -----\n");

        Account currentAccount = login(scanner);
        if (currentAccount != null) {
            showMenu(currentAccount, scanner);
        }

        scanner.close();
    }
}
