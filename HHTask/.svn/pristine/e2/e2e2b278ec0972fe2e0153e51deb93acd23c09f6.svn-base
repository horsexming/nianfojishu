package com.task.entity.menjin;

import java.io.Serializable;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.util.FieldMeta;

/**
 * 卷帘门申请表
 * 
 * @author YYHB 2017-2-8 表名ta_jlm_jlmapplication 卷帘门申请表
 */
public class JLMApplication implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;// 申请表ID
	private String proposer_id; // 申请人id
	@FieldMeta(name = "申请人姓名")
	private String proposer_name;// 申请人姓名
	@FieldMeta(name = "申请人部门")
	private String proposer_dept;// 申请人部门
	@FieldMeta(name = "申请原因")
	private String reason;// 申请原因
	@FieldMeta(name = "添加时间")
	private String addTime;// 添加时间
	@FieldMeta(name = "门的名称")
	private String doorName;// 门的名称
	@FieldMeta(name = "送货单号")
	private String planNumber;// 送货单号
	private Integer epId;// 审批流程id
	private String status;// 审批状态
	private Set<Operation> operation;

	@JSONField(serialize = false)
	public Set<Operation> getOperation() {
		return operation;
	}

	public String getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}

	public void setOperation(Set<Operation> operation) {
		this.operation = operation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProposer_id() {
		return proposer_id;
	}

	public void setProposer_id(String proposerId) {
		proposer_id = proposerId;
	}

	public String getProposer_name() {
		return proposer_name;
	}

	public void setProposer_name(String proposerName) {
		proposer_name = proposerName;
	}

	public String getProposer_dept() {
		return proposer_dept;
	}

	public void setProposer_dept(String proposerDept) {
		proposer_dept = proposerDept;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getDoorName() {
		return doorName;
	}

	public void setDoorName(String doorName) {
		this.doorName = doorName;
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

}
