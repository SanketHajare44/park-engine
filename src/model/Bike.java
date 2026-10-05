package model;

public class Bike extends Vehicle{
    
    public Bike(String vehicleNumber){
        super(vehicleNumber, VehicleType.BIKE);
    }
   
    @Override 
    public void display(){
        System.out.println("Bike : "+ getVehicleNumber());
    }

}
