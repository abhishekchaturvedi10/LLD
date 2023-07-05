package LLD.AbstractFactory;

public class BikeFactory implements VehicleFactory<Bike> {

    private static final BikeFactory bikeFactory = new BikeFactory();

    public static BikeFactory getFactory() {
        System.out.println("Bike Factory");
        return bikeFactory;
    }

    public Bike create(String bikeType) {
        if (bikeType.equalsIgnoreCase("NORMAL")) {
            return new NormalBike();
        } else if (bikeType.equalsIgnoreCase("SPORTS")) {
            return new SportsBike();
        } else {
            System.out.println("No such bike type exists");
            return null;
        }
    }
}
