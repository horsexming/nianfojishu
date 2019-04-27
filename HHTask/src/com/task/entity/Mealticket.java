package com.task.entity;

public class Mealticket implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private int id;
	private String name;
	private String job;
	private String company;
	private String reason;
	private String barcode; // 条形码
	private String manage; // 申请人
	private int number; // 人数
	private String addDate;
	private String fuck;//审核状态
	private String checkname;//审核人
	private String intime;//有效日期
	private Integer copy;//打印
	private String code;//当前用户的登录名

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getManage() {
		return manage;
	}

	public void setManage(String manage) {
		this.manage = manage;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getAddDate() {
		return addDate;
	}

	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}

	
	public String getCheckname() {
		return checkname;
	}

	public void setCheckname(String checkname) {
		this.checkname = checkname;
	}

	public String getFuck() {
		return fuck;
	}

	public void setFuck(String fuck) {
		this.fuck = fuck;
	}

	public String getIntime() {
		return intime;
	}

	public void setIntime(String intime) {
		this.intime = intime;
	}

	public Integer getCopy() {
		return copy;
	}

	public void setCopy(Integer copy) {
		this.copy = copy;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
