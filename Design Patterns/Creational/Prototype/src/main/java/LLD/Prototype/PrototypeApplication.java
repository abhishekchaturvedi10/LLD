package LLD.Prototype;

public class PrototypeApplication {

    public static void main(String[] args) {
        ShapeCache.loadCache();

        System.out.println("Creating a Rectangle");
        Shape clonedRectangle = ShapeCache.getShape("Rectangle");
        System.out.println("Cloned Shape: " + clonedRectangle.getType());

        System.out.println("\nCreating a Circle");
        Shape clonedCircle = ShapeCache.getShape("Circle");
        System.out.println("Cloned Shape: " + clonedCircle.getType());
    }
}
