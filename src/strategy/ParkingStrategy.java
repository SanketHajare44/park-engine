package strategy;

import java.util.*;
import model.ParkingFloor;
import model.ParkingSpot;
import model.Vehicle;

// Defines the contract for algorithms used to select an available parking spot
public interface ParkingStrategy {
    
    ParkingSpot findSpot(List<ParkingFloor> floors, Vehicle vehicle);

}
