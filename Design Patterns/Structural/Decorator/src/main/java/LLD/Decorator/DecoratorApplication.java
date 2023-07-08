package LLD.Decorator;

public class DecoratorApplication {

    public static void main(String[] args) {
        Pizza basePizza = new Pizza();
        System.out.println(basePizza.getDescription() + " Cost: " + basePizza.getCost());

        Pizza farmHouse = new FarmHouse();
        System.out.println(farmHouse.getDescription() + " Cost: " + farmHouse.getCost());

        Pizza chickenFiesta = new ChickenFiesta();
        System.out.println(chickenFiesta.getDescription() + " Cost: " + chickenFiesta.getCost());

        Pizza doubleCheese = new Cheese(new Cheese(new Pizza()));
        System.out.println(doubleCheese.getDescription() + "  Cost: " + doubleCheese.getCost());

        Pizza cheeseFarmHouse = new Cheese(new FarmHouse());
        System.out.println(cheeseFarmHouse.getDescription() + "  Cost: " + cheeseFarmHouse.getCost());

        Pizza cheeseAndPepperoniFarmHouse = new Cheese(new Pepperoni(new FarmHouse()));
        System.out.println(cheeseAndPepperoniFarmHouse.getDescription() + "  Cost: " + cheeseAndPepperoniFarmHouse.getCost());
    }
}
