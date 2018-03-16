package com.parse.util;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolExecutorConfig {
	private int corePoolSize = 5;  
	private int maxPoolSize = 5; 
	private int queueCapacity = 10; 
	private String ThreadNamePrefix = "TaskExecutor-"; 
	 
	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor taskPool = new ThreadPoolTaskExecutor();
		taskPool.setCorePoolSize(corePoolSize);  
		taskPool.setMaxPoolSize(maxPoolSize);  
		taskPool.setQueueCapacity(queueCapacity);  
		taskPool.setThreadNamePrefix(ThreadNamePrefix);  
        
		taskPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());  
		taskPool.initialize();  
		
        return taskPool;  
	}
}
