package com.parse.task;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class TaskCenter implements ITaskCenter {
	private boolean started = false;
	
	private TaskThread taskThread = null;

	private TaskCenter() {
	}
	
	@PostConstruct
	@Override
	public void start() {
		System.out.println(">>>>>>开始启动任务中心>>>>>>>>>>>>>");
		
		if (this.started) {
			return;
		}
		
		taskThread = new TaskThread();
		taskThread.start();
		
		this.started = true;
		
		System.out.println(">>>>>>结束启动任务中心>>>>>>>>>>>>>");
	}
	
	@Override
	public void sendRemote(String message) {
	}
	
	@Override
	public void sendLocal(String message) {
		taskThread.addTask(message);
	}

	@PreDestroy
	@Override
	public void stop() {
		System.out.println(">>>>>>开始关闭任务中心>>>>>>>>>>>>>");
		this.started = false;
		
		if (null != taskThread) {
			taskThread.close();
			taskThread = null;
		}
		
		System.out.println(">>>>>>结束关闭任务中心>>>>>>>>>>>>>");
	}
}
