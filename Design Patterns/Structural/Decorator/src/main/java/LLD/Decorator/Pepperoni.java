package LLD.Decorator;

public class Pepperoni extends ToppingDecorator {

    public Pepperoni(Pizza pizza) {
        this.pizza = pizza;
    }

    public String getDescription() {
        return pizza.getDescription() + ", Pepperoni";
    }

    public float getCost() {
        return pizza.getCost() + 35;
    }
}
