package lld.lldproblems.ridesharingapp.Service;

import lld.lldproblems.ridesharingapp.Location.Location;
import lld.lldproblems.ridesharingapp.User.Driver;
import java.util.concurrent.CopyOnWriteArrayList;
import static lld.lldproblems.ridesharingapp.Location.LocationService.calculateDistance;

public class DriverService {
	
	private final CopyOnWriteArrayList<Driver> drivers;
	
	public DriverService() {
		this.drivers = new CopyOnWriteArrayList<>();
	}
	
	public void addDriver(Driver driver) {
		drivers.add(driver);
	}
	
	public Driver findDriver(Location fromLocation, String vehicleType) {
		Driver bestDriver = null;
		double minDistance = Double.MAX_VALUE;
		for(Driver driver : drivers) {
			if(driver.isAvailable() && driver.getVehicle().getVehicleType().equals(vehicleType)) {
				double distance = calculateDistance(fromLocation, driver.getLocation());
				if(distance < minDistance) {
					bestDriver = driver;
					minDistance = distance;
				}
			}
		}
		if(bestDriver != null) {
			bestDriver.setAvailable(false);
		}
		return bestDriver;
	}
}