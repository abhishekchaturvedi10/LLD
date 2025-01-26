package Zomato;

import Zomato.Order.Order;
import Zomato.Order.OrderStatus;
import Zomato.User.Customer;
import Zomato.User.DeliveryAgent;

import java.util.ArrayList;
import java.util.Map;

public class ZomatoDemo {

    public static void main(String[] args) {

        ZomatoService zomatoService = ZomatoService.getInstance();

        // Register customers
        Customer customer1 = new Customer("C001", "John Doe", "john@example.com");
        Customer customer2 = new Customer("C002", "Jane Smith", "jane@example.com");

        zomatoService.addCustomer(customer1);
        zomatoService.addCustomer(customer2);

        // Register restaurants
        ArrayList<Item> restaurant1Menu = new ArrayList<>();
        restaurant1Menu.add(new Item("M001", "Burger", 10.2, true));
        restaurant1Menu.add(new Item("M002", "Pizza", 15.7, true));
        Restaurant restaurant1 = new Restaurant("R001", "Restaurant 1", restaurant1Menu);
        zomatoService.addRestaurant(restaurant1);

        ArrayList<Item> restaurant2Menu = new ArrayList<>();
        restaurant2Menu.add(new Item("M003", "Sushi", 15.99, true));
        restaurant2Menu.add(new Item("M004", "Ramen", 10.99, true));
        Restaurant restaurant2 = new Restaurant("R002", "Restaurant 2", restaurant2Menu);
        zomatoService.addRestaurant(restaurant2);

        // Register delivery agents
        DeliveryAgent agent1 = new DeliveryAgent("D001", "Agent 1", "9999999999");
        DeliveryAgent agent2 = new DeliveryAgent("D002", "Agent 2", "8888888888");
        zomatoService.addDeliveryAgent(agent1);
        zomatoService.addDeliveryAgent(agent2);

        // Place an order
        ArrayList<Map<Item,Double>> orderItems1 = new ArrayList<>();
        orderItems1.add(Map.of(restaurant1Menu.getFirst(), 2.0));
        orderItems1.add(Map.of(restaurant1Menu.get(1), 1.0));
        Order order1 = zomatoService.fulfillOrder(customer1, restaurant1, orderItems1);
        if (order1.getStatus() == OrderStatus.DELIVERED) {
            System.out.println("Order request fulfilled successfully");
        } else if (order1.getStatus() == OrderStatus.CANCELLED) {
            System.out.println("Order cancelled");
        } else {
            System.out.println("Order is in progress");
        }
    }
}
