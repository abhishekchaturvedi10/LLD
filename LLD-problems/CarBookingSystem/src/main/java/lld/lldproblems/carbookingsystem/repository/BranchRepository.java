package interview.lld.carbookingsystem.repository;

import interview.lld.carbookingsystem.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
