package com.task.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 *工作日志表(表名:ta_log_workLog)
 * 
 * @author 刘培
 * 
 */
public class WorkLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String code;// 工号
	private String cardId;// 卡号
	private Integer userId;// 员工id
	private String userName;// 员工姓名
	private String zpname;//指派人姓名;
	private String zpdept;//指派人部门;
	private Integer zpuserId;//指派人Id;
	private String dept;// 部门
	private String mouth;// 月份
	private String title;// 标题
	private String content;// 内容
	private String remarks;// 备注(完成时添加)
	private String addDateTime;// 添加时间(开始时间)(yyyy-MM-dd HH:ss;mm)
	private String endDateTime;// 结束时间(yyyy-MM-dd HH:ss;mm)
	private String submitDateTime;//提交时间(yyyy-MM-dd)
	private String zptime;//规定完成日期;
	private String yjtime;//预计完成日期;
	private Integer wgcount;//违规次数；
	private String  caoshi;//超时未完成
	private String qrStatus;//确认状态;
	private String logStatus;// 状态(待办,办理中,已完成)
	private String zpStatus;//指派状态(带指派，指派，待确认，确认);
	private Integer jindu;//进度(完成百分比)
	private WorkLogClass workLogClass;// 日志类别表
	private String addZpDataTime;//添加时间(指派的添加的时间)
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getMouth() {
		return mouth;
	}

	public void setMouth(String mouth) {
		this.mouth = mouth;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAddDateTime() {
		return addDateTime;
	}

	public void setAddDateTime(String addDateTime) {
		this.addDateTime = addDateTime;
	}

	public String getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}

	public String getLogStatus() {
		return logStatus;
	}

	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}
	@JSONField(serialize = false)
	public WorkLogClass getWorkLogClass() {
		return workLogClass;
	}

	public void setWorkLogClass(WorkLogClass workLogClass) {
		this.workLogClass = workLogClass;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Integer getJindu() {
		return jindu;
	}

	public void setJindu(Integer jindu) {
		this.jindu = jindu;
	}

	public String getZpname() {
		return zpname;
	}

	public void setZpname(String zpname) {
		this.zpname = zpname;
	}

	public String getZpdept() {
		return zpdept;
	}

	public void setZpdept(String zpdept) {
		this.zpdept = zpdept;
	}

	public String getSubmitDateTime() {
		return submitDateTime;
	}

	public void setSubmitDateTime(String submitDateTime) {
		this.submitDateTime = submitDateTime;
	}

	public Integer getWgcount() {
		return wgcount;
	}

	public void setWgcount(Integer wgcount) {
		this.wgcount = wgcount;
	}


	public String getZptime() {
		return zptime;
	}

	public void setZptime(String zptime) {
		this.zptime = zptime;
	}
	public String getCaoshi() {
		return caoshi;
	}

	public void setCaoshi(String caoshi) {
		this.caoshi = caoshi;
	}

	public String getZpStatus() {
		return zpStatus;
	}

	public void setZpStatus(String zpStatus) {
		this.zpStatus = zpStatus;
	}

	public Integer getZpuserId() {
		return zpuserId;
	}

	public void setZpuserId(Integer zpuserId) {
		this.zpuserId = zpuserId;
	}

	public String getYjtime() {
		return yjtime;
	}

	public void setYjtime(String yjtime) {
		this.yjtime = yjtime;
	}

	public String getQrStatus() {
		return qrStatus;
	}

	public void setQrStatus(String qrStatus) {
		this.qrStatus = qrStatus;
	}

	public String getAddZpDataTime() {
		return addZpDataTime;
	}

	public void setAddZpDataTime(String addZpDataTime) {
		this.addZpDataTime = addZpDataTime;
	}

	
	
}
