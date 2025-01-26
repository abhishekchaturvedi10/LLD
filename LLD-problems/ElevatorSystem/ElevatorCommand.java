package ElevatorSystem;

public class ElevatorCommand {
    private final User requestedBy;
    private final int sourceFloor;
    private final int destinationFloor;
    private final RequestDirection direction;

    public ElevatorCommand(User requestedBy, int sourceFloor, int destinationFloor, RequestDirection direction) {
        this.requestedBy = requestedBy;
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
        this.direction = direction;
    }

    public User getRequestedBy() {
        return requestedBy;
    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public RequestDirection getDirection() {
        return direction;
    }
}
