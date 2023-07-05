package LLD.AbstractFactory;

public class CarFactory implements VehicleFactory<Car> {

    private static final CarFactory carFactory = new CarFactory();

    public static CarFactory getFactory() {
        System.out.println("Car Factory");
        return carFactory;
    }

    public Car create(String carType) {
        if (carType.equalsIgnoreCase("MINI")) {
            return new MiniCar();
        } else if (carType.equalsIgnoreCase("SEDAN")) {
            return new SedanCar();
        } else {
            System.out.println("No such car type exists");
            return null;
        }
    }
}
