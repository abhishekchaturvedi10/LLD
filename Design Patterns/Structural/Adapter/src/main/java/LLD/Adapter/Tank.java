package LLD.Adapter;

public class Tank extends Character {

    Tank(String tankName) {
        super(tankName);
        System.out.println("Tank " + super.characterName + " created");
    }

    public void assignDriver(String driverName) {
        System.out.println(driverName + " is driving the tank " + characterName);
    }

    public void move() {
        System.out.println(characterName + " Tank is moving");
    }

    public void fire() {
        System.out.println(characterName + " Tank is firing");
    }
}
