package Zomato.User;

import Zomato.Order.Order;

public class DeliveryAgent extends User {

    private final String deliveryAgentID;
    private boolean isAvailable;

    public DeliveryAgent(String agentID, String name, String phone) {
        super(name, phone);
        this.deliveryAgentID = "DA-" + agentID;
        this.isAvailable = true;
    }

    public String getDeliveryAgentID() {
        return deliveryAgentID;
    }

    public boolean acceptOrder() {
        return isAvailable;
    }

    public void updateAvailability(boolean availability) {
        this.isAvailable = availability;
    }

    public boolean deliverOrder(Order order) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }

    public void notifyOrder(Order order) {
    }
}
