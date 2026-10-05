package payment;

import model.Vehicle;

public class NormalPricingStrategy implements PricingStrategy{
    
    @Override
    public double calculatePrice(Vehicle vehicle, long hours){
        if(hours <= 0){
            hours = 1;
        }

        switch (vehicle.getVehicleType()) {
            case BIKE:
                
                return hours * 20;

            case CAR:
                
                return hours * 50;

            case TRUCK:
                
                return hours * 100;
            
            default:
                return 0;
        }
    }
}
