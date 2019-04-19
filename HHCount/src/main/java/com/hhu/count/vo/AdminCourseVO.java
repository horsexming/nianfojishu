package com.hhu.count.vo;

import java.util.Date;

/*
 * 课程统计
 * */
public class AdminCourseVO {
	private String id; // 课程ID
	private String coursename; // 课程名
	private Date publishTime; // 发布时间
	private String writerid;// 发布者id
	private String courseRead; // 课程量
	private String writer; // 发布者
	private String readername;// 读者名
	private String reading;// 阅读量
	private String poorR;// 阅读差
	private Date buildFirst;
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

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getWriterid() {
		return writerid;
	}

	public void setWriterid(String writerid) {
		this.writerid = writerid;
	}

	public String getCourseRead() {
		return courseRead;
	}

	public void setCourseRead(String courseRead) {
		this.courseRead = courseRead;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getReadername() {
		return readername;
	}

	public void setReadername(String readername) {
		this.readername = readername;
	}

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public String getPoorR() {
		return poorR;
	}

	public void setPoorR(String poorR) {
		this.poorR = poorR;
	}

	public Date getBuildFirst() {
		return buildFirst;
	}

	public void setBuildFirst(Date buildFirst) {
		this.buildFirst = buildFirst;
	}
	
	

	
}
