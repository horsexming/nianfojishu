package com.task.entity.menjin.visitor;

import com.task.util.FieldMeta;

/**
 *  表名 ta_mj_visitor
 * @author WCY
 *	访客记录表
 */
public class Visitor {

	private Integer id;
	
	@FieldMeta(name = "来访人")
	private String visitorName;// 来访人
	@FieldMeta(name="来访人公司")
	private String visitorComp;//来访人公司
	@FieldMeta(name="来访人手机号")
	private String visitorPhone;//来访人手机号
	private String visitorMail;//邮件
	@FieldMeta(name="来访人数")
	private String visitorPop;//来访人数
	@FieldMeta(name="被访人")
	private String interviewee;//被访人
	private String intervieweePhone;//被访人手机号
	
	private Integer intervieweeId;//被访人Id
	@FieldMeta(name="来访缘由")
	private String visitorCause;//来访缘由
	
	private String identityCard;//来访人身份证号
	private String visitorIdentityPic;
	private String fingerprint;//来访人指纹
	private String fingerId;//指纹Id 存储到指纹库 四位整数
	private String picture;//来访人照片
	private String voucher;//凭证
	private String addTime;//添加来访时间（申请时间）
	private String dateTime;//访客时间
	private String endTime;//访客时间====结束时间
	private String inTime;//进入时间
	private String outTime;//离开时间
	private Integer epId;
	private String epStatus;
	private String visitorStatus;//来访状态(预申请、拒绝访客、待打印、待进门、待出门、已出门)
	private Integer longEpId;
	private String longEpStatus;
	private String applyPerson;
	private String visitorLongDateTime;//申请长访的时间
	
	private Integer identityId;//访客身份表Id//页面传值，不保存记录
	
	private Integer followId;//主访和随访关联
	
	private String smsLine;//短信链接
	
	private String mjFingerId;//门禁指纹Id
	private String mjFingerStatus;//门禁指纹状态
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVisitorName() {
		return visitorName;
	}
	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	public String getVisitorComp() {
		return visitorComp;
	}
	public void setVisitorComp(String visitorComp) {
		this.visitorComp = visitorComp;
	}
	public String getVisitorPhone() {
		return visitorPhone;
	}
	public void setVisitorPhone(String visitorPhone) {
		this.visitorPhone = visitorPhone;
	}
	public String getVisitorMail() {
		return visitorMail;
	}
	public void setVisitorMail(String visitorMail) {
		this.visitorMail = visitorMail;
	}
	public String getVisitorPop() {
		return visitorPop;
	}
	public void setVisitorPop(String visitorPop) {
		this.visitorPop = visitorPop;
	}
	public String getInterviewee() {
		return interviewee;
	}
	public void setInterviewee(String interviewee) {
		this.interviewee = interviewee;
	}
	public Integer getIntervieweeId() {
		return intervieweeId;
	}
	public void setIntervieweeId(Integer intervieweeId) {
		this.intervieweeId = intervieweeId;
	}
	public String getVisitorCause() {
		return visitorCause;
	}
	public void setVisitorCause(String visitorCause) {
		this.visitorCause = visitorCause;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getFingerprint() {
		return fingerprint;
	}
	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getEpStatus() {
		return epStatus;
	}
	public void setEpStatus(String epStatus) {
		this.epStatus = epStatus;
	}
	public String getVisitorStatus() {
		return visitorStatus;
	}
	public void setVisitorStatus(String visitorStatus) {
		this.visitorStatus = visitorStatus;
	}
	public String getVoucher() {
		return voucher;
	}
	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public Integer getIdentityId() {
		return identityId;
	}
	public void setIdentityId(Integer identityId) {
		this.identityId = identityId;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getIntervieweePhone() {
		return intervieweePhone;
	}
	public void setIntervieweePhone(String intervieweePhone) {
		this.intervieweePhone = intervieweePhone;
	}
	public String getFingerId() {
		return fingerId;
	}
	public void setFingerId(String fingerId) {
		this.fingerId = fingerId;
	}
	public String getVisitorIdentityPic() {
		return visitorIdentityPic;
	}
	public void setVisitorIdentityPic(String visitorIdentityPic) {
		this.visitorIdentityPic = visitorIdentityPic;
	}
	public Integer getLongEpId() {
		return longEpId;
	}
	public void setLongEpId(Integer longEpId) {
		this.longEpId = longEpId;
	}
	public String getLongEpStatus() {
		return longEpStatus;
	}
	public void setLongEpStatus(String longEpStatus) {
		this.longEpStatus = longEpStatus;
	}
	public String getApplyPerson() {
		return applyPerson;
	}
	public void setApplyPerson(String applyPerson) {
		this.applyPerson = applyPerson;
	}
	public String getVisitorLongDateTime() {
		return visitorLongDateTime;
	}
	public void setVisitorLongDateTime(String visitorLongDateTime) {
		this.visitorLongDateTime = visitorLongDateTime;
	}
	public Integer getFollowId() {
		return followId;
	}
	public void setFollowId(Integer followId) {
		this.followId = followId;
	}
	public String getSmsLine() {
		return smsLine;
	}
	public void setSmsLine(String smsLine) {
		this.smsLine = smsLine;
	}
	public String getMjFingerId() {
		return mjFingerId;
	}
	public void setMjFingerId(String mjFingerId) {
		this.mjFingerId = mjFingerId;
	}
	public String getMjFingerStatus() {
		return mjFingerStatus;
	}
	public void setMjFingerStatus(String mjFingerStatus) {
		this.mjFingerStatus = mjFingerStatus;
	}

}
