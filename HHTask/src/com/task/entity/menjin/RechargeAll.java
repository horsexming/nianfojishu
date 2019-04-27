package com.task.entity.menjin;

import java.io.Serializable;

/**
 * 充值表
 * 
 * @author Li_Cong 2016-09-17 表名 ta_mj_RechargeAll 添加充值消费汇总表（与用户表对应）
 */
public class RechargeAll implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;// 员工姓名
	private String code;// 工号
	private String cardId;// 卡号
	private String dept;// 部门名称
	private Integer userId;// userID
	private Float balance;// 余额
	private String addTime;// 添加时间
	private String updateTime;// 上次更改时间

	// get set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
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
