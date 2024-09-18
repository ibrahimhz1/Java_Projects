package base1;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList <Passenger> passengerArr = new ArrayList<>();
		ArrayList <Train> trainArr = new ArrayList<>();
		ArrayList <Ticket> ticketArr = new ArrayList<>();
		Admin admin = null;
		int exit = 0;
		while(exit == 0) {
			System.out.println("1. Register Passenger\n2. Login Passenger\n3. Register Admin\n4. Login Register\n8. Exit Program");
			System.out.print("Choice: ");
			int choice = sc.nextInt();sc.nextLine();
			if(choice == 1) {
				passengerArr = Passenger.addNewPassenger(passengerArr, sc);
			}else if(choice == 2) {
				CustomObject result = Passenger.loginUser(passengerArr, trainArr, ticketArr, sc);
				if(result != null) {
					ticketArr = (ArrayList<Ticket>) result.getTicketList();
					passengerArr = (ArrayList<Passenger>) result.getPassengerList();
				}
			}else if(choice == 3) {
				admin = Admin.adminRegister(sc);
			}else if(choice == 4) {
				trainArr = Admin.adminLogin(admin, trainArr, passengerArr, ticketArr, sc);
			}else if(choice == 8) {
				exit = 1;
				System.out.println("Program Exited");
			}
		}
		
		sc.close();
	}
}