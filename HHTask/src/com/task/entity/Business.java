package com.task.entity;

import java.io.Serializable;
import java.util.Set;

/**
 * @Title: Business.java
 * @Package com.task.entity
 * @Description: TODO 业务实体类
 * @author 曾建森
 * @date 2012-10-29 上午09:53:20
 * @version V1.0
 */
public class Business implements Serializable {
	private static final long serialVersionUID = 1L;
	/** ID */
	private int id;
	/** 业务类型 */
	private String type;
	/** 业务内容 */
	private String content;
	/** 费用金额 */
	private float money;
	/** 收款单位 */
	private String collectionUnit;
	/** 发票号 */
	private Set<Invoice> invoices;
	/** 付款依据 */
	private String paymentBasis;
	/** 办理人 */
	private String transactor;
	/** 办理时间 */
	private String time;
	/** 货币类型 */
	private String currencyType;
	/** 审核状态 */
	private String status;
	/** 审核流程 */
	private String flow;
	/** 部门名字 */
	private String dept;
	/** 付款凭证 */
	private PrintProof pp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public String getCollectionUnit() {
		return collectionUnit;
	}

	public void setCollectionUnit(String collectionUnit) {
		this.collectionUnit = collectionUnit;
	}

	public Set<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

	public String getPaymentBasis() {
		return paymentBasis;
	}

	public void setPaymentBasis(String paymentBasis) {
		this.paymentBasis = paymentBasis;
	}

	public String getTransactor() {
		return transactor;
	}

	public void setTransactor(String transactor) {
		this.transactor = transactor;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public PrintProof getPp() {
		return pp;
	}

	public void setPp(PrintProof pp) {
		this.pp = pp;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
}