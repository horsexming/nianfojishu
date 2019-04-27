package com.tast.entity.zhaobiao;

import java.io.Serializable;

/*
 * 招标信息表
 */

public class HuikuangXiangxi implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer hxid;//主见id
	private String hname;// 题目
	private Integer hxXid;// 模版id
	private float  hmoney;//回款moeny\
	private String danwei;//单位
	
	
	public Integer getHxid() {
		return hxid;
	}
	public void setHxid(Integer hxid) {
		this.hxid = hxid;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public Integer getHxXid() {
		return hxXid;
	}
	public void setHxXid(Integer hxXid) {
		this.hxXid = hxXid;
	}
	public float getHmoney() {
		return hmoney;
	}
	public void setHmoney(float hmoney) {
		this.hmoney = hmoney;
	}
	public String getDanwei() {
		return danwei;
	}
	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}
	
	


	
}
