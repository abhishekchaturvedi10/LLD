package lld.lldproblems.multithreading;

import java.util.Random;
import java.util.Stack;

public class SharedResource {
	
	Stack<String> stack = new Stack<>();
	private final Random random = new Random();
	
	public synchronized void produceItem() throws InterruptedException {
		System.out.println("Inside produce item");
		while(stack.size() == 3) {
			try {
				System.out.println("Stack is full, waiting for consumer to consume item");
				wait();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		Thread.sleep(random.nextInt(10000));
		String item = "item" + (stack.size() + 1);
		stack.push(item);
		System.out.println("Item produced - " + item);
		notifyAll();
	}
	
	public synchronized void consumeItem() throws InterruptedException {
		System.out.println("Inside consume item");
		while (stack.isEmpty()) {
			try {
				System.out.println("Stack is empty, waiting for producer to produce item");
				wait();
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
		Thread.sleep(random.nextInt(10000));
		System.out.println("Item consumed - " + stack.peek());
		stack.pop();
		notifyAll();
	}
}
