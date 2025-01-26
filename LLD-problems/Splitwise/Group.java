package Splitwise;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupId;
    private String name;
    private List<UserAccount> members;
    private List<Expense> expenses;
    private List<Transaction> transactions;
    private SettlementStatus settlementStatus;

    public Group(String groupId, String name) {
        this.groupId = groupId;
        this.name = name;
        members = new ArrayList<>();
        expenses = new ArrayList<>();
        transactions = new ArrayList<>();
        settlementStatus = SettlementStatus.SETTLED;
    }

    public String getGroupId() {
        return groupId;
    }

    public void addMember(UserAccount user) {
        members.add(user);
        user.addGroup(this);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        updateSettlementStatus();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        updateSettlementStatus();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void listExpenses() {
        System.out.println("Expenses for group: " + name);
        for (Expense expense : expenses) {
            System.out.println("******** EXPENSE - " + expense.getExpenseId() + " ********");
            System.out.println(expense.getDescription());
            System.out.println(expense.getAmount());
            System.out.println(expense.getPaidBy().getName());
            for(UserAccount user : expense.getPaidFor()) {
                System.out.print(user.getName() + " ");
            }
            System.out.println("\n-----------------------------------------------------------");
        }
    }

    public void listTransactions() {
        System.out.println("Transactions for group: " + name);
        for (Transaction transaction : transactions) {
            System.out.println("******** TRANSACTION - " + transaction.getTransactionId() + " ********");
            System.out.println(transaction.getTransactionId());
            System.out.println(transaction.getAmount());
            System.out.println(transaction.getPaidBy().getName());
            System.out.println(transaction.getPaidTo().getName());
            System.out.println("-----------------------------------------------------------");
        }
    }

    private void updateSettlementStatus() {
        for (UserAccount user : members) {
            if (!user.getBalance().isEmpty()) {
                settlementStatus = SettlementStatus.UNSETTLED;
                return;
            }
        }
        settlementStatus = SettlementStatus.SETTLED;
    }

    public SettlementStatus getSettlementStatus() {
        return settlementStatus;
    }
}
