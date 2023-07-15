package LLD.Bridge;

public class BlueCircle implements DrawAPI {
    public void drawCircle(int x, int y, int radius) {
        System.out.println("Drawing a blue colored circle at " + x + ", " + y + " with radius " + radius);
    }
}
