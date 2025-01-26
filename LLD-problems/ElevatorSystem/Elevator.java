package ElevatorSystem;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class Elevator {
    private final String elevatorId;
    private int currentFloor;
    private final SortedSet<Integer> sourceFloors;
    private final SortedSet<Integer> destinationFloors;
    private ElevatorState state;
    private final int capacity;
    private int currentOccupancy;

    public Elevator(String elevatorId, int currentFloor, int capacity) {
        this.elevatorId = elevatorId;
        this.currentFloor = currentFloor;
        this.state = ElevatorState.IDLE;
        this.capacity = capacity;
        this.currentOccupancy = 0;
        this.sourceFloors = Collections.synchronizedSortedSet(new TreeSet<>());
        this.destinationFloors = Collections.synchronizedSortedSet(new TreeSet<>());
    }

    public String getElevatorId() {
        return elevatorId;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public SortedSet<Integer> getSourceFloors() {
        return sourceFloors;
    }

    public SortedSet<Integer> getDestinationFloors() {
        return destinationFloors;
    }

    public void addSourceFloor(int sourceFloor) {
        sourceFloors.add(sourceFloor);
    }

    public void addDestinationFloor(int destinationFloor) {
        destinationFloors.add(destinationFloor);
    }

    public void removeSourceFloor(int sourceFloor) {
        sourceFloors.remove(sourceFloor);
    }

    public void removeDestinationFloor(int destinationFloor) {
        destinationFloors.remove(destinationFloor);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public void incrementOccupancy() {
        this.currentOccupancy++;
    }

    public void decrementOccupancy() {
        this.currentOccupancy--;
    }
}
