package jdbcconnectivity;

import java.sql.*;
import java.util.*;

public class Main {

    private static Connection conn;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Train Ticket Booking System");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Registration");
            System.out.println("3. Customer Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    adminLogin(scanner);
                    break;
                case 2:
                    customerRegistration(scanner);
                    break;
                case 3:
                    customerLogin(scanner);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void connect() {
        try {
			String driver = "org.apache.derby.jdbc.EmbeddedDriver";
			String url = "jdbc:derby:D:\\PLAY\\Eclipse\\TCS_Programs\\Train_Booking_System\\MYDB\\TrainManDB;create=true";
			// String url = "jdbc:derby:C:\\Users\\inith\\MyDB;create=true";
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    private static void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void adminLogin(Scanner scanner) {
        System.out.print("Enter Admin Username: ");
        String username = scanner.next();
        System.out.print("Enter Admin Password: ");
        String password = scanner.next();
        if (username.equals("admin") && password.equals("admin123")) {
            adminMenu(scanner);
        } else {
            System.out.println("Incorrect Username or Password.");
        }
    }

    private static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Register Train");
            System.out.println("2. Update Train");
            System.out.println("3. Delete Train");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    registerTrain(scanner);
                    break;
                case 2:
                    updateTrain(scanner);
                    break;
                case 3:
                    deleteTrain(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void registerTrain(Scanner scanner) {
    	connect();
    	try {
    	    String query = "CREATE TABLE IF NOT EXISTS Customers ("
    	            + "username VARCHAR(50) PRIMARY KEY,"
    	            + "password VARCHAR(50) NOT NULL,"
    	            + "name VARCHAR(100),"
    	            + "email VARCHAR(100) UNIQUE,"
    	            + "phone CHAR(10) CHECK (LENGTH(phone) = 10),"
    	            + "active BOOLEAN DEFAULT TRUE);";
    	    PreparedStatement ps = conn.prepareStatement(query);
    	    ps.executeUpdate();
    	    System.out.println("Customers Table Created");
    	} catch(SQLException e) {
    	    System.out.println("Error while Creating Customers Table : " + e.getMessage());
    	}
        try {
            System.out.println("Enter Train Number: ");
            int trainNumber = scanner.nextInt();
            System.out.println("Enter Train Name: ");
            String trainName = scanner.next();
            System.out.println("Enter Origin Station: ");
            String origin = scanner.next();
            System.out.println("Enter Destination Station: ");
            String destination = scanner.next();
            System.out.println("Enter Departure Date: ");
            String departure = scanner.next();
            System.out.println("Enter Arrival Date: ");
            String arrival = scanner.next();
            System.out.println("Enter Total no. of seats available: ");
            int seats = scanner.nextInt();

            String query = "INSERT INTO Trains (train_number, train_name, origin, destination, departure, arrival, available_seats) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, trainNumber);
            ps.setString(2, trainName);
            ps.setString(3, origin);
            ps.setString(4, destination);
            ps.setString(5, departure);
            ps.setString(6, arrival);
            ps.setInt(7, seats);
            ps.executeUpdate();
            System.out.println("Train registered successfully.");
        } catch (SQLException e) {
            System.out.println("Error registering train: " + e.getMessage());
        } finally {
            close();
        }
    }

    private static void updateTrain(Scanner scanner) {
        connect();
        try {
            System.out.print("Enter Train Number to Update: ");
            int trainNumber = scanner.nextInt();
            System.out.print("Enter new Train Name: ");
            String trainName = scanner.next();
            System.out.print("Enter new Origin Station: ");
            String origin = scanner.next();
            System.out.print("Enter new Destination Station: ");
            String destination = scanner.next();
            System.out.print("Enter new Departure Date: ");
            String departure = scanner.next();
            System.out.print("Enter new Arrival Date: ");
            String arrival = scanner.next();
            System.out.println("Enter new Total no. of seats available: ");
            int seats = scanner.nextInt();

            String query = "UPDATE Trains SET train_name=?, origin=?, destination=?, departure=?, arrival=? WHERE train_number=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, trainName);
            ps.setString(2, origin);
            ps.setString(3, destination);
            ps.setString(4, departure);
            ps.setString(5, arrival);
            ps.setInt(6, trainNumber);
            ps.setInt(7, seats);
            ps.executeUpdate();
            System.out.println("Train details updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating train: " + e.getMessage());
        } finally {
            close();
        }
    }

    private static void deleteTrain(Scanner scanner) {
        connect();
        try {
            System.out.print("Enter Train Number to Delete: ");
            int trainNumber = scanner.nextInt();

            String query = "DELETE FROM Trains WHERE train_number=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, trainNumber);
            ps.executeUpdate();
            System.out.println("Train deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting train: " + e.getMessage());
        } finally {
            close();
        }
    }

    private static void customerRegistration(Scanner scanner) {
        System.out.print("Enter Username: ");
        String username = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();
        System.out.print("Enter Name: ");
        String name = scanner.next();
        System.out.print("Enter Email: ");
        String email = scanner.next();
        System.out.print("Enter Phone (10 digits): ");
        String phone = scanner.next();
        if (!phone.matches("\\d{10}")) {
            System.out.println("Invalid phone number. It should contain exactly 10 digits.");
            return;
        }
        if (!email.matches("\\S+@\\S+\\.\\S+")) {
            System.out.println("Invalid email format.");
            return;
        }

        connect();
        try {
            String query = "INSERT INTO Customers (username, password, name, email, phone) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, name);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.executeUpdate();
            System.out.println("Customer registered successfully.");
        } catch (SQLException e) {
            System.out.println("Error registering customer: " + e.getMessage());
        } finally {
            close();
        }
    }

    private static void customerLogin(Scanner scanner) {
    	System.out.println("\nLogin Menu");
        System.out.print("Enter Username: ");
        String username = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();
        if (authenticateCustomer(username, password)) {
            customerMenu(scanner, username);
        } else {
            System.out.println("Incorrect Username/Password or Deactivated account");
        }
    }

    private static boolean authenticateCustomer(String username, String password) {
        connect();
        boolean authenticated = false;
        try {
            String query = "SELECT * FROM Customers WHERE username=? AND password=? AND active=true";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            authenticated = rs.next();
        } catch (SQLException e) {
            System.out.println("Error during authentication: " + e.getMessage());
        } finally {
            close();
        }
        return authenticated;
    }

    private static void customerMenu(Scanner scanner, String username) {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Update Details");
            System.out.println("2. Deactivate Account");
            System.out.println("3. Display Available Trains");
            System.out.println("4. Book Ticket");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. View Booking History");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    updateCustomerDetails(scanner, username);
                    break;
                case 2:
                    deactivateAccount(scanner, username);
                    break;
                case 3:
                    displayAvailableTrains(scanner);
                    break;
                case 4:
                    bookTicket(scanner, username);
                    break;
                case 5:
                    cancelTicket(scanner, username);
                    break;
                case 6:
                    viewBookingHistory(username);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void updateCustomerDetails(Scanner scanner, String username) {
        connect();
        try {
            System.out.print("Enter new Name: ");
            String name = scanner.next();
            System.out.print("Enter new Email: ");
            String email = scanner.next();
            System.out.print("Enter new Phone (10 digits): ");
            String phone = scanner.next();
            if (!phone.matches("\\d{10}")) {
                System.out.println("Invalid phone number. It should contain exactly 10 digits.");
                return;
            }
            if (!email.matches("\\S+@\\S+\\.\\S+")) {
                System.out.println("Invalid email format.");
                return;
            }

            String query = "UPDATE Customers SET name=?, email=?, phone=? WHERE username=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, username);
            ps.executeUpdate();
            System.out.println("Customer details updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating customer details: " + e.getMessage());
        } finally {
            close();
        }
    }

    private static void deactivateAccount(Scanner scanner, String username) {
        connect();
        try {
            String query = "UPDATE Customers SET active=false WHERE username=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.executeUpdate();
            System.out.println("Account deactivated successfully.");
        } catch (SQLException e) {
            System.out.println("Error deactivating account: " + e.getMessage());
        } finally {
            close();
        }
    }

    private static void displayAvailableTrains(Scanner scanner) {
        connect();
        try {
            System.out.print("Enter Origin Station: ");
            String origin = scanner.next();
            System.out.print("Enter Destination Station: ");
            String destination = scanner.next();
            System.out.print("Enter Departure Date (yyyy-mm-dd): ");
            String date = scanner.next();

            String query = "SELECT * FROM Trains WHERE origin=? AND destination=? AND departure LIKE ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, origin);
            ps.setString(2, destination);
            ps.setString(3, date + "%");
            ResultSet rs = ps.executeQuery();

            System.out.println("Available Trains:");
            while (rs.next()) {
                System.out.println("Train Number: " + rs.getInt("train_number"));
                System.out.println("Train Name: " + rs.getString("train_name"));
                System.out.println("Departure: " + rs.getString("departure"));
                System.out.println("Arrival: " + rs.getString("arrival"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error displaying trains: " + e.getMessage());
        } finally {
            close();
        }
    }
    
    private static void bookTicket(Scanner scanner, String username) {
        connect();
        try {
            System.out.print("Enter Train Number: ");
            int trainNumber = scanner.nextInt();
            System.out.print("Enter Travel Date (yyyy-mm-dd): ");
            String date = scanner.next();
            System.out.print("Enter Preferred Class (e.g., Sleeper, AC): ");
            String preferredClass = scanner.next();
            System.out.print("Enter number of seats required: ");
            int tickets_needed = scanner.nextInt();

            String checkAvailabilityQuery = "SELECT available_seats FROM Trains WHERE train_number=?";
            PreparedStatement ps = conn.prepareStatement(checkAvailabilityQuery);
            ps.setInt(1, trainNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int availableSeats = rs.getInt("available_seats");
                if (availableSeats >= tickets_needed) {
                	if(rs.getString("status").equals(""))
                    System.out.print("Seats available! Please confirm your booking (yes/no): ");
                    String confirmation = scanner.next();
                    if (confirmation.equalsIgnoreCase("yes")) {
                        String bookingQuery = "INSERT INTO Bookings (username, train_number, travel_date, class, total_seats, status) VALUES (?, ?, ?, ?, ?, 'Booked')";
                        PreparedStatement psBooking = conn.prepareStatement(bookingQuery);
                        psBooking.setString(1, username);
                        psBooking.setInt(2, trainNumber);
                        psBooking.setString(3, date);
                        psBooking.setString(4, preferredClass);
                        psBooking.setInt(5, tickets_needed);
                        psBooking.executeUpdate();

                        String updateSeatsQuery = "UPDATE Trains SET available_seats = available_seats - ? WHERE train_number=?";
                        PreparedStatement psUpdateSeats = conn.prepareStatement(updateSeatsQuery);
                        psUpdateSeats.setInt(1, tickets_needed);
                        psUpdateSeats.setInt(2, trainNumber);
                        psUpdateSeats.executeUpdate();

                        System.out.println("Booking successful.");
                    } else {
                        System.out.println("Booking cancelled.");
                    }
                } else {
                    System.out.println("Not enough seats available.");
                }
            } else {
                System.out.println("Invalid Train Number.");
            }
        } catch (SQLException e) {
            System.out.println("Error booking ticket: " + e.getMessage());
        } finally {
            close();
        }
    }

    
    private static void cancelTicket(Scanner scanner, String username) {
        connect();
        try {
            System.out.print("Enter Booking ID: ");
            int bookingId = scanner.nextInt();

            String verifyQuery = "SELECT * FROM Bookings WHERE booking_id=? AND username=?";
            PreparedStatement ps = conn.prepareStatement(verifyQuery);
            ps.setInt(1, bookingId);
            ps.setString(2, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String status = rs.getString("status");
                if ("Booked".equalsIgnoreCase(status)) {
                    System.out.println("Booking ID verified.");
                    String updateStatusQuery = "UPDATE Bookings SET status='Cancelled' WHERE booking_id=?";
                    PreparedStatement psUpdateStatus = conn.prepareStatement(updateStatusQuery);
                    psUpdateStatus.setInt(1, bookingId);
                    psUpdateStatus.executeUpdate();

                    int trainNumber = rs.getInt("train_number");
                    int seatsToCancel = rs.getInt("total_seats");

                    String updateSeatsQuery = "UPDATE Trains SET available_seats = available_seats + ? WHERE train_number=?";
                    PreparedStatement psUpdateSeats = conn.prepareStatement(updateSeatsQuery);
                    psUpdateSeats.setInt(1, seatsToCancel);
                    psUpdateSeats.setInt(2, trainNumber);
                    psUpdateSeats.executeUpdate();

                    System.out.println("Ticket cancelled successfully.");
                } else {
                    System.out.println("Ticket cannot be cancelled as it is not in 'Booked' status.");
                }
            } else {
                System.out.println("Invalid Booking ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error cancelling ticket: " + e.getMessage());
        } finally {
            close();
        }
    }
    
    private static void viewBookingHistory(String username) {
        connect();
        try {
            String query = "SELECT * FROM Bookings WHERE username=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            System.out.println("Booking History:");
            while (rs.next()) {
                System.out.println("Booking ID: " + rs.getInt("booking_id"));
                System.out.println("Train Number: " + rs.getInt("train_number"));
                System.out.println("Travel Date: " + rs.getString("travel_date"));
                System.out.println("Class: " + rs.getString("class"));
                System.out.println("Status: " + rs.getString("status"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error viewing booking history: " + e.getMessage());
        } finally {
            close();
        }
    }
}
