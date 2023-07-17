package LLD.TemplateMethod;

public abstract class HouseBuilderTemplate {

    public void buildHouse() {
        buildFoundation();
        buildPillars();
        buildFloor();
        buildWalls();
        buildWindows();
        buildCeiling();
    }

    protected void buildFoundation() {
        System.out.println("Building foundation with concrete and sand...");
    }

    protected void buildPillars() {
        System.out.println("Building pillars with iron rods and cement...");
    }

    protected void buildWindows() {
        System.out.println("Building glass windows...");
    }

    protected abstract void buildFloor();

    protected abstract void buildWalls();

    protected abstract void buildCeiling();
}
