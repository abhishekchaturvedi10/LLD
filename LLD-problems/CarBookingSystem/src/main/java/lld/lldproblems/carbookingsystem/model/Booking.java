package lld.lldproblems.carbookingsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Getter
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public Long carId;
	public Double amount;
	public String starDatetime;
	public String endDatetime;
	
	public Booking(Long carId, Double amount, String starDatetime, String endDatetime) {
		this.carId = carId;
		this.amount = amount;
		this.starDatetime = starDatetime;
		this.endDatetime = endDatetime;
	}
}