package com.hhu.count.vo;
/*
 * 管理员课程统计
 * (首页)
 * */

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CourseVO {
	private String readNum; // 课程总阅读人数
	private String userReading; // 用户总阅读数
	private String SYReading; // 实际应该阅读数参考
	private String YCReading; // 实际应该阅读数和用户总阅读数的差
	private String id; // 课程id
	private String coursename; // 课程名
	private String courseRead; // 每日阅读量参考
	private String writerid; // 作者id
	private String state; // 课程状态
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date publishTime; // 发布时间

	public String getReadNum() {
		return readNum;
	}

	public void setReadNum(String readNum) {
		this.readNum = readNum;
	}

	public String getUserReading() {
		return userReading;
	}

	public void setUserReading(String userReading) {
		this.userReading = userReading;
	}

	public String getSYReading() {
		return SYReading;
	}

	public void setSYReading(String sYReading) {
		SYReading = sYReading;
	}

	public String getYCReading() {
		return YCReading;
	}

	public void setYCReading(String yCReading) {
		YCReading = yCReading;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getWriterid() {
		return writerid;
	}

	public void setWriterid(String writerid) {
		this.writerid = writerid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

}
