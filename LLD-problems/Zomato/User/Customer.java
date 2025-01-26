package Zomato.User;

import Zomato.Order.Order;

public class Customer extends User {

    private final String customerID;

    public Customer(String customerID, String name, String phone) {
        super(name, phone);
        this.customerID = "C-" + customerID;
    }

    public String getCustomerID() {
        return customerID;
    }

    @Override
    public void notifyOrder(Order order) {
    }
}
