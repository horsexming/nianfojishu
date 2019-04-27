package com.task.entity;

import java.io.Serializable;

public class PurchasePackage  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String month;
	private float quantity;
	private String detailNumber;
	private String explanation;

	public PurchasePackage(String month, float quantity, String detailNumber,String explanation) {
		super();
		this.month = month;
		this.quantity = quantity;
		this.detailNumber = detailNumber;
		this.explanation = explanation;
	}

	public PurchasePackage() {
		super();
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

}
