package com.task.entity.system;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.DeptNumber;

/***
 * 流程定制(表名:ta_sys_CircuitCustomize)
 * 
 * @author 刘培
 * 
 */
public class CircuitCustomize implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	private String name;// 流程名称
	private String more;// 备注(如XXX功能的审批流程)
	private String auditType;// 审批类型(oneBack/lastBack/oneAudit即一个打回就重新开始或者最终打回重新开始)
	private String addUserName;// 添加人
	private String addDateTime;// 添加时间
	private String isVerification;//是否验证
	private String isopinion;//是否必填审批意见;
	private String isspoption;//是否增加审批选项;
	private String dept;//部门（不存入数据；传值使用）
	private String danxuanorduoxuan;//单选或者多选
	private Set<Option> setoption;//(一对多)审批意见选项
	private List<Option> listoption;//页面传值
	private Set<AuditNode> auditNodeSet;// 审批节点
	private Set<DeptNumber> deptset;//部门表 (多对多关系)

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

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}

	public String getAddUserName() {
		return addUserName;
	}

	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}

	public String getAddDateTime() {
		return addDateTime;
	}

	public void setAddDateTime(String addDateTime) {
		this.addDateTime = addDateTime;
	}

	public Set<AuditNode> getAuditNodeSet() {
		return auditNodeSet;
	}

	public void setAuditNodeSet(Set<AuditNode> auditNodeSet) {
		this.auditNodeSet = auditNodeSet;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public String getIsVerification() {
		return isVerification;
	}

	public void setIsVerification(String isVerification) {
		this.isVerification = isVerification;
	}
	
	@JSONField(serialize = false)
	public Set<DeptNumber> getDeptset() {
		return deptset;
	}

	public void setDeptset(Set<DeptNumber> deptset) {
		this.deptset = deptset;
	}

	public String getIsopinion() {
		return isopinion;
	}

	public void setIsopinion(String isopinion) {
		this.isopinion = isopinion;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getIsspoption() {
		return isspoption;
	}

	public void setIsspoption(String isspoption) {
		this.isspoption = isspoption;
	}

	public String getDanxuanorduoxuan() {
		return danxuanorduoxuan;
	}

	public void setDanxuanorduoxuan(String danxuanorduoxuan) {
		this.danxuanorduoxuan = danxuanorduoxuan;
	}
	@JSONField(serialize = false)
	public Set<Option> getSetoption() {
		return setoption;
	}

	public void setSetoption(Set<Option> setoption) {
		this.setoption = setoption;
	}

	public List<Option> getListoption() {
		return listoption;
	}

	public void setListoption(List<Option> listoption) {
		this.listoption = listoption;
	}
	
}
