package ATM;

import ATM.Transaction.Transaction;
import ATM.Transaction.WithdrawTransaction;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class BankingService {
    private Map<String, Account> accounts;

    public BankingService() {
        accounts = new ConcurrentHashMap<>();
    }

    public List<String> createAccount(String accountHolder, double balance) {
        String accountNumber = generateAcctNumber();
        String cardNumber = generateCardNumber();
        String pin = generatePin();
        accounts.put(accountNumber, new Account(accountHolder, accountNumber, new Card(cardNumber,pin), balance));
        return List.of(accountNumber,pin);
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public double checkBalance(String pin, String accountNumber) {
        Account account = getAccount(accountNumber);
        if (!account.getAtmCard().getPin().equals(pin)) {
            System.out.println("Invalid pin");
            return Integer.MIN_VALUE;
        }
        return account.getBalance();
    }

    public void processTransaction(String pin, Transaction transaction) {
        if (!transaction.getAccount().getAtmCard().getPin().equals(pin)) {
            System.out.println("Invalid pin");
            return;
        }
        if (transaction instanceof WithdrawTransaction) {
            if (transaction.getAccount().getBalance() < transaction.getAmount()) {
                System.out.println("Insufficient balance");
                return;
            }
        }
        transaction.execute();
    }

    private String generateAcctNumber() {
        return "ACCT-"+ UUID.randomUUID();
    }

    private String generateCardNumber() {
        return "CARD-"+ UUID.randomUUID();
    }

    private String generatePin() {
        return 1000 + new Random().nextInt(9000) + "";
    }
}
