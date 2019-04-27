package com.task.entity.android;

import java.io.Serializable;
import java.util.Set;

/****
 * 巡检表  表名:ta_m_InsRecord
 * @author jhh
 * 
 */
public class InsRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String czg;// 操作工
	private String jcpc;// 检查批次
	private String quantity;// 本批数量

	private String nextDate;// 下次开始时间
	private String nowDate;// 本次检查时间
	private String groupDate;// 分组用

	private String verification;// 是否合格
	private Set<InsRecordScope> recordScope;
	private InsTemplate root;
	private String username;

	private String dateCount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCzg() {
		return czg;
	}

	public void setCzg(String czg) {
		this.czg = czg;
	}

	public String getJcpc() {
		return jcpc;
	}

	public void setJcpc(String jcpc) {
		this.jcpc = jcpc;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public InsTemplate getRoot() {
		return root;
	}

	public void setRoot(InsTemplate root) {
		this.root = root;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNextDate() {
		return nextDate;
	}

	public void setNextDate(String nextDate) {
		this.nextDate = nextDate;
	}

	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}

	public Set<InsRecordScope> getRecordScope() {
		return recordScope;
	}

	public void setRecordScope(Set<InsRecordScope> recordScope) {
		this.recordScope = recordScope;
	}

	public String getGroupDate() {
		return groupDate;
	}

	public void setGroupDate(String groupDate) {
		this.groupDate = groupDate;
	}

	public String getDateCount() {
		return dateCount;
	}

	public void setDateCount(String dateCount) {
		this.dateCount = dateCount;
	}

}