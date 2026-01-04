package lld.lldproblems.messagequeue;


public class Subscriber {
	
	private final String name;
	
	public Subscriber(String name) {
		this.name = name;
	}
	
	public void subscribe(Topic topic) {
		for (Partition partition : topic.getPartitions()) {
			partition.registerSubscriber(this);
			new Thread(() -> consumeMessages(partition)).start();
		}
	}
	
	private void consumeMessages(Partition partition) {
		while (true) {
			Message message = partition.getNextMessageForSubscriber(this);
			if (message != null) {
				processMessage(message);
			}
		}
	}
	
	private void processMessage(Message message) {
		System.out.println("Timestamp:" + System.currentTimeMillis() +
				" Subscriber " + name + " consumed message: " + message.getContent());
	}
}
