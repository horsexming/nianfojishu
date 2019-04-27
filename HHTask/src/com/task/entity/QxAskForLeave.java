package com.task.entity;

import com.task.util.FieldMeta;

/**
 * 销假表:(ta_QxAskForLeave)
 * @author wxf
 *
 */
public class QxAskForLeave implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	public Integer id;
	public Integer leaveId;//请假表Id
	@FieldMeta(name="申请消假人姓名")
	public String userName;//申请消假人姓名
	@FieldMeta(name="申请消假人工号")
	public String userCode;//申请消假人工号
	@FieldMeta(name="实际开始时间")
	public String startDate;//实际开始时间
	@FieldMeta(name="实际结束时间")
	public String endDate;//实际结束时间;
	@FieldMeta(name="实际请假天数")
	public Float days;//实际请假天数;
	@FieldMeta(name="实际请假小时")
	public Float hours;//实际请假小时数;
	public Integer epId;
	public String epStatus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
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
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Float getDays() {
		return days;
	}
	public void setDays(Float days) {
		this.days = days;
	}
	public Float getHours() {
		return hours;
	}
	public void setHours(Float hours) {
		this.hours = hours;
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
	
	
	
}