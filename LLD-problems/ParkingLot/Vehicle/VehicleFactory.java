package ParkingLot.Vehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType type) {
        return switch (type) {
            case SMALL -> new SmallVehicle();
            case MEDIUM -> new MediumVehicle();
            case LARGE -> new LargeVehicle();
        };
    }
}