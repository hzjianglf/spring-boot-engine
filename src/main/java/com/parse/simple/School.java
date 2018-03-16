package com.parse.simple;

import java.util.HashMap;
import java.util.Map;

import com.parse.util.BetwixtUtil;

public class School {
	private static Map<String,BetwixtUtil> brm = new HashMap<String,BetwixtUtil>();
	
	private int id;
    private String name;
	
    public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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
