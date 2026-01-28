package lld.lldproblems.expensesharingapp.model.Expense;

import jakarta.persistence.*;
import lld.lldproblems.expensesharingapp.model.Split;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String expenseName;
	private BigDecimal amount;
	@Enumerated(EnumType.STRING)
	private ExpenseType expenseType;
	@OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
	private List<Split> splits;
}