import com.panels.EntryPanel;
import com.panels.OutPanel;
import com.parkingFloor.DisplayBoard;
import com.parkingFloor.ParkingFloor;
import com.parkingSpot.ParkingSpotType;
import com.parkinglot.ParkingLot;
import com.vehicle.Vehicle;
import com.vehicle.VehicleColour;
import com.vehicle.VehicleType;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        DisplayBoard entryBoard = new DisplayBoard(UUID.randomUUID().toString(), "ONE Mall Parking Lot Main Entrance");
        DisplayBoard outBoard = new DisplayBoard(UUID.randomUUID().toString(), "ONE Mall Parking Lot Main Exit");
        EntryPanel entryPanel = new EntryPanel(UUID.randomUUID().toString(), entryBoard);
        OutPanel outPanel = new OutPanel(UUID.randomUUID().toString(), outBoard);

        entryBoard.displayMessage("Welcome to ONE Mall Parking Lot!");

        ParkingLot multiFloorParkingLot = new ParkingLot(UUID.randomUUID().toString(),"ONE MALL","Noida",5,entryPanel,outPanel);
        ParkingFloor groundFloor = new ParkingFloor("ground Floor", new DisplayBoard(UUID.randomUUID().toString(), "Ground Floor"));
        ParkingFloor firstFloor = new ParkingFloor("First Floor", new DisplayBoard(UUID.randomUUID().toString(), "First Floor"));
        ParkingFloor secondFloor = new ParkingFloor("Second Floor", new DisplayBoard(UUID.randomUUID().toString(), "Second Floor"));
        ParkingFloor thirdFloor = new ParkingFloor("Third Floor", new DisplayBoard(UUID.randomUUID().toString(), "Third Floor"));
        ParkingFloor fourthFloor = new ParkingFloor("Fourth Floor", new DisplayBoard(UUID.randomUUID().toString(), "Fourth Floor"));

        multiFloorParkingLot.addParkingFloor(groundFloor);
        multiFloorParkingLot.addParkingFloor(firstFloor);
        multiFloorParkingLot.addParkingFloor(secondFloor);
        multiFloorParkingLot.addParkingFloor(thirdFloor);
        multiFloorParkingLot.addParkingFloor(fourthFloor);

        groundFloor.addParking(ParkingSpotType.TWO_WHEELER, "T1");
        groundFloor.addParking(ParkingSpotType.MEDIUM, "M1");
        groundFloor.addParking(ParkingSpotType.LARGE, "L1");
        groundFloor.addParking(ParkingSpotType.TWO_WHEELER, "T2");
        groundFloor.addParking(ParkingSpotType.MEDIUM, "M2");
        groundFloor.addParking(ParkingSpotType.LARGE, "L2");
        groundFloor.addParking(ParkingSpotType.TWO_WHEELER, "T3");
        groundFloor.addParking(ParkingSpotType.MEDIUM, "M3");
        groundFloor.addParking(ParkingSpotType.LARGE, "L3");
        groundFloor.addParking(ParkingSpotType.TWO_WHEELER, "T4");
        groundFloor.addParking(ParkingSpotType.MEDIUM, "M4");
        groundFloor.addParking(ParkingSpotType.LARGE, "L4");
        groundFloor.addParking(ParkingSpotType.TWO_WHEELER, "T5");
        groundFloor.addParking(ParkingSpotType.MEDIUM, "M5");
        groundFloor.addParking(ParkingSpotType.LARGE, "L5");
        groundFloor.addParking(ParkingSpotType.TWO_WHEELER, "T6");
        groundFloor.addParking(ParkingSpotType.MEDIUM, "M6");
        groundFloor.addParking(ParkingSpotType.LARGE, "L6");

        Vehicle vehicle =  new Vehicle("UP-16-MB-0001", VehicleColour.BLUE, VehicleType.CAR);
        multiFloorParkingLot.parkVehicle(vehicle);
        

        outBoard.displayMessage("Thank you for visiting ONE Mall Parking Lot!");
    }
    
}