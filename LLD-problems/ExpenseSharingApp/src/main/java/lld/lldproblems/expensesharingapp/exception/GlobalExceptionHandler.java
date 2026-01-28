package lld.lldproblems.expensesharingapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handleBadRequestException(IllegalArgumentException ex) {
		return ResponseEntity.status(400).body("Illegal request: " + ex.getMessage());
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(Exception ex) {
		return ResponseEntity.status(404).body("Not found error: " + ex.getMessage());
	}
}