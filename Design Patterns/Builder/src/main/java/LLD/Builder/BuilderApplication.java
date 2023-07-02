package LLD.Builder;

public class BuilderApplication {

    public static void main(String[] args) {

        MealBuilder mealBuilder = new MealBuilder();

        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("VEG MEAL");
        vegMeal.items();
        System.out.println("Total Cost: " + vegMeal.price());

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.out.println("\n\nNON-VEG MEAL");
        nonVegMeal.items();
        System.out.println("Total Cost: " + nonVegMeal.price());
    }
}
