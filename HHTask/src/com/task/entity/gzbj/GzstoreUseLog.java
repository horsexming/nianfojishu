package com.task.entity.gzbj;

import java.io.Serializable;

/**
 * 工装使用记录 表名：GzstoreUserLog
 * @author txb
 *
 */
public class GzstoreUseLog implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer gzId;//工装Id
	private String gzNumber;//工装编号
	private String markId;//零件号
	private String gzName;//工装名称
	private String userName;//操作人
	private String userCode;//操作人工号
	private Integer processId;//工序id
	private String processName;//工序名称
	private Integer processNo;//工序号
	private String stratTime;//在工序领用的时候记录下时间
	private String endTime;//在工序提交的时候记录下时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProcessId() {
		return processId;
	}
	public void setProcessId(Integer processId) {
		this.processId = processId;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getStratTime() {
		return stratTime;
	}
	public void setStratTime(String stratTime) {
		this.stratTime = stratTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getGzId() {
		return gzId;
	}
	public void setGzId(Integer gzId) {
		this.gzId = gzId;
	}
	public String getGzName() {
		return gzName;
	}
	public void setGzName(String gzName) {
		this.gzName = gzName;
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
	public String getGzNumber() {
		return gzNumber;
	}
	public void setGzNumber(String gzNumber) {
		this.gzNumber = gzNumber;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public Integer getProcessNo() {
		return processNo;
	}
	public void setProcessNo(Integer processNo) {
		this.processNo = processNo;
	}
	
	

}
