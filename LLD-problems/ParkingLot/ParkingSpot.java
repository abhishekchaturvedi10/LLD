package ParkingLot;

import ParkingLot.Vehicle.Vehicle;
import ParkingLot.Vehicle.VehicleType;

public class ParkingSpot {

    private final String id;
    private final VehicleType vehicleType;
    private Vehicle vehicle;

    public ParkingSpot(String id, VehicleType vehicleType) {
        this.id = id;
        this.vehicleType = vehicleType;
        this.vehicle = null;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean isEmpty() {
        return vehicle == null;
    }

    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void unParkVehicle() {
        this.vehicle = null;
    }
}
