package com.task.entity.sop.qd;

import java.io.Serializable;

/**
 * 检验提醒（ta_sop_qd_CheckAlert）
 * @author txb
 *
 */
public class CheckAlert  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String markId;//件号
	private String selfCard;//批次
	private String proName;//零件名称
	private Integer processNo;//工序号
	private String processName;//工序名称
	private String gongwei;//工位
	private String shebeino;//设备编号
	private String addTime;//添加时间
	private Integer userId;//检验员Id
	private String userCode;//检验员工号
	private String userName;//检验员名字
	private String checkTime;//检验时间
	private String status;//(待通知，已通知，完成)
	private String endTime;//结束时间
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
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	
	public Integer getProcessNo() {
		return processNo;
	}
	public void setProcessNo(Integer processNo) {
		this.processNo = processNo;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getGongwei() {
		return gongwei;
	}
	public void setGongwei(String gongwei) {
		this.gongwei = gongwei;
	}
	public String getShebeino() {
		return shebeino;
	}
	public void setShebeino(String shebeino) {
		this.shebeino = shebeino;
	}
	
	
	
}
