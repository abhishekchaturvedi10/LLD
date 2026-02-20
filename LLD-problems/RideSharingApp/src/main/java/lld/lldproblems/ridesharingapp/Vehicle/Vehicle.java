package lld.lldproblems.ridesharingapp.Vehicle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Vehicle {
	
	protected static Long vehicleID = 0L;
	Double farePerKM;
	String vehicleType;
	
	public Vehicle(Double farePerKM) {
		this.farePerKM = farePerKM;
	}
}