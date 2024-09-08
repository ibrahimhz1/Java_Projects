import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    // Testing Purpose
    static void getAllCustomers(Customer[] custArr) {
        for (int i = 0; i < custArr.length; i++) {
            Customer cust = custArr[i];
            cust.getDetail();
        }
    }

    // Is Username Valid
    static int isUsernameValid(String username) {
        if (username.length() <= 50)
            return 1;
        else
            return 0;
    }

    // Checking For existing Email
    static int isExistingEmail(String email, Customer[] custArr) {
        if (custArr.length > 0) {
            int exist = 0;
            for (int i = 0; i < custArr.length; i++) {
                if (custArr[i].getEmail().equals(email)) {
                    exist = 1;
                }
            }
            if (exist == 1) {
                return 1;
            } else
                return 0;
        }
        return 0;
    }

    // Checking for password policy
    static int isPasswordValid(String password) {
        if (password.length() >= 6 && password.length() <= 12) {
            return 1;
        } else
            return 0;
    }

    // Is Address is Valid
    static int isAddressValid(String address) {
        if (address.length() <= 100)
            return 1;
        else
            return 0;
    }

    // is Phone Num Valid
    static int isPhoneNumValid(String contactNum) {
        if (contactNum.length() == 10)
            return 1;
        else
            return 0;
    }

    // Login Function
    static int loginValidation(String email, String password, Customer[] custArr) {
        int login = 0;
        for (int i = 0; i < custArr.length; i++) {
            if (custArr[i].getEmail().equals(email) && custArr[i].getPassword().equals(password)) {
                login = 1;
            }
        }
        if (login == 1)
            return 1;
        else
            return 0;
    }

    // Current Logged in user Data
    static Customer loggedInUserData(String email, Customer[] custArr) {
        for (int i = 0; i < custArr.length; i++) {
            if (custArr[i].getEmail().equals(email)) {
                Customer cust = custArr[i];
                return cust;
            }
        }
        return null;
    }

    // Edit Customer Profile Methods
    static void editCustomerProfileInfo(Customer customer, Scanner sc) {
        int exit = 0;
        while (exit == 0) {
            System.out.println(
                    "1. Show Profile Informations\n2. Change Username\n3. Change Email\n4. Change Password\n5. Edit Address\n6. Edit Phone Number\n0. Exit");
            System.out.print("Choice ( 1 / 2 / 3 / 4 / 5 / 6 / 0 ) : ");
            int cChoice = sc.nextInt();
            sc.nextLine();
            if (cChoice == 1) {
                customer.getDetail();
            } else if (cChoice == 2) {
                System.out.println("Changing Username from " + customer.getUsername() + " : ");
                String newUsername = sc.nextLine();
                customer.setUsername(newUsername);
                System.out.println("Your Details Updated Successfully");
            } else if (cChoice == 3) {
                System.out.println("Changing Email from " + customer.getEmail() + " : ");
                String newEmail = sc.nextLine();
                customer.setEmail(newEmail);
                System.out.println("Your Details Updated Successfully");
            } else if (cChoice == 4) {
                System.out.println("Enter Current Password : ");
                String currPasswd = sc.nextLine();
                if (customer.getPassword().equals(currPasswd)) {
                    System.out.print("New Password: ");
                    String newPasswd = sc.nextLine();
                    customer.setPassword(newPasswd);
                    System.out.println("Your Details Updated Successfully");
                } else {
                    System.out.println("Wrong Password !!! , You cant change Password !!!");
                }
            } else if (cChoice == 5) {
                System.out.print("New Address : ");
                String newAddress = sc.nextLine();
                customer.setAddress(newAddress);
                System.out.println("Your Details Updated Successfully");
            } else if (cChoice == 6) {
                System.out.print("New Phone Number : ");
                String newContactNum = sc.nextLine();
                customer.setContactNum(newContactNum);
                System.out.println("Your Details Updated Successfully");
            } else if (cChoice == 0) {
                exit = 1;
            }
        }
    }

    // Book Train Method
    static Booking[] bookTrainMethod(Customer currentCust, Booking[] bookArr, Train train, Scanner sc) {
        int exit = 0;
        while (exit == 0) {
            System.out.println("1. Book Train\n2. Exit");
            System.out.print("Choice 1 / 2 : ");
            int j = sc.nextInt();
            if (j == 1) {
                System.out.print("How many seats to book : ");
                int noOfSeat = sc.nextInt();
                sc.nextLine();
                String[] SeatArr = new String[noOfSeat];
                for (int i = 0; i < SeatArr.length; i++) {
                    System.out.print(
                            "Select Seat Number from 1 to " + train.getNumberOfSeats() + " for Passenger.No: " + i
                                    + 1 + " : ");
                    SeatArr[i] = sc.nextLine();
                }
                Booking book = new Booking(currentCust.getCustomerID(), train.getTrainNumber(), SeatArr);
                bookArr = Arrays.copyOf(bookArr, bookArr.length + 1);
                bookArr[bookArr.length - 1] = book;
                return bookArr;
            } else if (j == 2) {
                exit = 1;
            }
        }
        return bookArr;
    }

    // Search Train Method
    static Booking[] searchTrainMethod(Train[] trainArr, Customer currentCust, Booking[] bookArr, Scanner sc) {
        System.out.print("Enter Source Point : ");
        String sourcePoint = sc.nextLine();
        System.out.print("Enter Destination Point : ");
        String destPoint = sc.nextLine();

        Train[] searchedTrain = new Train[0];
        for (int i = 0; i < trainArr.length; i++) {
            if (trainArr[i].getOriginStation().equalsIgnoreCase(sourcePoint)
                    && trainArr[i].getDestinationStation().equalsIgnoreCase(destPoint)) {
                searchedTrain = Arrays.copyOf(searchedTrain, searchedTrain.length + 1);
                searchedTrain[searchedTrain.length - 1] = trainArr[i];
            }
        }
        System.out.println(searchedTrain.length + " Result Found for your Search");
        int flag = 0;
        for (int i = 0; i < searchedTrain.length; i++) {
            System.out.println(i + 1 + ". " + searchedTrain[i].getTrainNumber());
            flag = 1;
        }
        if (flag == 1) {
            System.out.print("Select : ");
            int s = sc.nextInt();
            searchedTrain[s - 1].getDetail();
            Train selectedTrain = searchedTrain[s - 1];
            bookArr = bookTrainMethod(currentCust, bookArr, selectedTrain, sc);
        }
        return bookArr;
    }

    static Booking[] viewBookedTickets(Customer currentCust, Booking[] bookArr, Scanner sc) {
        Booking[] bookedTickets = new Booking[0];
        for (int i = 0; i < bookArr.length; i++) {
            if (currentCust.getCustomerID().equals(bookArr[i].getCustomerID())) {
                bookedTickets = Arrays.copyOf(bookedTickets, bookedTickets.length + 1);
                bookedTickets[bookedTickets.length - 1] = bookArr[i];
            }
        }
        for (int i = 0; i < bookedTickets.length; i++) {
            System.out.println(i + 1 + " " + bookedTickets[i].getBookingID());
            bookedTickets[i].getDetail();
            System.out.println("#######################################");
        }
        System.out.println("1. Delete Booking\n2. Exit");
        System.out.print("Choice : ");
        int c = sc.nextInt();
        if (c == 1) {
            System.out.print("Enter Number : ");
            int n = sc.nextInt();
            bookedTickets[n - 1] = bookedTickets[bookedTickets.length - 1];
            bookedTickets = Arrays.copyOf(bookedTickets, bookedTickets.length - 1);
            bookArr = bookedTickets;
            return bookedTickets;
        } else if (c == 2) {
            return bookArr;
        }
        return bookArr;
    }

    // After Login Methods
    static Booking[] afterCustomerLoggedIn(Train[] trainArr, Customer currentCust, Booking[] bookArr, Scanner sc) {
        int exit = 0;
        Booking[] newBookArr = bookArr;
        while (exit == 0) {
            System.out
                    .println("1. Edit Profile Info\n2. Search / Book Ticket\n3. View Booked Tickets\n4. Logout / Exit");
            System.out.print("Choice 1 / 2 / 3 / 4 : ");
            int z = sc.nextInt();
            sc.nextLine();

            if (z == 1) {
                editCustomerProfileInfo(currentCust, sc);
            } else if (z == 2) {
                bookArr = searchTrainMethod(trainArr, currentCust, bookArr, sc);
                newBookArr = bookArr;
                // return bookArr;
            } else if (z == 3) {
                bookArr = viewBookedTickets(currentCust, bookArr, sc);
                newBookArr = bookArr;
            } else if (z == 4) {
                exit = 1;
                System.out.println(currentCust.getUsername() + " Logged Out :(");
            }
        }
        return newBookArr;
    }

    // Admin Register Function
    static Admin adminRegister(Scanner sc) {
        System.out.print("Enter Admin Username: ");
        String username = sc.nextLine();
        System.out.print("Enter Admin Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Admin Name : ");
        String adminName = sc.nextLine();
        System.out.print("Enter New Password: ");
        String password = sc.nextLine();
        Admin admin = new Admin(username, email, adminName, password);
        System.out.println("Admin Account Created");
        return admin;
    }

    // Admin Login Method
    static int adminLogin(String adminEmail, String adminPasswd, Admin admin) {
        if (admin.getPassword().equals(adminPasswd) && admin.getEmail().equals(adminEmail)) {
            System.out.println("Admin Logged In");
            return 1;
        } else
            return 0;
    }

    // Edit Train Details
    static Train[] editTrainDetails(String trainNumber, Train[] trainArr, Scanner sc) {
        int index = 0;
        int flag = 0;
        Train train = null;
        for (int i = 0; i < trainArr.length; i++) {
            if (trainArr[i].getTrainNumber().equals(trainNumber)) {
                train = trainArr[i];
                flag = 1;
                index = i;
            }
        }
        if (flag == 1) {
            int exit = 0;
            while (exit == 0) {
                System.out.println("##### Edit Train Details #####");
                System.out.println(
                        "1. Show Details\n2. Edit Train Number\n3. Edit Train Name\n4. Edit Origin Station\n5. Edit Destination Station\n6. Edit Departure Time\n7. Edit Arrival Time\n8. Edit Number of Seats\n9. Save & Exit");
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 1) {
                    train.getDetail();
                } else if (choice == 2) {
                    System.out.print("Change Train Number : ");
                    String newTrainNumber = sc.nextLine();
                    train.setTrainNumber(newTrainNumber);
                } else if (choice == 3) {
                    System.out.print("Change Train Name : ");
                    String trainName = sc.nextLine();
                    train.setTrainName(trainName);
                } else if (choice == 4) {
                    System.out.print("Change Origin Station : ");
                    String originStation = sc.nextLine();
                    train.setOriginStation(originStation);
                } else if (choice == 5) {
                    System.out.print("Change Destination Station : ");
                    String destStation = sc.nextLine();
                    train.setDestStation(destStation);
                } else if (choice == 6) {
                    System.out.print("Change Departure Time: ");
                    String deptTime = sc.nextLine();
                    train.setDepartTime(deptTime);
                } else if (choice == 7) {
                    System.out.print("Change Arrival Time: ");
                    String arrivalTime = sc.nextLine();
                    train.setArrivalTime(arrivalTime);
                } else if (choice == 8) {
                    System.out.print("Change Number of Seats: ");
                    String numOfSeats = sc.nextLine();
                    train.setArrivalTime(numOfSeats);
                } else if (choice == 9) {
                    trainArr[index] = train;
                    System.out.println("Train Details Updated");
                    exit = 1;
                }
            }
            trainArr[index] = train;
        }
        return trainArr;
    }

    // Admin After Loggin in Methods
    static Train[] afterAdminLogin(Admin admin, Scanner sc, Train[] trainArr) {
        int exit = 0;
        while (exit == 0) {
            System.out.println("\n1. Admin Info\n2. Add New Train\n3. Edit Train Details\n4. Logout");
            System.out.print("Choice 1 / 2 / 3 / 4 : ");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1)
                admin.getDetail();
            else if (choice == 2) {
                System.out.println("######## Add New Train ########");
                System.out.print("\nEnter Train Number: ");
                String trainNumber = sc.nextLine();

                System.out.print("\nEnter Train Name: ");
                String trainName = sc.nextLine();

                System.out.print("\nEnter Origin Station: ");
                String originStation = sc.nextLine();

                System.out.print("\nEnter Destination Station: ");
                String destStation = sc.nextLine();

                System.out.print("\nEnter Departure Time: ");
                String departureTime = sc.nextLine();

                System.out.print("\nEnter Arrival Time: ");
                String arrivalTime = sc.nextLine();

                System.out.print("\nNumber Of Seats: ");
                int numOfSeats = sc.nextInt();

                Train train = new Train(trainNumber, trainName, originStation, destStation, departureTime, arrivalTime,
                        numOfSeats);
                trainArr = Arrays.copyOf(trainArr, trainArr.length + 1);
                trainArr[trainArr.length - 1] = train;
                System.out.println(trainNumber + " - " + trainName + " Created");
            } else if (choice == 3) {
                System.out.println("###### Edit Train Details ######");
                System.out.print("Enter Train Number: ");
                String trainNumber = sc.nextLine();
                Train[] updatedTrainArr = editTrainDetails(trainNumber, trainArr, sc);
                trainArr = updatedTrainArr;
            } else if (choice == 4) {
                System.out.println("Admin Logged out :(");
                exit = 1;
            }
        }
        return trainArr;
    }

    public static void main(String[] args) {
        System.out.println("Train Ticket Booking System");
        Scanner sc = new Scanner(System.in);
        int exit = 0;
        Customer[] custArr = new Customer[0];
        Train[] trainArr = new Train[0];
        Booking[] bookArr = new Booking[0];
        Admin admin = null;

        while (exit == 0) {
            System.out.println(
                    "\n1. Register\n2. Login\n3. Register Admin\n4. Login Admin\n5. All Customers Details\n6. All Trains\n0. Exit Program");
            System.out.print("Choice : 1 / 2 / 3 / 4 / 5 / 6 / 0 : ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Enter Username: ");
                String username = sc.nextLine();
                while (isUsernameValid(username) == 0) {
                    System.out.println("Username cannot be more than 50 characters");
                    System.out.print("Enter Username: ");
                    username = sc.nextLine();
                }

                System.out.print("\nEnter Email: ");
                String email = sc.nextLine();
                while (isExistingEmail(email, custArr) == 1) {
                    System.out.println("Email Exist");
                    System.out.print("\nEnter Email: ");
                    email = sc.nextLine();
                }

                System.out.print("\nEnter Password: ");
                String password = sc.nextLine();
                while (isPasswordValid(password) == 0) {
                    System.out.println("Password does not meet the security policy");
                    System.out.print("\nEnter Password: ");
                    password = sc.nextLine();
                }

                System.out.print("\nEnter Address: ");
                String address = sc.nextLine();
                while (isAddressValid(address) == 0) {
                    System.out.print("\nAddress cannot be more than 100 characters");
                    System.out.print("\nEnter Address: ");
                    address = sc.nextLine();
                }

                System.out.print("\nEnter Contact Number: ");
                String contactNum = sc.nextLine();
                while (isPhoneNumValid(contactNum) == 0) {
                    System.out.print("\nPhone number cannot be less/more than 10 digits");
                    System.out.print("\nEnter Contact Number: ");
                    contactNum = sc.nextLine();
                }

                // Creating User Object
                Customer cust = new Customer(username, email, password, address, contactNum);
                // Acknowledgement of Account Creation
                System.out.println("Customer Account for " + username + " Created :)");

                // saving Object in Array
                custArr = Arrays.copyOf(custArr, custArr.length + 1);
                custArr[custArr.length - 1] = cust;

            } else if (choice == 2) {
                System.out.print("\nEnter Email: ");
                String email = sc.nextLine();
                System.out.print("\nEnter Password: ");
                String password = sc.nextLine();

                if (loginValidation(email, password, custArr) == 1) {
                    Customer currentCust = loggedInUserData(email, custArr);
                    if (currentCust != null) {
                        System.out.println(currentCust.getUsername() + " Logged In :)");
                        bookArr = afterCustomerLoggedIn(trainArr, currentCust, bookArr, sc);
                    }
                }else{
                    System.out.println("Wrong Credentials");
                }

            } else if (choice == 3) {
                admin = adminRegister(sc);
            } else if (choice == 4) {
                System.out.print("Login Admin Email : ");
                String adminEmail = sc.nextLine();
                System.out.print("Login Admin Password: ");
                String adminPasswd = sc.nextLine();
                if (adminLogin(adminEmail, adminPasswd, admin) == 1) {
                    Train[] updatedTrainArr = afterAdminLogin(admin, sc, trainArr);
                    trainArr = updatedTrainArr;
                }else{
                    System.out.println("Wrong Credentials !!! ");
                }
            } else if (choice == 5) {
                // Get all customers details
                getAllCustomers(custArr);
            } else if (choice == 6) {
                for (int i = 0; i < trainArr.length; i++) {
                    trainArr[i].getDetail();
                }
            } else if (choice == 0) {
                exit = 1;
            }
        }
        sc.close();
    }
}

class Customer {
    String customerID;
    String username;
    String email;
    String password;
    String address;
    String contactNum;

    private void generateUserID() {
        int min = 10000;
        int max = 99999;
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        this.customerID = String.valueOf(rand);
    }

    public Customer(String username, String email, String password, String address, String contactNum) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.contactNum = contactNum;
        this.generateUserID();
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public void getDetail() {
        System.out.println(this.customerID);
        System.out.println(this.username);
        System.out.println(this.email);
        System.out.println(this.password);
        System.out.println(this.address);
        System.out.println(this.contactNum);
    }
}

class Admin {
    int adminID;
    String username;
    String email;
    String adminName;
    String password;
    String role = "admin";

    public void generateAdminID() {
        int min = 10000;
        int max = 99999;
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        this.adminID = rand;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Admin(String username, String email, String adminName, String password) {
        this.username = username;
        this.email = email;
        this.adminName = adminName;
        this.password = password;
        this.generateAdminID();
    }

    public void getDetail() {
        System.out.println(this.adminID);
        System.out.println(this.username);
        System.out.println(this.email);
        System.out.println(this.password);
        System.out.println(this.role);
    }
}

class Train {
    String trainNumber;
    String trainName;
    String originStation;
    String destinationStation;
    String departureTime;
    String arrivalTime;
    int numberOfSeats;

    public Train(String trainNumber, String trainName, String originStation, String destinationStation,
            String departureTime, String arrivalTime, int numberOfSeats) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.originStation = originStation;
        this.destinationStation = destinationStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.numberOfSeats = numberOfSeats;
    }

    public String getTrainNumber() {
        return this.trainNumber;
    }

    public String getTrainname() {
        return this.trainName;
    }

    public String getOriginStation() {
        return this.originStation;
    }

    public String getDestinationStation() {
        return this.destinationStation;
    }

    public String getDepartureTime() {
        return this.departureTime;
    }

    public int getNumberOfSeats() {
        return this.numberOfSeats;
    }

    public void getDetail() {
        System.out.println("Train Number : " + this.trainNumber);
        System.out.println("Train Name : " + this.trainName);
        System.out.println("Origin Station : " + this.originStation);
        System.out.println("Destination Station : " + this.destinationStation);
        System.out.println("Departure Time : " + this.departureTime);
        System.out.println("Arrival Time : " + this.arrivalTime);
        System.out.println("Number of Seats : " + this.numberOfSeats);
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public void setOriginStation(String originStation) {
        this.originStation = originStation;
    }

    public void setDestStation(String destinationStation) {
        this.destinationStation = destinationStation;
    }

    public void setDepartTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setNumOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}

class Booking {
    String bookingID;
    String customerID;
    String trainNumber;
    String[] seatNumber;
    String bookingDate;
    int fare = 0;
    String bookingStatus;

    public void generateBookingID() {
        int min = 10000;
        int max = 99999;
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        this.bookingID = String.valueOf(rand);
    }

    public int fareCalculate(int numOfSeats) {
        return numOfSeats * 100;
    }

    public String generateBookingDate() {
        Date date = new Date();
        // Create a SimpleDateFormat object with the desired pattern
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // Format the date
        String formattedDate = formatter.format(date);
        return formattedDate;
    }

    public Booking(
            String customerID,
            String trainNumber,
            String[] seatNumber) {
        this.generateBookingID();
        this.customerID = customerID;
        this.trainNumber = trainNumber;
        this.seatNumber = seatNumber;
        this.bookingDate = generateBookingDate();
        this.bookingStatus = "active";
        this.fare = fareCalculate(seatNumber.length);
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public String getBookingID() {
        return this.bookingID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public void setSeatNumber(String[] seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setFare(int fare) {
        this.fare = fare;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void getDetail() {
        System.out.println("Customer ID : " + this.customerID);
        System.out.println("Train Number : " + this.trainNumber);
        for (int i = 0; i < this.seatNumber.length; i++) {
            System.out.println("Seat Number : " + this.seatNumber[i]);
        }
        System.out.println("Booking Date : " + this.bookingDate);
        System.out.println("Fare : " + this.fare);
        System.out.println("Booking Status : " + this.bookingStatus);
    }
}