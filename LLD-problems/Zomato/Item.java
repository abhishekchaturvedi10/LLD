package Zomato;

public class Item {

    private String itemID;
    private String name;
    private double price;
    private boolean isAvailable;

    public Item(String itemID, String name, double price, boolean isAvailable) {
        this.itemID = "I-" + itemID;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updatePrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void updateAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
