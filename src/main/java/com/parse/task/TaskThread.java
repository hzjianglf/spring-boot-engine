package com.parse.task;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class TaskThread extends AbstractTaskThread {
	private LinkedList<String> taskQueue = new LinkedList<String>();
	private ReentrantLock reentrantLock = new ReentrantLock();
	
	public TaskThread() {
		super("task_thread");
	}
	
	public void addTask(String message) {
		reentrantLock.lock();
		try {
			taskQueue.offer(message);
			System.out.println(">>>>>任务队列数目：" + taskQueue.size() + ">>>>>>>>");
		} finally {
			reentrantLock.unlock();
		}
	}

	@Override
	public void doRun() {
		while (taskQueue.size() > 0) {
			reentrantLock.lock();
			try {
				System.out.println(">>>>>任务队列数目：" + taskQueue.size() + ">>>>>>>>");
				String message = taskQueue.poll();
				if (message != null) {
					SimpleTask simpleTask = SimpleTask.getInstance();
					simpleTask.execute(message);
				}
			} catch (Exception e) {
			} finally {
				reentrantLock.unlock();
			}
		}
	}
}
