package ParkingLot.FeeCalculator;

public class LargeVehicleFeeCalculator implements FeeCalculator {
    @Override
    public double calculateFee(long duration) {
        return 30 * duration;
    }
}
