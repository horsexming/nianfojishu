package com.task.entity.barandqr;

import java.io.Serializable;

/***
 * 气密产品管理
 * 
 * @表名 ta_AirProduct
 * @author liupei
 * 
 */
public class AirProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String markId;// 件号
	private String customerNumber;// 客户编号
	private String type;// 类型
	private String beforcontext;// 气密标记前缀
	private String status;// 状态（yes，no）yes表示被锁定的件号
	private String isNeedOtherContext;// 是否需要其他标识（yes,no）
	private Float xielou;// 泄漏量标准
	private Float yali;// 压力值标准
	private Long testTime;// 测试时长（单位：毫秒）
	private String kgIp;// 开关Ip
	private String czIp;// 传值Ip
	private String addTime;// 添加时间
	private String syStatus;//使用状态(使用、停止);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBeforcontext() {
		return beforcontext;
	}

	public void setBeforcontext(String beforcontext) {
		this.beforcontext = beforcontext;
	}

	public String getIsNeedOtherContext() {
		return isNeedOtherContext;
	}

	public void setIsNeedOtherContext(String isNeedOtherContext) {
		this.isNeedOtherContext = isNeedOtherContext;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTestTime() {
		return testTime;
	}

	public void setTestTime(Long testTime) {
		this.testTime = testTime;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Float getXielou() {
		return xielou;
	}

	public void setXielou(Float xielou) {
		this.xielou = xielou;
	}

	public Float getYali() {
		return yali;
	}

	public void setYali(Float yali) {
		this.yali = yali;
	}

	public String getKgIp() {
		return kgIp;
	}

	public void setKgIp(String kgIp) {
		this.kgIp = kgIp;
	}

	public String getCzIp() {
		return czIp;
	}

	public void setCzIp(String czIp) {
		this.czIp = czIp;
	}

	public String getSyStatus() {
		return syStatus;
	}

	public void setSyStatus(String syStatus) {
		this.syStatus = syStatus;
	}

}
