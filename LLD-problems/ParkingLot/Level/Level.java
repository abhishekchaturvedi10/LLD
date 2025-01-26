package ParkingLot.Level;

import ParkingLot.ParkingSpot;
import ParkingLot.Vehicle.Vehicle;

import java.util.ArrayList;

public abstract class Level {

    private final String id;
    private final int capacity;
    private final ArrayList<ParkingSpot> parkingSpots;

    public Level(String id, int capacity, ArrayList<ParkingSpot> parkingSpots) {
        this.id = id;
        this.capacity = capacity;
        this.parkingSpots = parkingSpots;
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public boolean hasAvailableSpots() {
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.isEmpty() && parkingSpot.getVehicleType().equals(vehicle.getType())) {
                parkingSpot.parkVehicle(vehicle);
                return parkingSpot;
            }
        }
        return null;
    }
}
