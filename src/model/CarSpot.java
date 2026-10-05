package model;

public class CarSpot extends ParkingSpot{
    
    public CarSpot(int spotNumber){
        super(spotNumber, SpotType.CAR);
    }

    @Override
    public boolean canFitVehicle(Vehicle vehicle)
    {
        if(vehicle.getVehicleType() == VehicleType.CAR)
        {
            return true;
        }else{
            return false;
        }
    }
}
