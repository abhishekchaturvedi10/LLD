package ATM.Transaction;

import ATM.Account;

public class DepositTransaction extends Transaction {
    public DepositTransaction(Account account, double amount) {
        super(account, amount);
    }

    public void execute() {
        account.addBalance(amount);
    }
}
