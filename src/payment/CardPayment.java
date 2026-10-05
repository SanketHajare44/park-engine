package payment;

// Handles payments made using card
public class CardPayment implements PaymentStrategy {
    
    @Override 
    public void pay(double amount){
        
        System.out.println("Card Payment Successful : Rs. " + amount);
    }
}
