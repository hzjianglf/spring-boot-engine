package com.parse.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.parse.StartApplication;
import com.parse.engine.FlowEngine;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StartApplication.class)
public class EngineTest {
	@Autowired
	private FlowEngine flowEngine;
	
	@Test
	public void testEngine() {
		while (true) {
			Map<String,String> params = new HashMap<String,String>();
			params.put("type","ACTION");
			params.put("node","N1");
		
			flowEngine.doAction(params);
		}
	}
}
