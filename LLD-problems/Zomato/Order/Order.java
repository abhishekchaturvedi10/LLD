package Zomato.Order;

import Zomato.Item;
import Zomato.Location;
import Zomato.Restaurant;
import Zomato.User.Customer;
import Zomato.User.DeliveryAgent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Order {

    private final String orderID;
    private final Restaurant restaurant;
    private final Map<Item, Double> items;
    private double price;
    private OrderStatus status;
    private final Customer customer;
    private final Location deliveryLocation;
    private DeliveryAgent deliveryAgent;

    public Order(String orderID, Restaurant restaurant, Customer customer) {
        this.orderID = orderID;
        this.restaurant = restaurant;
        this.customer = customer;
        this.items = new ConcurrentHashMap<>();
        this.price = 0;
        this.status = OrderStatus.PENDING;
        this.deliveryLocation = customer.getCurrentLocation();
    }

    public String getOrderID() {
        return orderID;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Map<Item,Double> getItems() {
        return items;
    }

    public void addItem(Item item, double quantity) {
        items.put(item, quantity);
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Location getDeliveryLocation() {
        return deliveryLocation;
    }

    public DeliveryAgent getDeliveryAgent() {
        return deliveryAgent;
    }

    public void setDeliveryAgent(DeliveryAgent deliveryAgent) {
        this.deliveryAgent = deliveryAgent;
    }
}
