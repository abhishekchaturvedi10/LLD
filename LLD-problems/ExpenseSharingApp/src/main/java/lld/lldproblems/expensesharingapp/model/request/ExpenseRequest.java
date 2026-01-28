package lld.lldproblems.expensesharingapp.model.request;

import lld.lldproblems.expensesharingapp.model.Expense.ExpenseType;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ExpenseRequest implements Request {
	public String expenseName;
	public BigDecimal totalAmount;
	public Long paidByUserId;
	public ExpenseType expenseType;
	public List<Long> paidForUserIds;
	public List<Double> values;
	public Long userId;
}