package com.task.entity.android;

import java.io.Serializable;
import java.util.Date;

/**
 * 巡检记录
 * 
 * @author 马凯
 * 
 */
public class TemplateRecordm implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String czg;// 操作工
	private String jcpc;// 检查批次
	private String quantity;// 本批数量
	private String contentStr;// 检查内容
	private Date startDate;// 开始时间
	private Date nowDate;// 结束时间
	private String verification;//是否合格
	private Integer templateTypeId;// 外键,android用
	private String username;
	private String usercode;
	private TemplateTypem root;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCzg() {
		return czg;
	}

	public void setCzg(String czg) {
		this.czg = czg;
	}

	public String getJcpc() {
		return jcpc;
	}

	public void setJcpc(String jcpc) {
		this.jcpc = jcpc;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getContentStr() {
		return contentStr;
	}

	public void setContentStr(String contentStr) {
		this.contentStr = contentStr;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getNowDate() {
		return nowDate;
	}

	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}

	public Integer getTemplateTypeId() {
		return templateTypeId;
	}

	public void setTemplateTypeId(Integer templateTypeId) {
		this.templateTypeId = templateTypeId;
	}

	public TemplateTypem getRoot() {
		return root;
	}

	public void setRoot(TemplateTypem root) {
		this.root = root;
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

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

}