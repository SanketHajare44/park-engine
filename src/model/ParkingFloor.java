package model;

import java.util.ArrayList;
import java.util.List;
import observer.ParkingObserver;

public class ParkingFloor {
 
    // Unique floor number
    private int floorNumber;

    // Collection of all parking spots
    private List<ParkingSpot> parkingSpots;

    // Collection of observers registred for the floor
    private  List<ParkingObserver> observers;

    public ParkingFloor(int floorNumber){
        
        this.floorNumber = floorNumber;
        this.parkingSpots = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public int getFloorNumber(){

        return this.floorNumber;
    }

    public void addParkingSpot(ParkingSpot spot){

        parkingSpots.add(spot);
    }

    public void addObservers(ParkingObserver observer){

        observers.add(observer);
    }

    private void notifyObservers(){

        for(ParkingObserver observer : observers){

            observer.update();
        }
    }

    // Method is going to search parking spot for specific type of vehicle
    public ParkingSpot findAvilableSpot(Vehicle vehicle){

        for(ParkingSpot spot : parkingSpots){

            if(!spot.isOccupied() && spot.canFitVehicle(vehicle)){

                return spot;
            }
        }

        return null;
    }

    // This occupySpot method is called when vehicle is parked
    public void occupySpot(ParkingSpot spot, Vehicle vehicle){

        // Allocate spot for the vehicle
        spot.parkVehicle(vehicle);

        // Notify to the all observers about the availability of spot
        notifyObservers();
    }

    public void releaseSpot(ParkingSpot spot){

        // Release the already allocated spot 
        spot.removeVehicle();

        notifyObservers();
        // Notify to the all observers about the availability of spot
    }

    // This method will return the available spot of that specific type of vehicle 
    public int getAvaialableCount(SpotType type){

        int count = 0;

        for(ParkingSpot spot : parkingSpots){

            if(spot.getSpotType() == type && !spot.isOccupied()){

                count++;
            }
        }

        return count;
    }

    // Display all parking spots on specific floor
    public void displayFloor(){

        System.out.println();

        System.out.println("Floor : "+this.floorNumber);

        for(ParkingSpot spot : parkingSpots){

            spot.display();
        }
    }
}
