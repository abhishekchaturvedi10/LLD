package lld.lldproblems.ridesharingapp;

import lld.lldproblems.ridesharingapp.FareStrategy.*;
import lld.lldproblems.ridesharingapp.Location.Location;
import lld.lldproblems.ridesharingapp.Ride.RideType;
import lld.lldproblems.ridesharingapp.Service.DriverService;
import lld.lldproblems.ridesharingapp.Service.RideMatchingSystem;
import lld.lldproblems.ridesharingapp.User.Driver;
import lld.lldproblems.ridesharingapp.User.Passenger;
import lld.lldproblems.ridesharingapp.Vehicle.Auto;
import lld.lldproblems.ridesharingapp.Vehicle.Bike;
import lld.lldproblems.ridesharingapp.Vehicle.Car.Hatchback;
import lld.lldproblems.ridesharingapp.Vehicle.Car.SUV;
import lld.lldproblems.ridesharingapp.Vehicle.Car.Sedan;
import lld.lldproblems.ridesharingapp.Vehicle.Vehicle;
import java.util.Random;
import java.util.UUID;

public class RideSharingAppApplication {
	
	public static RideMatchingSystem rideMatchingSystem = new RideMatchingSystem(new DriverService());
	
	public static void main(String[] args) {
		
		Random random = new Random();
		
		RideType[] rideTypes = { RideType.LUXURY, RideType.STANDARD, RideType.LATE_NIGHT, RideType.SHARED };
		String[] vehicleTypes = { "Sedan", "Hatchback", "SUV", "Auto", "Bike" };
		Location[] fromLocations = {
				new Location(12.9716,77.5946),
				new Location(11.0168,76.9558),
				new Location(10.8505,76.2711),
				new Location(12.2958,76.6394)
		};
		Location[] toLocations = {
				new Location(11.0168,76.9558),
				new Location(12.9716,77.5946),
				new Location(10.8505,76.2711),
				new Location(12.2958,76.6394)
		};
		FareStrategy[] fareStrategies = {
				new LuxuryFareStrategy(),
				new StandardFareStrategy(),
				new LateNightFareStrategy(),
				new SharedFareStrategy()
		};
		Vehicle[] vehicles = {
				new Bike(11.35),
				new Auto(15.57),
				new Sedan(83.92),
				new SUV(56.84),
				new Hatchback(39.63)
		};
		
		for (int i =1; i <=20; i++) {
			Driver driver = new Driver(
					UUID.randomUUID().toString(),
					"Driver" + i,
					"driver" + i + "@example.com",
					"0987654321" + i,
					vehicles[random.nextInt(vehicleTypes.length)],
					true,
					toLocations[random.nextInt(toLocations.length)]
					);
			rideMatchingSystem.addDriver(driver);
		}
		
		for (int i =1; i <=100; i++) {
			Passenger passenger = new Passenger(
					UUID.randomUUID().toString(),
					"Passenger" + i,
					"passenger" + i + "@example.com",
					"0987654321" + i );
			
			int rideTypeIndex = random.nextInt(rideTypes.length);
			RideType rideType = rideTypes[rideTypeIndex];
			FareStrategy fareStrategy = fareStrategies[rideTypeIndex];
			Location fromLocation = fromLocations[random.nextInt(fromLocations.length)];
			Location toLocation = toLocations[random.nextInt(toLocations.length)];
			String vehicleType = vehicleTypes[random.nextInt(vehicleTypes.length)];
			
			rideMatchingSystem.requestRide(
					passenger,
					rideType,
					fromLocation,
					toLocation,
					fareStrategy,
					vehicleType
			);
		}
	}
}
