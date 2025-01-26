package ATM.States;

import ATM.ATMService;

public class CardInsertedState extends ATMState {
    private final ATMService atmService;

    public CardInsertedState(ATMService atmService) {
        this.atmService = atmService;
    }

    @Override
    public void insertCard() {
        System.out.println("Card already inserted");
        atmService.setAtmState(this);
    }

    @Override
    public void enterPin() {
        System.out.println("Pin entered");
        atmService.setAtmState(new PinEnteredState(atmService));
    }

    @Override
    public void dispenseCash() {
        System.out.println("Please enter pin first");
        atmService.setAtmState(this);
    }

    @Override
    public void acceptCash() {
        System.out.println("Please enter pin first");
        atmService.setAtmState(this);
    }

    @Override
    public void ejectCard() {
        System.out.println("Card ejected");
        atmService.setAtmState(new IdleState(atmService));
    }
}
