package interview.lld.carbookingsystem.Controller;

import interview.lld.carbookingsystem.model.Branch;
import interview.lld.carbookingsystem.model.Car.CarType;
import interview.lld.carbookingsystem.model.Request.PriceRequest;
import interview.lld.carbookingsystem.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/branch")
public class BranchController {
	
	@Autowired
	private BranchRepository branchRepository;
	
	@PostMapping("/create")
	public Branch createBranch(@RequestBody Branch branch) {
		return branchRepository.save(branch);
	}
	
	@PutMapping("/updatePrice")
	public  Branch updatePrice(@RequestBody PriceRequest priceRequest) {
		Branch oldBranch = branchRepository.findById(priceRequest.getBranchId()).orElse(null);
		if (oldBranch != null) {
			if(priceRequest.getCarType().equals(CarType.HATCHBACK)) {
				oldBranch.setHatchbackPrice(priceRequest.getPrice());
			} else if (priceRequest.getCarType().equals(CarType.SEDAN)) {
				oldBranch.setSedanPrice(priceRequest.getPrice());
			} else if (priceRequest.getCarType().equals(CarType.SUV)) {
				oldBranch.setSUVPrice(priceRequest.getPrice());
			} else {
				throw new IllegalArgumentException("Invalid car type");
			}
			return branchRepository.save(oldBranch);
		} else {
			throw new IllegalArgumentException("Branch not found");
		}
	}
}
