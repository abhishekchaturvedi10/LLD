package interview.lld.carbookingsystem.model.Car;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type"
)
@JsonSubTypes({
		@JsonSubTypes.Type(value = SUV.class, name = "SUV"),
		@JsonSubTypes.Type(value = Sedan.class, name = "SEDAN"),
		@JsonSubTypes.Type(value = Hatchback.class, name = "HATCHBACK")
})
@Data
public abstract class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long branchId;
	public abstract String getType();
	private List<Long> bookingIds;
}
