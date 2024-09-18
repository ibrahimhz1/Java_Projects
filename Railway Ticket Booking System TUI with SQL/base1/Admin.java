package base1;
import java.util.*;

public class Admin extends Thread {
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
    
    static Admin adminRegister (Scanner sc) {
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
    
    static ArrayList<Train> adminLogin(Admin admin, ArrayList<Train> trainArr, ArrayList<Passenger> passengerArr, ArrayList<Ticket> ticketArr, Scanner sc) {
    	System.out.print("Login Admin Email : ");
        String adminEmail = sc.nextLine();
        System.out.print("Login Admin Password: ");
        String adminPasswd = sc.nextLine();
        if (adminLoginValidation(adminEmail, adminPasswd, admin) == 1) {
            ArrayList<Train> updatedTrainArr = afterAdminLogin(admin, sc, trainArr, ticketArr, passengerArr);
            trainArr = updatedTrainArr;
        }else{
            System.out.println("Wrong Credentials !!! ");
        }
        return trainArr;
    }
    
    // Admin Login Method
    static int adminLoginValidation(String adminEmail, String adminPasswd, Admin admin) {
        if (admin.getPassword().equals(adminPasswd) && admin.getEmail().equals(adminEmail)) {
            System.out.println("Admin Logged In");
            return 1;
        } else
            return 0;
    }
    
    // Admin After Loggin in Methods
    static ArrayList<Train> afterAdminLogin(Admin admin, Scanner sc, ArrayList<Train> trainArr, ArrayList<Ticket> ticketArr, ArrayList<Passenger> passengerArr) {
        int exit = 0;
        while (exit == 0) {
            System.out.println("\n1. Admin Info\n2. Add New Train\n3. Edit Train Details\n4. All Trains\n5. Passengers Of Particular Train\n6. All Passengers\n7. Logout");
            System.out.print("Choice 1 / 2 / 3 / 4 / 5 / 6 : ");
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
                trainArr.add(train);
                System.out.println(trainNumber + " - " + trainName + " Created");
            } else if (choice == 3) {
                System.out.println("###### Edit Train Details ######");
                System.out.print("Enter Train Number: ");
                String trainNumber = sc.nextLine();
                ArrayList<Train> updatedTrainArr = editTrainDetails(trainNumber, trainArr, sc);
                trainArr = updatedTrainArr;
            } else if(choice == 4){
                for (Train t : trainArr) {
                    t.getDetail();
                }
            } else if(choice == 5) {
            	Train.ListPassengersOfTrain(sc, ticketArr);
            } else if(choice == 6){
                // Get all customers details
            	for (Passenger p : passengerArr) {
                    p.getPassengerDetail();
                }
            } else if (choice == 7) {
                System.out.println("Admin Logged out :(");
                exit = 1;
            }
        }
        return trainArr;
    }
    
 // Edit Train Details
    static ArrayList<Train> editTrainDetails(String trainNumber, ArrayList<Train> trainArr, Scanner sc) {
        int index = 0;
        int flag = 0;
        Train train = null;
        for (Train t : trainArr) {
            if (t.getTrainNumber().equals(trainNumber)) {
                train = t;
                flag = 1;
            }
            index += 1;
        }
        if (flag == 1) {
            int exit = 0;
            while (exit == 0) {
                System.out.println("##### Edit Train Details #####");
                System.out.println("1. Show Details\n2. Edit Train Number\n3. Edit Train Name\n4. Edit Origin Station\n5. Edit Destination Station\n6. Edit Departure Time\n7. Edit Arrival Time\n8. Edit Number of Seats\n9. Save & Exit");
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
                    trainArr.set(index-1, train);
                    System.out.println("Train Details Updated");
                    exit = 1;
                }
            }
            trainArr.set(index-1, train);
        }
        return trainArr;
    }
    
}