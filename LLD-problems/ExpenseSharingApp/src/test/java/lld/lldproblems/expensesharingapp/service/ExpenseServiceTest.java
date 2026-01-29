package lld.lldproblems.expensesharingapp.service;

import lld.lldproblems.expensesharingapp.model.Expense.Expense;
import lld.lldproblems.expensesharingapp.model.Expense.ExpenseType;
import lld.lldproblems.expensesharingapp.model.User;
import lld.lldproblems.expensesharingapp.model.request.ExpenseRequest;
import lld.lldproblems.expensesharingapp.repository.ExpenseRepository;
import lld.lldproblems.expensesharingapp.repository.SplitRepository;
import lld.lldproblems.expensesharingapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ExpenseServiceTest {
	
	@Mock
	private ExpenseRepository expenseRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private SplitRepository splitRepository;
	
	@InjectMocks
	private ExpenseService expenseService;
	
	public ExpenseServiceTest() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testAddExpense() {
		ExpenseRequest expenseRequest = new ExpenseRequest();
		Expense expense = new Expense();
		expense.setAmount(BigDecimal.valueOf(50));
		expense.setExpenseType(ExpenseType.EQUAL);
		when(expenseRepository.save(any(Expense.class))).thenReturn(expense);
		when(userRepository.findById(any())).thenReturn(Optional.of(new User()));
		
		Expense saved = expenseService.addExpense(
				"test",
				BigDecimal.valueOf(100),
				1L,
				ExpenseType.valueOf(ExpenseType.EQUAL.toString()),
				List.of(1L,2L,3L),
				expenseRequest.getValues());
		assertEquals(BigDecimal.valueOf(100), saved.getAmount());
	}
}