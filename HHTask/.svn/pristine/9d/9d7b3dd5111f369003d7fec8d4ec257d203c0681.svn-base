package com.task.entity.renshi;

import java.io.Serializable;
import java.util.Set;

import com.task.util.FieldMeta;

/**
 * 会议表
 * @author WCY
 *tableName = ta_hr_meeting
 */
public class Meeting implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	@FieldMeta(name="会议标题")
	private String title;//会议标题
	@FieldMeta(name="会议位置")
	private String position;//位置
	@FieldMeta(name="申请时间")
	private String addTime;//添加时间
	@FieldMeta(name="会议预计开始时间")
	private String startDate;
	@FieldMeta(name="会议预计结束时间")
	private String endDate;
	@FieldMeta(name="会议内容")
	private String content;
	private Integer userId;
	@FieldMeta(name="添加人工号")
	private String userCode;
	@FieldMeta(name="添加人姓名")
	private String userName;
	private Integer epId;
	private String epStatus;
	
	private Set<MeetingPerson> personSet;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Set<MeetingPerson> getPersonSet() {
		return personSet;
	}
	public void setPersonSet(Set<MeetingPerson> personSet) {
		this.personSet = personSet;
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
