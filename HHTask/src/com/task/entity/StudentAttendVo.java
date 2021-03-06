package com.task.entity;

import java.io.Serializable;
import java.util.List;

public class StudentAttendVo implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userName;
	private String userCode;
	private String lDate;
	private List<Integer> lnumberList;
	private List<String> attendStatusList;
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
	public String getlDate() {
		return lDate;
	}
	public void setlDate(String lDate) {
		this.lDate = lDate;
	}
	public List<Integer> getLnumberList() {
		return lnumberList;
	}
	public void setLnumberList(List<Integer> lnumberList) {
		this.lnumberList = lnumberList;
	}
	public List<String> getAttendStatusList() {
		return attendStatusList;
	}
	public void setAttendStatusList(List<String> attendStatusList) {
		this.attendStatusList = attendStatusList;
	}
	
}
