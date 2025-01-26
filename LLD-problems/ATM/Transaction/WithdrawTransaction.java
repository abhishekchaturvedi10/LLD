package ATM.Transaction;

import ATM.Account;

public class WithdrawTransaction extends Transaction {
    public WithdrawTransaction(Account account, double amount) {
        super(account, amount);
    }

    public void execute() {
        account.deductBalance(amount);
    }
}
