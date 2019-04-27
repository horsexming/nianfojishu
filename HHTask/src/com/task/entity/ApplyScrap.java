package com.task.entity;


import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: Scrap
 * @Description: 归还时申请报废表
 * @author mdd
 */
public class ApplyScrap  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String goodsMarkId; //编号(件号)
	private String goodsLotId;// 批次
	private String goodsFullName;//名称
	private String format;//规格
	private Float badNum;//报废数量
	private String unit;//单位
	private String cardNum;// 卡号
	private String username;//员工
	private String dept;
	private String ldate;//借出日期
	private String badDate;//报废日期
	private String badTime;//报废具体日期
	private String badIdea;//损失意见
	private String reason;//损失原因
	private String mix;//合成主码
	private String remark;//备注
	private Integer state;//根据什么报废(借完报废为0或者直接报废1)
	private Integer adminId;//管理员iD
	private String admin;//管理员
	private Integer  lendId;//借用Id
	private Integer goodsId;//库存Id
	
	
	
	
	public ApplyScrap() {
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the goodsMarkId
	 */
	public String getGoodsMarkId() {
		return goodsMarkId;
	}

	/**
	 * @param goodsMarkId the goodsMarkId to set
	 */
	public void setGoodsMarkId(String goodsMarkId) {
		this.goodsMarkId = goodsMarkId;
	}

	/**
	 * @return the goodsFullName
	 */
	public String getGoodsFullName() {
		return goodsFullName;
	}

	/**
	 * @param goodsFullName the goodsFullName to set
	 */
	public void setGoodsFullName(String goodsFullName) {
		this.goodsFullName = goodsFullName;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	

	/**
	 * @return the badNum
	 */
	public Float getBadNum() {
		return badNum;
	}

	/**
	 * @param badNum the badNum to set
	 */
	public void setBadNum(Float badNum) {
		this.badNum = badNum;
	}

	/**
	 * @return the cardNum
	 */
	public String getCardNum() {
		return cardNum;
	}

	/**
	 * @param cardNum the cardNum to set
	 */
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}

	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}


	/**
	 * @return the badIdea
	 */
	public String getBadIdea() {
		return badIdea;
	}

	/**
	 * @param badIdea the badIdea to set
	 */
	public void setBadIdea(String badIdea) {
		this.badIdea = badIdea;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the mix
	 */
	public String getMix() {
		return mix;
	}

	/**
	 * @param mix the mix to set
	 */
	public void setMix(String mix) {
		this.mix = mix;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the state
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return the lendId
	 */
	public Integer getLendId() {
		return lendId;
	}

	/**
	 * @param lendId the lendId to set
	 */
	public void setLendId(Integer lendId) {
		this.lendId = lendId;
	}

	/**
	 * @return the goodsId
	 */
	public Integer getGoodsId() {
		return goodsId;
	}

	/**
	 * @param goodsId the goodsId to set
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * @return the adminId
	 */
	public Integer getAdminId() {
		return adminId;
	}

	/**
	 * @param adminId the adminId to set
	 */
	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	/**
	 * @return the admin
	 */
	public String getAdmin() {
		return admin;
	}

	/**
	 * @param admin the admin to set
	 */
	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnit() {
		return unit;
	}

	public void setBadDate(String badDate) {
		this.badDate = badDate;
	}

	public String getBadDate() {
		return badDate;
	}

	public void setLdate(String ldate) {
		this.ldate = ldate;
	}

	public String getLdate() {
		return ldate;
	}

	public void setGoodsLotId(String goodsLotId) {
		this.goodsLotId = goodsLotId;
	}

	public String getGoodsLotId() {
		return goodsLotId;
	}

	public void setBadTime(String badTime) {
		this.badTime = badTime;
	}

	public String getBadTime() {
		return badTime;
	}

	
}