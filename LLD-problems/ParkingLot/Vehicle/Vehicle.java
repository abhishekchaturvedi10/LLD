package ParkingLot.Vehicle;

public abstract class Vehicle {

    private final VehicleType type;

    protected Vehicle(VehicleType type) {
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }
}
