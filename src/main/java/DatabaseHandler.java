package com.example.tourmanagement;

import java.sql.*;

public class DatabaseHandler {
    private static DatabaseHandler instance;
    private Connection connection;

    private DatabaseHandler() {
        try {
            // загружаем драйвер PostgreSQL
            Class.forName("org.postgresql.Driver");

            // подключаемся к бд PostgreSQL
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/MoneyTour",
                    "postgres",
                    "Lasto4ka22"
            );




            connection.setAutoCommit(false);
            System.out.println(" Database connected successfully!");

            // вызываем метод для создания таблиц
            setupDatabase();

        } catch (ClassNotFoundException e) {
            System.out.println(" PostgreSQL JDBC Driver not found! Make sure it's in your dependencies.");
        } catch (SQLException e) {
            System.out.println(" Database connection failed: " + e.getMessage());
        }
    }

    public static DatabaseHandler getInstance() {
        if (instance == null) {
            instance = new DatabaseHandler();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    // метод для создания табл
    private void setupDatabase() {
        try (Statement stmt = connection.createStatement()) {

            // Табл пользователей(логины и пароли)
            stmt.execute("CREATE TABLE IF NOT EXISTS users ("
                    + "id SERIAL PRIMARY KEY, "
                    + "name TEXT, "
                    + "email TEXT UNIQUE, "
                    + "password TEXT, "
                    + "role TEXT CHECK(role IN ('admin', 'customer'))"
                    + ")");

            // табл туров
            stmt.execute("CREATE TABLE IF NOT EXISTS tours ("
                    + "id SERIAL PRIMARY KEY, "
                    + "name TEXT, "
                    + "location TEXT, "
                    + "price REAL, "
                    + "dates TEXT"
                    + ")");

            //таблица бронирования
            stmt.execute("CREATE TABLE IF NOT EXISTS bookings ("
                    + "id SERIAL PRIMARY KEY, "
                    + "customer_id INTEGER REFERENCES users(id), "
                    + "tour_id INTEGER REFERENCES tours(id)"
                    + ")");

            // Табл гидов
            stmt.execute("CREATE TABLE IF NOT EXISTS guides ("
                    + "id SERIAL PRIMARY KEY, "
                    + "name TEXT, "
                    + "language TEXT"
                    + ")");

            // таблица отзывов
            stmt.execute("CREATE TABLE IF NOT EXISTS reviews ("
                    + "id SERIAL PRIMARY KEY, "
                    + "tour_id INTEGER REFERENCES tours(id), "
                    + "customer_id INTEGER REFERENCES users(id), "
                    + "rating INTEGER, "
                    + "comment TEXT"
                    + ")");

            //фиксир изменения
            connection.commit();
            System.out.println(" Database setup completed.");

        } catch (SQLException e) {
            System.out.println(" Failed to setup database: " + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                System.out.println(" Rollback failed: " + rollbackEx.getMessage());
            }
        }
    }
    public String authenticateUser(String email, String password) {
        String role = null;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT role FROM users WHERE email = ? AND password = ?")) {

            stmt.setString(1, email);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    role = rs.getString("role"); // Получаем роль (admin или customer)
                }
            }
        } catch (SQLException e) {
            System.out.println("Authentication failed: " + e.getMessage());
        }
        return role; // Если null, значит логин или пароль неверные
    }

}

