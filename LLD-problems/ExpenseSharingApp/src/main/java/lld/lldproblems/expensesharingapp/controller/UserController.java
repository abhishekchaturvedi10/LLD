package lld.lldproblems.expensesharingapp.controller;

import lld.lldproblems.expensesharingapp.async.KafkaConsumer;
import lld.lldproblems.expensesharingapp.model.User;
import lld.lldproblems.expensesharingapp.model.request.BalanceRequest;
import lld.lldproblems.expensesharingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private KafkaConsumer kafkaConsumer;
	
	@PostMapping("/createUser")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/balance/{userId}")
	public ResponseEntity<Object> getUserBalance(@PathVariable Long userId) throws Exception {
		BalanceRequest request = new BalanceRequest();
		request.setUserId(userId);
		CompletableFuture<Object> future = new CompletableFuture<>();
		kafkaConsumer.addRequest(request, future);
		Object result = future.get(); // waits for result
		return ResponseEntity.status(200).body(result);
	}
}
