package lld.lldproblems.expensesharingapp.repository;

import lld.lldproblems.expensesharingapp.model.Split;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SplitRepository extends JpaRepository<Split, Long> {
	List<Split> findByPaidFor(long paidFor);
	List<Split> findByPaidBy(long paidBy);
}
