package LLD.Adapter;

public class RobotAdapter extends Character {
    private final Robot robot;

    RobotAdapter(Robot robot) {
        super(robot.getName());
        this.robot = robot;
        System.out.println("Adapter robot " + characterName + " created");
    }

    public void assignDriver(String driverName) {
        System.out.println("Calling assign driver in adapter");
        robot.assignController(driverName);
    }

    public void move() {
        System.out.println("Calling move in adapter");
        robot.move();
    }

    public void fire() {
        System.out.println("Calling fire in adapter");
        robot.smash();
    }
}
