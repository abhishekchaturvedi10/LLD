package ParkingLot.ParkingLot;

import ParkingLot.Level.Level;

import java.util.ArrayList;

public class ParkingLotBuilder {

    private final ArrayList<Level> levels;

    public ParkingLotBuilder() {
        levels = new ArrayList<>();
    }

    public ParkingLotBuilder addLevel(Level level) {
        levels.add(level);
        return this;
    }

    public ParkingLot build() {
        return new ParkingLot(levels);
    }
}
