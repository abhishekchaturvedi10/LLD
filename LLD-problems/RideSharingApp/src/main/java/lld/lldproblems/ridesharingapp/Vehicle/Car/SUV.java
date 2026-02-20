package lld.lldproblems.ridesharingapp.Vehicle.Car;

public class SUV extends Car {
	
	public SUV( Double farePerKM) {
		super(farePerKM);
		vehicleID++;
		this.setVehicleType("SUV");
	}
}