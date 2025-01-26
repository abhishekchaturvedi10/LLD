package ParkingLot.FeeCalculator;

public class MediumVehicleFeeCalculator implements FeeCalculator {
    @Override
    public double calculateFee(long duration) {
        return 20 * duration;
    }
}
