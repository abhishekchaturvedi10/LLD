package Splitwise;

public class Transaction {
    private String transactionId;
    private UserAccount paidBy;
    private UserAccount paidTo;
    private double amount;

    public Transaction(String transactionId, UserAccount paidBy, UserAccount paidTo, double amount) {
        this.transactionId = transactionId;
        this.paidBy = paidBy;
        this.paidTo = paidTo;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public UserAccount getPaidBy() {
        return paidBy;
    }

    public UserAccount getPaidTo() {
        return paidTo;
    }

    public double getAmount() {
        return amount;
    }
}
