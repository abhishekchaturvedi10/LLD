package lld.lldproblems.carbookingsystem.Controller;

import lld.lldproblems.carbookingsystem.model.Car.Car;
import lld.lldproblems.carbookingsystem.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class CarController {
	
	@Autowired
	private CarService carService;
	
	@PostMapping("/create")
	public ResponseEntity<Car> addCar(@RequestBody Car car) {
		Car createdCar = carService.createCar(car);
		if (createdCar != null) {
			return ResponseEntity.ok(createdCar);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}
}