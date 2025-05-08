package com.ticket;

import com.parkingFloor.ParkingFloor;
import com.parkingSpot.ParkingSpot;
import com.vehicle.Vehicle;

import java.util.Date;

public class ParkingTicket {
    private String ticketId;
    private Vehicle vehicle;
    private ParkingFloor parkingFloor;
    private Date outTime;
    private boolean isPaid;
    private ParkingSpot parkingSpot;
    private double amount;
    private Date entryTime;

    public ParkingTicket(String ticketId, Vehicle vehicle, ParkingFloor parkingFloor, Date outTime, boolean isPaid, ParkingSpot parkingSpot, double amount, Date entryTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingFloor = parkingFloor;
        this.outTime = outTime;
        this.isPaid = isPaid;
        this.parkingSpot = parkingSpot;
        this.amount = amount;
        this.entryTime = entryTime;
    }

    public ParkingTicket(String ticketId, Vehicle vehicle, Date entryTime, ParkingFloor parkingFloor, ParkingSpot parkingSpot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.parkingFloor = parkingFloor;
        this.parkingSpot = parkingSpot;
        this.isPaid = false;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void setParkingFloor(ParkingFloor parkingFloor) {
        this.parkingFloor = parkingFloor;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
