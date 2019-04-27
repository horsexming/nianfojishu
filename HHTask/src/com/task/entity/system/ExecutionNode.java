package com.task.entity.system;

import java.io.Serializable;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

/***
 * 审批节点(表名:ta_sys_ExecutionNode)
 * 
 * @author 刘培
 * 
 */
public class ExecutionNode implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;// 主键
	private Integer auditLevel;// 审批等级(1/2/3/4)
	private String auditUserName;// 审批人姓名
	private String auditUserDept;// 审批人部门
	private Integer auditUserId;// 审批人id
	private String auditStatus;// 审核状态(未审批/同意/打回)
	private String auditOpinion;// 审批意见
	private String auditOption;//审批意见1
	private String startDateTime;// 开始时间
	private String auditDateTime;// 审核时间
	private Float timeLong;// 审核时长（小时）
	private CircuitRun circuitRun;// 所属流程(多对一)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuditLevel() {
		return auditLevel;
	}

	public void setAuditLevel(Integer auditLevel) {
		this.auditLevel = auditLevel;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public String getAuditUserDept() {
		return auditUserDept;
	}

	public void setAuditUserDept(String auditUserDept) {
		this.auditUserDept = auditUserDept;
	}

	public Integer getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	public String getAuditDateTime() {
		return auditDateTime;
	}

	public void setAuditDateTime(String auditDateTime) {
		this.auditDateTime = auditDateTime;
	}
	@JSONField(serialize = false)
	public CircuitRun getCircuitRun() {
		return circuitRun;
	}

	public void setCircuitRun(CircuitRun circuitRun) {
		this.circuitRun = circuitRun;
	}

	public String getAuditOption() {
		return auditOption;
	}

	public void setAuditOption(String auditOption) {
		this.auditOption = auditOption;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Float getTimeLong() {
		return timeLong;
	}

	public void setTimeLong(Float timeLong) {
		this.timeLong = timeLong;
	}

}