package ParkingLot.Gate;

public abstract class Gate {

        protected final String id;
        protected boolean isFree;

        public Gate(String id) {
            this.id = id;
            this.isFree = true;
        }

        public abstract void openGate();

        public abstract void closeGate();
}
