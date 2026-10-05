/*
    1 : Create ParkingLot class object

    2 : Create multiple floor and add spots

    3 : Display boards;

    4 : Register observer

    5 : Add floor to parkingLot

    6 : Create Entry  Exit Gate

    7 : Display menu

*/

import java.util.*;

import factory.VehicleFactory;
import gate.EntryGate;
import gate.ExitGate;
import model.BikeSpot;
import model.CarSpot;
import model.ParkingFloor;
import model.ParkingTicket;
import model.TruckSpot;
import model.Vehicle;
import model.VehicleType;
import observer.ParkingDisplayBoard;
import payment.CardPayment;
import payment.CashPayment;
import payment.PaymentStrategy;
import payment.UPIPayment;

public class ParkEngine {

    public static void main(String A[]){

       Scanner sobj = new Scanner(System.in);
       
        ///////////////////////////////////////////////////
        /// 1 : Create Single Parking Lot object
        ///////////////////////////////////////////////////
        
        ParkingLot parkingLot = ParkingLot.getInstance();

        parkingLot.setParkingLotName("ParkEngine");

        ///////////////////////////////////////////////////
        /// 2 : Create multiple floor and add spots
        ///////////////////////////////////////////////////

        // Add first floor
        ParkingFloor floor1 = new ParkingFloor(1);

        // Add multiple spots on the floor1
        floor1.addParkingSpot(new BikeSpot(101));
        floor1.addParkingSpot(new BikeSpot(102));

        floor1.addParkingSpot(new CarSpot(103));
        floor1.addParkingSpot(new CarSpot(104));

        floor1.addParkingSpot(new TruckSpot(105));
        floor1.addParkingSpot(new TruckSpot(106));


        // Add Second floor
        ParkingFloor floor2 = new ParkingFloor(2);

        // Add multiple spots on the floor1
        floor2.addParkingSpot(new BikeSpot(201));
        floor2.addParkingSpot(new BikeSpot(202));

        floor2.addParkingSpot(new CarSpot(203));
        floor2.addParkingSpot(new CarSpot(204));

        floor2.addParkingSpot(new TruckSpot(205));
        floor2.addParkingSpot(new TruckSpot(206));

        ///////////////////////////////////////////////////
        /// 3 : Create Display Board
        ///////////////////////////////////////////////////
        
        ParkingDisplayBoard board1 = new ParkingDisplayBoard(floor1);

        ParkingDisplayBoard board2 = new ParkingDisplayBoard(floor2);

        ///////////////////////////////////////////////////
        /// 4 : Register Display board
        ///////////////////////////////////////////////////
        
        floor1.addObservers(board1);

        floor2.addObservers(board2);

        ///////////////////////////////////////////////////
        /// 5 : Add Floors to ParkingLot
        ///////////////////////////////////////////////////

        parkingLot.addFloor(floor1);

        parkingLot.addFloor(floor2);

        ///////////////////////////////////////////////////
        /// 6 : Create Entry gate and exit gate
        ///////////////////////////////////////////////////
        
        EntryGate entryGate = new EntryGate(1);

        ExitGate exitGate = new ExitGate(1);

        ///////////////////////////////////////////////////
        /// 7 : Display Menu
        ///////////////////////////////////////////////////
        
        int choice = 0;

        while(true){

            System.out.println("---------------------------------------------------");
            System.out.println("------------------- Park Engine -------------------");
            System.out.println("---------------------------------------------------");

            System.out.println();

            System.out.println("1 : Park Vehicle");
            System.out.println("2 : Exit Vehicle");
            System.out.println("3 : Search Vehicle");
            System.out.println("4 : Display Parking Lot");
            System.out.println("5 : Exit");

            System.out.println();

            System.out.println("---------------------------------------------------");

            System.out.println();
            System.out.println("Enter your choice        : ");


            choice = sobj.nextInt();

            try
            {
                switch (choice) {
                    case 1: // Park Vehicle
                        {
                            System.out.println();

                            System.out.println("Select vehicle type      : ");

                            System.out.println("1 : Bike");
                            System.out.println("2 : Car");
                            System.out.println("3 : Truck");
                            System.out.println();

                            int type = sobj.nextInt();

                            System.out.println();

                            System.out.println("Enter vehicle Number     : ");

                            String number = sobj.next();

                            System.out.println();

                            Vehicle vehicle;

                            // Factory Pattern is used

                            switch (type) {
                                case 1: // BIKE
                                    vehicle = VehicleFactory.createVehicle(VehicleType.BIKE, number);
                                    break;

                                case 2: // CAR
                                    vehicle = VehicleFactory.createVehicle(VehicleType.CAR, number);
                                    break;

                                case 3: // TRUCK
                                    vehicle = VehicleFactory.createVehicle(VehicleType.TRUCK, number);
                                    break;
                                
                                default:
                                    System.out.print("Invalid type of Vehicle");

                                    continue;
                            }

                            // Park the vehicle 

                            ParkingTicket ticket = parkingLot.parkVehicle(vehicle, entryGate);

                            // Display genreated ticket

                            ticket.displayTicket();

                            break;

                        }
                    case 2: // Exit Vehicle
                        {
                            System.out.println("Enter ticket number     : ");

                            int ticketNumber = sobj.nextInt();

                            System.out.println();

                            System.out.println("Enter the Payment option : ");

                            System.out.println("1 : Cash");
                            System.out.println("2 : Upi");
                            System.out.println("3 : Card");

                            int paymentType = sobj.nextInt();

                            PaymentStrategy paymentStrategy;

                            switch (paymentType) {
                                case 1: // Cash
                                    paymentStrategy = new CashPayment();
                                    break;

                                case 2: // Upi
                                    paymentStrategy = new UPIPayment();
                                    break;

                                case 3: // Card
                                    paymentStrategy = new CardPayment();
                                    break;
                                
                                default:
                                    System.out.println("Invalid payment option");
                                    continue;

                            }// End of  payment switch

                            parkingLot.removeVehicle(ticketNumber, exitGate, paymentStrategy);

                            break;
                        }

                    case 3: //
                        {
                            System.out.println("Enter vehicle number : ");

                            String vehicleNumber = sobj.next();

                            ParkingTicket ticket = parkingLot.searchVehicle(vehicleNumber);

                            if(ticket == null)
                            {
                                System.err.println("This Vehicle is not parked");
                            }
                            else
                            {
                                ticket.displayTicket();
                            }

                            break;
                        }
    
                    case 4: // Display Parking Lot
                        {
                            parkingLot.displayParkingLot();

                            break;
                        }

                    case 5:
                        {   
                            System.out.println();
                            System.out.println("-------- Thank You For Using Park Engine ----------");

                            sobj.close();

                            return;
                        }
                
                    default:
                        {
                            System.out.println("Invalid option");

                            break;
                        }
                    
                }// End of switch


            }// End of try
            catch(Exception eobj){
                System.out.println("Exception occured : "+ eobj);
            }
            
        }// End of while

    } // End of main method
    
} // End of ParkEngine Class