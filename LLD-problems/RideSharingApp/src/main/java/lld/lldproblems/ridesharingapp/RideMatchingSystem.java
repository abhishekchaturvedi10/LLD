package lld.lldproblems.ridesharingapp;

import lld.lldproblems.ridesharingapp.FareStrategy.FareStrategy;
import lld.lldproblems.ridesharingapp.Location.Location;
import lld.lldproblems.ridesharingapp.Ride.Ride;
import lld.lldproblems.ridesharingapp.Ride.RideStatus;
import lld.lldproblems.ridesharingapp.Ride.RideType;
import lld.lldproblems.ridesharingapp.User.Driver;
import lld.lldproblems.ridesharingapp.User.Passenger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RideMatchingSystem {
	
	private final DriverService driverService;
	private final ExecutorService executorService;
	
	public RideMatchingSystem(DriverService driverService) {
		this.driverService = driverService;
		this.executorService = Executors.newFixedThreadPool(10);
	}
	
	public void addDriver(Driver driver) {
		driverService.addDriver(driver);
	}
	
	public void requestRide(Passenger passenger, RideType rideType, Location fromLocation, Location toLocation, FareStrategy fareStrategy, String vehicleType) {
		executorService.submit(() -> {
			try {
				processRideCreation(passenger, rideType, fromLocation, toLocation, fareStrategy, vehicleType);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		});
	}
	
	public void processRideCreation(Passenger passenger, RideType rideType, Location fromLocation, Location toLocation, FareStrategy fareStrategy, String vehicleType) throws InterruptedException {
		
		System.out.println(Thread.currentThread().getName() + " - processing ride creation with params " + passenger.getName() + ", " + rideType + ", " + fromLocation + ", " + toLocation + ", " + vehicleType);
		
		System.out.println(Thread.currentThread().getName() + " - finding driver for ride request...");
		// Simulate time taken to find a driver
		Thread.sleep(1000);
		Driver assignedDriver = driverService.findDriver(fromLocation, vehicleType);
		if(assignedDriver == null) {
			System.out.println(Thread.currentThread().getName() + " - Driver not found for ride request");
			passenger.notify("No drivers available at the moment. Please try again later.");
			return;
		}
		
		Ride ride = new Ride(assignedDriver, passenger, fromLocation, toLocation, rideType, fareStrategy);
		passenger.notify("Driver " + assignedDriver.getName() + " has been assigned to your ride with estimated fare: " + ride.getFare() + " and OTP " + ride.getOTP());
		assignedDriver.notify("You have been assigned a new ride from " + passenger.getName() + " with estimated fare: " + ride.getFare() + " and OTP " + ride.getOTP());
		ride.updateStatus(RideStatus.ONGOING);
		System.out.println(Thread.currentThread().getName() + " - ride created is ongoing...");
		
		// Simulate ride completion after some time
		Thread.sleep(10000);
		
		ride.updateStatus(RideStatus.COMPLETED);
		assignedDriver.setAvailable(true);
		
		passenger.notify("Your ride with " + assignedDriver.getName() + " has been completed. Total fare: " + ride.getFare());
		assignedDriver.notify("Your ride with " + passenger.getName() + " has been completed. Total fare: " + ride.getFare());
		
		System.out.println(Thread.currentThread().getName() + " - ride completed...");
	}
}
