package strategy;

import java.util.List;

import model.ParkingFloor;
import model.ParkingSpot;
import model.Vehicle;

// Selects the first available parking spot for the vehicle
public class FirstAvailableParkingStrategy implements ParkingStrategy{
    
    @Override
    public ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle){

        // Check each floor in order to find a suitable parking spot
        for(ParkingFloor floor : floors){

            ParkingSpot spot = floor.findAvilableSpot(vehicle);
            
            if(spot != null)
            {
                return spot;
            }
        }

        // No suitable parking spot is available
        return null;
    }
}
