package practice1;

import java.util.ArrayList;

import java.util.Scanner;

class Customer{

	private String custID;

	private String custName;

	private int custAge;

	private String custGender;

	public Customer(String custID, String custName, int custAge, String custGender) {

		this.custID = custID;

		this.custName = custName;

		this.custAge = custAge;

		this.custGender = custGender;

	}

	public String getcustID() {return this.custID;}

	public String getcustName() {return this.custName;}

	public int getcustAge() {return this.custAge;}

	public String getcustGender() {return this.custGender;}

	

}

class Bus{

	private String busID;

	private String busName;

	private int ticketPrice;

	private String custID;

	public Bus(String busID, String busname, int ticketPrice, String custID) {

		this.busID = busID;

		this.busName = busname;

		this.ticketPrice = ticketPrice;

		this.custID = custID;

	}

	public String getName() {return this.busID;}

	public String getBusName() {return this.busName;}

	public int getTicketPrice() {return this.ticketPrice;}

	public String getCustomerID() {return this.custID;}

}

public class Question1 {

	public static double getTotalTicketPrice(String busName, ArrayList <Bus> busArr) {

		double sum = 0.0;

		for(Bus b: busArr) {

			if(b.getBusName().equalsIgnoreCase(busName)) {

				sum+=b.getTicketPrice();

			}

		}

		return sum;

	}

	public static ArrayList<String> getCustomersFromBus(String busname, ArrayList <Bus> busArr, ArrayList <Customer> custArr) {

		ArrayList<String> custList = new ArrayList<>();

		int flag = 0;

		for(Bus b : busArr) {

			if(b.getBusName().equalsIgnoreCase(busname)) {

				for(Customer c : custArr) {

					if(b.getCustomerID().equals(c.getcustID())){

						custList.add(c.getcustName());

						flag = 1;

					}

				}	

			}

		}

		if(flag == 1) {

			return custList;

		}

		return null;

	}

	public static int totalTicketBooked(String custID, ArrayList<Bus> busArr) {

		int count = 0;

		for(Bus b : busArr) {

			if(b.getCustomerID().equals(custID)) {

				count++;

			}

		}

		return count;

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ArrayList<Bus> busArr = new ArrayList<>();

		ArrayList<Customer> custArr = new ArrayList<>();

		System.out.println("Number of Customers : ");

		int n = sc.nextInt();sc.nextLine();

		for(int i=0; i<n; i++) {

			System.out.println("Customer ID : ");

			String custID = sc.nextLine();

			System.out.println("Customer Name : ");

			String custName = sc.nextLine();

			System.out.println("Customer Age : ");

			int custAge = sc.nextInt();sc.nextLine();

			System.out.println("Customer Gender : ");

			String custGender = sc.nextLine();

			System.out.print("No of Tickets : ");

			int noOfTickets = sc.nextInt();sc.nextLine();

			for(int j=0; j<noOfTickets; j++) {

				System.out.print("Bus ID : ");

				String busID = sc.nextLine();

				System.out.print("Bus name : ");

				String busname = sc.nextLine();

				System.out.print("ticket Price: ");

				int ticketPrice = sc.nextInt();sc.nextLine();	

				Bus bus = new Bus(busID, busname, ticketPrice, custID);

				busArr.add(bus);

			}

			Customer cust = new Customer(custID, custName, custAge, custGender);

			custArr.add(cust);

		}

		System.out.print("Bus Name : ");

		String busname = sc.nextLine();

		System.out.print("Customer ID : ");

		String custID = sc.nextLine();

		sc.close();

		ArrayList<String> customerList = getCustomersFromBus(busname, busArr, custArr);

		if(customerList != null) {

			for(String name : customerList) {

				System.out.println(name);

			}

		}

		double totalprice = getTotalTicketPrice(busname, busArr);

		System.out.println("Total Fare of " + busname + " bus : " + totalprice);

		int count = totalTicketBooked(custID, busArr);

		System.out.println("Total ticket booked by " + custID + " : " + count);

	}

}
