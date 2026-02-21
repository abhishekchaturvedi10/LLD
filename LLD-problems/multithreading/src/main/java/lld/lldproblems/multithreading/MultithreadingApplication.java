package lld.lldproblems.multithreading;

public class MultithreadingApplication {
	
	public static void main(String[] args) {
		
		SharedResource sharedResource = new SharedResource();
		
		Thread producer1 = new Thread(() -> {
			try {
				for (int i = 0; i < 5; i++) {
					sharedResource.produceItem();
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}, "Producer-1");
		
		Thread producer2 = new Thread(() -> {
			try {
				for (int i = 0; i < 5; i++) {
					sharedResource.produceItem();
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}, "Producer-2");
		
		// Create multiple consumer threads
		Thread consumer1 = new Thread(() -> {
			try {
				for (int i = 0; i < 5; i++) {
					sharedResource.consumeItem();
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}, "Consumer-1");
		
		Thread consumer2 = new Thread(() -> {
			try {
				for (int i = 0; i < 5; i++) {
					sharedResource.consumeItem();
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}, "Consumer-2");
		
		// Start all threads
		producer1.start();
		producer2.start();
		consumer1.start();
		consumer2.start();}
}