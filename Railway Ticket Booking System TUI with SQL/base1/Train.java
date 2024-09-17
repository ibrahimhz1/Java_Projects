package base1;

import java.util.*;

public class Train {
    String trainNumber;
    String trainName;
    String originStation;
    String destinationStation;
    String departureTime;
    String arrivalTime;
    int numberOfSeats;

    public Train(String trainNumber, String trainName, String originStation, String destinationStation, String departureTime, String arrivalTime, int numberOfSeats) {
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
    
    public String getArrivalTime() {
    	return this.arrivalTime;
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

    // Search Train Method
    public static ArrayList<Ticket> searchTrainMethod(ArrayList<Train> trainArr, Passenger currentPassenger, ArrayList<Ticket> ticketArr, Scanner sc) {
        System.out.print("Enter Source Point : ");
        String sourcePoint = sc.nextLine();
        System.out.print("Enter Destination Point : ");
        String destPoint = sc.nextLine();
        
        ArrayList<Train> AvailableTrains = new ArrayList<>();

        for(Train t : trainArr) {
        	if(t.getOriginStation().equalsIgnoreCase(sourcePoint) && t.getDestinationStation().equalsIgnoreCase(destPoint)) {
        		AvailableTrains.add(t);
        	}
        }

        // sorting the train number 
        for(Train at : AvailableTrains) {
        	for(Train ats : AvailableTrains) {
        		if(Integer.parseInt(at.getTrainNumber()) > Integer.parseInt(ats.getTrainNumber())) {
        			Train temp = at;
        			at = ats;
        			ats = temp;
        		}
        	}
        }
        
        System.out.println(AvailableTrains.size() + " Result Found for your Search");
        if(AvailableTrains.size() > 0) {
        	int i = 1;
        	for(Train t : AvailableTrains) {
            	System.out.println(i + ". " + t.getTrainNumber());
            	i = i + 1;
            }
        	System.out.print("Select : ");
            int s = sc.nextInt();
            AvailableTrains.get(s-1).getDetail();
            Train selectedTrain = AvailableTrains.get(s-1);
            ticketArr = Ticket.bookTrainMethod(currentPassenger, ticketArr, selectedTrain, sc);
        }
        return ticketArr;
    }
    
}
