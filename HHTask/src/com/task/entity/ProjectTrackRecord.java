package com.task.entity;

import java.io.Serializable;

/**
 * 表名:ta_ProjectTrack_Record
 * @author 马凯
 *
 */
public class ProjectTrackRecord  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String startTime;//开始时间
	private String endTime;//结束时间
	private String addTime;//添加时间
	private String thing;//事件
	private String description;//描述
	private String recordType;//项目类型
	private Double budgetMoney;//预算钱数
	private Double approveMoney;//批准钱数
	private Double realMoney;//实际花费
	private String username;//申请人名字

	private Boolean checked;//是否审核
	private Boolean agree;//是否同意
	private String about;//关于   审核,不建流程表了,直接用字段了..
	
	private ProjectTrack root;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getThing() {
		return thing;
	}

	public void setThing(String thing) {
		this.thing = thing;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Boolean getAgree() {
		return agree;
	}

	public void setAgree(Boolean agree) {
		this.agree = agree;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public ProjectTrack getRoot() {
		return root;
	}

	public void setRoot(ProjectTrack root) {
		this.root = root;
	}


	public Double getBudgetMoney() {
		return budgetMoney;
	}

	public void setBudgetMoney(Double budgetMoney) {
		this.budgetMoney = budgetMoney;
	}

	public Double getApproveMoney() {
		return approveMoney;
	}

	public void setApproveMoney(Double approveMoney) {
		this.approveMoney = approveMoney;
	}

	public Double getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	
	

}
