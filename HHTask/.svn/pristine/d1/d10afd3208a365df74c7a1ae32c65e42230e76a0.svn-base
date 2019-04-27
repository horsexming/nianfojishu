package com.huawei.openapi.openaipexample.client.http;

import java.io.Serializable;

public class OrderForB2B  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	private String vendorCode;// 供应商编码 VARCHAR(25)  
	private String vendorName;// 供应商名称 VARCHAR(240)  
	/***
	 * (全部(ALL)/待接受(before_signe_back)/已接受(signed_back)/预警(warn))
	 */
	private String poStatus;// 订单状态类别
	/***
	 * (全部(ALL)/(新下订单 huaweiPublishOrder 订单取消通知 huaweiNotifyCancelOrder/订单取消确认
	 * huaweiApplyCancelOrder/内容变更通知 huaweiNotifyOrderChange/订单交期修改
	 * huaweiApplyRequredDateChange/待华为确认交期更改
	 * vendorApplyPromiseDateChange/待华为确认数量变更 vendorApplyQuantityChange/待华为确认取消
	 * vendorApplyCancelOrder)
	 */
	private String colTaskOrPoStatus;// 待办类型
	/***
	 * (全部(ALL)/新下订单 NEW 在途订单 OPEN 取消订单 CANCELLED 关闭订单 CLOSED 已交货未关闭 CLOSED FOR
	 * RECEVING
	 */
	private String shipmentStatus;// 订单状态 VARCHAR(20)

	private String poSubType;// 业务领域（工程领域 E/ 生产领域 P /综合领域 G）

	private String poNumber;// PO号 VARCHAR(20)  
	private String poHeaderId;// 订单id
	private String poReleaseId;// 发布id+订单id =订单明细
	
	private Integer instanceId;// 华为系统内部标识用 BIGINT(16)  Y
	private Integer lineLocationId;// 华为系统内部标识用 BIGINT(16)  Y

	private String publishDate;// 订单下达日期

	private String businessMode;// ": "Normal", 采购模式
	private String currencyCode;// 币种 VARCHAR(25)

	private Integer poLineNum;// 订单行号 Number    
	private String shipmentNum;// 发运行号 Number
	private String needByDate;// 需求时期 
	private String promiseDate;// 承诺日期 date
	private String itemCode;// Item编码 VARCHAR(25)  
	private Float quantity;//需求数量 ": 140,
	private Float dueQty;// 未交付数量 Number   0.45
	private String unitOfMeasure;// 单位 VARCHAR(25)  
	private String itemRevision;// 物料/服务编码版本 VARCHAR(25)    

	private String itemDescription;// Item描述 VARCHAR(240)  
	private Float quantityReceived;// 已交付数量 Number
	private Float priceOverride;// 单价 Number  
	private Float totalAmount;// 总额
	private Float taxRate;// 税率 Number           
	private String expireDate;// 订单有效日期 date  
	private String shipToLocation;// 收货地点 VARCHAR(240)
	private String termsMode;// 付款方式 VARCHAR(240)
	private String issuOffice;// 开票单位 VARCHAR(25)  
	private String billToLocation;// 开票地址 VARCHAR(240)   On Agreement,ID


	private Integer needQuantity;// 需求数量 Number
	//  --------------------------------------------空值
	private Integer quantityCancelled;// 已取消数量 Number 新PO已取消数量为0
	private Integer number;// PO数量需计算：needQuantity- quantityCancelled
	private Integer vendorSiteId;// 供应商子公司ID Number 供应商有多家字公司时使用
	private String orgName;// 华为子公司 VARCHAR(25)   zh_CN=PT华为技术投资有限公司,en_US=PT.
	// Huawei Tech Investment
	private String remark;// 备注 VARCHAR(240)    
	private String prNumber;// PR号 VARCHAR(20)    
	private String taskNum;// 任务令号 Number  
	private String agentName;// 采购员 VARCHAR(240)  
	private String carrierName;// 发运方式 VARCHAR(360)
	private Float unSendQuantity;// 未交付数量 Number    
	private String paymentTerms;// 支付条款 VARCHAR(360)  
	// Completion*40%AC|PAC*45%PAC|FAC*15%FAC|LATER|TT/30D

	private String typeLookupCode;// ": "BLANKET",

	private String activityID;// 据说是追踪用的一个id，保存好。。。
	/****************************************************************************************/
	private Float revisionNum;// ": 0,
	private Integer poLineId;// ": 661396146,
	private String objectChangeContext;// ": "{\"closeList\":[],\"openList\":[{\"businessType\":\"huaweiPublishOrder\",\"count\":1}]}",
	private String projectNo;// ": "",
	private Integer phrLastUpdatedBy;// ": "135782",
	private Integer requisitionHeaderId;// ": 215125600,
	private String manufactureSiteInfo;// ": "外协",
	private String recvVendorFax;// ": "0755-28359998",
	private Float unitPrice;// ": 2520.4281,
	private String consignedFlag;// ": "N",
	private Integer termsId;// ": 14771,
	private String sendVendorAddr;// ": "No. 152,Luyuan Rd,",
	private String fobLookupCode;// ": "DDP_SZ",
	private String unitMeasLookupCode;// ": "PCS",
	private Integer taskQuantity;// ": 1,
	private String shipViaLookupCode;// ": "汽运",
	private String itemId;// ": 46789623,
	private Integer receivingRoutingId;// ": 2,
	private String closedCode;// ": "OPEN",
	private String phrLastUpdateDate;// ": "2017-07-07T10:30:28.000+0800",
	private String startDate;// ": "",
	private String agentEmployeeNumber;// ": "00224584",//代理人工号
	private Integer openTaskQuantity;// ": 1,
	private String receivedFinishFlag;// ": "N",
	private String authorizationStatus;// ": "APPROVED",
	private Integer orgId;// ": 218,
	private String shipmentType;// ": "BLANKET",
	private String termsDescription;// ": "Make payment within 75 calendar days
	// after products arrived or services
	// accepted/货到或服务完成后75天付款",
	private String recvVendorAddr;// ": "深圳市坂田基地新天下工业区T-3-02R",
	private String erpPoNumber;// ": "17671219",
	private String termsName;// ": "Arrive75D",
	private String itemDesc;// ": "zh_CN=逆变器-SUN2000-36KTL-太阳能逆变器,en_US=Inverter,SUN2000-36KTL,PV
	// Inverter",
	private String invoiceFinishFlag;// ": "N",
	private Integer releaseNum;// ": 16,
	private Integer shipToOrganizationId;// ": 157,
	private String phrCreatedBy;// ": "61188",
	private String repOfficeCode;// ": "0000",
	private String recvConnecter;// ": "华为技术有限公司应付业务部(生产采购核算部）",
	private Integer quantityBilled;// ": 0,
	private String partNumber;// ": "/",
	private String phrCreationDate;// ": "2017-07-07T09:11:41.000+0800",
	private String sendVendorTelNum;// ": "(0086769)82062778",
	private String lastUpdateDate;// ": "2017-07-07T10:30:28.000+0800",
	private String interfaceSourceCode;// ": "SRM",
	private String precision;// ": 2,
	private String quantityAccepted;// ": 0,
	private Integer vendorId;// ": 2408920,
	private String rateDate;// ": "2017-06-21T00:00:00.000+0800",
	private String deleteFlag;// ": "N",
	private String sendVendorFax;// ": "",
	private String lastUpdatedBy;// ": 135782,
	private String quantityRejected;// ": 0,
	private String creationDate;// ": "2017-07-07T09:11:41.000+0800",
	private Integer codeCombinationId;// ": 127140,
	private String approvedDate;// ": "2017-07-07T10:30:28.000+0800",
	private Integer wipEntityId;// ": 46446196,
	private Integer poDistributionId;// ": 536511898,
	private Integer aslId;// ": 39002671,
	private String sendConnecter;// ": "陈婉凤 ",
	private String deptCode;// ": "000000",
	private Integer agentId;// ": 592354,
	private Integer shipToLocationId;// ": 1954622,
	private String repOfficeName;// ": "Non-Region",
	private String sendPaymentTerms;// ": "Make payment within 75 calendar days
	// after products arrived or services
	// accepted/货到或服务完成后75天付款",
	private String rate;// ": 1,
	private String orgCode;// ": "OU_HW",
	private String comments;// ": "Project No:RFQ20170414PP4957; RFQ
	// NO:3487241",
	private String billToLocationId;// ": 743,
	private String createdBy;// ": 61188,
	private String prhaInterfaceSourceCode;// ": "WIP",
	private String recvVendorTelNum;// ": "0755-28568672/13798325877",
	private String category;// ": "TBD.TBD.TBD",
	private Integer categoryId;// ": 685,
	private String primaryKey;// ": "520386301|1"

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public Integer getPoLineNum() {
		return poLineNum;
	}

	public void setPoLineNum(Integer poLineNum) {
		this.poLineNum = poLineNum;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public Integer getNeedQuantity() {
		return needQuantity;
	}

	public void setNeedQuantity(Integer needQuantity) {
		this.needQuantity = needQuantity;
	}

	public Integer getQuantityCancelled() {
		return quantityCancelled;
	}

	public void setQuantityCancelled(Integer quantityCancelled) {
		this.quantityCancelled = quantityCancelled;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Float getPriceOverride() {
		return priceOverride;
	}

	public void setPriceOverride(Float priceOverride) {
		this.priceOverride = priceOverride;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public Float getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Float taxRate) {
		this.taxRate = taxRate;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getShipToLocation() {
		return shipToLocation;
	}

	public void setShipToLocation(String shipToLocation) {
		this.shipToLocation = shipToLocation;
	}

	public String getTermsMode() {
		return termsMode;
	}

	public void setTermsMode(String termsMode) {
		this.termsMode = termsMode;
	}

	public String getIssuOffice() {
		return issuOffice;
	}

	public void setIssuOffice(String issuOffice) {
		this.issuOffice = issuOffice;
	}

	public Integer getVendorSiteId() {
		return vendorSiteId;
	}

	public void setVendorSiteId(Integer vendorSiteId) {
		this.vendorSiteId = vendorSiteId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getItemRevision() {
		return itemRevision;
	}

	public void setItemRevision(String itemRevision) {
		this.itemRevision = itemRevision;
	}

	public String getPromiseDate() {
		return promiseDate;
	}

	public void setPromiseDate(String promiseDate) {
		this.promiseDate = promiseDate;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public Float getQuantityReceived() {
		return quantityReceived;
	}

	public void setQuantityReceived(Float quantityReceived) {
		this.quantityReceived = quantityReceived;
	}

	public Float getDueQty() {
		return dueQty;
	}

	public void setDueQty(Float dueQty) {
		this.dueQty = dueQty;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPrNumber() {
		return prNumber;
	}

	public void setPrNumber(String prNumber) {
		this.prNumber = prNumber;
	}

	public String getBillToLocation() {
		return billToLocation;
	}

	public void setBillToLocation(String billToLocation) {
		this.billToLocation = billToLocation;
	}

	public String getTaskNum() {
		return taskNum;
	}

	public void setTaskNum(String taskNum) {
		this.taskNum = taskNum;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getShipmentNum() {
		return shipmentNum;
	}

	public void setShipmentNum(String shipmentNum) {
		this.shipmentNum = shipmentNum;
	}

	public Float getUnSendQuantity() {
		return unSendQuantity;
	}

	public void setUnSendQuantity(Float unSendQuantity) {
		this.unSendQuantity = unSendQuantity;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
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

	public String getShipmentStatus() {
		return shipmentStatus;
	}

	public void setShipmentStatus(String shipmentStatus) {
		this.shipmentStatus = shipmentStatus;
	}

	public String getActivityID() {
		return activityID;
	}

	public void setActivityID(String activityID) {
		this.activityID = activityID;
	}

	public Float getRevisionNum() {
		return revisionNum;
	}

	public void setRevisionNum(Float revisionNum) {
		this.revisionNum = revisionNum;
	}

	public Integer getPoLineId() {
		return poLineId;
	}

	public void setPoLineId(Integer poLineId) {
		this.poLineId = poLineId;
	}

	public String getObjectChangeContext() {
		return objectChangeContext;
	}

	public void setObjectChangeContext(String objectChangeContext) {
		this.objectChangeContext = objectChangeContext;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public Integer getPhrLastUpdatedBy() {
		return phrLastUpdatedBy;
	}

	public void setPhrLastUpdatedBy(Integer phrLastUpdatedBy) {
		this.phrLastUpdatedBy = phrLastUpdatedBy;
	}

	public Integer getRequisitionHeaderId() {
		return requisitionHeaderId;
	}

	public void setRequisitionHeaderId(Integer requisitionHeaderId) {
		this.requisitionHeaderId = requisitionHeaderId;
	}

	public String getManufactureSiteInfo() {
		return manufactureSiteInfo;
	}

	public void setManufactureSiteInfo(String manufactureSiteInfo) {
		this.manufactureSiteInfo = manufactureSiteInfo;
	}

	public String getRecvVendorFax() {
		return recvVendorFax;
	}

	public void setRecvVendorFax(String recvVendorFax) {
		this.recvVendorFax = recvVendorFax;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getConsignedFlag() {
		return consignedFlag;
	}

	public void setConsignedFlag(String consignedFlag) {
		this.consignedFlag = consignedFlag;
	}

	public Integer getTermsId() {
		return termsId;
	}

	public void setTermsId(Integer termsId) {
		this.termsId = termsId;
	}

	public String getSendVendorAddr() {
		return sendVendorAddr;
	}

	public void setSendVendorAddr(String sendVendorAddr) {
		this.sendVendorAddr = sendVendorAddr;
	}

	public String getFobLookupCode() {
		return fobLookupCode;
	}

	public void setFobLookupCode(String fobLookupCode) {
		this.fobLookupCode = fobLookupCode;
	}

	public String getUnitMeasLookupCode() {
		return unitMeasLookupCode;
	}

	public void setUnitMeasLookupCode(String unitMeasLookupCode) {
		this.unitMeasLookupCode = unitMeasLookupCode;
	}

	public Integer getTaskQuantity() {
		return taskQuantity;
	}

	public void setTaskQuantity(Integer taskQuantity) {
		this.taskQuantity = taskQuantity;
	}

	public String getShipViaLookupCode() {
		return shipViaLookupCode;
	}

	public void setShipViaLookupCode(String shipViaLookupCode) {
		this.shipViaLookupCode = shipViaLookupCode;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Integer getReceivingRoutingId() {
		return receivingRoutingId;
	}

	public void setReceivingRoutingId(Integer receivingRoutingId) {
		this.receivingRoutingId = receivingRoutingId;
	}

	public String getClosedCode() {
		return closedCode;
	}

	public void setClosedCode(String closedCode) {
		this.closedCode = closedCode;
	}

	public String getPhrLastUpdateDate() {
		return phrLastUpdateDate;
	}

	public void setPhrLastUpdateDate(String phrLastUpdateDate) {
		this.phrLastUpdateDate = phrLastUpdateDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getAgentEmployeeNumber() {
		return agentEmployeeNumber;
	}

	public void setAgentEmployeeNumber(String agentEmployeeNumber) {
		this.agentEmployeeNumber = agentEmployeeNumber;
	}

	public Integer getOpenTaskQuantity() {
		return openTaskQuantity;
	}

	public void setOpenTaskQuantity(Integer openTaskQuantity) {
		this.openTaskQuantity = openTaskQuantity;
	}

	public String getReceivedFinishFlag() {
		return receivedFinishFlag;
	}

	public void setReceivedFinishFlag(String receivedFinishFlag) {
		this.receivedFinishFlag = receivedFinishFlag;
	}

	public String getAuthorizationStatus() {
		return authorizationStatus;
	}

	public void setAuthorizationStatus(String authorizationStatus) {
		this.authorizationStatus = authorizationStatus;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(String shipmentType) {
		this.shipmentType = shipmentType;
	}

	public String getTermsDescription() {
		return termsDescription;
	}

	public void setTermsDescription(String termsDescription) {
		this.termsDescription = termsDescription;
	}

	public String getBusinessMode() {
		return businessMode;
	}

	public void setBusinessMode(String businessMode) {
		this.businessMode = businessMode;
	}

	public String getRecvVendorAddr() {
		return recvVendorAddr;
	}

	public void setRecvVendorAddr(String recvVendorAddr) {
		this.recvVendorAddr = recvVendorAddr;
	}

	public String getNeedByDate() {
		return needByDate;
	}

	public void setNeedByDate(String needByDate) {
		this.needByDate = needByDate;
	}

	public String getTermsName() {
		return termsName;
	}

	public void setTermsName(String termsName) {
		this.termsName = termsName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getInvoiceFinishFlag() {
		return invoiceFinishFlag;
	}

	public void setInvoiceFinishFlag(String invoiceFinishFlag) {
		this.invoiceFinishFlag = invoiceFinishFlag;
	}

	public Integer getReleaseNum() {
		return releaseNum;
	}

	public void setReleaseNum(Integer releaseNum) {
		this.releaseNum = releaseNum;
	}

	public Integer getShipToOrganizationId() {
		return shipToOrganizationId;
	}

	public void setShipToOrganizationId(Integer shipToOrganizationId) {
		this.shipToOrganizationId = shipToOrganizationId;
	}

	public String getPhrCreatedBy() {
		return phrCreatedBy;
	}

	public void setPhrCreatedBy(String phrCreatedBy) {
		this.phrCreatedBy = phrCreatedBy;
	}

	public String getRepOfficeCode() {
		return repOfficeCode;
	}

	public void setRepOfficeCode(String repOfficeCode) {
		this.repOfficeCode = repOfficeCode;
	}

	public String getRecvConnecter() {
		return recvConnecter;
	}

	public void setRecvConnecter(String recvConnecter) {
		this.recvConnecter = recvConnecter;
	}

	public Integer getQuantityBilled() {
		return quantityBilled;
	}

	public void setQuantityBilled(Integer quantityBilled) {
		this.quantityBilled = quantityBilled;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getPhrCreationDate() {
		return phrCreationDate;
	}

	public void setPhrCreationDate(String phrCreationDate) {
		this.phrCreationDate = phrCreationDate;
	}

	public String getSendVendorTelNum() {
		return sendVendorTelNum;
	}

	public void setSendVendorTelNum(String sendVendorTelNum) {
		this.sendVendorTelNum = sendVendorTelNum;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getInterfaceSourceCode() {
		return interfaceSourceCode;
	}

	public void setInterfaceSourceCode(String interfaceSourceCode) {
		this.interfaceSourceCode = interfaceSourceCode;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getQuantityAccepted() {
		return quantityAccepted;
	}

	public void setQuantityAccepted(String quantityAccepted) {
		this.quantityAccepted = quantityAccepted;
	}

	public Integer getVendorId() {
		return vendorId;
	}

	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}

	public String getRateDate() {
		return rateDate;
	}

	public void setRateDate(String rateDate) {
		this.rateDate = rateDate;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getSendVendorFax() {
		return sendVendorFax;
	}

	public void setSendVendorFax(String sendVendorFax) {
		this.sendVendorFax = sendVendorFax;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getQuantityRejected() {
		return quantityRejected;
	}

	public void setQuantityRejected(String quantityRejected) {
		this.quantityRejected = quantityRejected;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getCodeCombinationId() {
		return codeCombinationId;
	}

	public void setCodeCombinationId(Integer codeCombinationId) {
		this.codeCombinationId = codeCombinationId;
	}

	public String getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(String approvedDate) {
		this.approvedDate = approvedDate;
	}

	public Integer getWipEntityId() {
		return wipEntityId;
	}

	public void setWipEntityId(Integer wipEntityId) {
		this.wipEntityId = wipEntityId;
	}

	public Integer getPoDistributionId() {
		return poDistributionId;
	}

	public void setPoDistributionId(Integer poDistributionId) {
		this.poDistributionId = poDistributionId;
	}

	public Integer getAslId() {
		return aslId;
	}

	public void setAslId(Integer aslId) {
		this.aslId = aslId;
	}

	public String getSendConnecter() {
		return sendConnecter;
	}

	public void setSendConnecter(String sendConnecter) {
		this.sendConnecter = sendConnecter;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public Integer getAgentId() {
		return agentId;
	}

	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}

	public Integer getShipToLocationId() {
		return shipToLocationId;
	}

	public void setShipToLocationId(Integer shipToLocationId) {
		this.shipToLocationId = shipToLocationId;
	}

	public String getTypeLookupCode() {
		return typeLookupCode;
	}

	public void setTypeLookupCode(String typeLookupCode) {
		this.typeLookupCode = typeLookupCode;
	}

	public String getRepOfficeName() {
		return repOfficeName;
	}

	public void setRepOfficeName(String repOfficeName) {
		this.repOfficeName = repOfficeName;
	}

	public String getSendPaymentTerms() {
		return sendPaymentTerms;
	}

	public void setSendPaymentTerms(String sendPaymentTerms) {
		this.sendPaymentTerms = sendPaymentTerms;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getBillToLocationId() {
		return billToLocationId;
	}

	public void setBillToLocationId(String billToLocationId) {
		this.billToLocationId = billToLocationId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getPoSubType() {
		return poSubType;
	}

	public void setPoSubType(String poSubType) {
		this.poSubType = poSubType;
	}

	public String getPrhaInterfaceSourceCode() {
		return prhaInterfaceSourceCode;
	}

	public void setPrhaInterfaceSourceCode(String prhaInterfaceSourceCode) {
		this.prhaInterfaceSourceCode = prhaInterfaceSourceCode;
	}

	public String getRecvVendorTelNum() {
		return recvVendorTelNum;
	}

	public void setRecvVendorTelNum(String recvVendorTelNum) {
		this.recvVendorTelNum = recvVendorTelNum;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getErpPoNumber() {
		return erpPoNumber;
	}

	public void setErpPoNumber(String erpPoNumber) {
		this.erpPoNumber = erpPoNumber;
	}

	public Integer getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(Integer instanceId) {
		this.instanceId = instanceId;
	}

	public Integer getLineLocationId() {
		return lineLocationId;
	}

	public void setLineLocationId(Integer lineLocationId) {
		this.lineLocationId = lineLocationId;
	}

	public String getPoStatus() {
		return poStatus;
	}

	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
	}

	public String getColTaskOrPoStatus() {
		return colTaskOrPoStatus;
	}

	public void setColTaskOrPoStatus(String colTaskOrPoStatus) {
		this.colTaskOrPoStatus = colTaskOrPoStatus;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

}
