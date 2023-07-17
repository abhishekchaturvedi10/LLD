package LLD.TemplateMethod;

public class BrickHouse extends HouseBuilderTemplate {
    protected void buildFloor() {
        System.out.println("Building brick floor...");
    }

    protected void buildWalls() {
        System.out.println("Building brick walls...");
    }

    protected void buildCeiling() {
        System.out.println("Building brick ceiling...");
    }
}
