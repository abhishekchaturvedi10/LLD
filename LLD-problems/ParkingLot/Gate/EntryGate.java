package ParkingLot.Gate;

public class EntryGate extends Gate {

    public EntryGate(String id) {
        super(id);
    }

    @Override
    public void openGate() {
        System.out.println("Verifying ticket and opening entry gate " + this.id);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Could not open gate: " + this.id);
        }
        System.out.println("Opened entry gate " + this.id);
    }

    @Override
    public void closeGate() {
        System.out.println("Closed entry gate " + this.id);
    }
}
