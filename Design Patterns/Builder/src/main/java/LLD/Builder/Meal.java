package LLD.Builder;

import java.util.ArrayList;
import java.util.List;

public class Meal {

    List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public float price() {
        float mealPrice = 0f;
        for (Item item : items) {
            mealPrice += item.price();
        }
        return mealPrice;
    }

    public void items() {
        for (Item item : items) {
            System.out.println(item.name() + " of Rs. " + item.price() + " packaged in " + item.packaging().pack());
        }
    }
}
