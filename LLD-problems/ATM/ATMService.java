package ATM;

import ATM.States.ATMState;
import ATM.States.CardInsertedState;
import ATM.States.IdleState;
import ATM.Transaction.Transaction;
import ATM.Transaction.DepositTransaction;
import ATM.Transaction.WithdrawTransaction;

public class ATMService {
    private ATMState atmState;
    private double cashInMachine;
    private final BankingService bankingService;

    public ATMService(BankingService bankingService, int cashInMachine) {
        this.atmState = new IdleState(this);
        this.cashInMachine = cashInMachine;
        this.bankingService = bankingService;
    }

    public void insertCard() {
        atmState.insertCard();
    }

    public void enterPin() {
        atmState.enterPin();
    }

    public void checkBalance(String pin, String accountNumber) {
        if (atmState instanceof IdleState) {
            System.out.println("Please insert card first");
            return;
        }
        if (atmState instanceof CardInsertedState) {
            System.out.println("Please enter pin first");
            return;
        }
        System.out.println("Balance: " + bankingService.checkBalance(pin, accountNumber));
    }

    public void withdrawCash(String pin, String accountNumber, double amount) {
        if (atmState instanceof IdleState) {
            System.out.println("Please insert card first");
            return;
        }
        if (atmState instanceof CardInsertedState) {
            System.out.println("Please enter pin first");
            return;
        }
        if (cashInMachine < amount) {
            System.out.println("Insufficient cash");
            return;
        }
        Account account = bankingService.getAccount(accountNumber);
        Transaction withdrawTransaction = new WithdrawTransaction(account, amount);
        bankingService.processTransaction(pin, withdrawTransaction);
        cashInMachine -= amount;
        atmState.dispenseCash();
        atmState.ejectCard();
    }

    public void depositCash(String pin, String accountNumber, double amount) {
        if (atmState instanceof IdleState) {
            System.out.println("Please insert card first");
            return;
        }
        if (atmState instanceof CardInsertedState) {
            System.out.println("Please enter pin first");
            return;
        }
        Account account = bankingService.getAccount(accountNumber);
        Transaction depositTransaction = new DepositTransaction(account, amount);
        bankingService.processTransaction(pin, depositTransaction);
        cashInMachine += amount;
        atmState.acceptCash();
        atmState.ejectCard();
    }

    public void setAtmState(ATMState atmState) {
        this.atmState = atmState;
    }
}
