package LLD.TemplateMethod;

public class WoodenHouse extends HouseBuilderTemplate {
    protected void buildFloor() {
        System.out.println("Building wooden floor...");
    }

    protected void buildWalls() {
        System.out.println("Building wooden walls...");
    }

    protected void buildCeiling() {
        System.out.println("Building wooden ceiling...");
    }
}
