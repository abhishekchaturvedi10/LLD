package LLD.Builder;

public abstract class ColdDrink implements Item {
    public abstract String name();

    public Packaging packaging() {
        return new Bottle();
    }

    public abstract float price();
}
