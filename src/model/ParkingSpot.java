package model;

public abstract class ParkingSpot {

    private int spotNumber;

    private SpotType spotType;

    private boolean occupied;

    private Vehicle vehicle;

    public ParkingSpot(int spotNumber, SpotType spotType){

        this.spotNumber = spotNumber;
        this.spotType = spotType;

        this.occupied = false;
        this.vehicle = null;
    }

    public int getSpotNumber(){

        return this.spotNumber;
    }

    public SpotType getSpotType(){

        return this.spotType;
    }

    public boolean isOccupied(){

        return this.occupied;
    }

    public Vehicle getVehicle(){

        return this.vehicle;
    }

    public void parkVehicle(Vehicle vehicle){

        if(this.occupied == true)
        {
            throw new RuntimeException("Parking spot is already occupied");
        } else{

            this.vehicle = vehicle;
            this.occupied = true;
        }
    }

    public Vehicle removeVehicle(){

        if(this.occupied == true){

            Vehicle temp = this.vehicle;

            this.vehicle = null;
            this.occupied = false;

            return  temp;
        }else{

            throw new RuntimeException("Parking spot is already empty");
        }

    }

    public void display(){

        System.out.println("Spot : "+this.spotNumber+" ["+this.spotType+"] ");

        if(this.occupied == true){

            System.out.println("Occupied by : "+ this.vehicle.getVehicleNumber());
        }else{

            System.out.println("Spot is available.");
        }
    }

    public abstract boolean canFitVehicle(Vehicle vehicle); 
    
} // End of ParkingSpot class