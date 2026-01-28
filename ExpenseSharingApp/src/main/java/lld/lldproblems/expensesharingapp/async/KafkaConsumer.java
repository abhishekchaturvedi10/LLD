package lld.lldproblems.expensesharingapp.async;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lld.lldproblems.expensesharingapp.model.Expense.Expense;
import lld.lldproblems.expensesharingapp.model.request.BalanceRequest;
import lld.lldproblems.expensesharingapp.model.request.ExpenseRequest;
import lld.lldproblems.expensesharingapp.model.request.Request;
import lld.lldproblems.expensesharingapp.model.request.RequestWrapper;
import lld.lldproblems.expensesharingapp.service.ExpenseService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.*;

@Service
@Getter
@Setter
public class KafkaConsumer {
	
	private final BlockingQueue<RequestWrapper> queue = new LinkedBlockingQueue<>();
	private final ExecutorService executor = Executors.newFixedThreadPool(10);
	
	@Autowired
	private ExpenseService expenseService;
	
	@PostConstruct
	public void init() {
		startProcessing();
	}
	
	@PreDestroy
	public void shutdown() {
		executor.shutdownNow();
	}
	
	private void startProcessing() {
		for (int i = 0; i < 10; i++) {
			executor.submit(() -> {
				while (true) {
					try {
						RequestWrapper request = queue.take();
						processRequest(request);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
						break;
					}
				}
			});
		}
	}
	
	public void addRequest(Request request, CompletableFuture<Object> future) {
		queue.offer(new RequestWrapper(request, future));
	}
	
	private void processRequest(RequestWrapper requestWrapper) {
		System.out.println("Processing: " + requestWrapper + " by " + Thread.currentThread().getName());
		try {
			if (requestWrapper.getRequest() instanceof ExpenseRequest expenseRequest) {
				Expense expense = expenseService.addExpense(
						expenseRequest.getExpenseName(),
						expenseRequest.getTotalAmount(),
						expenseRequest.getPaidByUserId(),
						expenseRequest.getExpenseType(),
						expenseRequest.getPaidForUserIds(),
						expenseRequest.getValues()
				);
				requestWrapper.getFuture().complete("Expense added successfully: " + expense);
			} else if (requestWrapper.getRequest() instanceof BalanceRequest balanceRequest) {
				BigDecimal balance = expenseService.getUserBalance(balanceRequest.getUserId());
				requestWrapper.getFuture().complete(balance);
			} else {
				requestWrapper.getFuture().complete("Unknown request type");
			}
		} catch (Exception e) {
			requestWrapper.getFuture().completeExceptionally(e);
			System.out.println("Failed to process request: " + e.getMessage());
		}
	}
}
