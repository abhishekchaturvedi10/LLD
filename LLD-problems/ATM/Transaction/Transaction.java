package ATM.Transaction;

import ATM.Account;

public abstract class Transaction {
    protected final Account account;
    protected final double amount;

    public Transaction(Account account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public abstract void execute();
}
