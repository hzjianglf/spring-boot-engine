package com.parse.model;

import java.util.HashMap;
import java.util.Map;
import com.parse.util.BetwixtUtil;

public class NodeDetail {
	private static Map<String,BetwixtUtil> brm = new HashMap<String,BetwixtUtil>();
	
	private String id;
	private String name;
	private String x;
	private String y;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}


	@Override
	public String toString() {
		BetwixtUtil bReader = brm.get(getClass().getName());
		if (bReader == null) {
			bReader = new BetwixtUtil();
			brm.put(getClass().getName(),bReader);
		}
		
		return bReader.write(this);
	}
}
