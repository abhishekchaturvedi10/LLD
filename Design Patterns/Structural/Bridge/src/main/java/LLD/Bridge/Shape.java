package LLD.Bridge;

public abstract class Shape {

    protected DrawAPI drawAPI;
    int x;
    int y;

    Shape(DrawAPI drawAPI, int x, int y) {
        this.drawAPI = drawAPI;
        this.x = x;
        this.y = y;
    }

    abstract void draw();
}
