package lld.lldproblems.carbookingsystem.model.Car;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SEDAN")
public class Sedan extends Car {
	@Override
	public String getType() {
		return "SEDAN";
	}
}
