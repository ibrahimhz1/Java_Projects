package base0;

import java.util.ArrayList;
import java.util.Scanner;

import base1.Passenger;
import base1.Ticket;

public class Train {
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