package com.task.entity.sop;

import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/***
 * 每日不合格统计(表名:ta_sop_FailureSSOnDay)
 * 
 * @author 刘培
 * 
 */
public class FailureSSOnDay implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id;
	private String type;//类型(外购、自制、外委)
	private Float oneDaySc;// 日检验总数(oneWeekSubmitCount)
	private Float oneDayFc;// 日提交不合格数量(oneWeekFailureCount)
	private Float frequency;// 发生频率
	private String weekds;// 周(yyyy年xx周)
	private String addTime;// 添加时间(yyyy-MM-dd HH:mm:ss)
	private String days;//天（yyyy年MM月dd日）
	private String months;//月份（yyyy年MM月）
	private String gongwei;//工位
//	private String bhgtype;//不合格类型
//	private String bhgcode;//缺陷代码；

	private Set<FailureStatisticsDetail> fsdSet;// 不合格品明细
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Float getFrequency() {
		return frequency;
	}

	public void setFrequency(Float frequency) {
		this.frequency = frequency;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	

	public Float getOneDaySc() {
		return oneDaySc;
	}

	public void setOneDaySc(Float oneDaySc) {
		this.oneDaySc = oneDaySc;
	}

	public Float getOneDayFc() {
		return oneDayFc;
	}

	public void setOneDayFc(Float oneDayFc) {
		this.oneDayFc = oneDayFc;
	}

	public String getWeekds() {
		return weekds;
	}

	public void setWeekds(String weekds) {
		this.weekds = weekds;
	}

	public String getGongwei() {
		return gongwei;
	}

	public void setGongwei(String gongwei) {
		this.gongwei = gongwei;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

//	public String getBhgtype() {
//		return bhgtype;
//	}
//
//	public void setBhgtype(String bhgtype) {
//		this.bhgtype = bhgtype;
//	}
//
//	public String getBhgcode() {
//		return bhgcode;
//	}
//
//	public void setBhgcode(String bhgcode) {
//		this.bhgcode = bhgcode;
//	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}
	@JSONField(serialize = false)
	public Set<FailureStatisticsDetail> getFsdSet() {
		return fsdSet;
	}

	public void setFsdSet(Set<FailureStatisticsDetail> fsdSet) {
		this.fsdSet = fsdSet;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
 
}
