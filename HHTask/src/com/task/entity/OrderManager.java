package com.task.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.util.FieldMeta;
import com.tast.entity.zhaobiao.InternalOrderzhaobiao;

/***
 * 订单(表名:TA_OrderManager)
 * 
 * 
 */
public class OrderManager  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** ID */
	private int id;
	/** 总金额 */
	private Double totalAmount = 0.0;
	/** 订单编号 */
	@FieldMeta(name = "订单编号（内部）")
	private String orderNum;// 订单编号（内部）
	@FieldMeta(name = "订单编号（外部）")
	private String outOrderNumber;// 订单编号（外部）
	private String type;// 订单类型 正常、补单、在途（null 表示正常）
	@FieldMeta(name = "订单类型")
	private String producttype;// 批产、试制(null 表示批产);
	/** 订单文件 */
	private String orderFil;
	/** 交付状态 */
	@FieldMeta(name = "交付状态")
	private String deliveryStatus;
	/** 回款状态 */
	@FieldMeta(name = "回款状态")
	private String backSection;
	/** 订单状态 */
	@FieldMeta(name = "订单状态")
	private String orderStatus;//yes/no
	/** 跟单人 */
	@FieldMeta(name = "跟单人")
	private String documentaryPeople;
	private Integer documentaryPeopleId;
	/** 开单人 */
	@FieldMeta(name = "开单人")
	private String billingPeople;
	/** 交付日期 */
	private String paymentDate;
	/** 协商交付日期 */
	@FieldMeta(name = "协商交付日期 ")
	private String paymentDate2;
	private String ypaymentDate;// 原交付时间当修改协商交付日期时记录之前的交付日期以,分割
	/** 合同文件 */
	private String contractDocuments;
	/** 部门ID */
	private String dept;
	@FieldMeta(name = "添加时间 ")
	private String addTime;// 添加时间

	private String orderType;// 订单类型(正式、预测、备货、售后)
	private String remark;// 备注

	/***** 成本分析 ********/
	private Float rengongfei;// 人工成本
	private Float shebeiZjFei;// 设备折旧费
	private Float nyxhFei;// 能源消耗费
	private Float clFei;// 材料费
	private Float wgFei;// 外购费
	private Float flFei;// 辅料费
	private Float glFei;// 管理费
	private Float clvFei;// 差旅费
	private Float canFei;// 餐费
	private Float allMoney;// 总费用

	/** 客户 */
	private ClientManagement custome;
	private String clientName;// 客户名称
	private String clientFzr;// 客户责任人
	private String clientDz;// 客户地址

	/** 产品 */
	private Set<ProductManager> products = new HashSet<ProductManager>();//订单明细表
	private List<ProductManager> pmList = new ArrayList<ProductManager>();
	private Set<InternalOrder> innerOrders = new HashSet<InternalOrder>();
	private String conversionStatus;
	private Float inputRate;// 入库率
	private Float completionrate;// 完成率(出库量/订单数量*100%)
	private Float timeRate;// 订单完成及时率
	private Float hkrate;// 订单回款率
	private Float kprate;// 开票及时率(该订单产生的已开票的发票数量/该订单产生的发票数量*100%)
	// 采购疾患详细表
	private Set orderzhaobiaos = new HashSet();
	// 招标内部计划表
	private Set<InternalOrderzhaobiao> innerOrderszhaobiao = new HashSet<InternalOrderzhaobiao>();
	/** 审批流程 **/
	private Integer epId;
	private String ep_statuts;// 审批状态;(未审批、同意)
	private Float cgjindu;// 页面传值
	private Float scjindu;// 页面传值
	private Integer orderId;// 预测订单id;(下正式订单时关联的预测订单)

	/***** 华为订单对接id ************/
	private String poHeaderId;// 订单id
	private String poReleaseId;// 发布id+订单id =订单明细

	@JSONField(serialize = false)
	public Set<InternalOrderzhaobiao> getInnerOrderszhaobiao() {
		return innerOrderszhaobiao;
	}

	public void setInnerOrderszhaobiao(
			Set<InternalOrderzhaobiao> innerOrderszhaobiao) {
		this.innerOrderszhaobiao = innerOrderszhaobiao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOrderFil() {
		return orderFil;
	}

	public void setOrderFil(String orderFil) {
		this.orderFil = orderFil;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getBackSection() {
		return backSection;
	}

	public void setBackSection(String backSection) {
		this.backSection = backSection;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getDocumentaryPeople() {
		return documentaryPeople;
	}

	public void setDocumentaryPeople(String documentaryPeople) {
		this.documentaryPeople = documentaryPeople;
	}

	public String getBillingPeople() {
		return billingPeople;
	}

	public void setBillingPeople(String billingPeople) {
		this.billingPeople = billingPeople;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getContractDocuments() {
		return contractDocuments;
	}

	public void setContractDocuments(String contractDocuments) {
		this.contractDocuments = contractDocuments;
	}

	public Set<ProductManager> getProducts() {
		return products;
	}

	@JSONField(serialize = false)
	public void setProducts(Set<ProductManager> products) {
		this.products = products;
	}

	@JSONField(serialize = false)
	public ClientManagement getCustome() {
		return custome;
	}

	public void setCustome(ClientManagement custome) {
		this.custome = custome;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	@JSONField(serialize = false)
	public Set<InternalOrder> getInnerOrders() {
		return innerOrders;
	}

	public void setInnerOrders(Set<InternalOrder> innerOrders) {
		this.innerOrders = innerOrders;
	}

	public String getConversionStatus() {
		return conversionStatus;
	}

	public void setConversionStatus(String conversionStatus) {
		this.conversionStatus = conversionStatus;
	}

	public Float getCompletionrate() {
		return completionrate;
	}

	public void setCompletionrate(Float completionrate) {
		this.completionrate = completionrate;
	}

	@JSONField(serialize = false)
	public Set getOrderzhaobiaos() {
		return orderzhaobiaos;
	}

	public void setOrderzhaobiaos(Set orderzhaobiaos) {
		this.orderzhaobiaos = orderzhaobiaos;
	}

	public Float getHkrate() {
		return hkrate;
	}

	public void setHkrate(Float hkrate) {
		this.hkrate = hkrate;
	}

	public String getOutOrderNumber() {
		return outOrderNumber;
	}

	public void setOutOrderNumber(String outOrderNumber) {
		this.outOrderNumber = outOrderNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getKprate() {
		return kprate;
	}

	public void setKprate(Float kprate) {
		this.kprate = kprate;
	}

	public void setRengongfei(Float rengongfei) {
		this.rengongfei = rengongfei;
	}

	public Float getShebeiZjFei() {
		return shebeiZjFei;
	}

	public void setShebeiZjFei(Float shebeiZjFei) {
		this.shebeiZjFei = shebeiZjFei;
	}

	public Float getNyxhFei() {
		return nyxhFei;
	}

	public void setNyxhFei(Float nyxhFei) {
		this.nyxhFei = nyxhFei;
	}

	public Float getClFei() {
		return clFei;
	}

	public void setClFei(Float clFei) {
		this.clFei = clFei;
	}

	public Float getWgFei() {
		return wgFei;
	}

	public void setWgFei(Float wgFei) {
		this.wgFei = wgFei;
	}

	public Float getFlFei() {
		return flFei;
	}

	public void setFlFei(Float flFei) {
		this.flFei = flFei;
	}

	public Float getGlFei() {
		return glFei;
	}

	public void setGlFei(Float glFei) {
		this.glFei = glFei;
	}

	public Float getClvFei() {
		return clvFei;
	}

	public void setClvFei(Float clvFei) {
		this.clvFei = clvFei;
	}

	public Float getCanFei() {
		return canFei;
	}

	public void setCanFei(Float canFei) {
		this.canFei = canFei;
	}

	public Float getAllMoney() {
		return allMoney;
	}

	public void setAllMoney(Float allMoney) {
		this.allMoney = allMoney;
	}

	public Float getTimeRate() {
		return timeRate;
	}

	public void setTimeRate(Float timeRate) {
		this.timeRate = timeRate;
	}

	public Float getRengongfei() {
		return rengongfei;
	}

	public String getPaymentDate2() {
		return paymentDate2;
	}

	public void setPaymentDate2(String paymentDate2) {
		this.paymentDate2 = paymentDate2;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Float getInputRate() {
		return inputRate;
	}

	public void setInputRate(Float inputRate) {
		this.inputRate = inputRate;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getEp_statuts() {
		return ep_statuts;
	}

	public void setEp_statuts(String epStatuts) {
		ep_statuts = epStatuts;
	}

	public List<ProductManager> getPmList() {
		return pmList;
	}

	public void setPmList(List<ProductManager> pmList) {
		this.pmList = pmList;
	}

	public String getYpaymentDate() {
		return ypaymentDate;
	}

	public void setYpaymentDate(String ypaymentDate) {
		this.ypaymentDate = ypaymentDate;
	}

	public Integer getDocumentaryPeopleId() {
		return documentaryPeopleId;
	}

	public void setDocumentaryPeopleId(Integer documentaryPeopleId) {
		this.documentaryPeopleId = documentaryPeopleId;
	}

	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientFzr() {
		return clientFzr;
	}

	public void setClientFzr(String clientFzr) {
		this.clientFzr = clientFzr;
	}

	public String getClientDz() {
		return clientDz;
	}

	public void setClientDz(String clientDz) {
		this.clientDz = clientDz;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Float getCgjindu() {
		return cgjindu;
	}

	public void setCgjindu(Float cgjindu) {
		this.cgjindu = cgjindu;
	}

	public Float getScjindu() {
		return scjindu;
	}

	public void setScjindu(Float scjindu) {
		this.scjindu = scjindu;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getPoHeaderId() {
		return poHeaderId;
	}

	public void setPoHeaderId(String poHeaderId) {
		this.poHeaderId = poHeaderId;
	}

	public String getPoReleaseId() {
		return poReleaseId;
	}

	public void setPoReleaseId(String poReleaseId) {
		this.poReleaseId = poReleaseId;
	}

}
