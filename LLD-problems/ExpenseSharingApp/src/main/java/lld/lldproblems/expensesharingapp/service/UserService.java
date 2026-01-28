package lld.lldproblems.expensesharingapp.service;

import lld.lldproblems.expensesharingapp.model.User;
import lld.lldproblems.expensesharingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public User getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new RuntimeException("User not found with id: " + id));
	}
}