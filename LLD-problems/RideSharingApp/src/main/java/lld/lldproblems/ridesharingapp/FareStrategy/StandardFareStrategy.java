package lld.lldproblems.ridesharingapp.FareStrategy;

public class StandardFareStrategy implements FareStrategy {
	
	@Override
	public Double calculateFare(Double distance, Double farePerKM) {
		return distance * farePerKM;
	}
}
