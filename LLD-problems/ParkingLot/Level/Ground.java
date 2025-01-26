package ParkingLot.Level;

import ParkingLot.Gate.Gate;
import ParkingLot.ParkingSpot;

import java.util.ArrayList;

public class Ground extends Level {

    private ArrayList<Gate> entryGates;
    private ArrayList<Gate> exitGates;

    public Ground(String id, int capacity, ArrayList<ParkingSpot> parkingSpots, ArrayList<Gate> entryGates, ArrayList<Gate> exitGates) {
        super(id, capacity, parkingSpots);
        this.entryGates = entryGates;
        this.exitGates = exitGates;
    }
}
