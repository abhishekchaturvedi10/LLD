package lld.lldproblems.ridesharingapp.FareStrategy;

public class SharedFareStrategy implements FareStrategy {
	
	@Override
	public Double calculateFare(Double distance, Double farePerKM) {
		return distance * farePerKM * 0.75;
	}
}