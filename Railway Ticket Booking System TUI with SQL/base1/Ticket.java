package base1;

import java.text.SimpleDateFormat;
import java.util.*;

public class Ticket extends Passenger {
    String bookingID;
    String passengerID;
    String trainNumber;
    String trainName;
    String[] seatNumber;
    String bookingDate;
    int fare = 0;
    String bookingStatus;
    String originStation;
    String destinationStation;
    String departureTime;
    String arrivalTime;

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
    public Ticket(
            String customerID,
            String trainNumber,
            String trainName,
            String[] seatNumber,
            String originStation,
            String destinationStation,
            String departureTime,
            String arrivalTime) {
    	this.generateBookingID();
        this.passengerID = customerID;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.seatNumber = seatNumber;
        this.bookingDate = generateBookingDate();
        this.bookingStatus = "confirmed";
        this.fare = fareCalculate(seatNumber.length);
        this.originStation = originStation;
        this.destinationStation = destinationStation;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getCustomerID() {
        return this.passengerID;
    }

    public String getBookingID() {
        return this.bookingID;
    }

    public String getBookingStatus(){
        return this.bookingStatus;
    }
    
    public String getTrainName() {return this.trainName;}

    public void setCustomerID(String customerID) {
        this.passengerID = customerID;
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
        System.out.println("Customer ID : " + this.passengerID);
        System.out.println("Train Number : " + this.trainNumber);
        System.out.println("Train Name : " + this.trainName);
        for (int i = 0; i < this.seatNumber.length; i++) {
            System.out.println("Seat Number : " + this.seatNumber[i]);
        }
        System.out.println("Booking Date : " + this.bookingDate);
        System.out.println("Source Point : " + this.originStation);
        System.out.println("Source Point : " + this.destinationStation);
        System.out.println("Fare : " + this.fare);
        System.out.println("Booking Status : " + this.bookingStatus);
    }
    
    public static ArrayList<Ticket> bookTrainMethod(Passenger currentPassenger, ArrayList<Ticket> ticketArr, Train train, Scanner sc) {
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
                Ticket book = new Ticket(currentPassenger.getPassengerID(), train.getTrainNumber(), train.getTrainname(), SeatArr, train.getOriginStation(), train.getDestinationStation(), train.getArrivalTime(), train.getDepartureTime());
                ticketArr.add(book);
                System.out.println("YOUR TICKET IS BOOKED");
                book.getDetail();
                System.out.println("######################");
                return ticketArr;
            } else if (j == 2) {
                exit = 1;
            }
        }
        return ticketArr;
    }
}