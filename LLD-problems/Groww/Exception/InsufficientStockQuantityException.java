package Groww.Exception;

public class InsufficientStockQuantityException extends RuntimeException {
    public InsufficientStockQuantityException(String message) {
        super(message);
    }
}
