package interview.lld.carbookingsystem.service;

import interview.lld.carbookingsystem.model.Branch;
import interview.lld.carbookingsystem.model.Car.Car;
import interview.lld.carbookingsystem.repository.BranchRepository;
import interview.lld.carbookingsystem.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	public Car createCar(Car car) {
		Car savedCar = carRepository.save(car);
		Long branchId = savedCar.getBranchId();
		Branch branch = branchRepository.findById(branchId).orElse(null);
		assert branch != null;
		if (branch.getCarIds() == null) {
			branch.setCarIds(new java.util.ArrayList<>());
		}
		branch.getCarIds().add(branchId);
		branchRepository.save(branch);
		return savedCar;
	}
}
