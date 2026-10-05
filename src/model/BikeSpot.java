package model;

public class BikeSpot extends ParkingSpot{
    
    public BikeSpot(int spotNumber){
        super(spotNumber, SpotType.BIKE);
    }

    @Override
    public boolean canFitVehicle(Vehicle vehicle)
    {
        if(vehicle.getVehicleType() == VehicleType.BIKE){
            return true;
        }else{
            return false;
        }
    }
}
