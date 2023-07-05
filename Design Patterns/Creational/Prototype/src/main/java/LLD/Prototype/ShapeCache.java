package LLD.Prototype;

import java.util.Hashtable;

public class ShapeCache {

    private static final Hashtable<String, Shape> shapeMap = new Hashtable<>();

    public static Shape getShape(String shapeType) {
        System.out.println("Cloning " + shapeType);
        Shape cachedShape = shapeMap.get(shapeType);
        return cachedShape.clone();
    }

    public static void loadCache() {
        Circle circle = new Circle();
        shapeMap.put(circle.getType(), circle);

        Rectangle rectangle = new Rectangle();
        shapeMap.put(rectangle.getType(), rectangle);
    }
}