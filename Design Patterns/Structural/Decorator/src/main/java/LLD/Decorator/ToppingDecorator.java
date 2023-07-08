package LLD.Decorator;

public abstract class ToppingDecorator extends Pizza {
    protected Pizza pizza;

    public abstract String getDescription();

    public abstract float getCost();
}
