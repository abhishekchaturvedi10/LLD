package LLD.Builder;

public abstract class Burger implements Item {
    public abstract String name();

    public Packaging packaging() {
        return new Wrapper();
    }

    public abstract float price();
}
