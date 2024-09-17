package base0;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    String bookingID;
    String customerID;
    String trainNumber;
    String trainName;
    String[] seatNumber;
    String bookingDate;
    int fare = 0;
    String bookingStatus;
    String originStation;
    String destinationStation;

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
            String trainName,
            String[] seatNumber,
            String originStation,
            String destinationStation) {
        this.generateBookingID();
        this.customerID = customerID;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.seatNumber = seatNumber;
        this.bookingDate = generateBookingDate();
        this.bookingStatus = "confirmed";
        this.fare = fareCalculate(seatNumber.length);
        this.originStation = originStation;
        this.destinationStation = destinationStation;
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public String getBookingID() {
        return this.bookingID;
    }

    public String getBookingStatus(){
        return this.bookingStatus;
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
}