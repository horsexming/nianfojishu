package com.task.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 索赔单
 * 
 * @author 马凯
 * 
 */
public class Tclaimform implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String ourPerson;// 已方负责人
	private String otherPerson;// 对方负责人
	private String otherPhone;// 对方手机号
	private String otherCompany;// 对方公司名
	private double claimAmount;// 索赔金额
	private String regDate;// 登记时间
	private String status;// 状态
	private String tclaimNumber;//索赔单号
	private Set<TclaimsRecord> records = new HashSet<TclaimsRecord>();// 索赔记录
	private String username;//谁添加的这个索赔单

	private String notification;//通知人
	private String notificationDate;//通知时间

	private String debit;//扣款人
	private String debitDate;//扣款时间
	private double debitAmount = 0.0;//扣款金额

	public String getOurPerson() {
		return ourPerson;
	}

	public void setOurPerson(String ourPerson) {
		this.ourPerson = ourPerson;
	}

	public String getOtherPerson() {
		return otherPerson;
	}

	public void setOtherPerson(String otherPerson) {
		this.otherPerson = otherPerson;
	}

	public String getOtherCompany() {
		return otherCompany;
	}

	public void setOtherCompany(String otherCompany) {
		this.otherCompany = otherCompany;
	}

	public double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<TclaimsRecord> getRecords() {
		return records;
	}

	public void setRecords(Set<TclaimsRecord> records) {
		this.records = records;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getTclaimNumber() {
		return tclaimNumber;
	}

	public void setTclaimNumber(String tclaimNumber) {
		this.tclaimNumber = tclaimNumber;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(String notificationDate) {
		this.notificationDate = notificationDate;
	}

	public String getDebit() {
		return debit;
	}

	public void setDebit(String debit) {
		this.debit = debit;
	}

	public String getDebitDate() {
		return debitDate;
	}

	public void setDebitDate(String debitDate) {
		this.debitDate = debitDate;
	}

	public double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}
}
