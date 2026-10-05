package gate;

import model.ParkingFloor;
import model.ParkingSpot;
import model.ParkingTicket;
import model.Vehicle;

// It is used to handle entry of a vehicle and its ticket genretation
public class EntryGate {

    private int gateNumber;

    public EntryGate(int gateNumber){

        this.gateNumber = gateNumber;
    }

    // Getter method for gate number
    public int getGateNumber(){

        return this.gateNumber;
    }

    public ParkingTicket generateTicket(Vehicle vehicle, ParkingFloor floor, ParkingSpot spot){

        System.out.println("Vehicle entering from gate : "+ this.gateNumber);
        System.out.println();

        return new ParkingTicket(vehicle, floor, spot);
    }

}// End of EntryGate class
