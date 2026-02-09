package lld.lldproblems.carbookingsystem.Controller;

import lld.lldproblems.carbookingsystem.model.Car.Car;
import lld.lldproblems.carbookingsystem.model.Request.BookingRequest;
import lld.lldproblems.carbookingsystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/create")
	public ResponseEntity<String> bookCar(@RequestBody BookingRequest req) {
		CompletableFuture<Car> car = bookingService.assignCar(req.getCarType(), req.getFromDatetime(), req.getToDatetime());
		if (car != null) {
			try {
				return ResponseEntity.ok().body("Car assigned successfully, type - " + car.get().getType());
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e);
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
