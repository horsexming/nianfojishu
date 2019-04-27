package com.task.entity;

import java.io.Serializable;

/**
 * (CRM)订单管理表
 * @author 钟永林
 */
public class OrderManagement  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;   //序号	int
	private String ordernumber;//订单编号
	private String ordertype;//订单类型 (批产,实制)
	private String ordername;	//联系人姓名	Varchar
	private String ordermobilenumber;	//手机号	Varchar
	private String ordercompanyname;	//客户名称	Varchar
	private String ordersummary;	//概要（要什么）	Varchar
	private Float orderquantity;	//数量	Float
	private Float orderunitprice;//	单价	Float
	private Float orderactualmoney;	//实际金额	Float
	private Float orderPrepaidmoney;	//预付金额	Float
	private String ordercreatePeople;//	创建人	Varchar
	private String ordercreatedatatime;//	创建时间	Varchar
	private String orderpersoncharge;//第一阶段负责人 Varchar(工艺)
	private String ordertwopersoncharge; //第二阶段负责人（采购）
	private String orderthreepersoncharge; //第三阶段负责人（加工）
	private String orderfourpersoncharge; //第四阶段负责人 （研发）
	 private String orderfivepersoncharge;//第五阶段负责人（财务）
	private String orderstatus;	//状态(开发中，开发失败,开发成功)	Varchar
	private String orderremarks;	//备注	Varchar\
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrdername() {
		return ordername;
	}
	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}
	public String getOrdermobilenumber() {
		return ordermobilenumber;
	}
	public void setOrdermobilenumber(String ordermobilenumber) {
		this.ordermobilenumber = ordermobilenumber;
	}
	public String getOrdercompanyname() {
		return ordercompanyname;
	}
	public void setOrdercompanyname(String ordercompanyname) {
		this.ordercompanyname = ordercompanyname;
	}
	public String getOrdersummary() {
		return ordersummary;
	}
	public void setOrdersummary(String ordersummary) {
		this.ordersummary = ordersummary;
	}
	public Float getOrderquantity() {
		return orderquantity;
	}
	public void setOrderquantity(Float orderquantity) {
		this.orderquantity = orderquantity;
	}
	public Float getOrderunitprice() {
		return orderunitprice;
	}
	public void setOrderunitprice(Float orderunitprice) {
		this.orderunitprice = orderunitprice;
	}
	public Float getOrderactualmoney() {
		return orderactualmoney;
	}
	public void setOrderactualmoney(Float orderactualmoney) {
		this.orderactualmoney = orderactualmoney;
	}
	public Float getOrderPrepaidmoney() {
		return orderPrepaidmoney;
	}
	public void setOrderPrepaidmoney(Float orderPrepaidmoney) {
		this.orderPrepaidmoney = orderPrepaidmoney;
	}
	public String getOrdercreatePeople() {
		return ordercreatePeople;
	}
	public void setOrdercreatePeople(String ordercreatePeople) {
		this.ordercreatePeople = ordercreatePeople;
	}
	public String getOrdercreatedatatime() {
		return ordercreatedatatime;
	}
	public void setOrdercreatedatatime(String ordercreatedatatime) {
		this.ordercreatedatatime = ordercreatedatatime;
	}
	public String getOrderpersoncharge() {
		return orderpersoncharge;
	}
	public void setOrderpersoncharge(String orderpersoncharge) {
		this.orderpersoncharge = orderpersoncharge;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}
	public String getOrderremarks() {
		return orderremarks;
	}
	public void setOrderremarks(String orderremarks) {
		this.orderremarks = orderremarks;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public String getOrdertwopersoncharge() {
		return ordertwopersoncharge;
	}
	public void setOrdertwopersoncharge(String ordertwopersoncharge) {
		this.ordertwopersoncharge = ordertwopersoncharge;
	}
	public String getOrderthreepersoncharge() {
		return orderthreepersoncharge;
	}
	public void setOrderthreepersoncharge(String orderthreepersoncharge) {
		this.orderthreepersoncharge = orderthreepersoncharge;
	}
	public String getOrderfourpersoncharge() {
		return orderfourpersoncharge;
	}
	public void setOrderfourpersoncharge(String orderfourpersoncharge) {
		this.orderfourpersoncharge = orderfourpersoncharge;
	}
	public String getOrderfivepersoncharge() {
		return orderfivepersoncharge;
	}
	public void setOrderfivepersoncharge(String orderfivepersoncharge) {
		this.orderfivepersoncharge = orderfivepersoncharge;
	}
	
	
}
