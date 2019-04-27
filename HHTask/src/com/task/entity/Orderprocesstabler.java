package com.task.entity;

import java.io.Serializable;

/**
 * (CRM)订单流程表
 * @author 钟永林
 */
public class Orderprocesstabler  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;	//序号	int
	private String ordertype;	//订单类型	Varchar
	private String orderprocess;	//订单流程	Varchar
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public String getOrderprocess() {
		return orderprocess;
	}
	public void setOrderprocess(String orderprocess) {
		this.orderprocess = orderprocess;
	}

	
}
