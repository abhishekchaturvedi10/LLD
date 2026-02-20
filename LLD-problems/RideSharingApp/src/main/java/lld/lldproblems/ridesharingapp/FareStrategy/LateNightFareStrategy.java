package lld.lldproblems.ridesharingapp.FareStrategy;

public class LateNightFareStrategy implements FareStrategy {
	
	@Override
	public Double calculateFare(Double distance, Double farePerKM) {
		return distance * farePerKM * 1.25;
	}
}
