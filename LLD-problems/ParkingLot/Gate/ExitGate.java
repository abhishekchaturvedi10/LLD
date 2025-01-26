package ParkingLot.Gate;

public class ExitGate extends Gate {

    public ExitGate(String id) {
        super(id);
    }

    @Override
    public void openGate() {
        System.out.println("Verifying ticket and opening exit gate " + this.id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Could not open gate: " + this.id);
        }
        System.out.println("Opened exit gate " + this.id);
    }

    @Override
    public void closeGate() {
        System.out.println("Closed exit gate " + this.id);
    }
}
