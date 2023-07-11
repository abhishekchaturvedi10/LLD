package LLD.Flyweight;

public abstract class Character {
    protected String weapon;

    public void assignWeapon(String weapon) {
        this.weapon = weapon;
    }

    abstract void description();
}
