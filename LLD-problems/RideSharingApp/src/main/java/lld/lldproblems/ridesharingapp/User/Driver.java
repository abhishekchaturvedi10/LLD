package lld.lldproblems.ridesharingapp.User;

import lld.lldproblems.ridesharingapp.Location.Location;
import lld.lldproblems.ridesharingapp.Vehicle.Vehicle;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Driver extends User {
	
	private Vehicle vehicle;
	private boolean isAvailable;
	private Location location;
	
	public Driver(String userID, String name, String email, String phoneNumber, Vehicle vehicle, Boolean isAvailable, Location location) {
		super(userID, name, email, phoneNumber);
		this.vehicle = vehicle;
		this.isAvailable = isAvailable;
		this.location = location;
	}
	
	public void notify(String message) {
		System.out.println(this.name + " Notified: " + message);
	}
}