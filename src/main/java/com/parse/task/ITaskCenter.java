package com.parse.task;


public interface ITaskCenter {
	/**
	 * 远程分发消息
	 */
	public void sendRemote(String message);
	
	/**
	 * 本地分发消息
	 */
	public void sendLocal(String messge);
	
	/**
	 * 启动任务中心
	 */
	public void start();
	
	/**
	 * 关闭任务中心
	 */
	public void stop();
}
