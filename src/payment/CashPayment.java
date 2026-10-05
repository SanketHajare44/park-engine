package payment;

// Handles payments made using cash
public class CashPayment implements PaymentStrategy {
    
    @Override 
    public void pay(double amount){

         System.out.println("Cash Payment Successful : Rs. " + amount);
    }
}
