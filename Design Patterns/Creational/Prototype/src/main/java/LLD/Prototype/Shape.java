package LLD.Prototype;

public abstract class Shape implements Cloneable {
    protected String type;

    public String getType() {
        return type;
    }

    public abstract void draw();

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
