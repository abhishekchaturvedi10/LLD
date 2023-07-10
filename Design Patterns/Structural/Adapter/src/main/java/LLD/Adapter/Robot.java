package LLD.Adapter;

public class Robot {
    private final String name;

    Robot(String robotName) {
        name = robotName;
        System.out.println("Robot " + name + " created");
    }

    public void assignController(String controllerName) {
        System.out.println(controllerName + " is controlling the robot " + name);
    }

    public void move() {
        System.out.println(name + " Robot is moving");
    }

    public void smash() {
        System.out.println(name + " Robot is Smashing");
    }

    public String getName() {
        return name;
    }
}
