import java.io.*;
import java.util.*;

class User {
    String username, password;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    static boolean register(String username, String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("users.txt", true))) {
            bw.write(username + "," + password);
            bw.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    static boolean login(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username) && data[1].equals(password)) return true;
            }
        } catch (IOException e) {
            return false;
        }
        return false;
    }
}

class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id; this.name = name; this.price = price;
    }

    static void showProducts(List<Product> products) {
        System.out.println("Available Products:");
        for (Product p : products) {
            System.out.println(p.id + ". " + p.name + " - $" + p.price);
        }
    }
}

class Cart {
    List<Product> cartItems = new ArrayList<>();

    void addProduct(Product p) {
        cartItems.add(p);
        System.out.println(p.name + " added to cart.");
    }

    void viewCart() {
        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("Items in Cart:");
        double total = 0;
        for (Product p : cartItems) {
            System.out.println("- " + p.name + " $" + p.price);
            total += p.price;
        }
        System.out.println("Total: $" + total);
    }

    void checkout() {
        if (cartItems.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            viewCart();
            System.out.println("Checkout successful. Thank you for your purchase!");
            cartItems.clear();
        }
    }
}

public class ECommerceApp {
    static Scanner sc = new Scanner(System.in);
    static List<Product> products = new ArrayList<>();
    static Cart cart = new Cart();

    public static void main(String[] args) {
        initProducts();
        System.out.println("Welcome to Java E-Commerce System");

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("1. Register\n2. Login\nChoose option:");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            System.out.print("Username: ");
            String user = sc.nextLine();
            System.out.print("Password: ");
            String pass = sc.nextLine();

            if (choice == 1) {
                if (User.register(user, pass)) {
                    System.out.println("Registration successful! Please login.");
                }
            } else if (choice == 2) {
                if (User.login(user, pass)) {
                    System.out.println("Login successful. Welcome, " + user + "!");
                    loggedIn = true;
                } else {
                    System.out.println("Invalid credentials. Try again.");
                }
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("\n1. View Products\n2. Add to Cart\n3. View Cart\n4. Checkout\n5. Exit");
            int option = sc.nextInt();
            switch (option) {
                case 1 -> Product.showProducts(products);
                case 2 -> {
                    System.out.print("Enter product ID to add: ");
                    int pid = sc.nextInt();
                    Product p = findProductById(pid);
                    if (p != null) cart.addProduct(p);
                    else System.out.println("Invalid product ID.");
                }
                case 3 -> cart.viewCart();
                case 4 -> cart.checkout();
                case 5 -> {
                    running = false;
                    System.out.println("Thanks for visiting!");
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void initProducts() {
        products.add(new Product(1, "Laptop", 599.99));
        products.add(new Product(2, "Smartphone", 399.99));
        products.add(new Product(3, "Headphones", 49.99));
        products.add(new Product(4, "Keyboard", 29.99));
    }

    static Product findProductById(int id) {
        for (Product p : products) {
            if (p.id == id) return p;
        }
        return null;
    }
}
