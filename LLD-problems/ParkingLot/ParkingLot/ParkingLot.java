package ParkingLot.ParkingLot;

import ParkingLot.Level.Level;

import java.util.ArrayList;

public class ParkingLot {

    private final ArrayList<Level> levels;

    public ParkingLot(ArrayList<Level> levels) {
        this.levels = levels;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }
}
