package com.tast.entity.zhaobiao;

import java.io.Serializable;



/**
 * ZhNews entity. @author MyEclipse Persistence Tools
 */

public class ZhNews  implements Serializable {
	private static final long serialVersionUID = 1L;


    // Fields    

     private Integer id;
     private String title;
     private String filel;
     private String cclass;
     private String uid;
     private String time;
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
	public String getFilel() {
		return filel;
	}
	public void setFilel(String filel) {
		this.filel = filel;
	}
	public String getCclass() {
		return cclass;
	}
	public void setCclass(String cclass) {
		this.cclass = cclass;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
    








}