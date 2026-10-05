package model;

public abstract class Vehicle {
    
    private String vehicleNumber;

    private VehicleType vehicleType;

    public Vehicle(String vehicleNumber, VehicleType vehicleType){
        
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public VehicleType getVehicleType(){
        return this.vehicleType;
    }

    public String getVehicleNumber(){
        return this.vehicleNumber;
    }

    public abstract void display();
}
