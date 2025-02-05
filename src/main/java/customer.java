package com.example.tourmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Customer extends User {
    private Connection connection;

    public Customer(int id, String name, String email, String password) {
        super(id, name, email, password, "customer");
        this.connection = DatabaseHandler.getInstance().getConnection();
    }

    public void viewTours() {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tours");
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("\n📌 Available Tours:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                        ", Location: " + rs.getString("location") + ", Price: $" + rs.getDouble("price") +
                        ", Dates: " + rs.getString("dates"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error retrieving tours: " + e.getMessage());
        }
    }


    public void bookTour(Scanner scanner) {
        System.out.print("Enter tour ID to book: ");
        int tourId = scanner.nextInt();
        scanner.nextLine();

        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO bookings (customer_id, tour_id) VALUES (?, ?)")) {
            stmt.setInt(1, id);
            stmt.setInt(2, tourId);
            stmt.executeUpdate();
            System.out.println("✅ Tour booked successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error booking tour: " + e.getMessage());
        }
    }
}

