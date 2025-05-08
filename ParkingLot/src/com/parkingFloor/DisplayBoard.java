package com.parkingFloor;

import com.display.DisplayPanel;
import com.parkingSpot.ParkingSpot;
import com.parkingSpot.ParkingSpotType;

import java.util.Map;

public class DisplayBoard implements DisplayPanel {
    private String displayBoardId;
    private String displayBoardLocation;

    public DisplayBoard(String displayBoardId, String displayBoardLocation) {
        this.displayBoardId = displayBoardId;
        this.displayBoardLocation = displayBoardLocation;
    }

    public String getDisplayBoardId() {
        return displayBoardId;
    }

    public void setDisplayBoardId(String displayBoardId) {
        this.displayBoardId = displayBoardId;
    }

    public String getDisplayBoardLocation() {
        return displayBoardLocation;
    }

    public void setDisplayBoardLocation(String displayBoardLocation) {
        this.displayBoardLocation = displayBoardLocation;
    }

    public void showAvailableSpots(Map<String , ParkingSpot> parkingSpotMap, ParkingSpotType parkingSpotType){
        for(Map.Entry<String , ParkingSpot> entry: parkingSpotMap.entrySet()){
            if(entry.getValue().getParkingSpotType() == parkingSpotType && !entry.getValue().isOccupied()){
                System.out.println(entry.getKey());
            }
        }
    }

    public void showCountOfAvailableSpot(ParkingSpotType parkingSpotType , int count){
        System.out.println("Available Spots of type "+parkingSpotType+ " are "+count);
    }

    public void showCountOfUnAvailableSpot(ParkingSpotType parkingSpotType , int count){
        System.out.println("Unavailable Spots of type "+parkingSpotType+ " are "+count);
    }

    public void showParkingFullMessage(){
        System.out.println("Parking is FULL!!");
    }

    public void showAddNewParkingSpot(String spotName,ParkingSpotType parkingSpotType){
        System.out.println("New parking spot added: "+ spotName +" of type "+ parkingSpotType);
    }

    public void showParkingFloorMaintenance(){
        System.out.println(" FLOOR UNDER MAINTENANCE");
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
