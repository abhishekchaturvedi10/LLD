package interview.lld.carbookingsystem.model.Request;

import interview.lld.carbookingsystem.model.Car.CarType;
import lombok.Data;

@Data
public class PriceRequest {
	private Long branchId;
	private CarType carType;
	private Double price;
}
