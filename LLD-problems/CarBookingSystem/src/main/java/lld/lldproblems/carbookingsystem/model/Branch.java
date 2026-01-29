package interview.lld.carbookingsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Branch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private List<Long> carIds;
	private Double SUVPrice;
	private Double sedanPrice;
	private Double hatchbackPrice;
}