package model;

public class Truck extends Vehicle {
    
    public Truck(String vehicleNumber){
        super(vehicleNumber, VehicleType.TRUCK);
    }

    public void display(){
        System.out.println("Truck : "+getVehicleNumber());
    }
}
