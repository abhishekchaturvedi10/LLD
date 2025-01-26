package Zomato;

import Zomato.Order.Order;
import Zomato.Order.OrderStatus;
import Zomato.User.Customer;
import Zomato.User.DeliveryAgent;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ZomatoService {

    private static ZomatoService zomatoService;
    private final Map<String, Restaurant> restaurants;
    private final Map<String, Customer> customers;
    private final Map<String, DeliveryAgent> deliveryAgents;
    private final Map<String, Order> orders;

    public ZomatoService() {
        this.restaurants = new ConcurrentHashMap<>();
        this.customers = new ConcurrentHashMap<>();
        this.deliveryAgents = new ConcurrentHashMap<>();
        this.orders = new ConcurrentHashMap<>();
    }

    public static synchronized ZomatoService getInstance() {
        if(zomatoService == null) {
            zomatoService = new ZomatoService();
        }
        return zomatoService;
    }

    public void addRestaurant(Restaurant restaurant) {
        this.restaurants.put(restaurant.getRestaurantID(), restaurant);
    }

    public void addCustomer(Customer customer) {
        this.customers.put(customer.getCustomerID(), customer);
    }

    public void addDeliveryAgent(DeliveryAgent deliveryAgent) {
        this.deliveryAgents.put(deliveryAgent.getDeliveryAgentID(), deliveryAgent);
    }

    public Order fulfillOrder(Customer customer, Restaurant restaurant, ArrayList<Map<Item,Double>> orderItems) {
        Order order = createOrder(customer, restaurant, orderItems);
        notifyRestaurant(order);
        boolean restaurantConfirmation = restaurant.acceptOrder(order);
        if (restaurantConfirmation) {
            notifyCustomer(order);
            order.setStatus(OrderStatus.CONFIRMED);
        } else {
            notifyCustomer(order);
            order.setStatus(OrderStatus.CANCELLED);
            System.out.println("Order cancelled by restaurant");
            return order;
        }
        DeliveryAgent deliveryAgent = assignDeliveryAgent(order);
        if (deliveryAgent == null) {
            order.setStatus(OrderStatus.CANCELLED);
            System.out.println("Order cancelled due to unavailability of delivery agents");
            notifyCustomer(order);
            notifyRestaurant(order);
            return order;
        } else {
            order.setStatus(OrderStatus.ASSIGNED);
            System.out.println("Order assigned to " + deliveryAgent.getName() + " for delivery");
            notifyDeliveryAgent(order);
            notifyCustomer(order);
        }
        double orderPrice = calculateOrderPrice(order);
        System.out.println("Order price is Rs. " + orderPrice);
        order.setPrice(orderPrice);
        notifyCustomer(order);
        order.setStatus(OrderStatus.PREPARING);
        System.out.println("Order is being prepared");
        notifyCustomer(order);
        System.out.println("Delivery agent is on the way to restaurant");
        notifyCustomer(order);
        order.setStatus(OrderStatus.READY);
        System.out.println("Order is ready for delivery");
        notifyCustomer(order);
        order.setStatus(OrderStatus.OUT_FOR_DELIVERY);
        System.out.println("Order is out for delivery");
        notifyDeliveryAgent(order);
        boolean deliverOrder = deliveryAgent.deliverOrder(order);
        if (deliverOrder) {
            order.setStatus(OrderStatus.DELIVERED);
            System.out.println("Order delivered successfully");
            notifyCustomer(order);
        } else {
            order.setStatus(OrderStatus.CANCELLED);
            System.out.println("Order cancelled due to delivery issues");
            notifyCustomer(order);
        }
        deliveryAgent.updateAvailability(true);
        return order;
    }

    public Order createOrder(Customer customer, Restaurant restaurant, ArrayList<Map<Item,Double>> orderItems) {
        Order order = new Order("ORD-"+ UUID.randomUUID(), restaurant, customer);
        for(Map<Item,Double> orderItem : orderItems) {
            for (Map.Entry<Item, Double> entry : orderItem.entrySet()) {
                Item item = entry.getKey();
                Double quantity = entry.getValue();
                order.addItem(item, quantity);
            }
        }
        this.orders.put(order.getOrderID(), order);
        return order;
    }

    public DeliveryAgent assignDeliveryAgent(Order order) {
        for(DeliveryAgent deliveryAgent : deliveryAgents.values()) {
            if(deliveryAgent.acceptOrder()) {
                deliveryAgent.updateAvailability(false);
                order.setDeliveryAgent(deliveryAgent);
                return deliveryAgent;
            }
        }
        return null;
    }

    public void notifyCustomer(Order order) {
        Customer customer = order.getCustomer();
        customer.notifyOrder(order);
    }

    public void notifyRestaurant(Order order) {
        Restaurant restaurant = order.getRestaurant();
        restaurant.notifyOrder(order);
    }

    public void notifyDeliveryAgent(Order order) {
        DeliveryAgent deliveryAgent = order.getDeliveryAgent();
        deliveryAgent.notifyOrder(order);
    }

    public double calculateOrderPrice(Order order) {
        double price = 0;
        Map<Item, Double> orderItems = order.getItems();
        for (Map.Entry<Item, Double> item : orderItems.entrySet())  {
            price += item.getValue()*item.getKey().getPrice();
        }
        Location orderDeliveryLocation = order.getDeliveryLocation();
        Location restaurantLocation = order.getRestaurant().getLocation();
        Location deliveryAgentLocation = order.getDeliveryAgent().getCurrentLocation();
        price += (Math.abs(orderDeliveryLocation.getLatitude()-restaurantLocation.getLatitude()) + Math.abs(orderDeliveryLocation.getLongitude()-restaurantLocation.getLongitude()))*10;
        price += (Math.abs(restaurantLocation.getLatitude()-deliveryAgentLocation.getLatitude()) + Math.abs(restaurantLocation.getLongitude()-deliveryAgentLocation.getLongitude()))*5;
        return price;
    }
}