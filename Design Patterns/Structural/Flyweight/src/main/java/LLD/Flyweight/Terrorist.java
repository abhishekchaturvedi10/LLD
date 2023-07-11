package LLD.Flyweight;

public class Terrorist extends Character {

    Terrorist() {
        System.out.println("Creating a Terrorist");
    }

    public void description() {
        System.out.println("Terrorist with weapon " + weapon);
    }
}
