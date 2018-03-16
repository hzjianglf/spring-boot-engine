package com.parse.task;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolFactory implements ThreadFactory {
	protected static final AtomicInteger poolNumber = new AtomicInteger(1);
	protected final AtomicInteger threadNumber = new AtomicInteger(1);
	protected final String namePrefix;
	
	public ThreadPoolFactory(String name) {
		namePrefix = name + "-pool-" + poolNumber.getAndIncrement() + "-thread-";
	}
	
	@Override
	public Thread newThread(Runnable r) {
		Thread thread = new Thread(r,namePrefix + threadNumber.getAndIncrement());
		if (thread.isDaemon()) {
			thread.setDaemon(false);
		}
		
		if (thread.getPriority() != Thread.NORM_PRIORITY) {
			thread.setPriority(Thread.NORM_PRIORITY);
		}
		
		return thread;
	}
}
