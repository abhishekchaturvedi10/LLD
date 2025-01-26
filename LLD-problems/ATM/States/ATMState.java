package ATM.States;

public abstract class ATMState {
    public abstract void insertCard();
    public abstract void enterPin();
    public abstract void dispenseCash();
    public abstract void acceptCash();
    public abstract void ejectCard();
}
