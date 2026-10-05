package model;

import java.time.Duration;
import java.time.LocalDateTime;

// It is used to represent one complete transaction
public class ParkingTicket {

    private static int counter = 1000;

    private int ticketNumber;

    private Vehicle vehicle;

    private ParkingFloor floor;

    private ParkingSpot spot;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private TicketStatus status;

    public ParkingTicket(Vehicle vehicle, ParkingFloor floor, ParkingSpot spot){

        this.ticketNumber = ++counter;
        this.vehicle = vehicle;
        this.floor = floor;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
        this.status = TicketStatus.ACTIVE;

    }
    
    // Getter method for ticket number
    public int getTicketNumber(){

        return this.ticketNumber;
    }

    // Getter method for vehicle
    public Vehicle getVehicle(){

        return this.vehicle;
    }

    // Getter method for floor
    public ParkingFloor getFloor(){

        return this.floor;
    }

    // Getter method for spot
    public ParkingSpot getSpot(){

        return this.spot;
    }

    // Getter method for entry time
    public LocalDateTime getEntryTime(){

        return this.entryTime;
    }

    // Getter method for exit time
    public LocalDateTime  getExitTime(){

        return this.exitTime;
    }

    // Getter method for exit time
    public TicketStatus getTicketStatus(){

        return this.status;
    }

    // Method get called when vehicle is going out
    public void closeTicket(){

        this.exitTime = LocalDateTime.now();

        this.status = TicketStatus.CLOSED;
    }

    // Calculate the total number of hours the Vehicle parked
    public long calculateHours(){

        LocalDateTime enDateTime;

        if(exitTime == null){

            enDateTime = LocalDateTime.now();
        }else{
            enDateTime = exitTime;
        }

        // Calculate actual time converts hours to minutes
        long minutes = Duration.between(entryTime, enDateTime).toMinutes();

        // Converts minutes to hours
        long hours = minutes / 60;

        if(minutes % 60 != 0){
            hours++;
        }

        if(hours == 0){
            hours = 1;
        }

        return hours;
    }

    // It will display Ticket on the screen
    public void displayTicket() {
        System.out.println("----------------------------------------------------");
        System.out.println("------------------ Parking Ticket ------------------");
        System.out.println("----------------------------------------------------");
        System.out.println();

        System.out.println("Ticket Number  : " + this.ticketNumber);
        System.out.println("Vehicle Number : " + this.vehicle.getVehicleNumber());
        System.out.println("Vehicle Type   : " + this.vehicle.getVehicleType());
        System.out.println("Floor Number   : " + this.floor.getFloorNumber());
        System.out.println("Spot Number    : " + this.spot.getSpotNumber());
        System.out.println("Entry Time     : " + this.entryTime);
        System.out.println("Ticket Status  : " + this.status);

        System.out.println();

    }
}

