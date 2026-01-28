package lld.lldproblems.expensesharingapp.service;

import lld.lldproblems.expensesharingapp.model.Split;
import lld.lldproblems.expensesharingapp.model.User;
import lld.lldproblems.expensesharingapp.repository.SplitRepository;
import lld.lldproblems.expensesharingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SplitRepository splitRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
	}
	
	public BigDecimal getUserBalance(Long userId) {
		BigDecimal balance = BigDecimal.ZERO;
		List<Split> paidForSplits = splitRepository.findByPaidFor(userId);
		for (Split split : paidForSplits) {
			balance = balance.subtract(split.getAmount());
		}
		List<Split> paidBySplits = splitRepository.findByPaidBy(userId);
		for (Split split : paidBySplits) {
			balance = balance.add(split.getAmount());
		}
		return balance.setScale(2, RoundingMode.HALF_UP);
	}
}