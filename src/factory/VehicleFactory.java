package factory;

import model.Vehicle;
import model.VehicleType;
import model.Bike;
import model.Car;
import model.Truck;

public class VehicleFactory {

    public static Vehicle createVehicle(VehicleType vehicleType, String vehicleNumber)
    {
        switch (vehicleType){
            case BIKE :
                return new Bike(vehicleNumber);
            
            case CAR:
                return new Car(vehicleNumber);
            
            case TRUCK:
                return new Truck(vehicleNumber);
            
            default:
                throw new IllegalArgumentException("Invalid vehicle type");
        }

    }
    
}