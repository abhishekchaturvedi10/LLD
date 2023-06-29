package LLD.Factory;

public class ProductTwo extends Product {
    protected Product createProduct() {
        System.out.println("Creating ProductTwo....");
        ID++;
        return new ProductTwo();
    }
}
