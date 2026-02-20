package lld.lldproblems.ridesharingapp.Location;

public class LocationService {
	
	public static Double calculateDistance(Location source, Location destination) {
		return Math.sqrt(Math.pow(destination.getLatitude() - source.getLatitude(), 2) + Math.pow(destination.getLongitude() - source.getLongitude(), 2));
	}
}
