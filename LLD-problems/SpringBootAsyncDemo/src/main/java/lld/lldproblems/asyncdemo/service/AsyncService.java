package lld.lldproblems.asyncdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncService {
	
	@Async
	public void executeAsyncTask(String payload) {
		log.info("Thread - {} is processing payload - {}", Thread.currentThread().threadId(), payload);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		log.info("Thread - {} processed payload - {}", Thread.currentThread().threadId(), payload);
	}
}