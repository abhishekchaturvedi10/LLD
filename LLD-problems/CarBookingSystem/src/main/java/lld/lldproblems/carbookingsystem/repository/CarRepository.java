package interview.lld.carbookingsystem.repository;

import interview.lld.carbookingsystem.model.Car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
