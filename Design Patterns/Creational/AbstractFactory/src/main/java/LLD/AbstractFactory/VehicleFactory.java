package LLD.AbstractFactory;

public interface VehicleFactory<T> {
    T create(String type);
}