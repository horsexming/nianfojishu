package com.task.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.task.Server.TconsumptionService;
import com.task.entity.Tconsumption;

/**
 * 消耗统计
 * @author 马凯
 *
 */
public class TconsumptionAction extends ActionSupport {
	private List<Tconsumption> consumptions;
	private TconsumptionService tconsumptionService;
	public String add(){
		tconsumptionService.addAll(consumptions);
		return "addOk";
	}
	public List<Tconsumption> getConsumptions() {
		return consumptions;
	}
	public void setConsumptions(List<Tconsumption> consumptions) {
		this.consumptions = consumptions;
	}
	public TconsumptionService getTconsumptionService() {
		return tconsumptionService;
	}
	public void setTconsumptionService(TconsumptionService tconsumptionService) {
		this.tconsumptionService = tconsumptionService;
	}

}
