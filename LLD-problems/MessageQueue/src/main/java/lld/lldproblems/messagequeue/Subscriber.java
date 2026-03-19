package lld.lldproblems.messagequeue;


public class Subscriber {
	
	private final String name;
	private ExecutorService executor;
	
	public Subscriber(String name) {
		this.name = name;
		this.executor = Executors.newCachedThreadPool();
	}
	
	public void subscribe(Topic topic) {
		for (Partition partition : topic.getPartitions()) {
			partition.registerSubscriber(this);
			executor.submit(() -> consumeMessages(partition));
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
