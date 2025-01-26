package Groww.Order;

import Groww.Account;
import Groww.Stock;

public class Order {
    private String orderId;
    private Groww.Order.OrderType orderType;
    private Stock stock;
    private double price;
    private int quantity;
    private Account account;
    private Groww.Order.OrderStatus status;

    public Order(String orderId, Stock stock, double price, int quantity, Groww.Order.OrderType orderType, Account account) {
        this.orderId = orderId;
        this.stock = stock;
        this.price = price;
        this.quantity = quantity;
        this.orderType = orderType;
        this.status = Groww.Order.OrderStatus.PENDING;
        this.account = account;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Order Type: " + orderType + ", Stock: " + stock.getStockId() + ", Price: " + price + ", Quantity: " + quantity + ", Status: " + status;
    }

    public String getOrderId() {
        return orderId;
    }

    public Groww.Order.OrderType getOrderType() {
        return orderType;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Groww.Order.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(Groww.Order.OrderStatus orderStatus) {
        this.status = orderStatus;
    }

    public Stock getStock() {
        return stock;
    }

    public Account getAccount() {
        return account;
    }
}
