package payment;

// Payment strategy interface
// Defines a common contract for all payment methods
public interface PaymentStrategy {
    
    void pay(double amount);
}
