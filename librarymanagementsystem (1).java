import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private int id;
    private String title;
    private String author;
    private String genre;
    private int quantity;

    public Book(int id, String title, String author, String genre, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "[Book ID: " + id + ", Title: " + title + ", Author: " + author +
               ", Genre: " + genre + ", Quantity: " + quantity + "]";
    }
}

class Student {
    private int id;
    private String name;
    private String email;
    private String department;

    public Student(int id, String name, String email, String department) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
    }

    public int getId() { return id; }

    @Override
    public String toString() {
        return "[Student ID: " + id + ", Name: " + name + ", Email: " + email +
               ", Department: " + department + "]";
    }
}

public class LibraryManagementSystem {
    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Search Book");
            System.out.println("4. Add Student");
            System.out.println("5. View Students");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    displayBooks();
                    break;
                case 3:
                    searchBook();
                    break;
                case 4:
                    addStudent();
                    break;
                case 5:
                    viewStudents();
                    break;
                case 6:
                    borrowBook();
                    break;
                case 7:
                    returnBook();
                    break;
                case 8:
                    System.out.println("Exiting... Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        books.add(new Book(id, title, author, genre, quantity));
        System.out.println("Book added successfully!");
    }

    private static void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void searchBook() {
        System.out.print("Enter title or author to search: ");
        String query = scanner.nextLine();
        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(query) || book.getAuthor().equalsIgnoreCase(query)) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching book found.");
        }
    }

    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        students.add(new Student(id, name, email, department));
        System.out.println("Student added successfully!");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
        } else {
            students.forEach(System.out::println);
        }
    }

    private static void borrowBook() {
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();

        Student student = students.stream().filter(s -> s.getId() == studentId).findFirst().orElse(null);
        Book book = books.stream().filter(b -> b.getId() == bookId).findFirst().orElse(null);

        if (student == null) {
            System.out.println("Invalid Student ID.");
        } else if (book == null || book.getQuantity() <= 0) {
            System.out.println("Book not available.");
        } else {
            book.setQuantity(book.getQuantity() - 1);
            System.out.println("Book borrowed successfully!");
        }
    }

    private static void returnBook() {
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        Book book = books.stream().filter(b -> b.getId() == bookId).findFirst().orElse(null);
        if (book == null) {
            System.out.println("Invalid Book ID.");
        } else {
            book.setQuantity(book.getQuantity() + 1);
            System.out.println("Book returned successfully!");
        }
    }
}
