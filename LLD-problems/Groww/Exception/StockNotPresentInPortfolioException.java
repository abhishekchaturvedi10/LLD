package Groww.Exception;

public class StockNotPresentInPortfolioException extends RuntimeException {
    public StockNotPresentInPortfolioException(String message) {
        super(message);
    }
}
