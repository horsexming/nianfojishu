package com.task.entity.parking;

import java.io.Serializable;

/**
 * 设备对应手机号码表
 * @author Li_Cong 2015-12-30
 * 表名：ta_cw_SimCarkTel
 */
public class SimCarkTel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String simId;//SIM卡ID
	private String simTel;//手机号码
	private String securityCode;//验证码(6位数)
	private String securityTel;//验证手机号（验证时候用来对比）
	private Integer securityshu;//获取验证码次数
	private String randomNum;//验证码对应的随机码（2位数）
	private String failTime;//失效日期
	private String status;//可用状态(初始/正常/停用)
	private String addTime;//添加时间
	private String updateTime;//修改时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getSimId() {
		return simId;
	}
	public void setSimId(String simId) {
		this.simId = simId;
	}
	public String getSimTel() {
		return simTel;
	}
	public void setSimTel(String simTel) {
		this.simTel = simTel;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public String getRandomNum() {
		return randomNum;
	}
	public void setRandomNum(String randomNum) {
		this.randomNum = randomNum;
	}
	public Integer getSecurityshu() {
		return securityshu;
	}
	public void setSecurityshu(Integer securityshu) {
		this.securityshu = securityshu;
	}
	public String getFailTime() {
		return failTime;
	}
	public void setFailTime(String failTime) {
		this.failTime = failTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSecurityTel() {
		return securityTel;
	}
	public void setSecurityTel(String securityTel) {
		this.securityTel = securityTel;
	}
}
