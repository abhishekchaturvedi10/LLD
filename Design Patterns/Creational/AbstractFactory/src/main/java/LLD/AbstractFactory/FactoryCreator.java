package LLD.AbstractFactory;

public class FactoryCreator {
    public static VehicleFactory<?> createFactory(VehicleType vehicleType){
        switch (vehicleType) {
            case CAR -> {
                return CarFactory.getFactory();
            } case BIKE -> {
                return BikeFactory.getFactory();
            }
        }
        return null;
    }
}
