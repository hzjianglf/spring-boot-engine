package com.parse.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.parse.util.BetwixtUtil;

public class ProcessDetail {
	private static Map<String,BetwixtUtil> brm = new HashMap<String,BetwixtUtil>();
	
	private int nodesIndex;
	private int linesIndex;
	private String recalculateHandler;
	private String rejectReturn;
	private String notifyType;
	private String notifyOnFinish;
	private String notifyDraftOnFinish;
	private String dayOfNotifyPrivileger;
	
	protected List<NodeDetail> nodes = new ArrayList<NodeDetail>();
	protected List<LineDetail> lines = new ArrayList<LineDetail>();
	
	public int getNodesIndex() {
		return nodesIndex;
	}
	
	public void setNodesIndex(int nodesIndex) {
		this.nodesIndex = nodesIndex;
	}
	
	public int getLinesIndex() {
		return linesIndex;
	}
	
	public void setLinesIndex(int linesIndex) {
		this.linesIndex = linesIndex;
	}
	
	public String getRecalculateHandler() {
		return recalculateHandler;
	}
	
	public void setRecalculateHandler(String recalculateHandler) {
		this.recalculateHandler = recalculateHandler;
	}
	
	public String getRejectReturn() {
		return rejectReturn;
	}
	
	public void setRejectReturn(String rejectReturn) {
		this.rejectReturn = rejectReturn;
	}
	
	public String getNotifyType() {
		return notifyType;
	}
	
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}
	
	public String getNotifyOnFinish() {
		return notifyOnFinish;
	}
	
	public void setNotifyOnFinish(String notifyOnFinish) {
		this.notifyOnFinish = notifyOnFinish;
	}
	
	public String getNotifyDraftOnFinish() {
		return notifyDraftOnFinish;
	}
	
	public void setNotifyDraftOnFinish(String notifyDraftOnFinish) {
		this.notifyDraftOnFinish = notifyDraftOnFinish;
	}
	
	public String getDayOfNotifyPrivileger() {
		return dayOfNotifyPrivileger;
	}
	
	public void setDayOfNotifyPrivileger(String dayOfNotifyPrivileger) {
		this.dayOfNotifyPrivileger = dayOfNotifyPrivileger;
	}
	
	public List<NodeDetail> getNodes() {
		return nodes;
	}
	
	public void setNodes(List<NodeDetail> nodes) {
		this.nodes = nodes;
	}
	
	public List<LineDetail> getLines() {
		return lines;
	}
	
	public void setLines(List<LineDetail> lines) {
		this.lines = lines;
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
