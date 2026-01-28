package lld.lldproblems.expensesharingapp.controller;

import lld.lldproblems.expensesharingapp.model.request.ExpenseRequest;
import lld.lldproblems.expensesharingapp.async.KafkaConsumer;
import lld.lldproblems.expensesharingapp.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
	
	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private KafkaConsumer kafkaConsumer;
	
	@PostMapping("/addExpense")
	public ResponseEntity<Object> addExpense(@RequestBody ExpenseRequest request) throws Exception {
		CompletableFuture<Object> future = new CompletableFuture<>();
		kafkaConsumer.addRequest(request, future);
		Object result = future.get(); // waits for result
		return ResponseEntity.status(200).body(result);
	}
}