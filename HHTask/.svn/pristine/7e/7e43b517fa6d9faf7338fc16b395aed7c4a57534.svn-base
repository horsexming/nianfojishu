package com.task.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 刘晓霆
 * @Date 2014-04-25 (表名:KQ_Person) 考勤人员信息表
 */
public class AttendancePersonInformation  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer atPerInforId;// 考勤信息Id
	private String classNo;// 班次编号（白班、或夜班）
	private Short workTimeMode;// 开始时间
	private Short overTimeMode;// 结束时间
	private Short restMode;// 是否休息
	private String restDays;// 休息天数
	private Byte isHolidayAsWorkTime;// 是否是假期开始时间
	private Byte isOverTimeAsWorkTime;// 是否是假期结束时间
	private Short personState;// 人员状态
	private String remark;// 个人评论
	private String userNo;// 用户编号
	private Date updateDate;// 更新时间
	private Person person;//一对一关系，对应Person表
    
	// getter和setter方法
	public Integer getAtPerInforId() {
		return atPerInforId;
	}

	public void setAtPerInforId(Integer atPerInforId) {
		this.atPerInforId = atPerInforId;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public Short getWorkTimeMode() {
		return workTimeMode;
	}

	public void setWorkTimeMode(Short workTimeMode) {
		this.workTimeMode = workTimeMode;
	}

	public Short getOverTimeMode() {
		return overTimeMode;
	}

	public void setOverTimeMode(Short overTimeMode) {
		this.overTimeMode = overTimeMode;
	}

	public Short getRestMode() {
		return restMode;
	}

	public void setRestMode(Short restMode) {
		this.restMode = restMode;
	}

	public String getRestDays() {
		return restDays;
	}

	public void setRestDays(String restDays) {
		this.restDays = restDays;
	}

	public Byte getIsHolidayAsWorkTime() {
		return isHolidayAsWorkTime;
	}

	public void setIsHolidayAsWorkTime(Byte isHolidayAsWorkTime) {
		this.isHolidayAsWorkTime = isHolidayAsWorkTime;
	}

	public Byte getIsOverTimeAsWorkTime() {
		return isOverTimeAsWorkTime;
	}

	public void setIsOverTimeAsWorkTime(Byte isOverTimeAsWorkTime) {
		this.isOverTimeAsWorkTime = isOverTimeAsWorkTime;
	}

	public Short getPersonState() {
		return personState;
	}

	public void setPersonState(Short personState) {
		this.personState = personState;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
