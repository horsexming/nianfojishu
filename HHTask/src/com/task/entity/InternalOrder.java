package com.task.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/***
 * 内部计划 表(表名:ta_crm_internalOrder)
 * 
 * @author 曾建森
 * 
 */
public class InternalOrder  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String num;
	private String genertorDate; // 生产计划年月
	private String newDate; // 生成生产计划的时间
	private String documentaryPeople;// 跟单人
	private Integer documentaryPeopleId;//
	private String status;// (审核/经理审核/同意/完成/取消)
	private String removeDate;//取消时间
	private String cardStatus;// (已生成/未生成)
	private String zhStatus;// 是否转完 (已转完/未转完)
	private String flow;
	private String orderType;//订单类型(正式、预测)
	private String orderIds;//订单ids
	private String orderNums;//订单编号s
	private Integer epId;
	private String producttype;//批产、试制(null 表示批产);
	private String bzStatus;//bom编制状态（未完成,完成）页面传值
	
	private String whetherPurchase;
	private ClientManagement custome;
	private Set<OrderManager> outerOrders = new HashSet<OrderManager>();

	private Set<InternalOrderDetail> interOrderDetails = new HashSet<InternalOrderDetail>();
	private String shebeiDate;//设变日期(只做页面传值使用)
	private String pieceNumber;//件号;(只做页面传值使用)
	private String isPeiJian;//是否是配件（true:是 false：不是）
	private int isVali;

	public InternalOrder() {
		super();
	}

	public InternalOrder(String num, String genertorDate, String newDate) {
		super();
		this.num = num;
		this.genertorDate = genertorDate;
		this.newDate = newDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getGenertorDate() {
		return genertorDate;
	}

	public void setGenertorDate(String genertorDate) {
		this.genertorDate = genertorDate;
	}

	public String getNewDate() {
		return newDate;
	}

	public void setNewDate(String newDate) {
		this.newDate = newDate;
	}

	public Set<OrderManager> getOuterOrders() {
		return outerOrders;
	}

	public void setOuterOrders(Set<OrderManager> outerOrders) {
		this.outerOrders = outerOrders;
	}

	public Set<InternalOrderDetail> getInterOrderDetails() {
		return interOrderDetails;
	}

	public void setInterOrderDetails(Set<InternalOrderDetail> interOrderDetails) {
		this.interOrderDetails = interOrderDetails;
	}
	
	public String getRemoveDate() {
		return removeDate;
	}

	public void setRemoveDate(String removeDate) {
		this.removeDate = removeDate;
	}

	public ClientManagement getCustome() {
		return custome;
	}

	public void setCustome(ClientManagement custome) {
		this.custome = custome;
	}

	public String getDocumentaryPeople() {
		return documentaryPeople;
	}

	public void setDocumentaryPeople(String documentaryPeople) {
		this.documentaryPeople = documentaryPeople;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public String getWhetherPurchase() {
		return whetherPurchase;
	}

	public void setWhetherPurchase(String whetherPurchase) {
		this.whetherPurchase = whetherPurchase;
	}

	public int getIsVali() {
		return isVali;
	}

	public void setIsVali(int isVali) {
		this.isVali = isVali;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getZhStatus() {
		return zhStatus;
	}

	public void setZhStatus(String zhStatus) {
		this.zhStatus = zhStatus;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	public String getOrderNums() {
		return orderNums;
	}

	public void setOrderNums(String orderNums) {
		this.orderNums = orderNums;
	}

	public String getPieceNumber() {
		return pieceNumber;
	}

	public void setPieceNumber(String pieceNumber) {
		this.pieceNumber = pieceNumber;
	}

	public Integer getDocumentaryPeopleId() {
		return documentaryPeopleId;
	}

	public void setDocumentaryPeopleId(Integer documentaryPeopleId) {
		this.documentaryPeopleId = documentaryPeopleId;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}

	public String getBzStatus() {
		return bzStatus;
	}

	public void setBzStatus(String bzStatus) {
		this.bzStatus = bzStatus;
	}

	public String getIsPeiJian() {
		return isPeiJian;
	}

	public void setIsPeiJian(String isPeiJian) {
		this.isPeiJian = isPeiJian;
	}

	public String getShebeiDate() {
		return shebeiDate;
	}

	public void setShebeiDate(String shebeiDate) {
		this.shebeiDate = shebeiDate;
	}
	

}
