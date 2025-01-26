package ElevatorSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ElevatorSystem {
    public static void main(String[] args) throws InterruptedException {
        int numElevators = 3;
        Elevator[] elevators = new Elevator[numElevators];
        List<User> users = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numElevators; i++) {
            elevators[i] = new Elevator("ELEVATOR-"+(char) ('A' + i), random.nextInt(11), 4);
            System.out.println(elevators[i].getElevatorId() + " starting at floor " + elevators[i].getCurrentFloor());
        }

        ElevatorService elevatorService = new ElevatorService(List.of(elevators));
        elevatorService.startElevatorSystem();

        for (int i = 0; i < 10; i++) {
            users.add(new User("USER-"+i, "NAME-"+i));
            int sourceFloor = random.nextInt(11), destinationFloor = random.nextInt(11);
            while (sourceFloor == destinationFloor) {
                destinationFloor = random.nextInt(11);
            }
            ElevatorCommand command = new ElevatorCommand(users.get(i), sourceFloor, destinationFloor, sourceFloor < destinationFloor ? RequestDirection.UP : RequestDirection.DOWN);
            Thread.sleep(500);
            System.out.println("Request " + i + " submitted");
            elevatorService.executeCommand(command);
        }
    }
}
