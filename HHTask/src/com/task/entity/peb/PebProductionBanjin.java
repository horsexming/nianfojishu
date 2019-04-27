package com.task.entity.peb;

import java.io.Serializable;


/**
 * 产品下线（生产效率简报）
 * ta_pebProduction
 * @author wcy
 *
 */
public class PebProductionBanjin implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String processName;
	private Integer year;//年份
	private Integer month;//月份
	private Integer day;//天数（日期）
	private Float cuNumber;//产出台数
	private Float xiShu;//系数
	private Float zsNumber;//折算台数（数量）
	private Float PEBSNumber;//pebs产出数量
	private Float K3Number;//k3产出数量
	private String remarks;//备注
	
	public Integer getId() {
		return id;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
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

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Float getCuNumber() {
		return cuNumber;
	}

	public void setCuNumber(Float cuNumber) {
		this.cuNumber = cuNumber;
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

	public Float getPEBSNumber() {
		return PEBSNumber;
	}

	public void setPEBSNumber(Float pEBSNumber) {
		PEBSNumber = pEBSNumber;
	}

	public Float getK3Number() {
		return K3Number;
	}

	public void setK3Number(Float k3Number) {
		K3Number = k3Number;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}