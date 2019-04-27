package com.task.entity.peb;

import java.io.Serializable;


/**
 * 产品下线（生产效率简报）
 * ta_pebProduction
 * @author wcy
 *
 */
public class PebProduction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String banzu;
	private Integer year;//年份
	private Integer month;//月份
	private Integer day;//天数（日期）
	private String markId;//产品编码
	private Integer cuNumber;//产出台数
	private String message;//异常
	private String measure;//采取措施
	private String zrComp;//责任单位
	private String zrCompMeasure;//责任单位措施
	private Float xiShu;//系数
	private Float zsNumber;//折算台数（数量）
	private String remarks;//备注
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBanzu() {
		return banzu;
	}
	public void setBanzu(String banzu) {
		this.banzu = banzu;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getZrComp() {
		return zrComp;
	}
	public void setZrComp(String zrComp) {
		this.zrComp = zrComp;
	}
	public String getZrCompMeasure() {
		return zrCompMeasure;
	}
	public void setZrCompMeasure(String zrCompMeasure) {
		this.zrCompMeasure = zrCompMeasure;
	}
	public Float getXiShu() {
		return xiShu;
	}
	public void setXiShu(Float xiShu) {
		this.xiShu = xiShu;
	}
	public Float getZsNumber() {
		return zsNumber;
	}
	public void setZsNumber(Float zsNumber) {
		this.zsNumber = zsNumber;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getCuNumber() {
		return cuNumber;
	}
	public void setCuNumber(Integer cuNumber) {
		this.cuNumber = cuNumber;
	}

	
	
	
}
