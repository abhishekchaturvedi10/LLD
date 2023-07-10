package LLD.Adapter;

public class AdapterApplication {

    public static void main(String[] args) {

        Robot robot = new Robot("Ncs86d");
        Character tank = new Tank("Gxv7");
        Character adaptedRobot = new RobotAdapter(robot);

        robot.assignController("Tommy");
        robot.move();
        robot.smash();

        tank.assignDriver("Frank");
        tank.move();
        tank.fire();

        adaptedRobot.assignDriver("Paul");
        adaptedRobot.move();
        adaptedRobot.fire();
    }
}
