package LLD.Factory;

public class ProductThree extends Product {
    protected Product createProduct() {
        System.out.println("Creating ProductThree....");
        ID++;
        return new ProductThree();
    }
}
