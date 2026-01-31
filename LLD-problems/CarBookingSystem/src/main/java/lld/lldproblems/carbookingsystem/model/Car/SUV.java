package lld.lldproblems.carbookingsystem.model.Car;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SUV")
public class SUV extends Car {
	@Override
	public String getType() {
		return "SUV";
	}
}