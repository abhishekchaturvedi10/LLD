package lld.lldproblems.expensesharingapp.repository;

import lld.lldproblems.expensesharingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
