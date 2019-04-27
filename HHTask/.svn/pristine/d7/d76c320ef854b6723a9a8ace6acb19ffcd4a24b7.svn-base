package com.task.entity.kvp;

import java.io.Serializable;

import com.task.util.FieldMeta;

/***
 * 项目执行表：ta_ExecuteKVP
 * 
 * @author 毛小龙
 *
 */
public class ExecuteKVP implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	@FieldMeta(name = "项目执行编号")
	private String executeNumber;// 项目执行编号
	// 改进部门
	private String improve_deptNum;// 部门编号
	private String improve_username;// 责任员工
	private String improve_usercode;// 员工编号
	// 责任部门
	private String res_deptNum;// 部门编号
	private String res_username;// 责任员工
	private String res_usercode;// 员工编号
	// 成本分析
	private Float materialcosts;// 单位材料成本(改进前)
	private Float laborcosts;// 单位人工成本(改进前)
	private Float materialcosts1;// 单位材料成本(改进后)
	private Float laborcosts1;// 单位人工成本(改进后)
	@FieldMeta(name = "成本结余")
	private Double costsavings;// 成本结余
	private Double bxMoney;// 报销金额
	private KVPAssess kvpAssess;// 关联主表
	private EightBReport eightBReport;// 与8B报表一对一
	private Integer epId;// 审批流程
	private String status;// 审批状态
	@FieldMeta(name = "执行时间")
	private String executeDate;// 执行时间
	private String improved_beforeproblems;// 改进前的问题
	private String improved_endproblems;// 改进后的问题

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExecuteNumber() {
		return executeNumber;
	}

	public void setExecuteNumber(String executeNumber) {
		this.executeNumber = executeNumber;
	}

	public String getImprove_deptNum() {
		return improve_deptNum;
	}

	public void setImprove_deptNum(String improveDeptNum) {
		improve_deptNum = improveDeptNum;
	}

	public String getImprove_username() {
		return improve_username;
	}

	public void setImprove_username(String improveUsername) {
		improve_username = improveUsername;
	}

	public String getImprove_usercode() {
		return improve_usercode;
	}

	public void setImprove_usercode(String improveUsercode) {
		improve_usercode = improveUsercode;
	}

	public String getRes_deptNum() {
		return res_deptNum;
	}

	public void setRes_deptNum(String resDeptNum) {
		res_deptNum = resDeptNum;
	}

	public String getRes_username() {
		return res_username;
	}

	public void setRes_username(String resUsername) {
		res_username = resUsername;
	}

	public String getRes_usercode() {
		return res_usercode;
	}

	public void setRes_usercode(String resUsercode) {
		res_usercode = resUsercode;
	}

	public Float getMaterialcosts() {
		return materialcosts;
	}

	public void setMaterialcosts(Float materialcosts) {
		this.materialcosts = materialcosts;
	}

	public Float getLaborcosts() {
		return laborcosts;
	}

	public void setLaborcosts(Float laborcosts) {
		this.laborcosts = laborcosts;
	}

	public Double getCostsavings() {
		return costsavings;
	}

	public void setCostsavings(Double costsavings) {
		this.costsavings = costsavings;
	}

	public KVPAssess getKvpAssess() {
		return kvpAssess;
	}

	public void setKvpAssess(KVPAssess kvpAssess) {
		this.kvpAssess = kvpAssess;
	}

	public Float getMaterialcosts1() {
		return materialcosts1;
	}

	public void setMaterialcosts1(Float materialcosts1) {
		this.materialcosts1 = materialcosts1;
	}

	public Float getLaborcosts1() {
		return laborcosts1;
	}

	public void setLaborcosts1(Float laborcosts1) {
		this.laborcosts1 = laborcosts1;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImproved_beforeproblems() {
		return improved_beforeproblems;
	}

	public void setImproved_beforeproblems(String improvedBeforeproblems) {
		improved_beforeproblems = improvedBeforeproblems;
	}

	public String getImproved_endproblems() {
		return improved_endproblems;
	}

	public void setImproved_endproblems(String improvedEndproblems) {
		improved_endproblems = improvedEndproblems;
	}

	public String getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(String executeDate) {
		this.executeDate = executeDate;
	}

	public EightBReport getEightBReport() {
		return eightBReport;
	}

	public void setEightBReport(EightBReport eightBReport) {
		this.eightBReport = eightBReport;
	}

	public Double getBxMoney() {
		return bxMoney;
	}

	public void setBxMoney(Double bxMoney) {
		this.bxMoney = bxMoney;
	}

}
