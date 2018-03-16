package com.parse.engine;

import java.util.Map;
import org.springframework.context.ApplicationEvent;

public class EngineEvent extends ApplicationEvent {
	private static final long serialVersionUID = 1L;
	private Map<String,String> source;
	
	public EngineEvent(Map<String,String> source) {
		super(source);
		this.source = source;
	}

	public Map<String, String> getSource() {
		return source;
	}

	public void setSource(Map<String, String> source) {
		this.source = source;
	}
}
