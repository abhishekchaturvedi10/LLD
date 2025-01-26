package Zomato;

import Zomato.Order.Order;

import java.util.ArrayList;
import java.util.Random;

public class Restaurant {

    private final String restaurantID;
    private final String name;
    private final Location location;
    private final ArrayList<Item> menu;

    public Restaurant(String restaurantID, String name, ArrayList<Item> menu) {
        this.restaurantID = "R-" + restaurantID;
        this.name = name;
        Random random = new Random();
        double longitude = random.nextDouble() * 100;
        double latitude = random.nextDouble() * 100;
        this.location = new Location(longitude,latitude);
        this.menu = menu;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public boolean acceptOrder(Order order) {
        return true;
    }

    public void notifyOrder(Order order) {
    }
}
