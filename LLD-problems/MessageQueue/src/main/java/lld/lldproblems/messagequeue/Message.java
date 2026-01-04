package lld.lldproblems.messagequeue;

import lombok.Data;


@Data
public class Message {
	
	private final String content;
	
	public Message(String content) {
		this.content = content;
	}
}