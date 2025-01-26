package Splitwise.SplitStrategy;

import Splitwise.UserAccount;

import java.util.List;
import java.util.Map;

public class PercentageSplitStrategy implements SplitStrategy {
    private final List<Double> percentages;

    public PercentageSplitStrategy(List<Double> percentages) {
        this.percentages = percentages;
    }

    @Override
    public Map<UserAccount, Double> splitExpense(double amount, List<UserAccount> participants) {
        Map<UserAccount,Double> sharePerParticipant = new java.util.HashMap<>();
        for(int i = 0; i < participants.size(); i++) {
            sharePerParticipant.put(participants.get(i), amount * percentages.get(i) / 100);
        }
        return sharePerParticipant;
    }
}
