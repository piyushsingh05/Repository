package com.vehicle;

public class Vehicle {
    String vehicleRegNum;

    VehicleColour vehicleColour;

    VehicleType vehicleType ;

    public Vehicle(String vehicleRegNum, VehicleColour vehicleColour, VehicleType vehicleType) {
        this.vehicleRegNum = vehicleRegNum;
        this.vehicleColour = vehicleColour;
        this.vehicleType = vehicleType;
    }

    public String getVehicleRegNum() {
        return vehicleRegNum;
    }

    public void setVehicleRegNum(String vehicleRegNum) {
        this.vehicleRegNum = vehicleRegNum;
    }

    public VehicleColour getVehicleColour() {
        return vehicleColour;
    }

    public void setVehicleColour(VehicleColour vehicleColour) {
        this.vehicleColour = vehicleColour;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
