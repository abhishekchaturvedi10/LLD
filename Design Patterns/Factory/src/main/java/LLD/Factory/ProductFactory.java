package LLD.Factory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class ProductFactory {
    private static final ProductFactory productFactory = new ProductFactory();
    private final HashMap<String, Class<?>> registeredProducts = new HashMap<>();
    private final HashMap<Integer, Product> createdProducts = new HashMap<>();

    public static ProductFactory getProductFactory() {
        return productFactory;
    }

    public void registerProduct(String productCode, Class<?> productClass) {
        registeredProducts.put(productCode, productClass);
        System.out.println(registeredProducts.get(productCode).getSimpleName() + " registered in factory against the name: " + productCode);
    }

    public void createProduct(String productCode) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> productClass = registeredProducts.get(productCode);
        Product product = ((Product) productClass.getConstructor().newInstance()).createProduct();
        createdProducts.put(product.getID(), product);
        System.out.println(createdProducts.get(product.getID()).getClass().getSimpleName() + " created by factory with ID: " + product.getID());
    }
}
