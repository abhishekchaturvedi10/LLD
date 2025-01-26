package ATM.States;

import ATM.ATMService;

public class PinEnteredState extends ATMState {
    private final ATMService atmService;

    public PinEnteredState(ATMService atmService) {
        this.atmService = atmService;
    }

    @Override
    public void insertCard() {
        System.out.println("Card already inserted");
        atmService.setAtmState(this);
    }

    @Override
    public void enterPin() {
        System.out.println("Pin already entered");
        atmService.setAtmState(this);
    }

    @Override
    public void dispenseCash() {
        System.out.println("Cash dispensed");
        atmService.setAtmState(this);
    }

    @Override
    public void acceptCash() {
        System.out.println("Cash accepted");
        atmService.setAtmState(this);
    }

    @Override
    public void ejectCard() {
        System.out.println("Card ejected");
        atmService.setAtmState(new IdleState(atmService));
    }
}
