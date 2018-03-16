package com.parse.task;

public abstract class AbstractTaskThread extends Thread {
	private boolean status = false;
	
	public AbstractTaskThread(String name) {
		super(name);
		this.setDaemon(true);
	}

	@Override
	public synchronized void start() {
		this.status = true;
		super.start();
	}

	@Override
	public void run() {
		while (status) {
			this.doRun();
		}
	}
	
	public void close() {
		this.status = false;
		this.interrupt();
	}
	
	public abstract void doRun(); 
}
