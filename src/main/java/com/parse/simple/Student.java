package com.parse.simple;

import java.util.HashMap;
import java.util.Map;
import com.parse.util.BetwixtUtil;

public class Student {
	private static Map<String,BetwixtUtil> brm = new HashMap<String,BetwixtUtil>();
	
	private String name;

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
