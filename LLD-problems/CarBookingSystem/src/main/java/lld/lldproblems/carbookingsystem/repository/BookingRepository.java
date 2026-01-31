package lld.lldproblems.carbookingsystem.repository;

import lld.lldproblems.carbookingsystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
