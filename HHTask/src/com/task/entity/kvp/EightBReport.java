package com.task.entity.kvp;

import java.io.Serializable;

import com.task.util.FieldMeta;

/***
 * 表名：ta_EightBReport
 * 
 * @author 毛小龙
 *
 */

public class EightBReport implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	@FieldMeta(name = "项目名称")
	private String projectname;// 项目名称
	@FieldMeta(name = "单位")
	private String unit;// 单位
	@FieldMeta(name = "姓名")
	private String name;// 姓名
	private String summary;// 项目概要
	private String improveoutcomes;// 改善结果
	private String problemstatement;// 问题陈述
	private String goal;// 目标
	private String factor;// 关键因素
	@FieldMeta(name = "财务结余")
	private Double savings;// 财务结余
	@FieldMeta(name = "时间")
	private String reportdate;// 8B时间
	private Integer epId;
	private String status;// 状态
	private ExecuteKVP executeKVP;// 与项目执行单一对一

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImproveoutcomes() {
		return improveoutcomes;
	}

	public void setImproveoutcomes(String improveoutcomes) {
		this.improveoutcomes = improveoutcomes;
	}

	public String getProblemstatement() {
		return problemstatement;
	}

	public void setProblemstatement(String problemstatement) {
		this.problemstatement = problemstatement;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}

	public ExecuteKVP getExecuteKVP() {
		return executeKVP;
	}

	public void setExecuteKVP(ExecuteKVP executeKVP) {
		this.executeKVP = executeKVP;
	}

	public String getReportdate() {
		return reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public Double getSavings() {
		return savings;
	}

	public void setSavings(Double savings) {
		this.savings = savings;
	}

}
