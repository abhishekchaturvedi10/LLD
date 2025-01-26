package Splitwise.SplitStrategy;

import Splitwise.UserAccount;

import java.util.List;
import java.util.Map;

public class ExactAmountSplitStrategy implements SplitStrategy {
    private final List<Double> exactAmounts;

    public ExactAmountSplitStrategy(List<Double> exactAmounts) {
        this.exactAmounts = exactAmounts;
    }

    @Override
    public Map<UserAccount, Double> splitExpense(double amount, List<UserAccount> participants) {
        Map<UserAccount,Double> sharePerParticipant = new java.util.HashMap<>();
        for(int i = 0; i < participants.size(); i++) {
            sharePerParticipant.put(participants.get(i), exactAmounts.get(i));
        }
        return sharePerParticipant;
    }
}
