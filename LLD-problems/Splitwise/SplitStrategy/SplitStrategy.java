package Splitwise.SplitStrategy;

import Splitwise.UserAccount;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    Map<UserAccount,Double> splitExpense(double amount, List<UserAccount> participants);
}
