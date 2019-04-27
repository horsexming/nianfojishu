package com.tast.entity.zhaobiao;

import java.io.Serializable;

/*
 * 外购外协件验收频次规定
 *   ta_Waigoujianpinci
 */
public class Waigoujianpinci implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;// id
	private String type;//(巡检、null)null表示以前
	private String leixing;// 产品类型
	private String shuoming;//说明
	
	private Float jiepai;//节拍
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLeixing() {
		return leixing;
	}
	public void setLeixing(String leixing) {
		this.leixing = leixing;
	}
	public String getShuoming() {
		return shuoming;
	}
	public void setShuoming(String shuoming) {
		this.shuoming = shuoming;
	}
	public Float getJiepai() {
		return jiepai;
	}
	public void setJiepai(Float jiepai) {
		this.jiepai = jiepai;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	

	
	
}
