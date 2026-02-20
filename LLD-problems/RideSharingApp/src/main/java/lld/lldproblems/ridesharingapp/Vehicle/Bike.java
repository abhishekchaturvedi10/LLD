package lld.lldproblems.ridesharingapp.Vehicle;

public class Bike extends Vehicle {
	
	public Bike(Double farePerKM) {
		super(farePerKM);
		vehicleID++;
		this.setVehicleType("Bike");
	}
}
