package Groww;

import Groww.Order.Order;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Account {
    private String accountId;
    private final User user;
    private List<Order> orders;
    private List<Holding> portfolio;
    private double balance;
    private List<Stock> watchlist;

    public Account(User user, double balance) {
        this.user = user;
        this.balance = balance;
        this.orders = new CopyOnWriteArrayList<>();
        this.portfolio = new CopyOnWriteArrayList<>();
        this.watchlist = new CopyOnWriteArrayList<>();
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void addBalance(double amount) {
        balance += amount;
        System.out.println("Balance added to account " + accountId + " is " + balance);
    }

    public synchronized void deductBalance(double amount) {
        balance -= amount;
        System.out.println("Balance deducted from account " + accountId + " is " + balance);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public synchronized void addOrder(Order order) {
        orders.add(order);
    }

    public synchronized void addStockInPortfolio(Stock stock, int quantity, double price) {
        for(Holding h : portfolio) {
            if(h.getStock().getStockId().equals(stock.getStockId())) {
                h.setAveragePrice((h.getAveragePrice()*h.getQuantity() + price*quantity) / (h.getQuantity() + quantity));
                h.setQuantity(h.getQuantity() + quantity);
                return;
            }
        }
        Holding holding = new Holding(stock, quantity, price);
        portfolio.add(holding);
    }

    public synchronized void removeStockFromPortfolio(Stock stock, int quantity, double price) {
        for(Holding h : portfolio) {
            if(h.getStock().getStockId().equals(stock.getStockId())) {
                h.setAveragePrice((h.getAveragePrice()*h.getQuantity() - price*quantity) / (h.getQuantity() - quantity));
                h.setQuantity(h.getQuantity() - quantity);
                return;
            }
        }
    }

    public void addStockToWatchlist(Stock stock) {
        watchlist.add(stock);
    }

    public void removeStockFromWatchlist(Stock stock) {
        watchlist.remove(stock);
    }

    public List<Holding> getPortfolio() {
        return portfolio;
    }

    public void update(Stock stock) {
        for(Stock watchlistStock : watchlist) {
            if(watchlistStock.getStockId().equals(stock.getStockId())) {
                System.out.println("Price of stock " + stock.getSymbol() + " has changed to " + stock.getPrice());
            }
        }
    }
}
