package LLD.Decorator;

public class Cheese extends ToppingDecorator {

    public Cheese(Pizza pizza) {
        this.pizza = pizza;
    }

    public String getDescription() {
        return pizza.getDescription() + ", Cheese";
    }

    public float getCost() {
        return pizza.getCost() + 50;
    }
}
