package gate;

import model.ParkingTicket;
import payment.PaymentStrategy;
import payment.PricingStrategy;

public class ExitGate {
    
    private int gateNumber;

    public ExitGate(int gateNumber){

        this.gateNumber = gateNumber;
    }

    // Getter method used for gate number
    public int getGateNumber(){

        return this.gateNumber;
    }

    // This performance complete exit operations
    public void processExit(ParkingTicket ticket, PricingStrategy pricingStartegy, PaymentStrategy paymentStrategy){

        // Step 1 : Close the ticket and record the exit time
        ticket.closeTicket();

        // Step 2 : Calculate the parking duration
        long hours = ticket.calculateHours();

        // Step 3 : Calculate the parking charges
        double amount = pricingStartegy.calculatePrice(ticket.getVehicle(), hours);

        System.out.println();

        System.out.println();

        System.out.println("Vehicle Exit from gate : "+ gateNumber);
        System.out.println("Parking duration       : "+ hours);
        System.out.println("Parking charges        : "+ amount);

        // Step 4 : process the payment using selected payment strategy       
        paymentStrategy.pay(amount);
    }
}
