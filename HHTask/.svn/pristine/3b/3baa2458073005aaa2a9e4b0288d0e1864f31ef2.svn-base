package com.task.entity.android.pscs;

import java.io.Serializable;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;



/****
 * 客户表  表名:ta_customer
 * @author 毛小龙
 * 
 */
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer customer_id;//ID
	private String customer_phone;//手机号
	private String customer_name;//客户姓名
	private String customer_passWord;//登陆密码
	private String cart_ype;//车型
	private String company_name;//公司名称
	private String customer_state;//客户状态(试用/永久)
	private Integer period;//客户周期(按月份算)
	private String startdate;//开始时间
	private String enddate;//结束时间
	
	private String securityCode;//验证码(6位数) 
	private String securityTel;//验证手机号（验证时候用来对比）
	private Integer securityshu;//获取验证码次数
	private String randomNum;//验证码对应的随机码（2位数）
	private String failTime;//失效日期
	private String status;//可用状态(初始/正常/停用)
	private String addTime;//添加时间
	private String updateTime;//修改时间
	private Set<CustomerInformation> customerInformations;
	
	public String getCart_ype() {
		return cart_ype;
	}
	public void setCart_ype(String cartYpe) {
		cart_ype = cartYpe;
	}
	@JSONField(serialize = false)
	public Set<CustomerInformation> getCustomerInformations() {
		return customerInformations;
	}
	public void setCustomerInformations(
			Set<CustomerInformation> customerInformations) {
		this.customerInformations = customerInformations;
	}
	public String getSecurityTel() {
		return securityTel;
	}
	public void setSecurityTel(String securityTel) {
		this.securityTel = securityTel;
	}
	public String getCustomer_passWord() {
		return customer_passWord;
	}
	public void setCustomer_passWord(String customerPassWord) {
		customer_passWord = customerPassWord;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customerId) {
		customer_id = customerId;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customerPhone) {
		customer_phone = customerPhone;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customerName) {
		customer_name = customerName;
	}
 
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String companyName) {
		company_name = companyName;
	}
	public String getCustomer_state() {
		return customer_state;
	}
	public void setCustomer_state(String customerState) {
		customer_state = customerState;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	

	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public Integer getSecurityshu() {
		return securityshu;
	}
	public void setSecurityshu(Integer securityshu) {
		this.securityshu = securityshu;
	}
	public String getRandomNum() {
		return randomNum;
	}
	public void setRandomNum(String randomNum) {
		this.randomNum = randomNum;
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
	
	
	

}
