package LLD.Bridge;

public class RedCircle implements DrawAPI {
    public void drawCircle(int x, int y, int radius) {
        System.out.println("Drawing a red colored circle at " + x + ", " + y + " with radius " + radius);
    }
}
