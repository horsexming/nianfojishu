package com.task.entity;

import java.io.Serializable;

/**
 * 所对应项目表 ta_Integralsource;
 * 积分来源表；
 * @author 王晓飞
 *
 */
public class Integralsource  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String laiyuan;//积分来源项
	private Integer addintegral;//添加积分;
	private String addtime; //添加时间;
	private String in_code;//工号;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLaiyuan() {
		return laiyuan;
	}
	public void setLaiyuan(String laiyuan) {
		this.laiyuan = laiyuan;
	}
	
	public Integer getAddintegral() {
		return addintegral;
	}
	public void setAddintegral(Integer addintegral) {
		this.addintegral = addintegral;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getIn_code() {
		return in_code;
	}
	public void setIn_code(String inCode) {
		in_code = inCode;
	}
	
	
	
	
	
	
	
	
}
