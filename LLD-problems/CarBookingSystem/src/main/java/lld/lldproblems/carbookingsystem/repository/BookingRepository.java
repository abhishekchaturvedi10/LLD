package interview.lld.carbookingsystem.repository;

import interview.lld.carbookingsystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
