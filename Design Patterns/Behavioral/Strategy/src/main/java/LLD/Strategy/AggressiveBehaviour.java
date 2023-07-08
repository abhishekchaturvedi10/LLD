package LLD.Strategy;

public class AggressiveBehaviour implements IBehaviour {
    public int moveCommand() {
        System.out.println("Aggressive.....moving forward");
        return 1;
    }
}
