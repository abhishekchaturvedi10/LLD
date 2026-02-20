package lld.lldproblems.ridesharingapp.Vehicle.Car;

public class Sedan extends Car {
	
	public Sedan(Double farePerKM) {
		super(farePerKM);
		vehicleID++;
		this.setVehicleType("Sedan");
	}
}