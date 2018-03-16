package com.parse.task;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.stereotype.Service;

@Service(value = "simpleTask")
public class SimpleTask implements Runnable {
	private static SimpleTask simpleTask = new SimpleTask();
	
	private SimpleTask() {
	}
	
	public static SimpleTask getInstance() {
		return simpleTask;
	}
	
	private LinkedList<String> sysWfEventQueue = new LinkedList<String>();
	private ReentrantLock reentrantLock = new ReentrantLock();
	private String running = null;
	
	public void addTask(String message) {
		reentrantLock.lock();
		try {
			sysWfEventQueue.offer(message);
		} finally {
			reentrantLock.unlock();
		}
	}
	
	@Override
	public void run() {
		System.out.println(">>>>>>>>>>>>>>>>>队列中事务数目：" + sysWfEventQueue.size() + ">>>>>>>>>>>>>>");
		
		while(sysWfEventQueue.size() > 0 || running != null) {
			reentrantLock.lock();
			try {
				if (running == null) {
					running = sysWfEventQueue.poll();
				}
				
				System.out.println("执行消息：" + running + ">>>>>>>>>>");
				running = null;
			} finally {
				reentrantLock.unlock();
			}
		}
	}
	
	public void execute(String message) {
		reentrantLock.lock();
		try {
			if (running == null) {
				handleTask(message);
			} else {
				this.addTask(message);
			}
		} catch (Exception e) {
		} finally {
			reentrantLock.unlock();
		}
	}
	
	public void handleTask(String message) {
		System.out.println(">>>>>>>>>>>>>开始处理任务>>>>>>>>>>>>>>");		
		ThreadPoolManager manager = ThreadPoolManager.getInstance();
		
		if (manager.isStarted()) {
			this.running = message;
			manager.submit(this);
		} else {
			System.out.println("处理任务的线程池已经被关闭.");
		}
		
		System.out.println(">>>>>>>>>>>>>结束处理任务>>>>>>>>>>>>>>");
	}
}
