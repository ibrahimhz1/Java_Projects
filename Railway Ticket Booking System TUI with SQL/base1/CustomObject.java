package base1;

import java.util.ArrayList;

//Custom class to hold two ArrayLists
public class CustomObject {
 private ArrayList<Ticket> ticketList;
 private ArrayList<Passenger> passengerList;

 public CustomObject(ArrayList<Ticket> ticketList, ArrayList<Passenger> passengerList) {
     this.ticketList = ticketList;
     this.passengerList = passengerList;
 }

 public ArrayList<Ticket> getTicketList() {
     return ticketList;
 }

 public ArrayList<Passenger> getPassengerList() {
     return passengerList;
 }
}