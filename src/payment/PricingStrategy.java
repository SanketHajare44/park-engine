package payment;

import model.Vehicle;

// PricingStrategy strategy interface
// Defines a common contract for all pricing methods
public interface PricingStrategy {

    double calculatePrice(Vehicle vehicle, long hours); 
}
