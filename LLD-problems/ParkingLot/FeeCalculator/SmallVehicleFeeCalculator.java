package ParkingLot.FeeCalculator;

public class SmallVehicleFeeCalculator implements FeeCalculator {
    @Override
    public double calculateFee(long duration) {
        return 10 * duration;
    }
}
