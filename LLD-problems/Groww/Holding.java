package Groww;

public class Holding {
    private final Stock stock;
    private int quantity;
    private double averagePrice;

    public Holding(Stock stock, int quantity, double averagePrice) {
        this.stock = stock;
        this.quantity = quantity;
        this.averagePrice = averagePrice;
    }

    @Override
    public String toString() {
        return "Stock: " + stock.getStockId() + ", Quantity: " + quantity + ", Average Price: " + averagePrice;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }
}
