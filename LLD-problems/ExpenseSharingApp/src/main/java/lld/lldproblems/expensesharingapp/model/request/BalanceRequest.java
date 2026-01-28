package lld.lldproblems.expensesharingapp.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceRequest implements Request {
	public Long userId;
}
