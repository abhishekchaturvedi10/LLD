package LLD.Factory;

public class ProductOne extends Product {
    protected Product createProduct() {
        System.out.println("Creating ProductOne....");
        ID++;
        return new ProductOne();
    }
}
