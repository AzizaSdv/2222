package com.example.tourmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Admin extends User {
    private Connection connection;

    public Admin(int id, String name, String email, String password) {
        super(id, name, email, password, "admin");
        this.connection = DatabaseHandler.getInstance().getConnection();
    }

    public void addTour(Scanner scanner) {
        System.out.print("Enter tour name: ");
        String name = scanner.nextLine();
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter dates: ");
        String dates = scanner.nextLine();

        // Сохранение в базу данных
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO tours (name, location, price, dates) VALUES (?, ?, ?, ?)")) {
            stmt.setString(1, name);
            stmt.setString(2, location);
            stmt.setDouble(3, price);
            stmt.setString(4, dates);
            stmt.executeUpdate();
            System.out.println("✅ Tour added successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error adding tour: " + e.getMessage());
        }
    }


    public void viewAllTours() {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tours");
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n📋 All Tours:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Name: " + rs.getString("name") +
                        " | Location: " + rs.getString("location") + " | Price: $" + rs.getDouble("price") +
                        " | Dates: " + rs.getString("dates"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error fetching tours: " + e.getMessage());
        }
    }
}

