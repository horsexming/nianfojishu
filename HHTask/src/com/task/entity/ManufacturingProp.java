package com.task.entity;

import java.util.Date;

/**
 * 记录
 * @author 马凯
 *
 */
public class ManufacturingProp implements java.io.Serializable{
	private static final long serialVersionUID =1L;
	private Integer id;
	private Date nowDate;//本次检查时间
	private Date nextDate;//下次时间
	private String contextStr;//检查内容
	private String verification;//是否合格
	private Manufacturing parent;
	private Integer f_manu_id;
	private String username;
	private String usercode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

	public String getContextStr() {
		return contextStr;
	}

	public void setContextStr(String contextStr) {
		this.contextStr = contextStr;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public Manufacturing getParent() {
		return parent;
	}

	public void setParent(Manufacturing parent) {
		this.parent = parent;
	}

	public Integer getF_manu_id() {
		return f_manu_id;
	}

	public void setF_manu_id(Integer fManuId) {
		f_manu_id = fManuId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

}
