package LLD.Strategy;

public class DefensiveBehaviour implements IBehaviour {
    public int moveCommand() {
        System.out.println("Defensive.....moving backward");
        return -1;
    }
}
