package com.task.entity;

import java.util.Date;

public class VOStore implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String matetag;
	private String number;
	private Float num;
	private String unit;
	private String format;
	private String parClass;
	private String storehouse;
	private String place;
	private Date date;
	private String dept;
	private String processPieceNum;
	private String cardNum;
	private String peopleName;

	private Integer carePeriod;
	private String duizhang;
	private String appDept;

	private String formUrl;

	private String startTime;
	private String endTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatetag() {
		return matetag;
	}

	public void setMatetag(String matetag) {
		this.matetag = matetag;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Float getNum() {
		return num;
	}

	public void setNum(Float num) {
		this.num = num;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getParClass() {
		return parClass;
	}

	public void setParClass(String parClass) {
		this.parClass = parClass;
	}

	public String getStorehouse() {
		return storehouse;
	}

	public void setStorehouse(String storehouse) {
		this.storehouse = storehouse;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getPeopleName() {
		return peopleName;
	}

	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Integer getCarePeriod() {
		return carePeriod;
	}

	public void setCarePeriod(Integer carePeriod) {
		this.carePeriod = carePeriod;
	}

	public String getDuizhang() {
		return duizhang;
	}

	public void setDuizhang(String duizhang) {
		this.duizhang = duizhang;
	}

	public String getAppDept() {
		return appDept;
	}

	public void setAppDept(String appDept) {
		this.appDept = appDept;
	}

	public String getProcessPieceNum() {
		return processPieceNum;
	}

	public void setProcessPieceNum(String processPieceNum) {
		this.processPieceNum = processPieceNum;
	}

	public String getFormUrl() {
		return formUrl;
	}

	public void setFormUrl(String formUrl) {
		this.formUrl = formUrl;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
