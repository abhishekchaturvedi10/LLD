package lld.lldproblems.ridesharingapp.Vehicle.Car;

public class Hatchback extends Car {
	
	public Hatchback(Double farePerKM) {
		super(farePerKM);
		vehicleID++;
		this.setVehicleType("Hatchback");
	}
}