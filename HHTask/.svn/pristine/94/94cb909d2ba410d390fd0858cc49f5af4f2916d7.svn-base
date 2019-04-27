package com.task.entity;

import java.io.Serializable;

/***
 * 内部计划详细(表名:ta_crm_internalOrderDetail)
 * 
 * @author 刘培
 * 
 */
public class InternalOrderDetail  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;

	private Integer procardId;
	private Integer orderId;
	private String orderNum;// 订单编号
	private Integer productManagerId;// 订单产品id
	private String status;//

	private String pieceNumber;// 件号
	private String name;// 品名
	private String unit;// 单位
	private Float num;// 计划数量
	private String touchanDate;// 投产日期;(预计)
	private String jihuoDate;// 装配日期;(预计)
	private String paymentDate;// 交付日期;(预计)

	private Float quantityCompletion;// 完成数量
	private Float sellCount;// 出库数量
	private Float turnCount;// 已转数量
	private Float cxCount;// 占用数量（预测或备货订单使用,指正式订单占用了多少数量）
	private String remark;
	private String ywMarkId;// 业务件号
	
	private InternalOrder internalOrder;

	private String isPeiJian;//是否是配件（true:是 false：不是）

	public InternalOrderDetail() {
		super();
	}

	public InternalOrderDetail(String name, String pieceNumber, Float num,
			Float turnCount, String remark, Float quantityCompletion) {
		super();
		this.name = name;
		this.pieceNumber = pieceNumber;
		this.num = num;
		this.turnCount = turnCount;
		this.remark = remark;
		this.quantityCompletion = quantityCompletion;
	}

	public InternalOrderDetail(String name, String pieceNumber,
			String ywMarkId, Float num, Float turnCount, String remark,
			Float quantityCompletion, String paymentDate) {
		super();
		this.name = name;
		this.pieceNumber = pieceNumber;
		if(ywMarkId==null||ywMarkId.length()==0){
			this.ywMarkId = pieceNumber;
		}else{
			this.ywMarkId = ywMarkId;
		}
		this.num = num;
		this.turnCount = turnCount;
		this.remark = remark;
		this.quantityCompletion = quantityCompletion;
		this.paymentDate = paymentDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPieceNumber() {
		return pieceNumber;
	}

	public void setPieceNumber(String pieceNumber) {
		this.pieceNumber = pieceNumber;
	}

	public Float getNum() {
		return num;
	}

	public void setNum(Float num) {
		this.num = num;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public InternalOrder getInternalOrder() {
		return internalOrder;
	}

	public void setInternalOrder(InternalOrder internalOrder) {
		this.internalOrder = internalOrder;
	}

	public Float getQuantityCompletion() {
		return quantityCompletion;
	}

	public void setQuantityCompletion(Float quantityCompletion) {
		this.quantityCompletion = quantityCompletion;
	}

	public Float getTurnCount() {
		return turnCount;
	}

	public void setTurnCount(Float turnCount) {
		this.turnCount = turnCount;
	}

	public Float getSellCount() {
		return sellCount;
	}

	public void setSellCount(Float sellCount) {
		this.sellCount = sellCount;
	}

	public String getYwMarkId() {
		return ywMarkId;
	}

	public void setYwMarkId(String ywMarkId) {
		this.ywMarkId = ywMarkId;
	}

	public Float getCxCount() {
		return cxCount;
	}

	public void setCxCount(Float cxCount) {
		this.cxCount = cxCount;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Integer getProcardId() {
		return procardId;
	}

	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getTouchanDate() {
		return touchanDate;
	}

	public void setTouchanDate(String touchanDate) {
		this.touchanDate = touchanDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getProductManagerId() {
		return productManagerId;
	}

	public void setProductManagerId(Integer productManagerId) {
		this.productManagerId = productManagerId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getJihuoDate() {
		return jihuoDate;
	}

	public void setJihuoDate(String jihuoDate) {
		this.jihuoDate = jihuoDate;
	}

	public String getIsPeiJian() {
		return isPeiJian;
	}

	public void setIsPeiJian(String isPeiJian) {
		this.isPeiJian = isPeiJian;
	}

}
