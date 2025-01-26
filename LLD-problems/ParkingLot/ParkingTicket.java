package ParkingLot;

import ParkingLot.FeeCalculator.LargeVehicleFeeCalculator;
import ParkingLot.FeeCalculator.MediumVehicleFeeCalculator;
import ParkingLot.FeeCalculator.FeeCalculator;
import ParkingLot.FeeCalculator.SmallVehicleFeeCalculator;
import ParkingLot.Vehicle.Vehicle;

public class ParkingTicket {

    private final String ticketId;
    private final Vehicle vehicle;
    private final long entryTime;
    private long exitTime;
    private double amount;
    private boolean isPaid;
    private final ParkingSpot parkingSpot;
    private FeeCalculator feeCalculator;

    public ParkingTicket(String ticketId, Vehicle vehicle, long entryTime, ParkingSpot parkingSpot) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.entryTime = entryTime;
        this.isPaid = false;
        this.parkingSpot = parkingSpot;
        setFeeCalculator(vehicle);
    }

    private void setFeeCalculator(Vehicle vehicle) {
        switch (vehicle.getType()) {
            case SMALL:
                this.feeCalculator = new SmallVehicleFeeCalculator();
                break;
            case MEDIUM:
                this.feeCalculator = new MediumVehicleFeeCalculator();
                break;
            case LARGE:
                this.feeCalculator = new LargeVehicleFeeCalculator();
                break;
            default:
                throw new IllegalArgumentException("Invalid vehicle type");
        }
    }

    public double calculateFee() {
        long duration = exitTime - entryTime;
        return feeCalculator.calculateFee(duration);
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}
