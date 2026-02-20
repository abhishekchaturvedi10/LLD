package lld.lldproblems.ridesharingapp.Ride;

import lld.lldproblems.ridesharingapp.FareStrategy.FareStrategy;
import lld.lldproblems.ridesharingapp.Location.Location;
import lld.lldproblems.ridesharingapp.User.Driver;
import lld.lldproblems.ridesharingapp.User.Passenger;
import lld.lldproblems.ridesharingapp.Vehicle.Vehicle;
import lombok.Getter;
import lombok.Setter;

import static lld.lldproblems.ridesharingapp.Location.LocationService.calculateDistance;

@Getter
@Setter
public class Ride {
	
	private Driver driver;
	private Passenger passenger;
	private Vehicle vehicle;
	private FareStrategy fareStrategy;
	private Double fare;
	private RideStatus status;
	private Location fromLocation;
	private Location toLocation;
	private RideType rideType;
	private String OTP;
	
	public Ride(Driver driver, Passenger passenger, Location fromLocation, Location toLocation, RideType rideType, FareStrategy fareStrategy) {
		this.driver = driver;
		this.passenger = passenger;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.rideType = rideType;
		this.fareStrategy = fareStrategy;
		this.vehicle = driver.getVehicle();
		this.status = RideStatus.REQUESTED;
		generateOTP();
		calculateFare();
	}
	
	private void generateOTP() {
		this.OTP = String.valueOf((int)(Math.random() * 9000) + 1000);
	}
	
	private void calculateFare() {
		double distance = calculateDistance(fromLocation, toLocation);
		this.fare = fareStrategy.calculateFare(distance, vehicle.getFarePerKM());
	}
	
	public void updateStatus(RideStatus newStatus) {
		this.status = newStatus;
		this.passenger.notify("Your ride status has been updated to: " + newStatus);
		this.driver.notify("Your ride status has been updated to: " + newStatus);
	}
}