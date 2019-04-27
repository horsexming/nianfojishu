package com.task.entity;

import java.util.Set;

import com.task.entity.zgkh.AssessPersonnel;
import com.task.util.FieldMeta;

/**
 * 考核模板表(表名:ta_hr_template)
 * 
 * @author 刘培
 */

public class Template {

	private Integer id;
	@FieldMeta(name = "模板名称", order = 1)
	private String name;// 模板名称
	@FieldMeta(name = "考核对象", order = 2)
	private String assObject;// 考核对象
	@FieldMeta(name = "考核月份", order = 3)
	private String asstMouth;// 考核月份
	@FieldMeta(name = "定制时间", order = 4)
	private String customDate;// 定制时间
	@FieldMeta(name = "定制人", order = 5)
	private String customProple;// 定制人
	private Integer customUserId;// 定制人用户id
	private String cardId;// 定制人卡号
	private String auditPeople;// 审核人
	@FieldMeta(name = "考核部门", order = 6)
	private String dept;// 考核部门
	@FieldMeta(name = "状态", order = 7)
	private String status;// 状态(添加明细、审批(通过/打回)、打分、结束)
	private String type;//考核类型(转正,)
	private String remarks;// 备注
	private Integer epId;// 审批流程Id
	private Set<TemplateDetails> templateDetails;// 模版明细
	private Set<AssessPersonnel> assessPersonnel;// 主管考核人员(多对多)

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

	public String getAssObject() {
		return assObject;
	}

	public void setAssObject(String assObject) {
		this.assObject = assObject;
	}

	public String getAsstMouth() {
		return asstMouth;
	}

	public void setAsstMouth(String asstMouth) {
		this.asstMouth = asstMouth;
	}

	public String getCustomDate() {
		return customDate;
	}

	public void setCustomDate(String customDate) {
		this.customDate = customDate;
	}

	public String getCustomProple() {
		return customProple;
	}

	public void setCustomProple(String customProple) {
		this.customProple = customProple;
	}

	public String getAuditPeople() {
		return auditPeople;
	}

	public void setAuditPeople(String auditPeople) {
		this.auditPeople = auditPeople;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Set<TemplateDetails> getTemplateDetails() {
		return templateDetails;
	}

	public void setTemplateDetails(Set<TemplateDetails> templateDetails) {
		this.templateDetails = templateDetails;
	}

	public Set<AssessPersonnel> getAssessPersonnel() {
		return assessPersonnel;
	}

	public void setAssessPersonnel(Set<AssessPersonnel> assessPersonnel) {
		this.assessPersonnel = assessPersonnel;
	}

	public Integer getCustomUserId() {
		return customUserId;
	}

	public void setCustomUserId(Integer customUserId) {
		this.customUserId = customUserId;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
