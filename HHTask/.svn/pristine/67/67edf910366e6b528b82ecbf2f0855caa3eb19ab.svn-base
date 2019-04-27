package com.task.entity.sop;

import java.io.File;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.task.entity.Goods;
import com.task.entity.OrderManager;
import com.task.util.FieldMeta;

/**
 * 物料需求明细表(ta_sop_w_ManualOrderPlanDetail)
 * 
 * @author 王晓飞
 * 
 */
public class ManualOrderPlanDetail implements java.io.Serializable{
	private static final long serialVersionUID =1L;

	private Integer id;
	@FieldMeta(name = "件号")
	private String markId;// 件号
	@FieldMeta(name = "零件名称")
	private String proName;// 零件名称
	@FieldMeta(name = "规格")
	private String specification;// 规格
	@FieldMeta(name = "版本")
	private String banben;// 版本
	@FieldMeta(name = "版次")
	private Integer banci;// 版次
	@FieldMeta(name = "单位")
	private String unit;// 单位
	@FieldMeta(name = "供料属性")
	private String kgliao;// //供料属性（外购件使用：是,否，null代表否）
	@FieldMeta(name = "图号")
	private String tuhao;// 图号
	@FieldMeta(name = "项目编号")
	private String proNumber;// 项目编号
	@FieldMeta(name = "物料类别")
	private String wgType;// 物料类别
	@FieldMeta(name = "采购数量")
	private Float cgnumber;// 采购数量
	private Float outcgNumber;// 已采购数量
	private Integer procardId;// 流水单Id
	private String addTime;// 添加时间
	private String updateTime;//修改时间
	@FieldMeta(name = "申请人")
	private String addUsers;// 添加人
	@FieldMeta(name = "申请人工号")
	private String addUsersCode;// 添加人工号
	@FieldMeta(name = "添加方式")
	private String type;// （手动紧急）>正式订单>试制订单>预测订单>安全库存>手动添加（正常） 优先级由左到右依次递减;
	private Float rukuNum;// 到货数量
	private Integer productId;// 产品Id
	private String quotedPriceName;// 项目名称
	private Integer quotedPriceId;// 项目id
	private String demanddept;//需求部门
	private String months;//月份(yyyy-MM月)
	private Float hsprice;//含税价(计算使用，前台不显示)
	private Float bhsprice;//不含税价(计算使用，前台不显示)
	private Float totalMoney;//总额(计算使用，前台不显示)
	private Float yearSumNum;//年累计请购量
	private Float kcCount;//库存量(前台显示，实时库存)
	private String yongtu;//用途
	
	private String needFinalDate;// 送货日期

	@FieldMeta(name = "是否紧急")
	private String isurgent;// 是否紧急;(YES/NO)手动添加使用
	private Integer epId;// 审批流程Id
	private String epStatus;// 审批状态
	private String remarks;// 备注
	private Procard procard;// 页面传值
	private OrderManager order;// 页面传值
	private Integer orderId;// 订单Id;
	private String orderNumber;// 订单号（内）
	private String orderOutNumber;// 订单号(外)
	private String rootMarkId;// 总成件号
	private String rootSelfCard;// 总成批次
	private String ywMarkId;// 业务件号
	@FieldMeta(name = "状态")
	private String status;// 状态;
	@FieldMeta(name = "申请类别")
	private String category;// 申请类别 （外购、辅料、研发、售后）
	private Float estimatePrice;// 预估价格

	private ManualOrderPlan manualPlan;// 多对一 物料需求表
	private ManualOrderPlanTotal planTotal;// 多对一 物料申请表
	private Float ylNumber;//
	private Float cancalNum;// 取消数量

	private String pickingStatus;// 领料状态     已领完、未领料、未领完
	private Float pickingNumber;// 已领数量

	private List<Goods> goodsList;//库存信息、、-----
	private Float lingquNum;//领取数量  //--------------
	private String fileUrl;//附件
	private String fileName;//文件名称
	
	private File files;//上传文件，页面...
	private String filesFileName;
	private String filesContentType;
	private String shengChengMopIdTime;//生成mopId时间
	private Float oldNumber;//原需求数量
	public ManualOrderPlanDetail() {

	}

	public ManualOrderPlanDetail(String markId, String proName,
			String specification, String banben, String unit, String kgliao,
			String tuhao, String wgType, Float cgnumber, Integer procardId,
			String addTime, String addUsers, String addUsersCode, String type,
			Integer epId, String epStatus, ManualOrderPlan manualPlan) {
		super();
		this.markId = markId;
		this.proName = proName;
		this.specification = specification;
		this.banben = banben;
		this.unit = unit;
		this.kgliao = kgliao;
		this.tuhao = tuhao;
		this.wgType = wgType;
		this.cgnumber = cgnumber;
		this.procardId = procardId;
		this.addTime = addTime;
		this.addUsers = addUsers;
		this.addUsersCode = addUsersCode;
		this.type = type;
		this.epId = epId;
		this.epStatus = epStatus;
		this.manualPlan = manualPlan;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShengChengMopIdTime() {
		return shengChengMopIdTime;
	}

	public void setShengChengMopIdTime(String shengChengMopIdTime) {
		this.shengChengMopIdTime = shengChengMopIdTime;
	}

	public Float getCgnumber() {
		return cgnumber;
	}

	public void setCgnumber(Float cgnumber) {
		this.cgnumber = cgnumber;
	}

	public Float getOutcgNumber() {
		return outcgNumber;
	}

	public void setOutcgNumber(Float outcgNumber) {
		this.outcgNumber = outcgNumber;
	}

	public Integer getProcardId() {
		return procardId;
	}

	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getRukuNum() {
		return rukuNum;
	}

	public void setRukuNum(Float rukuNum) {
		this.rukuNum = rukuNum;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	@JSONField(serialize = false)
	public ManualOrderPlan getManualPlan() {
		return manualPlan;
	}

	public void setManualPlan(ManualOrderPlan manualPlan) {
		this.manualPlan = manualPlan;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getAddUsers() {
		return addUsers;
	}

	public void setAddUsers(String addUsers) {
		this.addUsers = addUsers;
	}

	public String getAddUsersCode() {
		return addUsersCode;
	}

	public void setAddUsersCode(String addUsersCode) {
		this.addUsersCode = addUsersCode;
	}

	public String getIsurgent() {
		return isurgent;
	}

	public void setIsurgent(String isurgent) {
		this.isurgent = isurgent;
	}

	public Integer getEpId() {
		return epId;
	}

	public void setEpId(Integer epId) {
		this.epId = epId;
	}

	public String getEpStatus() {
		return epStatus;
	}

	public void setEpStatus(String epStatus) {
		this.epStatus = epStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMarkId() {
		return markId;
	}

	public void setMarkId(String markId) {
		this.markId = markId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getBanben() {
		return banben;
	}

	public void setBanben(String banben) {
		this.banben = banben;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getKgliao() {
		return kgliao;
	}

	public void setKgliao(String kgliao) {
		this.kgliao = kgliao;
	}

	public String getTuhao() {
		return tuhao;
	}

	public void setTuhao(String tuhao) {
		this.tuhao = tuhao;
	}

	public String getWgType() {
		return wgType;
	}

	public void setWgType(String wgType) {
		this.wgType = wgType;
	}

	public Procard getProcard() {
		return procard;
	}

	public void setProcard(Procard procard) {
		this.procard = procard;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public OrderManager getOrder() {
		return order;
	}

	public void setOrder(OrderManager order) {
		this.order = order;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNeedFinalDate() {
		return needFinalDate;
	}

	public void setNeedFinalDate(String needFinalDate) {
		this.needFinalDate = needFinalDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderOutNumber() {
		return orderOutNumber;
	}

	public void setOrderOutNumber(String orderOutNumber) {
		this.orderOutNumber = orderOutNumber;
	}

	public String getRootMarkId() {
		return rootMarkId;
	}

	public void setRootMarkId(String rootMarkId) {
		this.rootMarkId = rootMarkId;
	}

	public String getRootSelfCard() {
		return rootSelfCard;
	}

	public void setRootSelfCard(String rootSelfCard) {
		this.rootSelfCard = rootSelfCard;
	}

	public String getYwMarkId() {
		return ywMarkId;
	}

	public void setYwMarkId(String ywMarkId) {
		this.ywMarkId = ywMarkId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getQuotedPriceId() {
		return quotedPriceId;
	}

	public void setQuotedPriceId(Integer quotedPriceId) {
		this.quotedPriceId = quotedPriceId;
	}

	public String getQuotedPriceName() {
		return quotedPriceName;
	}

	public void setQuotedPriceName(String quotedPriceName) {
		this.quotedPriceName = quotedPriceName;
	}

	public ManualOrderPlanTotal getPlanTotal() {
		return planTotal;
	}

	public void setPlanTotal(ManualOrderPlanTotal planTotal) {
		this.planTotal = planTotal;
	}

	public Float getEstimatePrice() {
		return estimatePrice;
	}

	public void setEstimatePrice(Float estimatePrice) {
		this.estimatePrice = estimatePrice;
	}

	public String getProNumber() {
		return proNumber;
	}

	public void setProNumber(String proNumber) {
		this.proNumber = proNumber;
	}

	public Float getYlNumber() {
		return ylNumber;
	}

	public void setYlNumber(Float ylNumber) {
		this.ylNumber = ylNumber;
	}

	public Integer getBanci() {
		return banci;
	}

	public void setBanci(Integer banci) {
		this.banci = banci;
	}

	public Float getCancalNum() {
		return cancalNum;
	}

	public void setCancalNum(Float cancalNum) {
		this.cancalNum = cancalNum;
	}

	public String getPickingStatus() {
		return pickingStatus;
	}

	public void setPickingStatus(String pickingStatus) {
		this.pickingStatus = pickingStatus;
	}

	public Float getPickingNumber() {
		return pickingNumber;
	}

	public void setPickingNumber(Float pickingNumber) {
		this.pickingNumber = pickingNumber;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public Float getLingquNum() {
		return lingquNum;
	}

	public void setLingquNum(Float lingquNum) {
		this.lingquNum = lingquNum;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public File getFiles() {
		return files;
	}

	public void setFiles(File files) {
		this.files = files;
	}

	public String getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(String filesContentType) {
		this.filesContentType = filesContentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDemanddept() {
		return demanddept;
	}

	public void setDemanddept(String demanddept) {
		this.demanddept = demanddept;
	}

	public Float getOldNumber() {
		return oldNumber;
	}

	public void setOldNumber(Float oldNumber) {
		this.oldNumber = oldNumber;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public Float getHsprice() {
		return hsprice;
	}

	public void setHsprice(Float hsprice) {
		this.hsprice = hsprice;
	}

	public Float getBhsprice() {
		return bhsprice;
	}

	public void setBhsprice(Float bhsprice) {
		this.bhsprice = bhsprice;
	}

	public Float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Float getYearSumNum() {
		return yearSumNum;
	}

	public void setYearSumNum(Float yearSumNum) {
		this.yearSumNum = yearSumNum;
	}

	public Float getKcCount() {
		return kcCount;
	}

	public void setKcCount(Float kcCount) {
		this.kcCount = kcCount;
	}

	public String getYongtu() {
		return yongtu;
	}

	public void setYongtu(String yongtu) {
		this.yongtu = yongtu;
	}


}
