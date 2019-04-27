package com.task.entity.sop;

import com.task.util.FieldMeta;

/**
 * 外委申请明细表 ta_sop_w_ProcessInforWWApplyDetail
 * @author Administrator
 *
 */
public class ProcessInforWWApplyDetail {
	
	private Integer id;//
	private Integer procardId;//零件Id
	@FieldMeta(name="件号")
	private String markId;//件号
	@FieldMeta(name="零件名称")
	private String proName;//零件名称
	@FieldMeta(name="版本号")
	private String banbenNumber;//版本号
	@FieldMeta(name="版次")
	private Integer banci;//版次
	@FieldMeta(name="单位")
	private String unit;//单位
	@FieldMeta(name="批次")
	private String selfCard;//批次
	@FieldMeta(name="原工序号")
	private String processNOs;//工序号
	@FieldMeta(name="原工序名称")
	private String processNames;//名称
	@FieldMeta(name="申请数量")
	private Float applyCount;//申请数量
	private Integer userId;
	private String userName;//选择人名字
	private String userCode;//选择人工号
	private String addTime;//选择时间
	private String wwType;//工序外委，包工包料
	private String wwMarkId;//包工包料关联外委件号
	private Integer priceId;//合同Id
	private Integer gysId;//供应商id
	private String gysName;//供应商名称
	private String relatDown;//工序外委是否关联下层（标记在被外委的当层）(是，否)
	private String dataStatus;//数据状态
	private ProcessInforWWApply processInforWWApply;//申请表头
	@FieldMeta(name="业务件号")
	private String ywMarkId;//总成业务件号（页面传值使用）
	private String rootMarkId;//总成件号（页面传值使用）
	private String rootSelfCard;//总成批次（页面传值使用）
	private String applyStatus;//审批状态（页面传值使用）
	private String tzupdate;//图纸是否更新(是，否，先确认供应商)
	private Float price;// 不含税单价（页面传值使用）
	private Float hsprice;//含税单价（页面传值使用）
	@FieldMeta(name="新工序号 ")
	private String newprocessNOs;//新工序号 
	@FieldMeta(name="新工序名称 ")
	private String newprocessNames;//新工序名称 
	private String epstatus;//审批状态
	private Integer epId;//审批动态
	private String guanlianMarkId;//关联件号
	private String processStatus;//流转状态(预选未审批,合同待确认,外委待下单,订单外委采购，生产中,删除)
	private String oldprocessStatus;//
	private String hadChange;//与同供应商上次的订单相比是否有过设变
	
	private String deleteStatus;//删除审批状态
	private Integer deleteEpId;//删除审批节点Id
	
	private String bgMarkIds;//变更零件（页面展示）
	//applyDtailId;//外委申请明细id对应表ProcessInforWWProcard
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
	public String getSelfCard() {
		return selfCard;
	}
	public void setSelfCard(String selfCard) {
		this.selfCard = selfCard;
	}
	public Float getApplyCount() {
		return applyCount;
	}
	public void setApplyCount(Float applyCount) {
		this.applyCount = applyCount;
	}
	public ProcessInforWWApply getProcessInforWWApply() {
		return processInforWWApply;
	}
	public void setProcessInforWWApply(ProcessInforWWApply processInforWWApply) {
		this.processInforWWApply = processInforWWApply;
	}
	public String getProcessNOs() {
		return processNOs;
	}
	public void setProcessNOs(String processNOs) {
		this.processNOs = processNOs;
	}
	public String getProcessNames() {
		return processNames;
	}
	public void setProcessNames(String processNames) {
		this.processNames = processNames;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getWwType() {
		return wwType;
	}
	public void setWwType(String wwType) {
		this.wwType = wwType;
	}
	public String getWwMarkId() {
		return wwMarkId;
	}
	public void setWwMarkId(String wwMarkId) {
		this.wwMarkId = wwMarkId;
	}
	public Integer getGysId() {
		return gysId;
	}
	public void setGysId(Integer gysId) {
		this.gysId = gysId;
	}
	public String getGysName() {
		return gysName;
	}
	public void setGysName(String gysName) {
		this.gysName = gysName;
	}
	public Integer getProcardId() {
		return procardId;
	}
	public void setProcardId(Integer procardId) {
		this.procardId = procardId;
	}
	public String getBanbenNumber() {
		return banbenNumber;
	}
	public void setBanbenNumber(String banbenNumber) {
		this.banbenNumber = banbenNumber;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getPriceId() {
		return priceId;
	}
	public void setPriceId(Integer priceId) {
		this.priceId = priceId;
	}
	public Integer getBanci() {
		return banci;
	}
	public void setBanci(Integer banci) {
		this.banci = banci;
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
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	public String getRelatDown() {
		return relatDown;
	}
	public void setRelatDown(String relatDown) {
		this.relatDown = relatDown;
	}
	public String getTzupdate() {
		return tzupdate;
	}
	public void setTzupdate(String tzupdate) {
		this.tzupdate = tzupdate;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Float getHsprice() {
		return hsprice;
	}
	public void setHsprice(Float hsprice) {
		this.hsprice = hsprice;
	}
	public String getDataStatus() {
		return dataStatus;
	}
	public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
	}
	public String getNewprocessNOs() {
		return newprocessNOs;
	}
	public void setNewprocessNOs(String newprocessNOs) {
		this.newprocessNOs = newprocessNOs;
	}
	public String getNewprocessNames() {
		return newprocessNames;
	}
	public void setNewprocessNames(String newprocessNames) {
		this.newprocessNames = newprocessNames;
	}
	public String getEpstatus() {
		return epstatus;
	}
	public void setEpstatus(String epstatus) {
		this.epstatus = epstatus;
	}
	public Integer getEpId() {
		return epId;
	}
	public void setEpId(Integer epId) {
		this.epId = epId;
	}
	public String getGuanlianMarkId() {
		return guanlianMarkId;
	}
	public void setGuanlianMarkId(String guanlianMarkId) {
		this.guanlianMarkId = guanlianMarkId;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public String getHadChange() {
		return hadChange;
	}
	public void setHadChange(String hadChange) {
		this.hadChange = hadChange;
	}
	public String getDeleteStatus() {
		return deleteStatus;
	}
	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	public Integer getDeleteEpId() {
		return deleteEpId;
	}
	public void setDeleteEpId(Integer deleteEpId) {
		this.deleteEpId = deleteEpId;
	}
	public String getOldprocessStatus() {
		return oldprocessStatus;
	}
	public void setOldprocessStatus(String oldprocessStatus) {
		this.oldprocessStatus = oldprocessStatus;
	}
	public String getBgMarkIds() {
		return bgMarkIds;
	}
	public void setBgMarkIds(String bgMarkIds) {
		this.bgMarkIds = bgMarkIds;
	}
	
	
	
	
}