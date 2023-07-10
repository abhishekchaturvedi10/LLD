package LLD.Adapter;

public abstract class Character {
    protected String characterName;

    Character(String characterName) {
        this.characterName = characterName;
    }

    public abstract void assignDriver(String driverName);

    public abstract void move();

    public abstract void fire();
}
