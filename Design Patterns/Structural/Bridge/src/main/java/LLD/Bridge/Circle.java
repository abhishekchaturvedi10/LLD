package LLD.Bridge;

public class Circle extends Shape {

    int radius;

    Circle(DrawAPI drawAPI, int x, int y, int radius) {
        super(drawAPI, x, y);
        this.radius = radius;
    }

    void draw() {
        drawAPI.drawCircle(x, y, radius);
    }
}
