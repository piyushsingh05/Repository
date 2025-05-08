package com.parkingSpot;

import com.vehicle.Vehicle;

public class ParkingSpot {
    private Vehicle vehicle;
    private String spotName;
    private boolean isOccupied;
    private ParkingSpotType parkingSpotType;

    public ParkingSpot(Vehicle vehicle, String spotName, boolean isOccupied, ParkingSpotType parkingSpotType) {
        this.vehicle = vehicle;
        this.spotName = spotName;
        this.isOccupied = isOccupied;
        this.parkingSpotType = parkingSpotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public void setParkingSpotType(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
    }


    public ParkingSpot(String spotName, ParkingSpotType parkingSpotType) {
        this.spotName = spotName;
        this.vehicle = null;
        this.isOccupied = false;
        this.parkingSpotType = parkingSpotType;
    }

    public boolean parkVehicle(Vehicle vehicle){
        this.vehicle = vehicle;
        this.isOccupied = true;
        return true;
    }

    public boolean removeVehicle(){
        this.vehicle = null;
        this.isOccupied = false;

        return true;
    }
}
