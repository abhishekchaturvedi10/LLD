package Groww;

import java.util.ArrayList;
import java.util.List;

public class Stock {
    private String stockId;
    private String companyName;
    private String symbol;
    private double price;
    private List<Account> observers;

    public Stock(String stockId, String companyName, String symbol, double price) {
        this.stockId = stockId;
        this.companyName = companyName;
        this.symbol = symbol;
        this.price = price;
        this.observers = new ArrayList<>();
    }

    public String getStockId() {
        return stockId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    public void addObserver(Account account) {
        observers.add(account);
    }

    public void removeObserver(Account account) {
        observers.remove(account);
    }

    public String getSymbol() {
        return symbol;
    }

    public void notifyObservers() {
        for(Account account : observers) {
            account.update(this);
        }
    }
}
