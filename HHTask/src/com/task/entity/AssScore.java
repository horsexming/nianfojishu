package com.task.entity;

import java.io.Serializable;
import java.util.Set;

import com.task.entity.zgkh.ScoreStatistics;

/**
 * 考核成绩表 (表名:ta_hr_assScore)
 * 
 * @author 刘培
 */
public class AssScore  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Id id Ta_hr_cardId 卡号 Ta_hr_assScore 考核成绩 Ta_hr_assMouth 考核月份
	 * Ta_hr_assType 考核类型 Ta_hr_assPeople 考核人 Ta_hr_assProCardId 考核人卡号
	 * Ta_hr_remarks 备注
	 */

	private Integer id;
	private String cardId;// 成绩所属卡号
	private String code;// 工号
	private String userName;// 成绩所属人名称
	private String dept;// 所属部门
	private Float accScore;// 考核成绩
	private Float percentageScore;// 百分比分数
	private String rateDate;// 打分时间
	private String asstMouth;// 考核月份
	private String assType;// 考核类型(员工级/主管级)
	private String assPeople;// 打分人
	private String remarks;// 备注
	private Integer templateId;// 模板id
	private String templateName;// 模板名称

	private Integer userId;// 成绩所属人用户id
	private Integer addUserId;// 添加人userId
	private ScoreStatistics ss;// 总分表(主管级考核使用、多对一)
	
	
	private Set<ScoreDetails> scoreDetails;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Float getAccScore() {
		return accScore;
	}

	public void setAccScore(Float accScore) {
		this.accScore = accScore;
	}

	public String getRateDate() {
		return rateDate;
	}

	public void setRateDate(String rateDate) {
		this.rateDate = rateDate;
	}

	public String getAsstMouth() {
		return asstMouth;
	}

	public void setAsstMouth(String asstMouth) {
		this.asstMouth = asstMouth;
	}

	public String getAssType() {
		return assType;
	}

	public void setAssType(String assType) {
		this.assType = assType;
	}

	public String getAssPeople() {
		return assPeople;
	}

	public void setAssPeople(String assPeople) {
		this.assPeople = assPeople;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public Set<ScoreDetails> getScoreDetails() {
		return scoreDetails;
	}

	public void setScoreDetails(Set<ScoreDetails> scoreDetails) {
		this.scoreDetails = scoreDetails;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Float getPercentageScore() {
		return percentageScore;
	}

	public void setPercentageScore(Float percentageScore) {
		this.percentageScore = percentageScore;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAddUserId() {
		return addUserId;
	}

	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}

	public ScoreStatistics getSs() {
		return ss;
	}

	public void setSs(ScoreStatistics ss) {
		this.ss = ss;
	}

}
