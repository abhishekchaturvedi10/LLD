package LLD.Flyweight;

public class Soldier extends Character {

    Soldier() {
        System.out.println("Creating a Soldier");
    }

    public void description() {
        System.out.println("Soldier with weapon " + weapon);
    }
}
