package lld.lldproblems.carbookingsystem.service;

import lld.lldproblems.carbookingsystem.model.Branch;
import lld.lldproblems.carbookingsystem.model.Car.Car;
import lld.lldproblems.carbookingsystem.repository.BranchRepository;
import lld.lldproblems.carbookingsystem.repository.CarRepository;
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
