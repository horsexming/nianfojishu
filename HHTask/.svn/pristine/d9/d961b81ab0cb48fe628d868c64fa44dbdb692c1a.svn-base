package com.task.entity.fin.budget;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.util.FieldMeta;

/**
 * 部门月度科目预算金额明细表（ta_fin_deptMonthBudget）
 * 
 * @author jhh
 * 
 */
public class DeptMonthBudget implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	@FieldMeta(name="科目名称")
	private String name;// 科目名称
	@FieldMeta(name="预算月份")
	private String budgetMonth;// 预算月份
	@FieldMeta(name="预算明细")
	private String budgetDetail;// 预算明细 (说明)
	@FieldMeta(name="预算金额")
	private Double accountMoney;// 预算金额
	private Double realMoney;// 实际花费金额
	private Double realMoney2;// 实际花费金额(页面显示加上采购还没有花掉的金额数量)
	@FieldMeta(name="状态")
	private String status;// 状态（审核,打回，通过）
	private String auditResult;// 审批结果
	private Integer epId;// 审批结果
	@FieldMeta(name="填报人姓名")
	private String username;// 填报人姓名
	@FieldMeta(name="填报人部门")
	private String userDept;// 填报人部门

	private String subTime;// 提交时间
	@FieldMeta(name="审核时间")
	private String auditTime;// 审核时间

	private String more;// 备注
	private String budgetStyle;// 填报类型（预算，临时）
	private String linshiFile;// 临时填报文件地址

	private String jhStatus;// 计划状态(nei/wai)
	private String borcode;// 条码
	private Integer rootId;// 每月总id

	private SubMonthMoney subMonthMoney;// 预算总额

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBudgetMonth() {
		return budgetMonth;
	}

	public void setBudgetMonth(String budgetMonth) {
		this.budgetMonth = budgetMonth;
	}

	public String getBudgetDetail() {
		return budgetDetail;
	}

	public void setBudgetDetail(String budgetDetail) {
		this.budgetDetail = budgetDetail;
	}

	public Double getAccountMoney() {
		return accountMoney;
	}

	public void setAccountMoney(Double accountMoney) {
		this.accountMoney = accountMoney;
	}

	public Double getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(Double realMoney) {
		this.realMoney = realMoney;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	public String getSubTime() {
		return subTime;
	}

	public void setSubTime(String subTime) {
		this.subTime = subTime;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public String getBudgetStyle() {
		return budgetStyle;
	}

	public void setBudgetStyle(String budgetStyle) {
		this.budgetStyle = budgetStyle;
	}

	public String getLinshiFile() {
		return linshiFile;
	}

	public void setLinshiFile(String linshiFile) {
		this.linshiFile = linshiFile;
	}

	public String getJhStatus() {
		return jhStatus;
	}

	public void setJhStatus(String jhStatus) {
		this.jhStatus = jhStatus;
	}

	public String getBorcode() {
		return borcode;
	}

	public void setBorcode(String borcode) {
		this.borcode = borcode;
	}
	@JSONField(serialize = false)
	public SubMonthMoney getSubMonthMoney() {
		return subMonthMoney;
	}

	public void setSubMonthMoney(SubMonthMoney subMonthMoney) {
		this.subMonthMoney = subMonthMoney;
	}

	public Integer getRootId() {
		return rootId;
	}

	public void setRootId(Integer rootId) {
		this.rootId = rootId;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public Double getRealMoney2() {
		return realMoney2;
	}

	public void setRealMoney2(Double realMoney2) {
		this.realMoney2 = realMoney2;
	}
	
}
