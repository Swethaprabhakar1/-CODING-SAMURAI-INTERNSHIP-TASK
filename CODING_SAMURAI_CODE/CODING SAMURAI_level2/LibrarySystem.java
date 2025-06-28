import java.util.ArrayList;
import java.util.Scanner;
class Book {
    private String title;
    private String author;
    private boolean isAvailable = true;
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
    public String getTitle() {
        return title;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void borrow() {
        isAvailable = false;
    }
    public void returnBook() {
        isAvailable = true;
    }
    public String toString() {
        return title + " by " + author + (isAvailable ? " (Available)" : " (Borrowed)");
    }
}
class User {
    private String name;
    private ArrayList<String> borrowedBooks = new ArrayList<>();
    public User(String name) {
        this.name = name;
    }
    public void borrowBook(String title) {
        borrowedBooks.add(title);
    }
    public void returnBook(String title) {
        borrowedBooks.remove(title);
    }
    public void showHistory() {
        System.out.println("\nBorrowing history for " + name + ":");
        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            for (String book : borrowedBooks) {
                System.out.println("- " + book);
            }
        }
    }
}
class Library {
    private ArrayList<Book> books = new ArrayList<>();
    public Library() {
        // Sample books
        books.add(new Book("Harry Potter", "J.K. Rowling"));
        books.add(new Book("The Alchemist", "Paulo Coelho"));
        books.add(new Book("Clean Code", "Robert C. Martin"));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien"));
    }
    public void showBooks() {
        System.out.println("\nAvailable Books:");
        for (Book b : books) {
            System.out.println("- " + b);
        }
    }
    public Book searchBook(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }
    public boolean borrowBook(String title, User user) {
        Book book = searchBook(title);
        if (book != null && book.isAvailable()) {
            book.borrow();
            user.borrowBook(title);
            System.out.println(" Book borrowed: " + title);
            return true;
        } else {
            System.out.println("Book not available or doesn't exist.");
            return false;
        }
    }
    public boolean returnBook(String title, User user) {
        Book book = searchBook(title);
        if (book != null && !book.isAvailable()) {
            book.returnBook();
            user.returnBook(title);
            System.out.println(" Book returned: " + title);
            return true;
        } else {
            System.out.println("This book wasn't borrowed or doesn't exist.");
            return false;
        }
    }
}
public class LibrarySystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        System.out.print(" Enter your name: ");
        String userName = scanner.nextLine();
        User user = new User(userName);
        int choice;
        do {
            System.out.println("\n=== Library Menu ===");
            System.out.println("1. Show All Books");
            System.out.println("2. Search Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Borrowing History");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            switch (choice) {
                case 1:
                    library.showBooks();
                    break;
                case 2:
                    System.out.print("🔍 Enter book title to search: ");
                    String titleToSearch = scanner.nextLine();
                    Book found = library.searchBook(titleToSearch);
                    System.out.println(found != null ? found : "Book not found.");
                    break;
                case 3:
                    System.out.print("📘 Enter book title to borrow: ");
                    String titleToBorrow = scanner.nextLine();
                    library.borrowBook(titleToBorrow, user);
                    break;
                case 4:
                    System.out.print(" Enter book title to return: ");
                    String titleToReturn = scanner.nextLine();
                    library.returnBook(titleToReturn, user);
                    break;
                case 5:
                    user.showHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using the Library System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);
        scanner.close();
    }
}
