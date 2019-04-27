package com.task.entity.quality;

/**
 * TaQualityAbnormalresume entity. @author MyEclipse Persistence Tools
 */

public class QualityAbnormalresume implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String type;
	private String complaintTime;
	private String code;
	private String description;
	private String attachmentPath;
	private String scheme;
	private String closedLoop;
	private String unfoldCode;
	private String responsible;
	
	private String feedbackCo;//反馈单位
	private String responsibleCo;//责任部门
	private String trackPeople;//跟踪人
	private String typeOfProblem;//问题类型（大）
	private String inTypeOfProblem;//问题类型（小）
	
	private String analyzes;//原因分析
	// Constructors

	

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComplaintTime() {
		return this.complaintTime;
	}

	public void setComplaintTime(String complaintTime) {
		this.complaintTime = complaintTime;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAttachmentPath() {
		return this.attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String getScheme() {
		return this.scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getClosedLoop() {
		return this.closedLoop;
	}

	public void setClosedLoop(String closedLoop) {
		this.closedLoop = closedLoop;
	}

	public String getUnfoldCode() {
		return this.unfoldCode;
	}

	public void setUnfoldCode(String unfoldCode) {
		this.unfoldCode = unfoldCode;
	}

	public String getResponsible() {
		return this.responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public String getFeedbackCo() {
		return feedbackCo;
	}

	public void setFeedbackCo(String feedbackCo) {
		this.feedbackCo = feedbackCo;
	}

	public String getResponsibleCo() {
		return responsibleCo;
	}

	public void setResponsibleCo(String responsibleCo) {
		this.responsibleCo = responsibleCo;
	}

	public String getTrackPeople() {
		return trackPeople;
	}

	public void setTrackPeople(String trackPeople) {
		this.trackPeople = trackPeople;
	}

	public String getTypeOfProblem() {
		return typeOfProblem;
	}

	public void setTypeOfProblem(String typeOfProblem) {
		this.typeOfProblem = typeOfProblem;
	}

	public String getInTypeOfProblem() {
		return inTypeOfProblem;
	}

	public void setInTypeOfProblem(String inTypeOfProblem) {
		this.inTypeOfProblem = inTypeOfProblem;
	}

	public String getAnalyzes() {
		return analyzes;
	}

	public void setAnalyzes(String analyzes) {
		this.analyzes = analyzes;
	}

	
	

}