package com.hhu.count.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/*
 * 阅读表
 * 
 * */

public class MyRead {

	private String id;
	private String readername;		//读者名称
	private String coursename;		//课程名称
	private String reading;			//阅读量
	private String readerID;		//读者id唯一标识
	private String courseID;		//课程id唯一标识
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date MyrDate;
	private String state;
	private Date buildFirst;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReadername() {
		return readername;
	}

	public void setReadername(String readername) {
		this.readername = readername;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	public String getReaderID() {
		return readerID;
	}

	public void setReaderID(String readerID) {
		this.readerID = readerID;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public Date getMyrDate() {
		return MyrDate;
	}

	public void setMyrDate(Date myrDate) {
		MyrDate = myrDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getBuildFirst() {
		return buildFirst;
	}

	public void setBuildFirst(Date buildFirst) {
		this.buildFirst = buildFirst;
	}

	
	
	
	
	

}
