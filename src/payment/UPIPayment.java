package payment;

// Handles payments made using UPI
public class UPIPayment implements PaymentStrategy{
    
    @Override
    public void pay(double amount){

        System.out.println("UPI Payment Successful : Rs. " + amount);
    }
}
