package LLD.Strategy;

public class NormalBehaviour implements IBehaviour {
    public int moveCommand() {
        System.out.println("Normal.....not moving");
        return 0;
    }
}
