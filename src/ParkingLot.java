
import java.util.*;

import gate.EntryGate;
import model.ParkingFloor;
import model.ParkingSpot;
import model.ParkingTicket;
import model.Vehicle;
import payment.NormalPricingStrategy;
import payment.PaymentStrategy;
import payment.PricingStrategy;
import strategy.FirstAvailableParkingStrategy;
import strategy.ParkingStrategy;
import gate.ExitGate;

public class ParkingLot {

    // Instance of ParkingLot class
    private static ParkingLot instance;

    // Store the parking lot name
    private String parkingLotName;

    // Stores all floors of the parking lot
    private List<ParkingFloor> floors;
    
    // Map the active ticket number with active parking spot
    private Map<Integer, ParkingTicket> activeTickets;

    // Map vehicle number with active tickets
    // Used for searching vehicles and it prevents the duplications
    private Map<String, ParkingTicket> vehicleTicketMap;

    // Algorithm used for selecting parking spot
    private ParkingStrategy parkingStrategy;

    // Algorithms used for calculating parking charges
    private PricingStrategy pricingStrategy;

    // Private Constructor for singleton class
    private ParkingLot(){

        floors = new ArrayList<>();

        activeTickets = new HashMap<>();

        vehicleTicketMap = new HashMap<>();

        // Default parking strategy
        parkingStrategy = new FirstAvailableParkingStrategy();

        // Default pricing strategy
        pricingStrategy = new NormalPricingStrategy();
    }

    // This method return the singleton class object
    public static synchronized ParkingLot getInstance(){
        
        if(instance == null){

            instance = new ParkingLot();
        }

        return instance;
    }

    // Used to set name for complete parking floor
    public void setParkingLotName(String parkingLotName){

        this.parkingLotName = parkingLotName;
    }

    // used tp add new parking floor
    public void addFloor(ParkingFloor floor){

        // Insert in arrayList
        floors.add(floor);
    }

    // This method is return list of all floors
    public List<ParkingFloor> getFloors(){

        return floors;
    }

    // This method can be used to chnage the default parking strategy
    public void setParkingStrategy(ParkingStrategy parkingStrategy){

        this.parkingStrategy = parkingStrategy;
    }

    // This method can be used to change the default pricing strategy
    public void setPricingStrategy(PricingStrategy pricingStrategy){

        this.pricingStrategy = pricingStrategy;
    }

    
    /*  ---------- Algorithm for Park the vehicle ----------

                      Check duplicates vehicle
                                |
                        Find available spot
                                |
                    Identify floor for the vehicle
                                |
                      Occupy spot for vehicle
                                |
                     Generates ticket for vehicle
                                |
                     Store the final result
 
        ---------------------------------------------------
    */

    public ParkingTicket parkVehicle(Vehicle vehicle, EntryGate entryGate){

        // Step 1 : Prevent the same vehicle for being parked multiple times
        if(vehicleTicketMap.containsKey(vehicle.getVehicleNumber())){

            System.out.println("This vehicle is alreday parked");

            throw new RuntimeException("This vehicle is already parked");
        }

        // Step 2 : Find Avialable spot
        ParkingSpot spot = parkingStrategy.findSpot(floors, vehicle);

        // If there is no empty spot
        if(spot == null){
            throw new RuntimeException("Parking is full");
        }

        // Step 3 : Indentify the exact floor for the vehicle

        ParkingFloor selectedFloor = null;

        for(ParkingFloor floor : floors){

            ParkingSpot temp = floor.findAvilableSpot(vehicle);
            
            if(temp == spot){

                selectedFloor = floor;
                break;
            }
        }

        if(selectedFloor == null){

            throw new RuntimeException("Unable to identify floor");
        }

        // step 4 : Occupy the spot
        selectedFloor.occupySpot(spot, vehicle);

        // Step 5 : Generate Parking ticket from entry gate
        ParkingTicket ticket = entryGate.generateTicket(vehicle, selectedFloor, spot);

        // Step 6 : Store the ticket using ticket number
        activeTickets.put(ticket.getTicketNumber(), ticket);

        // Step 7 : Store the ticket using vehicle number
        vehicleTicketMap.put(vehicle.getVehicleNumber(), ticket);

        return ticket;
    }

    /*  ---------- Algorithm for remove the vehicle ----------

                        Find Ticket
                            |
                        Process Exit
                            |
                        Calculate charges
                            |
                        Payment
                            |
                        Release spot
                            |
                        Remove active records
 
        ---------------------------------------------------
    */

    public void removeVehicle(int ticketNumber, ExitGate exitGate, PaymentStrategy paymentStrategy){

        // Step 1 : Find active ticket using ticket number
        ParkingTicket ticket = activeTickets.get(ticketNumber);

        if(ticket == null){

            throw new RuntimeException("There is no such ticket");
        }

        // Step 2 : Perform billing and payment
        exitGate.processExit(ticket, pricingStrategy, paymentStrategy);

        // Step 3 : Release the occupied spot
        ticket.getFloor().releaseSpot(ticket.getSpot());

        // Step 4 : Remove ticket
        activeTickets.remove(ticketNumber);

        // Step 5 : Remove vehicle from active vehicle
        vehicleTicketMap.remove(ticket.getVehicle().getVehicleNumber());

        System.out.println("Vehicle removed successfully");
    }

    // Search the specified method
    public ParkingTicket searchVehicle(String vehicleNumber){

        return vehicleTicketMap.get(vehicleNumber);
    }

    // Display complete parking lot information
    public void displayParkingLot(){
        System.out.println();
        System.out.println("---------------------------------------------------");
        System.out.println("--------------- Parking Lot Details ---------------");
        System.out.println("---------------------------------------------------");

        for(ParkingFloor floor : floors){
            floor.displayFloor();
        }

        System.out.println("---------------------------------------------------");
    }

}// End of ParkingLot class