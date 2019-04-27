package com.task.entity;

import java.io.Serializable;

import com.task.util.FieldMeta;

/**
 * @author wxf
 *成品退货申请表：（ta_ChengPinTuiHuo）;
 */
public class ChengPinTuiHuo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer sellId;//出库记录Id
	private Integer orderId;//订单Id
	private Integer productId;//产品Id
	@FieldMeta(name="件号")
	private String markId;//件号
	@FieldMeta(name="名称")
	private String proname;//名称
	@FieldMeta(name="批次")
	private String selfCard;//批次
	@FieldMeta(name="订单号")
	private String orderNo;//订单号
	@FieldMeta(name="业务件号")
	private String ywmarkId;//业务件号
	@FieldMeta(name="库别")
	private String kubie;//库别
	@FieldMeta(name="仓区")
	private String cangqu;//仓区
	@FieldMeta(name="库位")
	private String kuwei;//库位
	@FieldMeta(name="申请数量")
	private Float sqNum;//申请数量
	@FieldMeta(name="申请人")
	private String shPlanNum;//送货单号
	private String sqUsers;//申请人
	private String sqUserCode;//申请人工号
	private Integer sqUsersId;//申请人Id
	private String dept;//申请人部门
	@FieldMeta(name="申请时间")
	private String addTime;//添加时间
	private String epStatus;//审批状态
	private Integer epId;//
	private Sell sell;//页面传值
	
	public ChengPinTuiHuo() {
		super();
	}
	public ChengPinTuiHuo(Integer sellId, Integer orderId, Integer productId,
			String markId, String selfCard, String orderNo, String kubie,
			String cangqu, String kuwei, Float sqNum, String sqUsers,
			String addTime) {
		super();
		this.sellId = sellId;
		this.orderId = orderId;
		this.productId = productId;
		this.markId = markId;
		this.selfCard = selfCard;
		this.orderNo = orderNo;
		this.kubie = kubie;
		this.cangqu = cangqu;
		this.kuwei = kuwei;
		this.sqNum = sqNum;
		this.sqUsers = sqUsers;
		this.addTime = addTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSellId() {
		return sellId;
	}
	public void setSellId(Integer sellId) {
		this.sellId = sellId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getMarkId() {
		return markId;
	}
	public void setMarkId(String markId) {
		this.markId = markId;
	}
	public String getSelfCard() {
		return selfCard;
	}
	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getKubie() {
		return kubie;
	}
	public void setKubie(String kubie) {
		this.kubie = kubie;
	}
	public String getCangqu() {
		return cangqu;
	}
	public void setCangqu(String cangqu) {
		this.cangqu = cangqu;
	}
	public String getKuwei() {
		return kuwei;
	}
	public void setKuwei(String kuwei) {
		this.kuwei = kuwei;
	}
	
	public Float getSqNum() {
		return sqNum;
	}
	public void setSqNum(Float sqNum) {
		this.sqNum = sqNum;
	}
	public String getSqUsers() {
		return sqUsers;
	}
	public void setSqUsers(String sqUsers) {
		this.sqUsers = sqUsers;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getEpStatus() {
		return epStatus;
	}
	public void setEpStatus(String epStatus) {
		this.epStatus = epStatus;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getProname() {
		return proname;
	}
	public void setProname(String proname) {
		this.proname = proname;
	}
	public String getYwmarkId() {
		return ywmarkId;
	}
	public void setYwmarkId(String ywmarkId) {
		this.ywmarkId = ywmarkId;
	}
	public String getSqUserCode() {
		return sqUserCode;
	}
	public void setSqUserCode(String sqUserCode) {
		this.sqUserCode = sqUserCode;
	}
	public Integer getSqUsersId() {
		return sqUsersId;
	}
	public void setSqUsersId(Integer sqUsersId) {
		this.sqUsersId = sqUsersId;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Sell getSell() {
		return sell;
	}
	public void setSell(Sell sell) {
		this.sell = sell;
	}
	public String getShPlanNum() {
		return shPlanNum;
	}
	public void setShPlanNum(String shPlanNum) {
		this.shPlanNum = shPlanNum;
	}
	
	

}