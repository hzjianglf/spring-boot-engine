package com.parse.node;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class NodeServiceImpl implements NodeService {
	public static Map<String,String> flowChart = new HashMap<String,String>();
	
	public NodeServiceImpl() {
		flowChart.put("N1","N2");
		flowChart.put("N2","N4");
		flowChart.put("N4","N5");
		flowChart.put("N5","N6");
		flowChart.put("N6","N3");
	}

	@Override
	public String doAction(String node) {
		String value = flowChart.get(node);
		
		System.out.println(">>>>>>>>>>" + node + "<>" + value + ">>>>>>>>>");
		
		return value;
	}
}
