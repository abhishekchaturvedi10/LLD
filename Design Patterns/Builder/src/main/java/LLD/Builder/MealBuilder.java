package LLD.Builder;

public class MealBuilder {
    public Meal prepareVegMeal() {
        Meal vegMeal = new Meal();
        vegMeal.addItem(new VegBurger());
        vegMeal.addItem(new Slice());
        return vegMeal;
    }

    public Meal prepareNonVegMeal() {
        Meal nonVegMeal = new Meal();
        nonVegMeal.addItem(new ChickenBurger());
        nonVegMeal.addItem(new Coke());
        return nonVegMeal;
    }
}
