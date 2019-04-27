package com.task.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 曾建森(表名:ST_SupplyFund)
 * @FileNam SupplyFund.java
 * @Date 2012-10-9
 */
public class SupplyFund implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;

	private int personId;

	private Date supplyDate;

	private float supplyFund;

	private float cardBalance;

	private int dataType;

	private String userNo;

	public SupplyFund() {
		super();
	}

	public SupplyFund(int personId, Date supplyDate, float supplyFund,
			float cardBalance, int dataType, String userNo) {
		super();
		this.personId = personId;
		this.supplyDate = supplyDate;
		this.supplyFund = supplyFund;
		this.cardBalance = cardBalance;
		this.dataType = dataType;
		this.userNo = userNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public Date getSupplyDate() {
		return supplyDate;
	}

	public void setSupplyDate(Date supplyDate) {
		this.supplyDate = supplyDate;
	}

	public float getSupplyFund() {
		return supplyFund;
	}

	public void setSupplyFund(float supplyFund) {
		this.supplyFund = supplyFund;
	}

	public float getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(float cardBalance) {
		this.cardBalance = cardBalance;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

}