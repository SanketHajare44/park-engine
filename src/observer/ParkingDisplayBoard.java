package observer;

import model.ParkingFloor;
import model.SpotType;

public class ParkingDisplayBoard implements ParkingObserver {
    
    // The parking floor whose availability is monitored by this display board
    private  ParkingFloor floor;

    public ParkingDisplayBoard(ParkingFloor floor){

        this.floor = floor;
    }

    // Updates the display board whenever parking availability changes
    @Override 
    public void update() {
        System.out.println();
        System.out.println("------------------ Display Board -------------------");

        System.out.println("Floor                : " + this.floor.getFloorNumber());
        System.out.println("Available Bike pots  : " + this.floor.getAvaialableCount(SpotType.BIKE));
        System.out.println("Available Car pots   : " + this.floor.getAvaialableCount(SpotType.CAR));
        System.out.println("Available Truck pots : " + this.floor.getAvaialableCount(SpotType.TRUCK));

        System.out.println("----------------------------------------------------");
        System.out.println();
    }

}
