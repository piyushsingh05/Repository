package com.parkinglot;

import com.panels.EntryPanel;
import com.panels.OutPanel;
import com.parkingFloor.ParkingFloor;
import com.parkingSpot.ParkingSpot;
import com.ticket.ParkingTicket;
import com.vehicle.Vehicle;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParkingLot {
   private String parkingLotId;
   private String parkingLotName;
   private String address;
   private int totalFloor;
   private EntryPanel entryPanel;
   private OutPanel outPanel;
   private List<ParkingFloor> parkingFloors ;

    public ParkingLot(String parkingLotId, String parkingLotName, String address, int totalFloor, EntryPanel entryPanel, OutPanel outPanel) {
        this.parkingLotId = parkingLotId;
        this.parkingLotName = parkingLotName;
        this.address = address;
        this.totalFloor = totalFloor;
        this.entryPanel = entryPanel;
        this.outPanel = outPanel;
    }
    public ParkingLot(String parkingLotId, String parkingLotName, String address, int totalFloor, EntryPanel entryPanel, OutPanel outPanel, List<ParkingFloor> parkingFloors) {
        this.parkingLotId = parkingLotId;
        this.parkingLotName = parkingLotName;
        this.address = address;
        this.totalFloor = totalFloor;
        this.entryPanel = entryPanel;
        this.outPanel = outPanel;
        this.parkingFloors = parkingFloors;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(int totalFloor) {
        this.totalFloor = totalFloor;
    }

    public EntryPanel getInPanel() {
        return entryPanel;
    }

    public void setInPanel(EntryPanel entryPanel) {
        this.entryPanel = entryPanel;
    }

    public OutPanel getOutPanel() {
        return outPanel;
    }

    public void setOutPanel(OutPanel outPanel) {
        this.outPanel = outPanel;
    }

    public List<ParkingFloor> getParkingFloors() {
        return parkingFloors;
    }

    public void setParkingFloors(List<ParkingFloor> parkingFloors) {
        this.parkingFloors = parkingFloors;
    }

    public boolean isParkingLotFull() {
        return parkingFloors.stream().allMatch(floor -> floor.getTotalAvailableSpotsCount() == 0);
    }

    public boolean isParkingLotEmpty(){
        return parkingFloors.stream().allMatch(floor -> floor.getTotalOccupiedSpotCount() == 0);

    }
    public void addParkingFloor(ParkingFloor floor){
        if (parkingFloors == null) {
            parkingFloors = new ArrayList<>();
        }
        if (!parkingFloors.contains(floor)) {
            parkingFloors.add(floor);
        }
        parkingFloors.add(floor);

    }

    public void removeParkingFloor(ParkingFloor floor){
         parkingFloors.remove(floor);
    }

    public void parkVehicle(Vehicle vehicle){
        ParkingFloor parkingFloor = entryPanel.computeFloorTobeParkedOn(parkingFloors, vehicle.getVehicleType());
        ParkingTicket ticket = entryPanel.generateParkingTicket(vehicle, parkingFloor);
        ParkingSpot spot = parkingFloor.getAvailableParkingSpotForVehicle(entryPanel.generateSpotTypeBasedOnVehicleType(vehicle.getVehicleType()));
        spot.parkVehicle(vehicle);
        System.out.println("Vehicle parked at spot "+spot.getSpotName()+" on floor "+parkingFloor.getFloorName()+" with ticket "+ticket.getTicketId());
    }

    public void removeVehicleFromParking(Vehicle vehicle , ParkingTicket ticket){
        ParkingFloor parkingFloor = ticket.getParkingFloor();
        ParkingSpot parkingSpot = ticket.getParkingSpot();
        parkingSpot.removeVehicle();
        ticket.setOutTime(Date.from(Instant.now()));
        double amount = outPanel.calculateAmount(ticket);
        ticket.setAmount(amount);
        ticket.setPaid(true);
        System.out.println("Vehicle is out from spot "+parkingSpot.getSpotName()+" on floor "+parkingFloor.getFloorName());
    }
}
