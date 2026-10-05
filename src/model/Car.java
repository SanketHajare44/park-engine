package model;

public class Car extends Vehicle {

    public Car(String vehicleNumber){
        super(vehicleNumber, VehicleType.CAR);
    }

    public void display(){
        System.out.println("Car : "+getVehicleNumber());
    }
}
