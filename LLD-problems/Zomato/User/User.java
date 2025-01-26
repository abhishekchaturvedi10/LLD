package Zomato.User;

import Zomato.Location;
import Zomato.Order.Order;

import java.util.Random;

public abstract class User {

    private String name;
    private String phone;
    protected Location location;

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public Location getCurrentLocation() {
        Random random = new Random();
        double longitude = random.nextDouble() * 100;
        double latitude = random.nextDouble() * 100;
        location = new Location(longitude, latitude);
        return location;
    }

    public abstract void notifyOrder(Order order);
}
