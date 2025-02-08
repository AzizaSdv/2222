#Tour Management System

#Project Description

The Tour Management System is a Java-based application designed to facilitate the management of tour services. The system allows users to book tours, manage itineraries, process payments, and collect user reviews. It is built with modular architecture, enabling easy scalability and maintenance.

#Features

Tour Booking - Users can book tours online = booking.java

User Management - Supports different user roles: customers and admins (User.java, customer.java, admin.java)

Tour Itineraries - Create and manage tour routes (Itinerary.java)

Payment Processing - Secure payment transactions (Payment.java)

Tour Management - Admins can add, edit, and remove tours (Tour.java)

Review System - Users can leave feedback and ratings (Review.java)

Promotions and Discounts - Apply special offers (Promotion.java)

Database Integration - Uses MySQL for storing tour and user data (DatabaseHandler.java)

#Technologies Used
Programming Language: Java
Database: PostgreSQL
Maven

#Main Classes and Their Roles

1. TourManagementSystem.java

The main entry point of the application.

Initializes system components and loads necessary configurations.

Manages the interaction between different system modules.

2. DatabaseHandler.java

Handles database connectivity and SQL operations.

Provides methods to insert, update, delete, and retrieve data.

Ensures secure database transactions and connection pooling.

3. User.java

Represents a generic system user with attributes such as ID, name, and email.

Acts as a base class for customer and admin users.

4. customer.java

Extends the User class to add customer-specific functionalities.

Allows customers to book tours, make payments, and leave reviews.

5. admin.java

Extends the User class and provides administrative privileges.

Enables tour creation, modification, and deletion.

Manages system users and bookings.

6. Tour.java

Represents a tour entity with details like name, description, price, and duration.

Stores and retrieves tour-related data from the database.

7. Itinerary.java

Manages tour itineraries, defining activities and schedules for each tour.

Ensures that each tour has a structured and detailed plan.

8. Payment.java

Handles the processing of customer payments.

Includes methods to validate transactions, generate receipts, and manage refunds.

9. Review.java

Stores and manages user reviews and ratings for tours.

Allows users to provide feedback on completed tours.

10. Promotion.java

Manages discounts and special promotions for tours.

Provides methods to apply promotional codes and calculate discounted prices.

11. booking.java

Manages the booking of tours by customers.

Ensures that tour availability is checked before confirming a reservation 
#Using Command Line

Open a terminal or command prompt.
Navigate to the project root directory.
Execute the following command to start the application:
java -jar target/tour-management-system.jar

Using an IDE

Open the project in an IDE such as IntelliJ IDEA or Eclipse.
Ensure that the required dependencies are installed.
Configure the database connection inside DatabaseHandler.java.
Run TourManagementSystem.java as a Java application.

Verifying the Setup
Open a web browser or API testing tool like Postman.
Check if the application is running and responding.
Use the admin panel to manage tours, bookings, and users.
