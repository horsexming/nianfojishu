package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.TtoolingService;
import com.task.entity.Ttooling;
import com.task.util.MKUtil;

/**
 * 工装模具（项目管理）
 * @author 马凯
 *
 */
public class TtoolingAction extends ActionSupport {
	private Ttooling tooling;
	private List<Ttooling> toolings;
	private TtoolingService ttoolingService;
	
	public String addInput(){
		return "addInput";
	}
	
	public String add(){
		ttoolingService.addAll(toolings);
		return "addOk";
	}
	
	public String selector(){
		MKUtil.writeJSON(ttoolingService.selector(tooling));
		return null;
	}

	public Ttooling getTooling() {
		return tooling;
	}

	public void setTooling(Ttooling tooling) {
		this.tooling = tooling;
	}

	public List<Ttooling> getToolings() {
		return toolings;
	}

	public void setToolings(List<Ttooling> toolings) {
		this.toolings = toolings;
	}

	public TtoolingService getTtoolingService() {
		return ttoolingService;
	}

	public void setTtoolingService(TtoolingService ttoolingService) {
		this.ttoolingService = ttoolingService;
	}

}
