package ElevatorSystem;

import java.util.*;

public class ElevatorService {
    private final List<Elevator> elevators;

    public ElevatorService(List<Elevator> elevators) {
        this.elevators = elevators;
    }

    public void run(Elevator elevator) throws InterruptedException {
        moveElevator(elevator);
    }

    private Elevator findElevator(int sourceFloor, int destinationFloor, RequestDirection direction) {
        int waitTime = Integer.MAX_VALUE;
        Elevator selectedElevator = null;
        for (Elevator elevator : elevators) {
            if (elevator.getCapacity() <= elevator.getCurrentOccupancy() + elevator.getSourceFloors().size()) {
                continue;
            }
            int elevatorCurrentFloor = elevator.getCurrentFloor();
            SortedSet<Integer> destinationFloors = elevator.getDestinationFloors();
            int timeToWaitForLift, travelTimeInLift;
            if (elevator.getState().equals(ElevatorState.MOVING_UP)) {
                if (direction.equals(RequestDirection.UP)) {
                    if (elevatorCurrentFloor < sourceFloor) {
                        timeToWaitForLift = sourceFloor - elevatorCurrentFloor;
                    } else {
                        timeToWaitForLift = (destinationFloors.last() - elevatorCurrentFloor) + (destinationFloors.last() - sourceFloor);
                    }
                    travelTimeInLift = destinationFloor - sourceFloor;
                } else {
                    if (elevatorCurrentFloor >= sourceFloor) {
                        timeToWaitForLift = elevatorCurrentFloor - sourceFloor;
                    } else {
                        timeToWaitForLift = (elevatorCurrentFloor - destinationFloors.first()) + (sourceFloor - destinationFloors.first());
                    }
                    travelTimeInLift = sourceFloor - destinationFloor;
                }
                if (waitTime > timeToWaitForLift + travelTimeInLift) {
                    waitTime = timeToWaitForLift + travelTimeInLift;
                    selectedElevator = elevator;
                }
            } else if (elevator.getState().equals(ElevatorState.MOVING_DOWN)) {
                if (direction.equals(RequestDirection.UP)) {
                    if (elevatorCurrentFloor >= sourceFloor) {
                        timeToWaitForLift = elevatorCurrentFloor - sourceFloor;
                        travelTimeInLift = sourceFloor - destinationFloors.first() + destinationFloor - destinationFloors.first();
                    } else {
                        timeToWaitForLift = elevatorCurrentFloor - destinationFloors.first() + sourceFloor - destinationFloors.first();
                        travelTimeInLift = destinationFloor - sourceFloor;
                    }
                } else {
                    if (elevatorCurrentFloor <= sourceFloor) {
                        timeToWaitForLift = sourceFloor - elevatorCurrentFloor;
                        travelTimeInLift = destinationFloors.last() - sourceFloor + destinationFloors.last() - destinationFloor;
                    } else {
                        timeToWaitForLift = destinationFloors.last() - elevatorCurrentFloor + destinationFloors.last() - sourceFloor;
                        travelTimeInLift = sourceFloor - destinationFloor;
                    }
                }
                if (waitTime > timeToWaitForLift + travelTimeInLift) {
                    waitTime = timeToWaitForLift + travelTimeInLift;
                    selectedElevator = elevator;
                }
            } else {
                if (elevatorCurrentFloor < sourceFloor) {
                    timeToWaitForLift = sourceFloor - elevatorCurrentFloor;
                } else {
                    timeToWaitForLift = elevatorCurrentFloor - sourceFloor;
                }
                travelTimeInLift = Math.abs(destinationFloor - sourceFloor);
                if (waitTime > timeToWaitForLift + travelTimeInLift) {
                    waitTime = timeToWaitForLift + travelTimeInLift;
                    selectedElevator = elevator;
                }
            }
        }
        return selectedElevator;
    }

    public synchronized void executeCommand(ElevatorCommand elevatorCommand) {
        Elevator elevator = findElevator(elevatorCommand.getSourceFloor(), elevatorCommand.getDestinationFloor(), elevatorCommand.getDirection());
        System.out.println(elevator.getElevatorId() + " found for " + elevatorCommand.getSourceFloor() + " to " + elevatorCommand.getDestinationFloor());
        elevator.addSourceFloor(elevatorCommand.getSourceFloor());
        elevator.addDestinationFloor(elevatorCommand.getDestinationFloor());
        notifyAll();
    }

    public synchronized void moveElevator(Elevator elevator) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is moving " + elevator.getElevatorId());
        while (true) {
            SortedSet<Integer> sourceFloors = Collections.synchronizedSortedSet(elevator.getSourceFloors());
            SortedSet<Integer> destinationFloors = Collections.synchronizedSortedSet(elevator.getDestinationFloors());
            System.out.println(elevator.getElevatorId() + " is " + elevator.getState() + " at " + elevator.getCurrentFloor() + " with source floors " + sourceFloors + " destination floors " + destinationFloors);
            if (destinationFloors.isEmpty() && sourceFloors.isEmpty()) {
                elevator.setState(ElevatorState.IDLE);
                wait();
            }
            ArrayList<Integer> removeSourceFloor = new ArrayList<>();
            ArrayList<Integer> removeDestinationFloor = new ArrayList<>();
            int currentFloor = elevator.getCurrentFloor();
            for (int sourceFloor : sourceFloors) {
                if (sourceFloor == currentFloor) {
                    System.out.println(elevator.getElevatorId() + " is at " + elevator.getCurrentFloor() + " with source floors " + sourceFloors + " destination floors " + destinationFloors);
                    elevator.setState(ElevatorState.DOOR_OPEN);
                    Thread.sleep(2000);
                    elevator.incrementOccupancy();
                    removeSourceFloor.add(currentFloor);
                    elevator.setState(ElevatorState.MOVING_UP);
                }
            }
            for (int destinationFloor : destinationFloors) {
                if (destinationFloor == currentFloor) {
                    System.out.println(elevator.getElevatorId() + " is at " + elevator.getCurrentFloor() + " with source floors " + sourceFloors + " destination floors " + destinationFloors);
                    elevator.setState(ElevatorState.DOOR_OPEN);
                    Thread.sleep(2000);
                    elevator.decrementOccupancy();
                    removeDestinationFloor.add(currentFloor);
                    elevator.setState(ElevatorState.MOVING_UP);
                }
            }
            for (int sourceFloor : removeSourceFloor) {
                elevator.removeSourceFloor(sourceFloor);
            }
            for (int destinationFloor : removeDestinationFloor) {
                elevator.removeDestinationFloor(destinationFloor);
            }
            sourceFloors = Collections.synchronizedSortedSet(elevator.getSourceFloors());
            destinationFloors = Collections.synchronizedSortedSet(elevator.getDestinationFloors());
            if (elevator.getState().equals(ElevatorState.MOVING_UP)) {
                boolean liftMovingUp = false;
                for (int sourceFloor : sourceFloors) {
                    if (sourceFloor > currentFloor) {
                        elevator.setCurrentFloor(currentFloor + 1);
                        liftMovingUp = true;
                        break;
                    }
                }
                Iterator<Integer> destinationIterator = destinationFloors.iterator();
                while (!liftMovingUp && destinationIterator.hasNext()) {
                    int destinationFloor = destinationIterator.next();
                    if (destinationFloor > currentFloor) {
                        elevator.setCurrentFloor(currentFloor + 1);
                        liftMovingUp = true;
                        break;
                    }
                }
                if (!liftMovingUp) {
                    elevator.setState(ElevatorState.MOVING_DOWN);
                }
            } else if (elevator.getState().equals(ElevatorState.MOVING_DOWN)) {
                boolean liftMovingDown = false;
                for (int sourceFloor : sourceFloors) {
                    if (sourceFloor < currentFloor) {
                        elevator.setCurrentFloor(currentFloor - 1);
                        liftMovingDown = true;
                        break;
                    }
                }
                Iterator<Integer> destinationIterator = destinationFloors.iterator();
                while (!liftMovingDown && destinationIterator.hasNext()) {
                    int destinationFloor = destinationIterator.next();
                    if (destinationFloor < currentFloor) {
                        elevator.setCurrentFloor(currentFloor - 1);
                        liftMovingDown = true;
                        break;
                    }
                }
                if (!liftMovingDown) {
                    elevator.setState(ElevatorState.MOVING_UP);
                }
            } else {
                if (!sourceFloors.isEmpty()) {
                    if (sourceFloors.first() > currentFloor) {
                        elevator.setState(ElevatorState.MOVING_UP);
                    } else {
                        elevator.setState(ElevatorState.MOVING_DOWN);
                    }
                }
                if (!destinationFloors.isEmpty()) {
                    if (destinationFloors.first() > currentFloor) {
                        elevator.setState(ElevatorState.MOVING_UP);
                    } else {
                        elevator.setState(ElevatorState.MOVING_DOWN);
                    }
                }
            }
            for (int sourceFloor : removeSourceFloor) {
                elevator.removeSourceFloor(sourceFloor);
            }
            for (int destinationFloor : removeDestinationFloor) {
                elevator.removeDestinationFloor(destinationFloor);
            }
        }
    }

    public void startElevatorSystem() {
        ArrayList<Thread> threads = new ArrayList<>();
        for (Elevator elevator : elevators) {
            threads.add(new Thread(() -> {
                try {
                    run(elevator);
                } catch (InterruptedException e) {
                    System.out.println("Elevator " + elevator.getElevatorId() + " interrupted");
                }
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }
}