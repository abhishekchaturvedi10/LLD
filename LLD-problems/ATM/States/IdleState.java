package ATM.States;

import ATM.ATMService;

public class IdleState extends ATMState {
    private final ATMService atmService;

    public IdleState(ATMService atmService) {
        this.atmService = atmService;
    }

    @Override
    public void insertCard() {
        System.out.println("Card Inserted");
        atmService.setAtmState(new CardInsertedState(atmService));
    }

    @Override
    public void enterPin() {
        System.out.println("Please insert card first");
        atmService.setAtmState(this);
    }

    @Override
    public void dispenseCash() {
        System.out.println("Please insert card first");
        atmService.setAtmState(this);
    }

    @Override
    public void acceptCash() {
        System.out.println("Please insert card first");
        atmService.setAtmState(this);
    }

    @Override
    public void ejectCard() {
        System.out.println("Card ejected");
        atmService.setAtmState(this);
    }
}
