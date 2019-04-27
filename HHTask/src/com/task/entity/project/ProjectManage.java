package com.task.entity.project;

import java.util.Set;

import sun.print.resources.serviceui;

import com.alibaba.fastjson.annotation.JSONField;
import com.opensymphony.xwork2.inject.util.Strings;
import com.task.util.FieldMeta;

/***
 * 项目立项管理
 * 
 * @表名 ta_pro_ProjectManage
 * 
 * @author 刘培
 * 
 */
public class ProjectManage implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id;
	@FieldMeta(name = "项目名称", order = 1)
	private String projectName;// 项目名称
	@FieldMeta(name = "项目编号", order = 2)
	private String projectNum;// 项目编号
	private String projectNumber;//项目编码、、五位数一直递增
	private String company;// 公司名称
	@FieldMeta(name = "客户", order = 4)
	private String client;// 客户
	@FieldMeta(name = "预估金额", order = 5)
	private Float yuMoney;// 预估金额
	@FieldMeta(name = "项目内容", order = 6)
	private String content;// 项目内容

	private Integer userId;// 填报人id
	private String code;// 填报人工号
	@FieldMeta(name = "填报人姓名", order = 7)
	private String userName;// 填报人姓名
	@FieldMeta(name = "添加时间", order = 8)
	private String dateTime;// 添加时间
	private String aduitStatus;// 审核状态(未审核、审核中、同意)
	private String status;// 状态(立项、核算、跟踪)
	private String fileName;// 附件
	private String isbaomi;//是否保密 (是/否)
	private String proType;//项目类型
	
	private Integer epId;// 审批id

	private Set<ProjectTime> projectTimeSet;// 项目运行时间表
	private Set<ProjectWenJian> projectWenJianSet;//项目文件

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Float getYuMoney() {
		return yuMoney;
	}

	public void setYuMoney(Float yuMoney) {
		this.yuMoney = yuMoney;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getAduitStatus() {
		return aduitStatus;
	}

	public void setAduitStatus(String aduitStatus) {
		this.aduitStatus = aduitStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	@JSONField(serialize = false)
	public Set<ProjectTime> getProjectTimeSet() {
		return projectTimeSet;
	}

	public void setProjectTimeSet(Set<ProjectTime> projectTimeSet) {
		this.projectTimeSet = projectTimeSet;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getIsbaomi() {
		return isbaomi;
	}
	

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public void setIsbaomi(String isbaomi) {
		this.isbaomi = isbaomi;
	}

	public Set<ProjectWenJian> getProjectWenJianSet() {
		return projectWenJianSet;
	}

	public void setProjectWenJianSet(Set<ProjectWenJian> projectWenJianSet) {
		this.projectWenJianSet = projectWenJianSet;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

}
