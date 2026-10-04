<div align="center">

<img src="assets/banner.svg" alt="ParkEngine - Parking Lot Management System" width="100%"/>

# ParkEngine - Parking Lot Management System

**A console-based multi-floor parking system built with Java, OOP principles, Low-Level Design, and Design Patterns.**

<p>
  <img src="https://img.shields.io/badge/Java-8%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Low--Level%20Design-LLD-0A66C2?style=for-the-badge" alt="Low Level Design"/>
  <img src="https://img.shields.io/badge/Design%20Patterns-Singleton%20%7C%20Factory%20%7C%20Strategy%20%7C%20Observer-6E40C9?style=for-the-badge" alt="Design Patterns"/>
  <img src="https://img.shields.io/badge/OOP-Abstraction%20%7C%20Encapsulation%20%7C%20Inheritance%20%7C%20Polymorphism-2E7D32?style=for-the-badge" alt="OOP"/>
</p>

<p>
  <img src="https://img.shields.io/badge/Interface-CLI-black?style=flat-square" alt="CLI"/>
  <img src="https://img.shields.io/badge/Principles-SOLID-orange?style=flat-square" alt="SOLID"/>
</p>

</div>

---

## Overview

**ParkEngine** is a Java-based Low-Level Design implementation of a multi-floor parking lot.

The system handles:

- Vehicle entry
- Parking spot allocation
- Parking ticket generation
- Parking availability tracking
- Vehicle search
- Parking fee calculation
- Payment processing
- Vehicle exit
- Parking spot release

The project focuses on applying **Object-Oriented Programming, SOLID principles, interfaces, abstraction, inheritance, polymorphism, and classic design patterns** to a real-world system.

---

## Features

- Multiple vehicle types: Bike, Car, and Truck
- Multiple parking floors
- Vehicle-specific parking spots
- Parking spot allocation using Strategy Pattern
- Vehicle creation using Factory Pattern
- Parking availability updates using Observer Pattern
- Central parking management using Singleton Pattern
- Parking ticket generation and tracking
- Vehicle search using vehicle number
- Duplicate vehicle parking prevention
- Parking fee calculation
- Cash, UPI, and Card payment methods
- Console-based CLI application

---

## Design Patterns Used

| Pattern       | Implementation                           | Purpose                                               |
| ------------- | ---------------------------------------- | ----------------------------------------------------- |
| **Singleton** | `ParkingLot`                             | Maintains a single parking lot instance               |
| **Factory**   | `VehicleFactory`                         | Creates vehicle objects based on vehicle type         |
| **Strategy**  | `ParkingStrategy`                        | Provides parking spot allocation algorithms           |
| **Strategy**  | `PricingStrategy`                        | Provides parking fee calculation algorithms           |
| **Strategy**  | `PaymentStrategy`                        | Supports different payment methods                    |
| **Observer**  | `ParkingObserver`, `ParkingDisplayBoard` | Updates parking availability when spot status changes |

### Singleton Pattern

`ParkingLot` uses the Singleton Pattern to maintain a single instance of the parking lot.

```text
Application
     |
     v
ParkingLot.getInstance()
     |
     v
Single ParkingLot Instance
```

### Factory Pattern

`VehicleFactory` creates the required vehicle based on `VehicleType`.

```text
VehicleFactory
      |
      +----> Bike
      |
      +----> Car
      |
      +----> Truck
```

### Strategy Pattern

Strategy Pattern is used for:

```text
ParkingStrategy
      |
      +----> FirstAvailableParkingStrategy

PricingStrategy
      |
      +----> NormalPricingStrategy

PaymentStrategy
      |
      +----> CashPayment
      |
      +----> UPIPayment
      |
      +----> CardPayment
```

This allows different algorithms or implementations to be changed without modifying the main parking flow.

### Observer Pattern

`ParkingFloor` maintains observers and notifies them when parking availability changes.

```text
ParkingFloor
      |
      | notify
      v
ParkingObserver
      ^
      |
ParkingDisplayBoard
```

---

## OOP & SOLID Principles

### Abstraction

Abstract classes and interfaces define common behavior while hiding implementation details.

Examples:

```text
Vehicle
ParkingSpot
ParkingStrategy
PricingStrategy
PaymentStrategy
ParkingObserver
```

### Encapsulation

Internal state is kept private and accessed through methods.

Example:

```java
private String vehicleNumber;
private VehicleType vehicleType;
```

### Inheritance

Common behavior is defined in parent classes and specialized by child classes.

```text
Vehicle
├── Bike
├── Car
└── Truck
```

```text
ParkingSpot
├── BikeSpot
├── CarSpot
└── TruckSpot
```

### Polymorphism

Different implementations can be used through common interfaces.

For example:

```java
PaymentStrategy paymentStrategy;
```

can refer to:

```java
new CashPayment();
new UPIPayment();
new CardPayment();
```

### Single Responsibility

Different responsibilities are separated into different classes:

```text
VehicleFactory     → Vehicle creation
ParkingLot         → Parking management
EntryGate          → Vehicle entry
ExitGate           → Vehicle exit
PricingStrategy    → Price calculation
PaymentStrategy    → Payment processing
ParkingDisplayBoard → Availability display
```

### Open/Closed Principle

New payment methods, pricing strategies, and parking strategies can be added by creating new implementations instead of modifying the existing interfaces.

### Dependency Inversion

Core operations depend on interfaces such as:

```text
ParkingStrategy
PricingStrategy
PaymentStrategy
ParkingObserver
```

rather than depending directly on specific implementations.

---

# Architecture

## Component View

```mermaid
flowchart TB

    CLI["ParkEngine<br/>(CLI Application)"]

    FAC["VehicleFactory"]

    PAY["PaymentStrategy<br/>Cash / UPI / Card"]

    subgraph Core["ParkingLot - Singleton"]
        PL["parkVehicle()<br/>removeVehicle()<br/>searchVehicle()"]
        PS["ParkingStrategy<br/>(First Available)"]
        PR["PricingStrategy<br/>(Normal Pricing)"]
        TK["activeTickets<br/>vehicleTicketMap"]
    end

    subgraph Gates["Gates"]
        EG["EntryGate"]
        XG["ExitGate"]
    end

    subgraph Floors["Parking Floors"]
        PF["ParkingFloor"]
        SP["ParkingSpot<br/>Bike / Car / Truck"]
        OB["ParkingDisplayBoard<br/>(Observer)"]
    end

    CLI -->|"createVehicle()"| FAC
    CLI -->|"getInstance()"| PL
    CLI -->|"payment selection"| PAY

    PL --> PS
    PS -->|"findSpot()"| PF

    PL -->|"occupySpot() / releaseSpot()"| PF

    PF --> SP
    PF -.->|"update()"| OB

    PL -->|"generateTicket()"| EG
    PL -->|"processExit()"| XG

    XG --> PR
    XG --> PAY

    PL --> TK
```

---

## Class Diagram

```mermaid
classDiagram

    class ParkingLot {
        -ParkingLot instance
        -List~ParkingFloor~ floors
        -Map activeTickets
        -Map vehicleTicketMap
        -ParkingStrategy parkingStrategy
        -PricingStrategy pricingStrategy
        +getInstance() ParkingLot
        +parkVehicle(vehicle, entryGate) ParkingTicket
        +removeVehicle(ticketNo, exitGate, payment)
        +searchVehicle(number) ParkingTicket
    }

    class ParkingFloor {
        +occupySpot()
        +releaseSpot()
        +findAvailableSpot()
        +addObserver()
    }

    class ParkingSpot {
        <<abstract>>
        +canFitVehicle()*
    }

    class Vehicle {
        <<abstract>>
        +display()*
    }

    class ParkingTicket

    class EntryGate {
        +generateTicket()
    }

    class ExitGate {
        +processExit()
    }

    class VehicleFactory {
        +createVehicle() Vehicle
    }

    class ParkingStrategy {
        <<interface>>
    }

    class PricingStrategy {
        <<interface>>
    }

    class PaymentStrategy {
        <<interface>>
    }

    class ParkingObserver {
        <<interface>>
    }

    ParkingLot "1" o-- "*" ParkingFloor
    ParkingLot --> ParkingStrategy
    ParkingLot --> PricingStrategy
    ParkingLot ..> EntryGate : uses
    ParkingLot ..> ExitGate : uses
    ParkingLot "1" o-- "*" ParkingTicket : active

    ParkingFloor "1" o-- "*" ParkingSpot
    ParkingFloor "1" o-- "*" ParkingObserver

    ParkingSpot --> Vehicle : holds

    EntryGate ..> ParkingTicket : creates
    ExitGate ..> PricingStrategy : uses
    ExitGate ..> PaymentStrategy : uses

    ParkingTicket --> Vehicle
    ParkingTicket --> ParkingFloor
    ParkingTicket --> ParkingSpot

    VehicleFactory ..> Vehicle : creates

    Vehicle <|-- Bike
    Vehicle <|-- Car
    Vehicle <|-- Truck

    ParkingSpot <|-- BikeSpot
    ParkingSpot <|-- CarSpot
    ParkingSpot <|-- TruckSpot

    ParkingStrategy <|.. FirstAvailableParkingStrategy
    PricingStrategy <|.. NormalPricingStrategy

    PaymentStrategy <|.. CashPayment
    PaymentStrategy <|.. UPIPayment
    PaymentStrategy <|.. CardPayment

    ParkingObserver <|.. ParkingDisplayBoard
```

---

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

> `.class` files are generated Java bytecode and should not be committed to GitHub.

---

# Getting Started

## Prerequisites

- JDK 8 or higher
- Git

Check the installed Java version:

```bash
java -version
javac -version
```

## Clone the Repository

```bash
git clone https://github.com/SanketHajare44/Park-Engine.git
cd Park-Engine
```

## Compile

From the project root:

### Linux / macOS

```bash
javac -d out $(find src -name "*.java")
```

### Windows PowerShell

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object FullName)
```

## Run

```bash
java -cp out ParkEngine
```

---

# Application Menu

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

Enter your choice :
```

---

# Example

## Park Vehicle

```text
Select vehicle type:
1 : Bike
2 : Car
3 : Truck

Enter vehicle Number:
MH12SH003
```

The system:

1. Creates the vehicle using `VehicleFactory`
2. Finds a suitable parking spot
3. Occupies the spot
4. Generates a parking ticket
5. Updates the display board

Example ticket:

```text
------------------ Parking Ticket ------------------

Ticket Number  : 1001
Vehicle Number : MH12SH003
Vehicle Type   : BIKE
Floor Number   : 1
Spot Number    : 101
Ticket Status  : ACTIVE
```

## Exit Vehicle

The user enters the ticket number and selects a payment method:

```text
1 : Cash
2 : UPI
3 : Card
```

The system:

1. Finds the active ticket
2. Calculates the parking duration
3. Calculates the parking charge
4. Processes the selected payment
5. Releases the parking spot
6. Updates the display board
7. Removes the ticket from active records

---

# Pricing

`NormalPricingStrategy` calculates parking charges based on the vehicle type and parking duration.

| Vehicle | Rate per Hour |
| ------- | ------------: |
| Bike    |        Rs. 20 |
| Car     |        Rs. 50 |
| Truck   |       Rs. 100 |

Partial parking hours are rounded up, with a minimum charge of one hour.

---

# Extending the System

The system is designed so that new behavior can be added through existing interfaces.

| Requirement                            | Implementation                                                   |
| -------------------------------------- | ---------------------------------------------------------------- |
| Add a new payment method               | Implement `PaymentStrategy`                                      |
| Add a new pricing rule                 | Implement `PricingStrategy`                                      |
| Add a new parking allocation algorithm | Implement `ParkingStrategy`                                      |
| Add a new availability observer        | Implement `ParkingObserver`                                      |
| Add a new vehicle type                 | Add a `Vehicle` implementation and update vehicle creation logic |

For example, a new payment method can be added by implementing:

```java
public class WalletPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Wallet Payment Successful : Rs. " + amount);
    }
}
```

The existing `PaymentStrategy` contract remains unchanged.

---

# Roadmap

- [ ] Improve CLI input validation
- [ ] Add JUnit 5 unit tests
- [ ] Add persistent storage
- [ ] Add more parking allocation strategies
- [ ] Add dynamic / peak-hour pricing
- [ ] Add monthly parking passes
- [ ] Add concurrency support for multiple gates
- [ ] Add REST API using Spring Boot

These are future improvements and are not part of the current implementation.

---

# Code Commenting Style

Comments in the source code are kept short and focused on the responsibility of the class, interface, enum, or important logic block.

### Class

```java
// Manages the complete parking facility
public class ParkingLot {
}
```

### Interface

```java
// Defines the contract for parking allocation strategies
public interface ParkingStrategy {
}
```

### Enum

```java
// Defines the supported vehicle types
public enum VehicleType {
    BIKE,
    CAR,
    TRUCK
}
```

### Strategy Implementation

```java
// Selects the first suitable available parking spot
public class FirstAvailableParkingStrategy
        implements ParkingStrategy {
}
```

### Factory

```java
// Creates vehicle objects based on vehicle type
public class VehicleFactory {
}
```

### Observer

```java
// Displays parking availability for a floor
public class ParkingDisplayBoard
        implements ParkingObserver {
}
```

Comments should explain **purpose and responsibility**, not obvious Java syntax.

---

# Learning Outcomes

This project provided practical experience with:

- Java
- Object-Oriented Programming
- Low-Level Design
- UML
- SOLID principles
- Abstraction
- Encapsulation
- Inheritance
- Polymorphism
- Interfaces
- Design Patterns
- Separation of Responsibilities
- Loose Coupling
- Object Collaboration

---

# Future Scope

The current implementation is a console-based LLD project. It can be extended into a larger application by adding:

- Database persistence
- REST APIs
- Authentication and authorization
- Web or mobile interface
- Online parking reservation
- Real-time availability
- Payment gateway integration
- Administrative dashboard
- Vehicle number plate recognition
- Advanced parking allocation
- Concurrent vehicle entry and exit handling

---

# Contributing

This project is primarily maintained as a learning and portfolio project.

Suggestions and improvements are welcome.

For a contribution:

```bash
git checkout -b feature/your-feature
```

Make your changes and commit:

```bash
git add .
git commit -m "Add your feature"
```

Push the branch:

```bash
git push origin feature/your-feature
```

---

# Author

**Sanket Sadashiv Hajare**

B.E. Computer Engineering | Java | Software Engineering | System Design | LLD

[![GitHub](https://img.shields.io/badge/GitHub-SanketHajare44-181717?style=flat-square&logo=github)](https://github.com/SanketHajare44)

---

<div align="center">

**ParkEngine - Parking Lot Management System**

</div>
