package lld.lldproblems.expensesharingapp.model;

import lld.lldproblems.expensesharingapp.model.Expense.Expense;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class SplitTest {
	
	@Test
	void testSplitGettersAndSetters() {
		Expense expense = new Expense();
		Split split = new Split();
		split.setId(1L);
		split.setAmount(BigDecimal.valueOf(100));
		split.setPaidBy(2L);
		split.setPaidFor(3L);
		split.setExpense(expense);
		
		assertEquals(1L, split.getId());
		assertEquals(BigDecimal.valueOf(100), split.getAmount());
		assertEquals(2L, split.getPaidBy());
		assertEquals(3L, split.getPaidFor());
		assertEquals(expense, split.getExpense());
	}
	
	@Test
	void testSplitAllArgsConstructor() {
		Expense expense = new Expense();
		Split split = new Split(1L, BigDecimal.TEN, 2L, 3L, expense);
		
		assertEquals(1L, split.getId());
		assertEquals(BigDecimal.TEN, split.getAmount());
		assertEquals(2L, split.getPaidBy());
		assertEquals(3L, split.getPaidFor());
		assertEquals(expense, split.getExpense());
	}
}
