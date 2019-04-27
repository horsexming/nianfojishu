package com.task.entity.sop.fhyp;

import java.io.Serializable;
import java.util.Date;

import javax.xml.crypto.Data;

import com.task.entity.Users;

public class FanghuYongpinQuanxian   implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**ID*/
	private Integer id;
	
	/**领取用户ID*/
	private Integer lingquUserId ;
	/**拥有防护用品ID*/
	private Integer fanghuYongpinId;
	/**领取周期*/
	private String lingquZhouqi;
	/**是否可以领取 yes no*/
	private String lingquEnable;
	/**领取用户*/
	private Users lingquUser;
	/**客户端参数*/
	private String params;

	public String getParams() {
		if(params!=null){
			return params.replace("\\t", "").replace("\\r","").replace("\\n","").replace("\\f","").replace("\\","").replace(" ","");
		}
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLingquUserId() {
		return lingquUserId;
	}

	public void setLingquUserId(Integer lingquUserId) {
		this.lingquUserId = lingquUserId;
	}

	public Integer getFanghuYongpinId() {
		return fanghuYongpinId;
	}

	public void setFanghuYongpinId(Integer fanghuYongpinId) {
		this.fanghuYongpinId = fanghuYongpinId;
	}

	public String getLingquZhouqi() {
		return lingquZhouqi;
	}

	public void setLingquZhouqi(String lingquZhouqi) {
		this.lingquZhouqi = lingquZhouqi;
	}

	public String getLingquEnable() {
		return lingquEnable;
	}

	public void setLingquEnable(String lingquEnable) {
		this.lingquEnable = lingquEnable;
	}

	public Users getLingquUser() {
		return lingquUser;
	}

	public void setLingquUser(Users lingquUser) {
		this.lingquUser = lingquUser;
	}
}
