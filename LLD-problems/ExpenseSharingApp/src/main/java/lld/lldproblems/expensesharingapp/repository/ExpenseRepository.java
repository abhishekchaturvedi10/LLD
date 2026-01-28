package lld.lldproblems.expensesharingapp.repository;

import lld.lldproblems.expensesharingapp.model.Expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
