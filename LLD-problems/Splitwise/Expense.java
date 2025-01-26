package Splitwise;

import Splitwise.SplitStrategy.SplitStrategy;

import java.util.List;
import java.util.Map;

public class Expense {
    private String expenseId;
    private String description;
    private double amount;
    private UserAccount paidBy;
    private List<UserAccount> paidFor;
    private SplitStrategy splitStrategy;

    public Expense(String expenseId, String description, double amount, UserAccount paidBy, List<UserAccount> paidFor, SplitStrategy splitStrategy) {
        this.expenseId = expenseId;
        this.description = description;
        this.amount = amount;
        this.paidBy = paidBy;
        this.paidFor = paidFor;
        this.splitStrategy = splitStrategy;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public UserAccount getPaidBy() {
        return paidBy;
    }

    public List<UserAccount> getPaidFor() {
        return paidFor;
    }

    public Map<UserAccount, Double> getParticipantShare() {
        return splitStrategy.splitExpense(this.amount, this.paidFor);
    }
}
