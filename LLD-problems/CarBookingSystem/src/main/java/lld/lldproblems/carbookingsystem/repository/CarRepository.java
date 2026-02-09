package lld.lldproblems.carbookingsystem.repository;

import lld.lldproblems.carbookingsystem.model.Car.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}