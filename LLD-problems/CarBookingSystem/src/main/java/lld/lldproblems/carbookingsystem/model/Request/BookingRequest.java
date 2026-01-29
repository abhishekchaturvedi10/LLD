package interview.lld.carbookingsystem.model.Request;

import lombok.Data;

@Data
public class BookingRequest {
	private String carType;
	private String fromDatetime;
	private String toDatetime;
}
