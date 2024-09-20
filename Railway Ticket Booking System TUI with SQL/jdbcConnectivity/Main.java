package jdbcConnectivity;

import java.sql.*;
import java.util.*;

public class Main {
    private static Connection conn;
    static PreparedStatement ps = null;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome to the Train Ticket Booking System");
            System.out.println("1. Admin Registeration");
            System.out.println("2. Admin Login");
            System.out.println("3. Customer Registration");
            System.out.println("4. Customer Login");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();scanner.nextLine();
            switch (choice) {
                case 1:
                	adminRegister(scanner);
                    break;
                case 2:
                	adminLogin(scanner);
                    break;
                case 3:
                	customerRegistration(scanner);
                    break;
                case 4:
                	customerLogin(scanner);
                    break;
                case 5:
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
			String url = "jdbc:derby:C:\\Users\\2729299\\MyDB\\TrainManDB;create=true";
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
    
    private static void adminRegister(Scanner sc) {
    	connect();
    	System.out.print("Admin Username: ");
        String username = sc.nextLine();
        System.out.print("Admin Password: ");
        String password = sc.nextLine();
        PreparedStatement ps = null;
        try {
        	String createTableSQL = "CREATE TABLE ADMIN (adminUsername VARCHAR(20) PRIMARY KEY, password VARCHAR(30))";
    		PreparedStatement ps1 = conn.prepareStatement(createTableSQL);
    		ps1.executeUpdate();
    		System.out.println("Table Admin created");
    		String query = "INSERT INTO Admin (adminUsername, password) VALUES (?, ?)";
        	ps = conn.prepareStatement(query);
        	ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            System.out.println("Admin registered.");
        }catch(Exception e) {
        	System.out.println("Table Already Created");
        }finally {        	
        	close();
        }
    }

    private static void adminLogin(Scanner scanner) {
    	connect();
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();
        String aUname = "";
    	String adPasswd = "";
        try {
        	String loginQuery = "select adminUsername, password from ADMIN WHERE adminUsername=? and password=?";
        	PreparedStatement ps = conn.prepareStatement(loginQuery);
        	ps.setString(1, username);
        	ps.setString(2, password);
        	ResultSet rs = ps.executeQuery();
        	while(rs.next()) {
        		aUname = rs.getString("adminUsername");
        		adPasswd = rs.getString("password");
        	}
        	if (username.equals(aUname) && password.equals(adPasswd)) {
                adminMenu(scanner);
            } else {
                System.out.println("Incorrect Username or Password.");
            }
        }catch(SQLException e) {
        	System.out.println("Error while Logging in : " + e.getMessage());
        }finally {
        	close();
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
            int choice = scanner.nextInt();scanner.nextLine();
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
    	int trainNumber;
    	String trainName;
    	String origin;
    	String destination;
    	String departure;
    	String departure_time;
    	String arrival;
    	String arrival_time;
    	int seats;
    	PreparedStatement ps = null;
        try {
        	String createTableSQL = "CREATE TABLE Trains (train_number INT PRIMARY KEY,"
        			+ "train_name VARCHAR(100) NOT NULL,"
        			+ "origin VARCHAR(100) NOT NULL,"
        			+ "destination VARCHAR(100) NOT NULL,"
        			+ "departure VARCHAR(10) NOT NULL,"
        			+ "departure_time VARCHAR(10) NOT NULL,"
        			+ "arrival VARCHAR(10) NOT NULL,"
        			+ "arrival_time VARCHAR(10) NOT NULL,"
        			+ "available_seats INT NOT NULL,"
        			+ "intermediate_stations varchar(200) NOT NULL)";
    		ps = conn.prepareStatement(createTableSQL);
    		ps.executeUpdate();
    		System.out.println("Table Train created");
        } catch (SQLException e) {
            System.out.println(" Train Table Created Already: " + e.getMessage());
        }

		System.out.println("Enter Train Number: ");
        trainNumber = scanner.nextInt();scanner.nextLine();
        System.out.println("Enter Train Name: ");
        trainName = scanner.nextLine();
        System.out.println("Enter Origin Station: ");
        origin = scanner.nextLine();
        System.out.println("Enter Destination Station: ");
        destination = scanner.nextLine();
        System.out.println("Enter Departure Date: ");
        departure = scanner.nextLine();
        System.out.println("Enter Departure Time: ");
        departure_time = scanner.nextLine();
        System.out.println("Enter Arrival Date: ");
        arrival = scanner.nextLine();
        System.out.println("Enter Arrival Time: ");
        arrival_time = scanner.nextLine();
        System.out.println("Enter Total no. of seats available: ");
        seats = scanner.nextInt();scanner.nextLine();
        System.out.println("Enter the intermediate stations available Eg: Station A, Station B, Station C");
        String stations = scanner.nextLine();
		try {
			String query = "INSERT INTO Trains (train_number, train_name, origin, destination, departure, departure_time, arrival, arrival_time, available_seats, intermediate_stations) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, trainNumber);
	        ps.setString(2, trainName);
	        ps.setString(3, origin);
	        ps.setString(4, destination);
	        ps.setString(5, departure);
	        ps.setString(6, departure_time);
	        ps.setString(7, arrival);
	        ps.setString(8, arrival_time);
	        ps.setInt(9, seats);
	        ps.setString(10, stations);
	        ps.executeUpdate();
	        System.out.println("Train registered successfully.");
		}catch(Exception e) {
			System.out.println("Error Registering Train : " + e.getMessage());
		}
		close();
    }

    private static void updateTrain(Scanner scanner) {
        connect();
        ps = null;
        String qryString = "";
        for(Train t : getTrains()) {
        	System.out.println("Train No: " + t.trainNum + ", Name: " + t.trainName);
        }
        System.out.print("Enter Train No. to update : ");
        int trainNum = scanner.nextInt();
        try {
        	int exit = 0;
        	while(exit == 0) {
        		System.out.println("1. Update Train Number\n2. Update Train Name\n3. update Origin Station\n4. Update Destination\n5. Update Departure Date\n6. Update Departure time\n7. Update Arrival Date\n8. Update Arrival Time\n9. Number of Seats\n10. Update Intermediate Stations\n11. Save&Exit");
        		System.out.print("Choice : ");
        		int ch = scanner.nextInt();scanner.nextLine();
        		if(ch == 1) {
        			System.out.print("Enter Train Number to Update: ");
                    int trainNumber = scanner.nextInt();scanner.nextLine();
                    qryString = "update Trains set train_number=? where train_number=?";
                    ps = conn.prepareStatement(qryString);
                    ps.setInt(1, trainNumber);
                    ps.setInt(2, trainNum);
                    ps.executeUpdate();
        		}else if(ch == 2) {
        			System.out.print("Enter new Train Name: ");
                    String trainName = scanner.nextLine();
                    qryString = "update Trains set train_name=? where train_number=?";
                    ps = conn.prepareStatement(qryString);
                    ps.setString(1, trainName);
                    ps.setInt(2, trainNum);
                    ps.executeUpdate();
        		}else if(ch == 3) {
        			System.out.print("Enter new Origin Station: ");
                    String origin = scanner.nextLine();
                    qryString = "update Trains set origin=? where train_number=?";
                    ps = conn.prepareStatement(qryString);
                    ps.setString(1, origin);
                    ps.setInt(2, trainNum);
                    ps.executeUpdate();
        		}else if(ch == 4) {
        			System.out.print("Enter new Destination Station: ");
                    String destination = scanner.nextLine();
                    qryString = "update Trains set destination=? where train_number=?";
                    ps = conn.prepareStatement(qryString);
                    ps.setString(1, destination);
                    ps.setInt(2, trainNum);
                    ps.executeUpdate();
        		}else if(ch == 5) {
        			System.out.print("Enter new Departure Date: ");
                    String departure = scanner.nextLine();
                    qryString = "update Trains set departure=? where train_number=?";
                    ps = conn.prepareStatement(qryString);
                    ps.setString(1, departure);
                    ps.setInt(2, trainNum);
                    ps.executeUpdate();
        		}else if(ch == 6) {
        			System.out.println("Enter new departure time: ");
                    String departure_time = scanner.nextLine();
                    qryString = "update Trains set departure_time=? where train_number=?";
                    ps = conn.prepareStatement(qryString);
                    ps.setString(1, departure_time);
                    ps.setInt(2, trainNum);
                    ps.executeUpdate();
        		}else if(ch == 7) {
        			System.out.print("Enter new Arrival Date: ");
                    String arrival = scanner.nextLine();
                    qryString = "update Trains set arrival=? where train_number=?";
                    ps = conn.prepareStatement(qryString);
                    ps.setString(1, arrival);
                    ps.setInt(2, trainNum);
                    ps.executeUpdate();
        		}else if(ch == 8) {
        			System.out.println("Enter new arrival time: ");
                    String arrival_time = scanner.nextLine();
                    qryString = "update Trains set arrival_time=? where train_number=?";
                    ps = conn.prepareStatement(qryString);
                    ps.setString(1, arrival_time);
                    ps.setInt(2, trainNum);
                    ps.executeUpdate();
        		}else if(ch == 9) {
        			System.out.println("Enter new Total no. of seats available: ");
                    int seats = scanner.nextInt();scanner.nextLine();
                    qryString = "update Trains set available_seats=? where train_number=?";
                    ps = conn.prepareStatement(qryString);
                    ps.setInt(1, seats);
                    ps.setInt(2, trainNum);
                    ps.executeUpdate();
        		}else if(ch == 10) {
        			System.out.println("Enter New Intermediate Stations : ");
                    String stations = scanner.nextLine();
                    qryString = "update Trains set intermediate_stations=? where train_number=?";
                    ps = conn.prepareStatement(qryString);
                    ps.setString(1, stations);
                    ps.setInt(2, trainNum);
                    ps.executeUpdate();
        		}else if(ch == 11) {
        			exit = 1;
        		}
        	}
            System.out.println("Train details updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating train: " + e.getMessage());
        } finally {
            close();
        }
    }

    private static void deleteTrain(Scanner scanner) {
        connect();
        for(Train t : getTrains()) {
        	System.out.println("Train No: " + t.trainNum + ", Name: " + t.trainName);
        }
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
    	connect();
    	PreparedStatement ps = null;
    	try {
    		String tableCreateQuery = "CREATE TABLE Customers ("
    				+ "username VARCHAR(50) PRIMARY KEY,"
    				+ "password VARCHAR(50) NOT NULL,"
    				+ "name VARCHAR(100) NOT NULL,"
    				+ "email VARCHAR(100) NOT NULL UNIQUE,"
    				+ "phone CHAR(10) NOT NULL CHECK (LENGTH(phone) = 10),"
    				+ "active BOOLEAN DEFAULT TRUE)";
    		ps = conn.prepareStatement(tableCreateQuery);
    		ps.executeUpdate();
    		System.out.println("Customer Table Created");
    	}catch(Exception e) {
    		System.out.println("Customer Table Created Already");
    	}
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone (10 digits): ");
        String phone = scanner.nextLine();
        if (!phone.matches("\\d{10}")) {
            System.out.println("Invalid phone number. It should contain exactly 10 digits.");
            return;
        }
        if (!email.matches("\\S+@\\S+\\.\\S+")) {
            System.out.println("Invalid email format.");
            return;
        }
        try {
            String query = "INSERT INTO Customers (username, password, name, email, phone) VALUES (?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
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
        String qryString = "";
        try {
        	int exit = 0;
        	while(exit == 0) {
        		System.out.println("1. Update Name\n2. Update Email\n3. Update Phone Number\n4. Back");
        		System.out.print("Choice : ");
        		int ch = scanner.nextInt();scanner.nextLine();
        		if(ch == 1) {
        			System.out.print("Enter New Name: ");
                    String name = scanner.nextLine();
                    qryString = "update Customers set name=? where username=?";
                    ps = conn.prepareStatement(qryString);
                    ps.setString(1, name);
                    ps.setString(2, username);
                    ps.executeUpdate();
        		}else if(ch == 2) {
        			System.out.print("Enter new Email: ");
                    String email = scanner.nextLine();
                    if (!email.matches("\\S+@\\S+\\.\\S+")) {
                        System.out.println("Invalid email format.");
                        return;
                    }
                    qryString = "update Customers set email=? where username=?";
                    ps = conn.prepareStatement(qryString);
                    ps.setString(1, email);
                    ps.setString(2, username);
                    ps.executeUpdate();
        		}else if(ch == 3) {
        			System.out.print("Enter new Phone Number.: ");
                    String phone = scanner.nextLine();
                    if (!phone.matches("\\d{10}")) {
                        System.out.println("Invalid phone number. It should contain exactly 10 digits.");
                        return;
                    }
                    qryString = "update Customers set phone=? where username=?";
                    ps = conn.prepareStatement(qryString);
                    ps.setString(1, phone);
                    ps.setString(2, username);
                    ps.executeUpdate();
        		}else if(ch == 4) {
        			exit = 1;
        		}else {
        			System.out.println("Invalid Choice");
        		}
            System.out.println("Customer details updated successfully.");
        	}
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
        PreparedStatement ps = null;
        try {
        	String createTabQry = "CREATE TABLE Bookings ("
        			+ "booking_id INT PRIMARY KEY,"
        			+ "username VARCHAR(50) NOT NULL,"
        			+ "train_number INT NOT NULL,"
        			+ "travel_date VARCHAR(10) NOT NULL,"
        			+ "class VARCHAR(50) NOT NULL,"
        			+ "total_seats INT NOT NULL,"
        			+ "bookingStatus VARCHAR(50),"
        			+ "FOREIGN KEY (username) REFERENCES Customers(username),"
        			+ "FOREIGN KEY (train_number) REFERENCES Trains(train_number))";
        	ps = conn.prepareStatement(createTabQry);
        	ps.executeUpdate();
        	System.out.println("Booking Table Created");
        }catch(Exception e) {
        	System.out.println("Booking Table Created Already : "+ e.getMessage());
        }
        
        System.out.print("Enter Train Number: ");
        int trainNumber = scanner.nextInt();scanner.nextLine();
        System.out.print("Enter Travel Date (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        System.out.print("Enter Preferred Class (e.g., Sleeper, AC): ");
        String preferredClass = scanner.nextLine();
        System.out.print("Enter number of seats required: ");
        int tickets_needed = scanner.nextInt();scanner.nextLine();
        try {
            String checkAvailabilityQuery = "SELECT available_seats FROM Trains WHERE train_number=?";
            ps = conn.prepareStatement(checkAvailabilityQuery);
            ps.setInt(1, trainNumber);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int availableSeats = rs.getInt("available_seats");
                if (availableSeats >= tickets_needed) {
            		System.out.print("Seats available! Please confirm your booking (yes/no): ");
                    String confirmation = scanner.nextLine();
                    int bookingID = generateBookingID();
                    if (confirmation.equalsIgnoreCase("yes")) {
                        String bookingQuery = "INSERT INTO Bookings (booking_id, username, train_number, travel_date, class, total_seats, bookingStatus) VALUES (?, ?, ?, ?, ?, ?, 'Booked')";
                        PreparedStatement psBooking = conn.prepareStatement(bookingQuery);
                        psBooking.setInt(1, bookingID);
                        psBooking.setString(2, username);
                        psBooking.setInt(3, trainNumber);
                        psBooking.setString(4, date);
                        psBooking.setString(5, preferredClass);
                        psBooking.setInt(6, tickets_needed);
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
            System.out.println(e);
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
                String status = rs.getString("bookingStatus");
                if ("Booked".equalsIgnoreCase(status)) {
                    System.out.println("Booking ID verified.");
                    String updateStatusQuery = "UPDATE Bookings SET bookingStatus='Cancelled' WHERE booking_id=?";
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
                System.out.println("Status: " + rs.getString("bookingStatus"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error viewing booking history: " + e.getMessage());
        } finally {
            close();
        }
    }
    
    private static ArrayList<Train> getTrains(){
    	connect();
    	ps = null;
    	ArrayList<Train> trainArr = new ArrayList<>();
    	try {
			ps = conn.prepareStatement("select * from Trains");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				trainArr.add(new Train(rs.getInt("train_number"), rs.getString("train_name"), rs.getString("origin"), rs.getString("destination"), rs.getString("departure"), rs.getString("departure_time"), rs.getString("arrival"), rs.getString("arrival_time"), rs.getInt("available_seats"), rs.getString("intermediate_stations") ));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return trainArr;
    }
    
    private static int generateBookingID() {
        int min = 1000;
        int max = 9999;
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        return rand;
    }
}

class Train{
	int trainNum;
	String trainName;
	String origin;
	String destination;
	String departure;
	String departure_time;
	String arrival;
	String arrival_time;
	int availableSeats;
	String intermediateStations;
	
	public Train(int trainNum, String trainName, String origin, String destination, String departure, String departure_time, String arrival, String arrival_time, int availableSeats, String intermediateStations){
		this.trainNum = trainNum;
		this.trainName = trainName;
		this.origin = origin;
		this.destination = destination;
		this.departure = departure;
		this.departure_time = departure_time;
		this.arrival = arrival;
		this.arrival_time = arrival_time;
		this.availableSeats = availableSeats;
		this.intermediateStations = intermediateStations;
	}
}
