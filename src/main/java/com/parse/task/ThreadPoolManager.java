package com.parse.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadPoolManager {
	private static final int THREAD_POOL_SIZE = 1;
	private static ThreadPoolManager threadPoolManager = null;
	private ExecutorService executorService = null;
	
	private ThreadPoolManager() {
	}
	
	public synchronized static ThreadPoolManager getInstance() {
		if (threadPoolManager == null) {
			threadPoolManager = new ThreadPoolManager();
			threadPoolManager.start();
		}
		
		return threadPoolManager;
	}
	
	public synchronized void start() {
		if (executorService != null) {
			return;
		}
		
		ThreadFactory factory = new ThreadPoolFactory("cluster_thread");
		switch (THREAD_POOL_SIZE) {
		//单个线程
		case 1:
			executorService = Executors.newFixedThreadPool(1,factory);
			break;
		//无限制线程
		case Integer.MAX_VALUE:
			executorService = Executors.newCachedThreadPool(factory);
			break;
		//指定线程数
		default:
			executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE,factory);
			break;
		}
	}
	
	public void submit(Runnable task) {
		if (!isStarted()) {
			throw new RuntimeException("the ThreadPool has not started or has been shutdown.");
		}
		executorService.submit(task);
	}
	
	public synchronized void shutdown() {
		if (executorService == null) {
			return;
		}
		
		executorService.shutdown();
		executorService = null;
		threadPoolManager = null;
	}
	
	public boolean isStarted() {
		return executorService != null && !executorService.isShutdown();
	}
}
