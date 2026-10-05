package model;

public class TruckSpot extends ParkingSpot{
    
    public TruckSpot(int spotNumber){
        super(spotNumber, SpotType.TRUCK);
    }

    public boolean canFitVehicle(Vehicle vehicle){
        if(vehicle.getVehicleType() == VehicleType.TRUCK){
            return true;
        }else{
            return false;
        }
    }
}
