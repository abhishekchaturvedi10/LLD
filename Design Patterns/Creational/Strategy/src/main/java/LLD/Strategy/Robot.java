package LLD.Strategy;

public class Robot {
    String name;
    private IBehaviour behaviour;

    Robot(String name) {
        this.name = name;
    }

    public void setBehaviour(IBehaviour behaviour) {
        this.behaviour = behaviour;
    }

    public void move() {
        int step = behaviour.moveCommand();
        System.out.println("Robot " + name + " moved " + step);
    }
}
