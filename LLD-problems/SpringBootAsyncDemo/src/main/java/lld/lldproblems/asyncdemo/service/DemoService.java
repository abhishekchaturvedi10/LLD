package lld.lldproblems.asyncdemo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DemoService {
	
	@Autowired
	private AsyncService asyncService;
	
	public void submitTasks() {
		log.info("Preparing the Task list");
		List<String> taskList = new ArrayList<>();
		taskList.add("apple");
		taskList.add("orange");
		taskList.add("grapes");
		taskList.add("pineapple");
		taskList.add("banana");
		taskList.add("sapota");
		taskList.add("papaya");
		taskList.add("watermelon");
		taskList.add("coconut");
		taskList.add("muskmelon");
		taskList.add("lemon");
		taskList.add("guvava");
		
		log.info("Submitting task list");
		for (String payload : taskList) {
			asyncService.executeAsyncTask(payload);
		}
		log.info("Submitted task list");
	}
}
