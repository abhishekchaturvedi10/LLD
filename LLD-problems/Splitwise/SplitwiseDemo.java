package Splitwise;

import Splitwise.SplitStrategy.EqualSplitStrategy;
import Splitwise.SplitStrategy.ExactAmountSplitStrategy;
import Splitwise.SplitStrategy.PercentageSplitStrategy;

import java.util.List;
import java.util.UUID;

public class SplitwiseDemo {
    public static void main(String[] args) {
        UserAccount user1 = new UserAccount("u1", "user1", "abc@def.com", "1234567890");
        UserAccount user2 = new UserAccount("u2", "user2", "uvw@xyz.com", "0987654321");
        UserAccount user3 = new UserAccount("u3", "user3", "hij@klm.com", "6789054321");

        Group group = new Group("G-"+ UUID.randomUUID(), "Trip");

        group.addMember(user1);
        group.addMember(user2);
        group.addMember(user3);

        Expense expense1 = new Expense("E-"+UUID.randomUUID(), "Hotel", 3000, user1, List.of(user1, user2, user3), new EqualSplitStrategy());
        Expense expense2 = new Expense("E-"+UUID.randomUUID(), "Food", 1000, user2, List.of(user1, user2, user3), new PercentageSplitStrategy(List.of(30.0, 35.0, 45.0)));
        Expense expense3 = new Expense("E-"+UUID.randomUUID(), "Travel", 2000, user3, List.of(user1, user2, user3), new ExactAmountSplitStrategy(List.of(500.0, 700.0, 800.0)));

        group.addExpense(expense1);
        group.addExpense(expense2);
        group.addExpense(expense3);

        group.listExpenses();
        group.listTransactions();

        user1.printBalance();
        user2.printBalance();
        user3.printBalance();

        user1.printTransactionHistory();
        user2.printTransactionHistory();
        user3.printTransactionHistory();

        Transaction transaction = new Transaction("TR-"+ UUID.randomUUID(), user2, user1, 1000);

        group.addTransaction(transaction);
        group.listTransactions();

        user1.printBalance();
        user2.printBalance();
        user3.printBalance();

        user1.printTransactionHistory();
        user2.printTransactionHistory();
        user3.printTransactionHistory();

        user3.settleBalance(group, user1, 500);

        user1.printBalance();
        user2.printBalance();
        user3.printBalance();

        user1.printTransactionHistory();
        user2.printTransactionHistory();
        user3.printTransactionHistory();

        System.out.println(group.getSettlementStatus() + "\n");

        user3.settleBalance(group, user1, 500);

        user1.printBalance();
        user2.printBalance();
        user3.printBalance();

        user1.printTransactionHistory();
        user2.printTransactionHistory();
        user3.printTransactionHistory();

        System.out.println(group.getSettlementStatus() + "\n");
    }
}
