package LLD.AbstractFactory;

import static LLD.AbstractFactory.VehicleType.BIKE;
import static LLD.AbstractFactory.VehicleType.CAR;


public class AbstractFactoryApplication {

    public static void main(String[] args) {

        VehicleFactory<?> factory = FactoryCreator.createFactory(CAR);
        if (factory == null) {
            System.out.println("Cannot create car factory");
        } else {
            factory.create("MINI");
            factory.create("SEDAN");
        }

        factory = FactoryCreator.createFactory(BIKE);
        if (factory == null) {
            System.out.println("Cannot create bike factory");
        } else {
            factory.create("NORMAL");
            factory.create("SPORTS");
        }
    }
}
