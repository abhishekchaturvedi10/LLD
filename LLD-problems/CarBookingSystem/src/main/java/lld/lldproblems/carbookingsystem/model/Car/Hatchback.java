package lld.lldproblems.carbookingsystem.model.Car;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("HATCHBACK")
public class Hatchback extends Car {
	@Override
	public String getType() {
		return "HATCHBACK";
	}
}