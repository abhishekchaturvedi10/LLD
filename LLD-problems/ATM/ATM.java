package ATM;

import java.util.List;

public class ATM {
    public static void main(String[] args) {
        BankingService bankingService = new BankingService();

        List<String> accountDetails1 = bankingService.createAccount("John Doe", 1000);
        List<String> accountDetails2 = bankingService.createAccount("Jane Doe", 2000);

        ATMService atmService = new ATMService(bankingService, 1000);

//        atmService.insertCard();
//        atmService.enterPin();

        atmService.checkBalance(accountDetails1.get(1), accountDetails1.get(0));
    }
}
