package Groww;

import Groww.Exception.InsufficientFundsException;
import Groww.Exception.InsufficientStockQuantityException;
import Groww.Exception.StockNotPresentInPortfolioException;
import Groww.Order.Order;
import Groww.Order.OrderStatus;
import Groww.Order.OrderType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class StockBrokerageService {
    private static StockBrokerageService instance;
    private List<Account> accounts;
    private Map<Stock,Integer> stocks;
    private Queue<Order> orderQueue;
    private ArrayList<Thread> threads;

    public StockBrokerageService() {
        this.accounts = new ArrayList<>();
        this.stocks = new ConcurrentHashMap<>();
        this.orderQueue = new ConcurrentLinkedQueue<>();
        this.threads = new ArrayList<>();
    }

    public static StockBrokerageService getInstance() {
        if (instance == null) {
            instance = new StockBrokerageService();
        }
        return instance;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void addStock(Stock stock, int quantity) {
        stocks.put(stock, quantity);
    }

    public void placeOrder(Order order) {
        System.out.println(Thread.currentThread().getName() + " is placing order: " + order.getOrderId());
        orderQueue.add(order);
    }

    public void executeOrder(Order order) {
        System.out.println(Thread.currentThread().getName() + " is executing order: " + order.getOrderId());
        Account account = order.getAccount();
        account.addOrder(order);
        if (order.getOrderType().equals(OrderType.BUY)) {
            double amount = order.getPrice() * order.getQuantity();
            if(amount > account.getBalance()) {
                order.setStatus(OrderStatus.REJECTED);
                throw new InsufficientFundsException("Insufficient funds");
            } else {
                if(!stocks.containsKey(order.getStock()) || stocks.get(order.getStock()) < order.getQuantity()) {
                    order.setStatus(OrderStatus.REJECTED);
                    throw new InsufficientStockQuantityException("Insufficient stock in market");
                }
                stocks.put(order.getStock(), stocks.get(order.getStock()) - order.getQuantity());
                account.deductBalance(amount);
                account.addStockInPortfolio(order.getStock(), order.getQuantity(), order.getPrice());
                stocks.put(order.getStock(), stocks.get(order.getStock()) - order.getQuantity());
                order.setStatus(OrderStatus.COMPLETED);
            }
        } else {
            List<Holding> portfolio = account.getPortfolio();
            boolean stockPresentInPortfolio = false;
            for(Holding h : portfolio) {
                if(h.getStock().getStockId().equals(order.getStock().getStockId())) {
                    stockPresentInPortfolio = true;
                    if(h.getQuantity() < order.getQuantity()) {
                        order.setStatus(OrderStatus.REJECTED);
                        throw new InsufficientStockQuantityException("Insufficient stock in portfolio");
                    } else {
                        double amount = order.getPrice() * order.getQuantity();
                        account.addBalance(amount);
                        account.removeStockFromPortfolio(order.getStock(), order.getQuantity(), order.getPrice());
                        stocks.put(order.getStock(), stocks.get(order.getStock()) + order.getQuantity());
                        order.setStatus(OrderStatus.COMPLETED);
                    }
                }
            }
            if(!stockPresentInPortfolio) {
                order.setStatus(OrderStatus.REJECTED);
                throw new StockNotPresentInPortfolioException("Stock not present in portfolio so you cannot sell it");
            }
        }
        System.out.println(Thread.currentThread().getName() + " finished order " + order.getOrderId() + " with status: " + order.getStatus());
    }

    private void pollOrders() {
        System.out.println(Thread.currentThread().getName() + " polling orders");
        while(true) {
            if (!orderQueue.isEmpty()) {
                Order order = orderQueue.poll();
                Thread t = new Thread(() -> executeOrder(order));
                threads.add(t);
                t.start();
            }
        }
    }

    public void startService() {
        System.out.println(Thread.currentThread().getName() + " service started");
        Thread t = new Thread(this::pollOrders);
        threads.add(t);
        t.start();
    }

    public void addStockToWatchlist(Account account, Stock stock) {
        account.addStockToWatchlist(stock);
        stock.addObserver(account);
    }
}
