package lld.lldproblems.expensesharingapp.service;

import lld.lldproblems.expensesharingapp.exception.UserNotFoundException;
import lld.lldproblems.expensesharingapp.model.Expense.Expense;
import lld.lldproblems.expensesharingapp.model.Expense.ExpenseType;
import lld.lldproblems.expensesharingapp.model.Split;
import lld.lldproblems.expensesharingapp.model.User;
import lld.lldproblems.expensesharingapp.repository.ExpenseRepository;
import lld.lldproblems.expensesharingapp.repository.SplitRepository;
import lld.lldproblems.expensesharingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SplitRepository splitRepository;
	
	@Transactional
	public Expense addExpense(String expenseName, BigDecimal totalAmount, Long paidByUserId, ExpenseType expenseType, List<Long> paidForUserIds, List<Double> values) {
		
		Expense expense = new Expense();
		expense.setExpenseName(expenseName);
		expense.setAmount(totalAmount);
		expense.setExpenseType(expenseType);
		
		User paidByUser = userRepository.findById(paidByUserId).orElseThrow(() -> new UserNotFoundException("User with ID " + paidByUserId + " not found"));
		List<Split> splits = new ArrayList<>();
		
		if ("EQUAL".equalsIgnoreCase(expenseType.toString())) {
			BigDecimal equalShareAmount = totalAmount.divide(BigDecimal.valueOf(paidForUserIds.size()), 2, RoundingMode.DOWN);
			for (long userId : paidForUserIds) {
				if (userId == paidByUserId) {
					continue;
				}
				User paidForUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
				Split split = new Split();
				split.setExpense(expense);
				split.setPaidBy(paidByUser.getId());
				split.setPaidFor(paidForUser.getId());
				split.setAmount(equalShareAmount);
				splits.add(split);
			}
		} else if ("EXACT".equalsIgnoreCase(expenseType.toString())) {
			BigDecimal sum = BigDecimal.ZERO;
			for (Double v : values) sum = sum.add(BigDecimal.valueOf(v));
			if (sum.setScale(2, RoundingMode.HALF_UP).compareTo(totalAmount.setScale(2, RoundingMode.HALF_UP)) != 0)
				throw new IllegalArgumentException("Sum of exact amounts does not match total amount");
			for (int i=0; i<paidForUserIds.size(); i++) {
				if (paidByUserId.equals(paidForUserIds.get(i))) {
					continue;
				}
				int finalI = i;
				User paidForUser = userRepository.findById(paidForUserIds.get(i)).orElseThrow(() -> new UserNotFoundException("User with ID " + paidForUserIds.get(finalI) + " not found"));
				Split split = new Split();
				split.setExpense(expense);
				split.setPaidBy(paidByUser.getId());
				split.setPaidFor(paidForUser.getId());
				split.setAmount(BigDecimal.valueOf(values.get(i)).setScale(2, RoundingMode.HALF_UP));
				splits.add(split);
			}
		} else if ("PERCENTAGE".equalsIgnoreCase(expenseType.toString())) {
			BigDecimal sum = BigDecimal.ZERO;
			for (Double v : values) sum = sum.add(BigDecimal.valueOf(v));
			if (sum.setScale(2, RoundingMode.HALF_UP).compareTo(BigDecimal.valueOf(100.0).setScale(2, RoundingMode.HALF_UP)) != 0)
				throw new IllegalArgumentException("Sum of percents does not equal 100");
			for (int i=0; i<paidForUserIds.size(); i++) {
				if (paidByUserId.equals(paidForUserIds.get(i))) {
					continue;
				}
				int finalI = i;
				User paidForUser = userRepository.findById(paidForUserIds.get(i)).orElseThrow(() -> new UserNotFoundException("User with ID " + paidForUserIds.get(finalI) + " not found"));
				Split split = new Split();
				split.setExpense(expense);
				split.setPaidBy(paidByUser.getId());
				split.setPaidFor(paidForUser.getId());
				BigDecimal amount = totalAmount
						.multiply(BigDecimal.valueOf(values.get(i)))
						.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
				split.setAmount(amount);
				splits.add(split);
			}
		} else {
			throw new RuntimeException("Invalid expense type");
		}
		
		expense.setSplits(splits);
		expenseRepository.save(expense);
		splitRepository.saveAll(splits);
		return expense;
	}
}
