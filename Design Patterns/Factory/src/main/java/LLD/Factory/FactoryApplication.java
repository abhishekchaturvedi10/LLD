package LLD.Factory;

import static LLD.Factory.ProductFactory.getProductFactory;

public class FactoryApplication {
    static {
        try {
            Class.forName("LLD.Factory.ProductOne");
            Class.forName("LLD.Factory.ProductTwo");
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot load class");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getProductFactory().registerProduct("P1", ProductOne.class);
        getProductFactory().registerProduct("P2", ProductTwo.class);
        getProductFactory().registerProduct("P3", ProductThree.class);
        try {
            getProductFactory().createProduct("P1");
            getProductFactory().createProduct("P2");
            getProductFactory().createProduct("P3");
            getProductFactory().createProduct("P2");
            getProductFactory().createProduct("P3");
            getProductFactory().createProduct("P1");
        } catch (Exception e) {
            System.out.println("Factory cannot create product");
            e.printStackTrace();
        }
    }
}
