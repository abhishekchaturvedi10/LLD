package LLD.Factory;

public abstract class Product {

    protected static int ID = 0;

    public int getID() {
        return ID;
    }

    protected abstract Product createProduct();
}
