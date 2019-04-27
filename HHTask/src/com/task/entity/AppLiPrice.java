package com.task.entity;

import java.io.Serializable;

import com.task.util.FieldMeta;


/**
 * 价格合同申请查看表
 * 
 * @表名 ta_AppLiPrice
 * @author 李聪
 */
public class AppLiPrice  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer priceId;// 合同ID 
	@FieldMeta(name="申请人名称")
	private String shenqingName;// 申请人名称
	private Integer shenqingUserId;// 申请人usersId
	@FieldMeta(name="合同名称")
	private String priceName;// 合同名称
	@FieldMeta(name="件号")
	private String markId;// 件号
	private String priceUrl;// 合同位置
	private String appStratu;// 审批状态
	@FieldMeta(name="申请时间")
	private String addTime;// 申请时间
	private Integer epId;// 审批流程id
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
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getPriceId() {
		return priceId;
	}
	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}
	public String getShenqingName() {
		return shenqingName;
	}
	public void setShenqingName(String shenqingName) {
		this.shenqingName = shenqingName;
	}
	public Integer getShenqingUserId() {
		return shenqingUserId;
	}
	public void setShenqingUserId(Integer shenqingUserId) {
		this.shenqingUserId = shenqingUserId;
	}
	public String getPriceName() {
		return priceName;
	}
	public void setPriceName(String priceName) {
		this.priceName = priceName;
	}
	public String getPriceUrl() {
		return priceUrl;
	}
	public void setPriceUrl(String priceUrl) {
		this.priceUrl = priceUrl;
	}
	public String getAppStratu() {
		return appStratu;
	}
	public void setAppStratu(String appStratu) {
		this.appStratu = appStratu;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	

	
}
