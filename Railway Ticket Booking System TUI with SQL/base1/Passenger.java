package base1;

import java.util.ArrayList;
import java.util.Scanner;

public class Passenger {
	private String passengerID;
	private String passengerUsername;
	private String passengerName;
	private String passengerEmail;
	private String passengerPassword;
	private String passengerAddress;
	private int passengerAge;
	private String passengerContact;
	private String passengerGender;
	
	private void generateUserID() {
        int min = 10000;
        int max = 99999;
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        this.passengerID = String.valueOf(rand);
    }

	public Passenger(String username, String passengerName, String passengerEmail, String passengerPassword, String passengerAddress, String passengerContact, int age, String gender){
		this.passengerUsername = username;
		this.passengerName = passengerName;
		this.passengerEmail = passengerEmail;
		this.passengerPassword = passengerPassword;
		this.passengerAddress = passengerAddress;
		this.passengerAge = age;
		this.passengerContact = passengerContact;
		this.passengerGender = gender;
		this.generateUserID();
	}
	
	public Passenger() {
		// TODO Auto-generated constructor stub
	}

	public void setPassengerID(String passengerID) {this.passengerID = passengerID;}
	public void setPassengerUsername(String username) {this.passengerUsername = username;}
	public void setPassengerName(String passengerName) {this.passengerName = passengerName;}
	public void setPassengerEmail(String passengerEmail) {this.passengerEmail = passengerEmail;}
	public void setPassengerPassword(String passengerPassword) {this.passengerPassword = passengerPassword;}
	public void setPassengerAddress(String passengerAddress) {this.passengerAddress = passengerAddress;}
	public void setPassengerAge(int passengerAge) {this.passengerAge = passengerAge;}
	public void setPassengerContact(String passengerContact) {this.passengerContact = passengerContact;}
	public void setPassengerGender(String passengerGender) {this.passengerGender = passengerGender;}
	
	public String getPassengerID(){return this.passengerID;}
	public String getPassengerUsername() {return this.passengerUsername;}
	public String getPassengerName(){return this.passengerName;}
	public String getPassengerEmail(){return this.passengerEmail;}
	public String getPassengerPassword(){return this.passengerPassword;}
	public String getPassengerAddress(){return this.passengerAddress;}
	public int getPassengerAge(){return this.passengerAge;}
	public String getPassengerContact(){return this.passengerContact;}
	public String getPassengerGender(){return this.passengerGender;}

	public void getDetail() {
		System.out.println(this.passengerID);
		System.out.println(this.passengerUsername);
		System.out.println(this.passengerName);
		System.out.println(this.passengerEmail);
		System.out.println(this.passengerContact);
		System.out.println(this.passengerAddress);
		System.out.println(this.passengerAge);
		System.out.println(this.passengerGender);
	}
	
	public static ArrayList<Passenger> addNewPassenger(ArrayList <Passenger>passengerArr, Scanner sc) {
		System.out.print("Enter Username: ");
        String username = sc.nextLine();
        while (isUsernameValid(username) == 0) {
            System.out.println("Username cannot be more than 50 characters");
            System.out.print("Enter Username: ");
            username = sc.nextLine();
        }
        
        System.out.print("Enter Name : ");
        String name = sc.nextLine();
        while (isNameValid(name) == 0) {
            System.out.print("Name cannot be more than 70 characters");
            System.out.print("Enter Name: ");
            name = sc.nextLine();
        }

        System.out.print("\nEnter Email: ");
        String email = sc.nextLine();
        while (isExistingEmail(email, passengerArr) == 1) {
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
        
        System.out.print("\nEnter Age : ");
        int age = sc.nextInt();sc.nextLine();
        while(isAgeValid(age) == 0) {
        	System.out.print("\nAge should be Greater than 15");
            System.out.print("\nEnter Age: ");
            age = sc.nextInt();sc.nextLine();
        }
        
        System.out.print("\nEnter Gender: ");
        String gender = sc.nextLine();
        while (isGenderValid(gender) == 0) {
            System.out.print("\nGender should be either male or female or other");
            System.out.print("\nEnter Gender: ");
            gender = sc.nextLine();
        }
		Passenger passenger = new Passenger(username, name, email, password, address, contactNum, age, gender);
		passengerArr.add(passenger);
		return passengerArr;
	}
	
	// After Login Methods
    static ArrayList<Ticket> afterPassengerLoggedIn(ArrayList<Train> trainArr, Passenger currentPassenger, ArrayList<Ticket> ticketArr, Scanner sc) {
        int exit = 0;
        ArrayList<Ticket> newTicketArr = ticketArr;
        while (exit == 0) {
            System.out.println("1. Edit Profile Info\n2. Search / Book Ticket\n3. View Booked Tickets\n4. Booked History\n5. Logout / Exit");
            System.out.print("Choice 1 / 2 / 3 / 4 / 5 : ");
            int z = sc.nextInt();sc.nextLine();
            if (z == 1) {
                editCustomerProfileInfo(currentPassenger, sc);
            } else if (z == 2) {
                ticketArr = Train.searchTrainMethod(trainArr, currentPassenger, ticketArr, sc);
                newTicketArr = ticketArr;
            } else if (z == 3) {
                ticketArr = viewBookedTickets(currentPassenger, ticketArr, sc);
                newTicketArr = ticketArr;
            } else if(z == 4){
                viewBookedHistory(currentPassenger, ticketArr);
            } else if (z == 5) {
                exit = 1;
                System.out.println(currentPassenger.getPassengerUsername() + " Logged Out :(");
            }
        }
        return newTicketArr;
    }
    
 // Edit Customer Profile Methods
    static void editCustomerProfileInfo(Passenger passenger, Scanner sc) {
        int exit = 0;
        while (exit == 0) {
            System.out.println("1. Show Profile Informations\n2. Change Username\n3. Change Email\n4. Change Password\n5. Edit Address\n6. Edit Phone Number\n7. Edit Name\n8. Edit Age\n9. Edit Gender\n0. Exit");
            System.out.print("Choice ( 1 / 2 / 3 / 4 / 5 / 6 / 0 ) : ");
            int cChoice = sc.nextInt();
            sc.nextLine();
            if (cChoice == 1) {
                passenger.getDetail();
            } else if (cChoice == 2) {
                System.out.println("Changing Username from " + passenger.getPassengerUsername() + " : ");
                String newUsername = sc.nextLine();
                passenger.setPassengerUsername(newUsername);
                System.out.println("Your Details Updated Successfully");
            } else if (cChoice == 3) {
                System.out.println("Changing Email from " + passenger.getPassengerEmail() + " : ");
                String newEmail = sc.nextLine();
                passenger.setPassengerEmail(newEmail);
                System.out.println("Your Details Updated Successfully");
            } else if (cChoice == 4) {
                System.out.print("Enter Current Password : ");
                String currPasswd = sc.nextLine();
                if (passenger.getPassengerPassword().equals(currPasswd)) {
                    System.out.print("New Password: ");
                    String newPasswd = sc.nextLine();
                    passenger.setPassengerPassword(newPasswd);
                    System.out.println("Your Details Updated Successfully");
                } else {
                    System.out.println("Wrong Password !!! , You cant change Password !!!");
                }
            } else if (cChoice == 5) {
                System.out.print("New Address : ");
                String newAddress = sc.nextLine();
                passenger.setPassengerAddress(newAddress);
                System.out.println("Your Details Updated Successfully");
            } else if (cChoice == 6) {
                System.out.print("New Phone Number : ");
                String newContactNum = sc.nextLine();
                passenger.setPassengerContact(newContactNum);
                System.out.println("Your Details Updated Successfully");
            } else if (cChoice == 7) {
            	System.out.print("Edit Name : ");
                String newName = sc.nextLine();
                passenger.setPassengerName(newName);
                System.out.println("Your Details Updated Successfully");
            } else if(cChoice == 8) {
            	System.out.print("Edit Age: ");
                int newAge = sc.nextInt();sc.nextLine();
                passenger.setPassengerAge(newAge);
                System.out.println("Your Details Updated Successfully");
            } else if(cChoice == 9) {
            	System.out.print("Edit Gender: ");
                String newGender = sc.nextLine();
                passenger.setPassengerGender(newGender);
                System.out.println("Your Details Updated Successfully");
            } else if (cChoice == 0) {
                exit = 1;
            }
        }
    }
    
    static ArrayList<Ticket> viewBookedTickets(Passenger passenger, ArrayList<Ticket> ticketArr, Scanner sc) {
        ArrayList<Ticket> bookedTickets = new ArrayList<>();
        for(Ticket t : ticketArr) {
        	if(passenger.getPassengerID().equals(t.getCustomerID()) && !t.getBookingStatus().equals("cancelled")) {
        		bookedTickets.add(t);
        	}
        }
        int i = 1;
        for(Ticket t : bookedTickets) {
        	System.out.println(i + " " + t.getBookingID());
        	t.getDetail();
        	i = i + 1;
        	System.out.println("#######################################");
        }
        System.out.println("1. Cancel Booking\n2. Exit");
        System.out.print("Choice : ");
        int c = sc.nextInt();sc.nextLine();
        if (c == 1) {
            System.out.print("Enter Number : ");
            int n = sc.nextInt();sc.nextLine();
            if(n >= 1 && n <= i) {
            	String ticketID  = bookedTickets.get(n-1).getBookingID();   
                for(Ticket t : ticketArr) {
                	if(t.getBookingID().equals(ticketID)) {
                		t.setBookingStatus("cancelled");
                	}
                }
                System.out.println(ticketID + " Ticket is Cancelled !");
            }else System.out.println("Invalid Selection");
        } else if (c == 2) {
            return ticketArr;
        }
        return ticketArr;
    }

    // View Booked History
    static void viewBookedHistory(Passenger currentPassenger, ArrayList<Ticket> ticketArr){
        for(Ticket t : ticketArr) {
        	if(t.getCustomerID().equals(currentPassenger.getPassengerID())) {
        		t.getDetail();
        	}
        }
    }
    
	public static CustomObject loginUser(ArrayList<Passenger> passengerArr, ArrayList<Train> trainArr, ArrayList<Ticket> ticketArr, Scanner sc) {
		System.out.print("\nEnter Email: ");
        String email = sc.nextLine();
        System.out.print("\nEnter Password: ");
        String password = sc.nextLine();
        if (loginValidation(email, password, passengerArr) == 1) {
            Passenger currentPassenger = loggedInUserData(email, passengerArr);
            if (currentPassenger != null) {
                System.out.println(currentPassenger.getPassengerUsername() + " Logged In :)");
                ticketArr = afterPassengerLoggedIn(trainArr, currentPassenger, ticketArr, sc);
            }
        }else{
            System.out.println("Wrong Credentials");
            return null;
        }
        
        return new CustomObject(ticketArr, passengerArr);
	}
	
	// Login Validation Function
    static int loginValidation(String email, String password, ArrayList<Passenger> passengerArr) {
        int login = 0;
        for(Passenger p : passengerArr) {
        	if(p.getPassengerEmail().equals(email) && p.getPassengerPassword().equals(password)) {
        		login = 1;
        	}
        }
        if (login == 1) return 1;
        else return 0;
    }
    
    static Passenger loggedInUserData(String email, ArrayList<Passenger> passengerArr) {
    	for(Passenger p : passengerArr) {
    		if(p.getPassengerEmail().equals(email)) {
    			Passenger loggedInUser = p;
    			return loggedInUser;
    		}
    	}
    	return null;
    }

	// Get Registered Passengers List
    static void displayRegisteredPassengers(ArrayList<Passenger> passengerArr) {
    	for(Passenger ps : passengerArr) {
    		System.out.println(ps.getPassengerID() + "\n" + ps.getPassengerName() + "\n" + ps.getPassengerEmail() + 
    				"\n" + ps.getPassengerContact() + "\n" + ps.getPassengerAge() + "\n" + ps.getPassengerGender());
    		System.out.println("####################");
    	}
    }
    
    // Is User-name Valid
    static int isUsernameValid(String username) {
        if (username.length() <= 50)
            return 1;
        else
            return 0;
    }
    
    static int isNameValid(String name) {
    	int flag = 1;
    	if(name.length() <= 70) {
    		for (char c : name.toCharArray()) {
    		      if (Character.isDigit(c)) {
    		         flag = 0;
    		      }
    		}
    	}else return 0;
    	return flag;
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
	
	// Checking For existing Email
    static int isExistingEmail(String email, ArrayList<Passenger> passengerArr) {
        if (passengerArr.size() > 0) {
            int exist = 0;
            for (Passenger ps : passengerArr) {
                if (ps.getPassengerEmail().equals(email)) {
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
    
    // Checking Age
    static int isAgeValid(int age) {
    	if(age > 15) {
    		return 1;
    	}else return 0;
    }
    
    static int isGenderValid(String gender) {
    	if(gender.equals("male") || gender.equals("female") || gender.equals("other")) {
    		return 1;
    	}else return 0;
    }

}
