package com.parkingFloor;

import com.parkingSpot.ParkingSpot;
import com.parkingSpot.ParkingSpotType;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingFloor {
    private  String floorName;

    private  Map<ParkingSpotType , Map<String , ParkingSpot>> allParkingSpot;

    private  DisplayBoard displayBoard;

    private boolean isFloorUnderMaintenance;


    public ParkingFloor(String floorName, Map<ParkingSpotType, Map<String, ParkingSpot>> allParkingSpot, DisplayBoard displayBoard, boolean isFloorUnderMaintenance) {
        this.floorName = floorName;
        this.allParkingSpot = allParkingSpot;
        this.displayBoard = displayBoard;
        this.isFloorUnderMaintenance = isFloorUnderMaintenance;
    }


    public String getFloorName() {

        return floorName;
    }

    public void setFloorName(String floorName) {

        this.floorName = floorName;
    }

    public Map<ParkingSpotType, Map<String, ParkingSpot>> getAllParkingSpot() {

        return allParkingSpot;
    }

    public void setAllParkingSpot(Map<ParkingSpotType, Map<String, ParkingSpot>> allParkingSpot) {
        this.allParkingSpot = allParkingSpot;
    }

    public DisplayBoard getDisplayBoard() {

        return displayBoard;
    }

    public void setDisplayBoard(DisplayBoard displayBoard) {
        this.displayBoard = displayBoard;
    }

    public boolean isFloorUnderMaintenance() {

        return isFloorUnderMaintenance;
    }

    public void setFloorUnderMaintenance(boolean floorUnderMaintenance) {
        isFloorUnderMaintenance = floorUnderMaintenance;
    }

    public ParkingFloor(String floorName, DisplayBoard displayBoard ) {
        this.floorName = floorName;
        this.displayBoard = displayBoard;
        this.isFloorUnderMaintenance = false;
        this.allParkingSpot = new HashMap<>();
    }

    public boolean addParking(ParkingSpotType parkingSpotType, String spotName){
        if(allParkingSpot.containsKey(parkingSpotType)) {
            allParkingSpot.get(parkingSpotType).put(spotName, new ParkingSpot(spotName, parkingSpotType));
            displayBoard.showAddNewParkingSpot(spotName, parkingSpotType);
            return true;
        }
        HashMap<String, ParkingSpot> spotHashMap = new HashMap<>();
        spotHashMap.put(spotName, new ParkingSpot(spotName, parkingSpotType));
        allParkingSpot.put(parkingSpotType, spotHashMap);
        return false;
    }

    public boolean removeParkingSpot(ParkingSpotType parkingSpotType , String spotName){
        if(allParkingSpot.containsKey(parkingSpotType)){
            allParkingSpot.get(parkingSpotType).remove(spotName);
            return true;
        }
        return false;
    }

    public int getAvailableSpotCount(ParkingSpotType parkingSpotType){
        if(allParkingSpot.containsKey(parkingSpotType)){
            return (int)allParkingSpot.get(parkingSpotType).values().stream().filter(theSpot -> !theSpot.isOccupied()).count();
        }
        return 0;
    }

    public int getTotalAvailableSpotsCount(){
        return allParkingSpot.values().stream().mapToInt(theSpot -> (int)theSpot.values().stream().filter(spot -> !spot.isOccupied()).count()).sum();
    }

    public int getOccupiedSpotCount(ParkingSpotType parkingSpotType){
        if(allParkingSpot.containsKey(parkingSpotType)){
            return (int)allParkingSpot.get(parkingSpotType).values().stream().filter(ParkingSpot::isOccupied).count();
        }
        return 0;
    }

    public int getTotalOccupiedSpotCount(){
        return allParkingSpot.values().stream().mapToInt(thSpot ->(int)thSpot.values().stream().filter(ParkingSpot ::isOccupied).count()).sum();
    }

    public ParkingSpot getAvailableParkingSpotForVehicle(ParkingSpotType parkingSpotType){
        //KISS for Understanding
        if(isFloorUnderMaintenance){
            displayBoard.showParkingFloorMaintenance();
            return null;
        }
        if(getAvailableSpotCount(parkingSpotType)==0){
            displayBoard.showParkingFullMessage();
            return null;
        }
        displayBoard.showCountOfAvailableSpot(parkingSpotType ,getAvailableSpotCount(parkingSpotType));
        displayBoard.showAvailableSpots(allParkingSpot.get(parkingSpotType),parkingSpotType);
        if(allParkingSpot.containsKey(parkingSpotType)){
            return  allParkingSpot.get(parkingSpotType).values().stream().filter(theSpot -> !theSpot.isOccupied()).findFirst().orElse(null);
        }
        return null;
    }

    public Map<String ,ParkingSpot> getOccupiedSpotsOfParking(ParkingSpotType parkingSpotType){
        if(allParkingSpot.containsKey(parkingSpotType)){
            return allParkingSpot.get(parkingSpotType).values().stream().filter(ParkingSpot:: isOccupied).collect(Collectors.toMap(ParkingSpot :: getSpotName ,theSpot -> theSpot));

        }
        return null;
    }
    public void enableParkingFloorMaintenance(){
        isFloorUnderMaintenance =true;
        displayBoard.showParkingFloorMaintenance();
    }


}
