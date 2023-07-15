package LLD.Bridge;

public class BridgeApplication {

    public static void main(String[] args) {

        Shape redCircle = new Circle(new RedCircle(), 5, 8, 4);
        Shape blueCircle = new Circle(new BlueCircle(), 5, 8, 4);

        redCircle.draw();
        blueCircle.draw();
    }
}
