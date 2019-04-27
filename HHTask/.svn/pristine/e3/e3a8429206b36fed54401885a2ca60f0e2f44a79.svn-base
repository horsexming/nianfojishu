package com.task.entity.android.pscs;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

/****
 * 客户表  表名: ta_AngularSpeed
 * @author 于勇鸿斌
 * 
 */
public class AngularSpeed implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;//ID
//	private Integer customerinformation_id;//提交信息表ID
	private String angular;//角度
	private String speed;//转速
	private CustomerInformation customerInformation;
	
	@JSONField(serialize = false)
	public CustomerInformation getCustomerInformation() {
		return customerInformation;
	}
	public void setCustomerInformation(CustomerInformation customerInformation) {
		this.customerInformation = customerInformation;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAngular() {
		return angular;
	}
	public void setAngular(String angular) {
		this.angular = angular;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}

	
}
