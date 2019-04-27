package com.task.entity.sop;

public class ProcardMaterialHead implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private String markId;//总成件号
	private String selfCard;//总成批次
	private Integer userId;//叫料人
	private String userName;//叫人料名称
	private String userCode;//叫料人工号
	private String userDept;//叫料人部门
	private Float thisCount;//叫料数量
	private String hastrue;//是否确认(是,否)
	private String lingliaoStatus;//领料状态（未领，已领）
	private Integer receiverId;//领料人Id
	private String receiverName;//领料人名
	private String cangwei;//仓位
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getSelfCard() {
		return selfCard;
	}
	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
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
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Float getThisCount() {
		return thisCount;
	}
	public void setThisCount(Float thisCount) {
		this.thisCount = thisCount;
	}
	public String getUserDept() {
		return userDept;
	}
	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}
	public String getHastrue() {
		return hastrue;
	}
	public void setHastrue(String hastrue) {
		this.hastrue = hastrue;
	}
	public String getLingliaoStatus() {
		return lingliaoStatus;
	}
	public void setLingliaoStatus(String lingliaoStatus) {
		this.lingliaoStatus = lingliaoStatus;
	}
	public Integer getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getCangwei() {
		return cangwei;
	}
	public void setCangwei(String cangwei) {
		this.cangwei = cangwei;
	}
	
}
