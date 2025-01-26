package Splitwise;

import java.util.*;

public class UserAccount {
    private String userId;
    private String name;
    private String email;
    private String phone;
    private List<Group> groups;

    public UserAccount(String userId, String name, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        groups = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone() {
        this.phone = phone;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public Map<UserAccount, Double> getBalance() {
        Map<UserAccount, Double> balance = new HashMap<>();
        for(Group group : groups) {
            for(Expense expense : group.getExpenses()) {
                Map<UserAccount,Double> participantShare = expense.getParticipantShare();
                if(expense.getPaidBy().equals(this)) {
                    for(Map.Entry<UserAccount, Double> entry : participantShare.entrySet()) {
                        if (entry.getKey().equals(this)) {
                            continue;
                        }
                        if(balance.containsKey(entry.getKey())) {
                            balance.put(entry.getKey(), balance.get(entry.getKey()) + entry.getValue());
                        } else {
                            balance.put(entry.getKey(), entry.getValue());
                        }
                    }
                } else if (expense.getPaidFor().contains(this)) {
                    balance.put(expense.getPaidBy(), balance.getOrDefault(expense.getPaidBy(), 0.0) - participantShare.get(this));
                }
            }
            for (Transaction transaction : group.getTransactions()) {
                if(transaction.getPaidBy().equals(this)) {
                    if(balance.containsKey(transaction.getPaidTo())) {
                        balance.put(transaction.getPaidTo(), balance.get(transaction.getPaidTo()) + transaction.getAmount());
                    } else {
                        balance.put(transaction.getPaidTo(), transaction.getAmount());
                    }
                } else if(transaction.getPaidTo().equals(this)) {
                    if(balance.containsKey(transaction.getPaidBy())) {
                        balance.put(transaction.getPaidBy(), balance.get(transaction.getPaidBy()) - transaction.getAmount());
                    } else {
                        balance.put(transaction.getPaidBy(), -transaction.getAmount());
                    }
                }
            }
        }
        balance.entrySet().removeIf(entry -> entry.getValue() == 0.0);
        return balance;
    }

    public void printBalance() {
        System.out.println("Balance for user: " + name);
        Map<UserAccount, Double> balance = getBalance();
        for(Map.Entry<UserAccount, Double> entry : balance.entrySet()) {
            System.out.println(entry.getKey().getName() + " " + entry.getValue());
        }
        System.out.println();
    }

    public List<Transaction> getTransactionHistory() {
        List<Transaction> transactions = new java.util.ArrayList<>();
        for(Group group : groups) {
            List<Transaction> groupTransactions = group.getTransactions();
            for(Transaction transaction : groupTransactions) {
                if(transaction.getPaidBy().equals(this) || transaction.getPaidTo().equals(this)) {
                    transactions.add(transaction);
                }
            }
        }
        return transactions;
    }

    public void printTransactionHistory() {
        System.out.println("Transaction history for user: " + name);
        List<Transaction> transactions = getTransactionHistory();
        for(Transaction transaction : transactions) {
            System.out.println(transaction.getTransactionId() + " " + transaction.getPaidBy().getName() + " " + transaction.getPaidTo().getName() + " " + transaction.getAmount());
        }
        System.out.println();
    }

    public void settleBalance(Group group, UserAccount user, double amount) {
        Transaction transaction = new Transaction("TR-"+ UUID.randomUUID(), this, user, amount);
        group.addTransaction(transaction);
    }
}
