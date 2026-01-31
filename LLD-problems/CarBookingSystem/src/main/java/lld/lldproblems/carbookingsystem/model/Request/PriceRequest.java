package lld.lldproblems.carbookingsystem.model.Request;

import lld.lldproblems.carbookingsystem.model.Car.CarType;
import lombok.Data;

@Data
public class PriceRequest {
	private Long branchId;
	private CarType carType;
	private Double price;
}
