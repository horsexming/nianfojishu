package com.task.entity;

import java.io.Serializable;

/**
 * @Title: BusinessSubsidiary.java
 * @Package com.task.entity
 * @Description: TODO 业务明细
 * @author 曾建森
 * @date 2012-10-31 下午01:38:57
 * @version V1.0
 */
public class BusinessSubsidiary implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;

	private int sellId;

	private int businessId;

	public BusinessSubsidiary() {
		super();
	}

	public BusinessSubsidiary(int sellId, int businessId) {
		super();
		this.sellId = sellId;
		this.businessId = businessId;
	}

	public int getId() {
		return id;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public void setId(int id) {
		this.id = id;
	}
}
