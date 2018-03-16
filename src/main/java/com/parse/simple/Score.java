package com.parse.simple;

import java.util.HashMap;
import java.util.Map;

import com.parse.util.BetwixtUtil;

public class Score {
	private static Map<String,BetwixtUtil> brm = new HashMap<String,BetwixtUtil>();
	
	private int mathScore;

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
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
