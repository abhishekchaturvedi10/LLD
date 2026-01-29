package interview.lld.carbookingsystem.Controller;

import interview.lld.carbookingsystem.model.Car.Car;
import interview.lld.carbookingsystem.model.Request.BookingRequest;
import interview.lld.carbookingsystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/create")
	public ResponseEntity<String> bookCar(@RequestBody BookingRequest req) {
		Car car = bookingService.assignCar(req.getCarType(), req.getFromDatetime(), req.getToDatetime());
		if (car != null) {
			return ResponseEntity.ok().body("Car assigned successfully, type - " + car.getType());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
