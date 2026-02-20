package lld.lldproblems.ridesharingapp.Vehicle;

public class Auto extends Vehicle {
	
	public Auto(Double farePerKM) {
		super(farePerKM);
		vehicleID++;
		this.setVehicleType("Auto");
	}
}
