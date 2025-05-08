package com.panels;

import com.display.DisplayPanel;
import com.parkingFloor.ParkingFloor;
import com.parkingSpot.ParkingSpot;
import com.parkingSpot.ParkingSpotType;
import com.ticket.ParkingTicket;
import com.vehicle.Vehicle;
import com.vehicle.VehicleType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class EntryPanel {
    private String panelId;
    private DisplayPanel displayPanel;

    public EntryPanel(String panelId, DisplayPanel displayPanel) {
        this.panelId = panelId;
        this.displayPanel = displayPanel;
    }

    public String getPanelId() {
        return panelId;
    }

    public void setPanelId(String panelId) {
        this.panelId = panelId;
    }

    public DisplayPanel getDisplayPanel() {
        return displayPanel;
    }

    public void setDisplayPanel(DisplayPanel displayPanel) {
        this.displayPanel = displayPanel;
    }

    public ParkingFloor computeFloorTobeParkedOn(List<ParkingFloor> parkingFloors, VehicleType vehicleType) {
        ParkingSpotType parkingSpotType = generateSpotTypeBasedOnVehicleType(vehicleType);
        for(ParkingFloor floor : parkingFloors){
            if(!floor.isFloorUnderMaintenance() && floor.getAvailableSpotCount(parkingSpotType)>0){
                return floor;
            }
        }
        return  null;
    }

    public ParkingTicket generateParkingTicket(Vehicle vehicle, ParkingFloor parkingFloor) {
        ParkingSpotType parkingSpotType = generateSpotTypeBasedOnVehicleType(vehicle.getVehicleType());
        ParkingSpot spot = parkingFloor.getAvailableParkingSpotForVehicle(parkingSpotType);
        spot.parkVehicle(vehicle);
        return  new ParkingTicket(UUID.randomUUID().toString(),vehicle, new Date(), parkingFloor , spot);
    }

    public ParkingSpotType generateSpotTypeBasedOnVehicleType(VehicleType vehicleType) {
        return switch (vehicleType) {
            case CAR -> ParkingSpotType.MEDIUM;
            case BUS, TRUCK -> ParkingSpotType.LARGE;
            case BIKE -> ParkingSpotType.TWO_WHEELER;
            default -> null;
        };
    }
}
