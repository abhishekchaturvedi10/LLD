package Groww;

import Groww.Order.Order;
import Groww.Order.OrderType;

public class Groww {
    public static void main(String[] args) throws InterruptedException {
        StockBrokerageService service = StockBrokerageService.getInstance();
        User user1 = new User("John Doe", "john_doe", "abc@gmail.com", "1234567890");
        User user2 = new User("Jane Doe", "jane_doe", "xyz@gmail.com", "0987654321");
        Account account1 = new Account(user1, 10000);
        Account account2 = new Account(user2, 20000);
        service.addAccount(account1);
        service.addAccount(account2);
        Stock stock1 = new Stock("1", "Apple Inc.", "AAPL", 100.0);
        Stock stock2 = new Stock("2", "Microsoft Corporation", "MSFT", 200.0);
        service.addStock(stock1, 2000);
        service.addStock(stock2, 1022);
        Order order1 = new Order("1", stock1, 99.0, 10, OrderType.BUY, account1);
        Order order2 = new Order("2", stock2, 200.0, 5, OrderType.BUY, account2);
        Order order3 = new Order("3", stock1, 105.0, 20, OrderType.BUY, account1);
        Order order4 = new Order("4", stock2, 210.0, 10, OrderType.BUY, account2);
        Order order5 = new Order("5", stock1, 99.0, 10, OrderType.SELL, account1);
        Order order6 = new Order("6", stock2, 200.0, 5, OrderType.SELL, account2);
        service.startService();
        service.placeOrder(order1);
        service.placeOrder(order2);
        Thread.sleep(1000);
        service.placeOrder(order3);
        service.placeOrder(order4);
        Thread.sleep(1000);
        service.placeOrder(order5);
        service.placeOrder(order6);
        Thread.sleep(1000);
        Order order7 = new Order("7", stock2, 202, 20, OrderType.BUY, account1);
        service.placeOrder(order7);
        service.addStockToWatchlist(account1, stock1);
        stock1.setPrice(110.0);
        for (Order order: account1.getOrders()) {
            System.out.println(order.toString());
        }
        for (Holding holding: account1.getPortfolio()) {
            System.out.println(holding.toString());
        }

    }
}
