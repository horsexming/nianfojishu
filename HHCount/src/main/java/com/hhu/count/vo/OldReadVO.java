package com.hhu.count.vo;

import java.util.Date;

/*
 * 
 * 历史阅读记录
 * 
 * */
public class OldReadVO {
	private String MyRid;
	private Date readerDate; // 阅读时间
	private String oldReading;// 阅读次数
	private String coursename;// 课程名称
	private String courseRead;// 每日阅读次数参考
	private Date publishTime; // 发布时间
	private String state; // 发布状态
	private Date stopTime; // 停止时间

	public String getMyRid() {
		return MyRid;
	}

	public void setMyRid(String myRid) {
		MyRid = myRid;
	}

	public Date getReaderDate() {
		return readerDate;
	}

	public void setReaderDate(Date readerDate) {
		this.readerDate = readerDate;
	}

	public String getOldReading() {
		return oldReading;
	}

	public void setOldReading(String oldReading) {
		this.oldReading = oldReading;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getCourseRead() {
		return courseRead;
	}

	public void setCourseRead(String courseRead) {
		this.courseRead = courseRead;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

}
