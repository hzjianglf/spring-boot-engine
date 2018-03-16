package com.parse.engine;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.parse.node.NodeService;

@Service
public class FlowEngineImp implements FlowEngine,ApplicationContextAware,ApplicationListener<EngineEvent> {
	private ApplicationContext applicationContext;
	
	@Autowired
	private NodeService nodeService;
	
	@Override
	public void doAction(Map<String,String> params) {
		this.commitEvent(params);
	}
	
	@Async("taskExecutor")
	private void doCreateNodeEvent(Map<String,String> params) {
		System.out.println(">>>>>" + Thread.currentThread().getName() + ">>>>>>>>>>");
		String node = params.get("node");
		this.doAction(node);
	}
	
	private void doActionEvent(Map<String,String> params) {
		String node = params.get("node");
		this.doAction(node);
	}
	
	private void doAction(String node) {
		Map<String,String> params = new HashMap<String,String>();
		String result = nodeService.doAction(node);
		if (result.equals("N3")) {
			return;
		} else {
			params.put("type","INIT");
			params.put("node",result);
			this.commitEvent(params);
		}
	}
	
	private void commitEvent(Map<String,String> params) {
		EngineEvent engineEvent = new EngineEvent(params);
		
		applicationContext.publishEvent(engineEvent);
	}

	@Override
	public void onApplicationEvent(EngineEvent source) {
		Map<String,String> params = source.getSource();
		String type = params.get("type");
		
		if ("INIT".equals(type)) {
			doCreateNodeEvent(params);
		} else {
			doActionEvent(params);
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
