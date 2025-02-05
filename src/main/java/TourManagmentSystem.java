package com.example.tourmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TourManagementSystem {
    private static Connection connection = DatabaseHandler.getInstance().getConnection();

    public static User login(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.println(" Checking login for: " + email + " with password: " + password);

        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM users WHERE email = ? AND password = ?")) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                if ("admin".equals(role)) {
                    return new Admin(rs.getInt("id"), rs.getString("name"), email, password);
                } else {
                    return new Customer(rs.getInt("id"), rs.getString("name"), email, password);
                }
            } else {
                System.out.println(" Invalid login. Try again.");
                return null;
            }
        } catch (SQLException e) {
            System.out.println(" Login error: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = null;

        while (user == null) {
            user = login(scanner);
        }

        if (user instanceof Admin) {
            adminMenu((Admin) user, scanner);
        } else {
            customerMenu((Customer) user, scanner);
        }

        scanner.close();
    }

    private static void adminMenu(Admin admin, Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n🔹 Admin Menu:");
            System.out.println("1. Add Tour");
            System.out.println("2. View All Tours");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> admin.addTour(scanner);
                case 2 -> admin.viewAllTours();
                case 3 -> running = false;
                default -> System.out.println(" Invalid choice.");
            }
        }
    }

    private static void customerMenu(Customer customer, Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("\n🔹 Customer Menu:");
            System.out.println("1. View Tours");
            System.out.println("2. Book Tour");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> customer.viewTours();
                case 2 -> customer.bookTour(scanner);
                case 3 -> running = false;
                default -> System.out.println("❌ Invalid choice.");
            }
        }
    }
}

