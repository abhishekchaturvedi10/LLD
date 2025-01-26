package Splitwise.SplitStrategy;

import Splitwise.UserAccount;

import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy {
    @Override
    public Map<UserAccount, Double> splitExpense(double amount, List<UserAccount> participants) {
        Map<UserAccount,Double> sharePerParticipant = new java.util.HashMap<>();
        double amountPerHead = amount / participants.size();
        for(UserAccount participant : participants) {
            sharePerParticipant.put(participant, amountPerHead);
        }
        return sharePerParticipant;
    }
}
