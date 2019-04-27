package com.task.entity.bp;

import java.io.Serializable;

public class Detail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;//ID
	private Templet templet;//采购的产品
	private String month;//月份
	private float quantity;//数量
	private String detailNumber;//计划单编号
	private String explanation;//说明
	private String purchase;//是否采购


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	public Templet getTemplet() {
		return templet;
	}
	public void setTemplet(Templet templet) {
		this.templet = templet;
	}
	public String getDetailNumber() {
		return detailNumber;
	}
	public void setDetailNumber(String detailNumber) {
		this.detailNumber = detailNumber;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getPurchase() {
		return purchase;
	}
	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}

}
