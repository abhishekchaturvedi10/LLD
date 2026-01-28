package lld.lldproblems.expensesharingapp.model;

import jakarta.persistence.*;
import lld.lldproblems.expensesharingapp.model.Expense.Expense;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Split {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private BigDecimal amount;
	private Long paidBy;
	private Long paidFor;
	@ManyToOne
	@JoinColumn(name = "expense_id")
	private Expense expense;
}
