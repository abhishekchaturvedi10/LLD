package lld.lldproblems.expensesharingapp.model.request;

import lombok.Getter;
import lombok.Setter;
import java.util.concurrent.CompletableFuture;

@Getter
@Setter
public class RequestWrapper {
	private final Request request;
	private final CompletableFuture<Object> future;
	
	public RequestWrapper(Request request, CompletableFuture<Object> future) {
		this.request = request;
		this.future = future;
	}
}