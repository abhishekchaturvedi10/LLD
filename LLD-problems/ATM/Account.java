package ATM;

public class Account {
    private String accountHolder;
    private String accountNumber;
    private Card atmCard;
    private double balance;

    public Account(String accountHolder, String accountNumber, Card atmCard, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.atmCard = atmCard;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void deductBalance(double amount) {
        balance -= amount;
    }

    public synchronized void addBalance(double amount) {
        balance += amount;
    }

    public Card getAtmCard() {
        return atmCard;
    }
}
