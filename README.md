<div align="center">

# ParkEngine - Parking Lot Management System

<p align="center">
  <em>A console-based multi-floor parking system built with Java, Object-Oriented Programming, Low-Level Design, and Design Patterns.</em>
</p>

![Java](https://img.shields.io/badge/Java-8%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![LLD](https://img.shields.io/badge/Low--Level%20Design-LLD-0A66C2?style=for-the-badge)
![Design Patterns](https://img.shields.io/badge/Design%20Patterns-Singleton%20%7C%20Factory%20%7C%20Strategy%20%7C%20Observer-6E40C9?style=for-the-badge)
![OOP](https://img.shields.io/badge/OOP-Abstraction%20%7C%20Encapsulation%20%7C%20Inheritance%20%7C%20Polymorphism-2E7D32?style=for-the-badge)

</div>

---

## Overview

The **ParkEngine** is a Java-based application designed using **Object-Oriented System Design** principles and **Design Patterns**.

The project models a real-world parking facility where different types of vehicles can enter the parking lot, receive appropriate parking spaces, generate parking tickets, calculate parking charges, and exit through designated gates.

The primary objective of this project is not only to implement parking functionality, but also to demonstrate how a real-world problem can be converted into a **scalable, maintainable, modular,** and **extensible software architecture**.

The project focuses on applying **Object-Oriented Programming, SOLID principles, interfaces, abstraction, inheritance, polymorphism, and classic design patterns** to a real-world system.

---

## Highlights

- Supports **Bike, Car, and Truck**
- Multi-floor parking management
- Vehicle-specific parking spots
- Automatic parking spot allocation
- Parking ticket generation and tracking
- Vehicle search by vehicle number
- Duplicate vehicle parking prevention
- Parking fee calculation
- Cash, UPI, and Card payment methods
- Parking availability updates
- Console-based CLI application

---

## Design Patterns

| Pattern       | Implementation    | Purpose                                       |
| ------------- | ----------------- | --------------------------------------------- |
| **Singleton** | `ParkingLot`      | Maintains a single parking lot instance       |
| **Factory**   | `VehicleFactory`  | Creates vehicle objects based on vehicle type |
| **Strategy**  | `ParkingStrategy` | Handles parking spot allocation               |
| **Strategy**  | `PricingStrategy` | Handles parking fee calculation               |
| **Strategy**  | `PaymentStrategy` | Supports different payment methods            |
| **Observer**  | `ParkingObserver` | Updates parking availability                  |

---

# Project Structure

```text
Park-Engine/
│
├── README.md
├── .gitignore
├── assets/                                  # Project assets
│   └── banner.svg                          # GitHub README banner
│
└── src/
    │
    ├── factory/                             # Object creation
    │   └── VehicleFactory.java              # Creates vehicle objects
    │
    ├── gate/                                # Entry and exit operations
    │   ├── EntryGate.java                   # Handles vehicle entry and ticket generation
    │   └── ExitGate.java                    # Handles vehicle exit, pricing, and payment
    │
    ├── model/                               # Core domain objects
    │   ├── Vehicle.java                     # Abstract base class for vehicles
    │   ├── VehicleType.java                 # Enum for vehicle types
    │   ├── Bike.java                        # Represents a bike
    │   ├── Car.java                         # Represents a car
    │   ├── Truck.java                       # Represents a truck
    │   │
    │   ├── ParkingSpot.java                 # Abstract base class for parking spots
    │   ├── SpotType.java                    # Enum for parking spot types
    │   ├── BikeSpot.java                    # Parking spot for bikes
    │   ├── CarSpot.java                     # Parking spot for cars
    │   ├── TruckSpot.java                   # Parking spot for trucks
    │   │
    │   ├── ParkingFloor.java                # Manages spots and floor availability
    │   ├── ParkingTicket.java               # Stores parking ticket information
    │   └── TicketStatus.java                # Enum for ticket status
    │
    ├── observer/                            # Observer Pattern
    │   ├── ParkingObserver.java             # Interface for availability observers
    │   └── ParkingDisplayBoard.java         # Displays parking availability
    │
    ├── payment/                             # Payment and pricing
    │   ├── PaymentStrategy.java             # Interface for payment methods
    │   ├── CashPayment.java                 # Handles cash payments
    │   ├── UPIPayment.java                  # Handles UPI payments
    │   ├── CardPayment.java                 # Handles card payments
    │   ├── PricingStrategy.java             # Interface for pricing algorithms
    │   └── NormalPricingStrategy.java       # Calculates normal parking charges
    │
    ├── strategy/                            # Parking allocation strategies
    │   ├── ParkingStrategy.java             # Interface for parking allocation
    │   └── FirstAvailableParkingStrategy.java # Selects the first suitable parking spot
    │
    ├── ParkingLot.java                      # Central parking lot manager (Singleton)
    └── ParkEngine.java                      # CLI application entry point
```

---

## Getting Started

**Prerequisites:** JDK 8 or higher

```bash
# Clone
git clone https://github.com/SanketHajare44/Park-Engine.git
cd Park-Engine
cd src

# Compile
javac ParkEngine.java

# Run
java ParkEngine
```

The application starts with a ready-to-use lot of 2 floors, each with 2 bike, 2 car, and 2 truck spots, and shows this menu:

```text
---------------------------------------------------
------------------- Park Engine -------------------
---------------------------------------------------

1 : Park Vehicle
2 : Exit Vehicle
3 : Search Vehicle
4 : Display Parking Lot
5 : Exit

---------------------------------------------------

Enter your choice        :
```

---

## Sample Output

```text

Vehicle entering from gate : 1

------------------ Parking Ticket ------------------
Ticket Number  : 1001
Vehicle Number : MH10SR3003
Vehicle Type   : CAR
Floor Number   : 1
Spot Number    : 103
Ticket Status  : ACTIVE

Vehicle Exit from gate : 1
Parking duration       : 1
Parking charges        : 50.0
UPI Payment Successful : Rs. 50.0
```

---

## Author

**Sanket Sadashiv Hajare**

[![GitHub](https://img.shields.io/badge/GitHub-SanketHajare44-181717?style=flat-square&logo=github)](https://github.com/SanketHajare44)
